package com.sunll.web.controller.center;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.StringUtil;
import com.sunll.center.entity.Bill;
import com.sunll.center.entity.BillExample;
import com.sunll.center.entity.CallCdr;
import com.sunll.center.entity.CallCdrExample;
import com.sunll.center.mapper.BillMapper;
import com.sunll.center.mapper.CallCdrMapper;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;
import com.sunll.common.api.CenterApiReturnCode;
import com.sunll.common.util.CenterResult;
import com.sunll.common.util.HttpTookit;
import com.sunll.common.util.JsonUtils;
import com.sunll.systemset.api.login.LoginService;
import com.sunll.web.vo.CostStatistics;
import com.sunll.web.vo.RecordStatistics;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
@RequestMapping("/centerApi")
public class CenterApiAction {
    private Logger log = Logger.getLogger(this.getClass());

    @Reference(check = false, timeout = 100000)
    LoginService loginService;

    @ResponseBody
    @RequestMapping(value = "/bridgeDialCrm")
    public String bridgeDialCrm(@RequestBody Map<String, Object> acceptMap) {
        log.info("--- For Mobile : bridgeDial() start ---");

        String uname = "ywj";//用户名
        String token = "jtd2016";//用户密码

        //String showcaller = "83035660";//显示号码（接入号）
        //String from = "13261702440";//主叫
        //String to = "13261702440";//被叫
        String showcaller = (String) acceptMap.get("showcaller");
        String from = (String) acceptMap.get("from");
        String to = (String) acceptMap.get("to");

        Map paramMap = new HashMap();
        paramMap.put("uname", uname);
        paramMap.put("token", token);
        paramMap.put("showcaller", showcaller);
        paramMap.put("from", from);
        paramMap.put("to", to);
        String url = "http://freeswitch.dial.weiyingjia.org/msgapi/bothway";
        String result ="";
        try {
            result = HttpTookit.doPostParam(url, paramMap, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(result);
        log.info(result);
        log.info("--- For Mobile : bridgeDial() end ---");
        return result;
    }


    private static float add(float v1,float v2){
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.add(b2).floatValue();
    }

    public static void main(String[] args){
/*        float f1 =0f;
        float f2 =1.4f;

        float f3 = add(f1,f2);
        f1=add(f1,f2);
        System.out.println("f1: "+ f1);
        System.out.println("f3: "+f3);*/
        new CenterApiAction().percentageForInt(3,9);


    }

    @Autowired
    BillMapper billMapper;

    @ResponseBody
    @RequestMapping(value = "/billStatistics",method = RequestMethod.POST)
    public String billStatistics(@RequestBody Map<String, Object> acceptMap) {
        log.info("--- For CRM : billStatistics() start ---");
        BillExample billE = new BillExample();
        BillExample.Criteria criteria = billE.createCriteria();

        //String userId = request.getParameter("userId");
        //Integer userId = Integer.parseInt((String) acceptMap.get("userId"));
        String userId = (String) acceptMap.get("userId");

        log.info("--- getData() userId ---" + userId);
        //String accountMobile = "13261702440";
        String accountMobile = loginService.getAccountMobileByUserId(userId);
        log.info("--- getData() accountMobile ---" + accountMobile);

        //String createTimeFrom = request.getParameter("startTime");
        //String createTimeTo = request.getParameter("endTime");
        String createTimeFrom = (String) acceptMap.get("startTime");
        String createTimeTo = (String) acceptMap.get("endTime");


        if(StringUtil.isNotEmpty(createTimeFrom)){
            System.out.println(createTimeFrom);
            log.info(createTimeFrom);
            //yy-MM-dd HH:mm:ss
            createTimeFrom = createTimeFrom +" 00:00:00";
            Date from = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            try {
                from = sdf.parse(createTimeFrom);
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
            }

            criteria.andStartStampGreaterThanOrEqualTo(from);
        }

        if(StringUtil.isNotEmpty(createTimeTo)){
            //yy-MM-dd HH:mm:ss
            createTimeTo = createTimeTo +" 23:59:59";
            Date to = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            try {
                to = sdf.parse(createTimeTo);
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
            }
            criteria.andStartStampLessThanOrEqualTo(to);
        }

        if(StringUtil.isNotEmpty(accountMobile)&& NumberUtils.isNumber(accountMobile)){
            criteria.multiColumnOrClause(accountMobile);
            //billE.setOrderByClause("");
        }

        String userName = "";
        float seatCostTotal = 0f;
        int callTotalTime = 0;
        float callCostTotal = 0f;
        float total = 0f;


        List<Bill> bills = billMapper.selectByExample(billE);
        for(int i=0;i<bills.size();i++){
            callTotalTime = callTotalTime + bills.get(i).getBillsec().intValue();
            callCostTotal = this.add(callCostTotal,bills.get(i).getFee().floatValue());
        }

        total = this.add(total,seatCostTotal);
        total = this.add(total,callCostTotal);

        CostStatistics cost = new CostStatistics();
        cost.setUserName(userName);
        cost.setSeatCostTotal(seatCostTotal);
        cost.setCallTotalTime(callTotalTime);
        cost.setCallCostTotal(callCostTotal);
        cost.setTotal(total);

        //System.out.print(result);
        //log.info(result);
        log.info("--- For CRM : billStatistics() end ---");
        return JSON.toJSONString(cost);
    }

    private String percentageForInt(int diliverNum,int queryMailNum){
        //int diliverNum=3;//举例子的变量
        //int queryMailNum=9;//举例子的变量
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float)diliverNum/(float)queryMailNum*100);
        System.out.println("diliverNum和queryMailNum的百分比为:" + result + "%");
        return result + "%";
    }

    @Autowired
    CallCdrMapper callCdrMapper;

    @ResponseBody
    @RequestMapping(value = "/recordStatistics",method = RequestMethod.POST)
    public String recordStatistics(@RequestBody Map<String, Object> acceptMap) {
        log.info("--- For CRM : recordStatistics() start ---");
        CallCdrExample recordE = new CallCdrExample();
        CallCdrExample.Criteria criteria = recordE.createCriteria();

        //String userId = request.getParameter("userId");
        //Integer userId = Integer.parseInt((String) acceptMap.get("userId"));
        String userId = (String) acceptMap.get("userId");

        log.info("--- getData() userId ---" + userId);
        //String accountMobile = "13261702440";
        String accountMobile = loginService.getAccountMobileByUserId(userId);
        log.info("--- getData() accountMobile ---" + accountMobile);

        //String createTimeFrom = request.getParameter("startTime");
        //String createTimeTo = request.getParameter("endTime");
        String createTimeFrom = (String) acceptMap.get("startTime");
        String createTimeTo = (String) acceptMap.get("endTime");



        if(StringUtil.isNotEmpty(createTimeFrom)){
            System.out.println(createTimeFrom);
            log.info(createTimeFrom);
            //yy-MM-dd HH:mm:ss
            createTimeFrom = createTimeFrom +" 00:00:00";
            Date from = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            try {
                from = sdf.parse(createTimeFrom);
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
            }
            //milisecond to second
            long fromSecond = from.getTime()/1000;
            String fromCond = Long.toString(fromSecond);

            criteria.andStartStampGreaterThanOrEqualTo(new Integer(fromCond));
        }

        if(StringUtil.isNotEmpty(createTimeTo)){
            //yy-MM-dd HH:mm:ss
            createTimeTo = createTimeTo +" 23:59:59";
            Date to = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            try {
                to = sdf.parse(createTimeTo);
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
            }

            //milisecond to second
            long toSecond = to.getTime()/1000;
            String toCond = Long.toString(toSecond);
            criteria.andStartStampLessThanOrEqualTo(new Integer(toCond));
        }

        if(StringUtil.isNotEmpty(accountMobile)){
            criteria.multiColumnOrClause(accountMobile);
        }

        criteria.andDirectionEqualTo("outbound");
        List<CallCdr> callCdrs = callCdrMapper.selectByExample(recordE);

        /*部门/人员 外呼总数 外呼成功数 外呼失败数 外呼成功率  转化率 通话总时长*/
        String userName = "";
        int outBoundTotal = 0;
        int outBoundSuccess = 0;
        int outBoundFailure = 0;
        String outBoundPercentage = "";
        int callTimeTotal = 0;

        int tempBillsec = 0;

        for(int i=0;i<callCdrs.size();i++){
            tempBillsec = callCdrs.get(i).getBillsec().intValue();
            callTimeTotal = callTimeTotal + tempBillsec;
            if(tempBillsec==0){
                outBoundFailure++;
            }else{
                outBoundSuccess++;
            }
            outBoundTotal++;
        }

        outBoundPercentage = percentageForInt(outBoundSuccess,outBoundTotal);

        RecordStatistics s = new RecordStatistics();
        s.setUserName(userName);
        s.setCallTimeTotal(callTimeTotal);
        s.setOutBoundFailure(outBoundFailure);
        s.setOutBoundPercentage(outBoundPercentage);
        s.setOutBoundTotal(outBoundTotal);
        s.setOutBoundSuccess(outBoundSuccess);
        //System.out.print(result);
        //log.info(result);
        log.info("--- For CRM : recordStatistics() end ---");
        return JSON.toJSONString(s);
    }


    /**
     * 应用APP登录接口
     * @param apiAcceptData 接受参数 账号 密码
     * @return user对象
     */
    @ResponseBody
    @RequestMapping(value = "/bridgeDial", method = RequestMethod.POST)
    public ApiSendData bridgeDial(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("呼叫中心桥接电话接口请求参数：" + JSON.toJSONString(apiAcceptData));
        System.out.print("呼叫中心桥接电话接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        JSONObject jsonReturn = new JSONObject();
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(CenterApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(CenterApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("呼叫中心桥接电话接口响应参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                Map map = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()), Map.class);
                if (map != null) {
                    JSONObject json = new JSONObject();
                    String showcaller = map.get("showcaller").toString();
                    String from = map.get("from").toString();
                    String to = map.get("to").toString();

                    String uname = "ywj";//用户名
                    String token = "jtd2016";//用户密码

                    Map paramMap = new HashMap();
                    paramMap.put("uname", uname);
                    paramMap.put("token", token);
                    paramMap.put("showcaller", showcaller);
                    paramMap.put("from", from);
                    paramMap.put("to", to);
                    log.info(paramMap);
                    System.out.println(paramMap);
                    String url = "http://freeswitch.dial.weiyingjia.org/msgapi/bothway";
                    String s = HttpTookit.doPostParam(url, paramMap, "utf-8");
                    System.out.print(s);
                    log.info(s);

                    CenterResult result = JsonUtils.jsonToPojo(s, CenterResult.class);//获取result
                    System.out.print("result.getStatuscode() : " + result.getStatuscode());
                    log.info("result.getStatuscode() : " + result.getStatuscode());
                    if (result.getStatuscode() == 1000) {
                        json.put("callback",result.getCallback());
                        apiSendData.setData(json);
                        apiSendData.setCode(CenterApiReturnCode.SUCCESS.getCode());
                        apiSendData.setMessage(CenterApiReturnCode.SUCCESS.getMessage());
                    } else if (result.getStatuscode() == 1001) {
                        json.put("callback",result.getCallback());
                        apiSendData.setData(json);
                        apiSendData.setCode(CenterApiReturnCode.USERNAME_PWD_ERROR.getCode());
                        apiSendData.setMessage(CenterApiReturnCode.USERNAME_PWD_ERROR.getMessage());
                    } else if (result.getStatuscode() == 1002) {
                        json.put("callback",result.getCallback());
                        apiSendData.setData(json);
                        apiSendData.setCode(CenterApiReturnCode.PARAMETER_MISS.getCode());
                        apiSendData.setMessage(CenterApiReturnCode.PARAMETER_MISS.getMessage());
                    } else if (result.getStatuscode() == 1003) {
                        json.put("callback",result.getCallback());
                        apiSendData.setData(json);
                        apiSendData.setCode(CenterApiReturnCode.CONVERSATION_FAIL.getCode());
                        apiSendData.setMessage(CenterApiReturnCode.CONVERSATION_FAIL.getMessage());
                    } else if (result.getStatuscode() == 1010) {
                        json.put("callback",result.getCallback());
                        apiSendData.setData(json);
                        apiSendData.setCode(CenterApiReturnCode.SYSTEM_ERROR.getCode());
                        apiSendData.setMessage(CenterApiReturnCode.SYSTEM_ERROR.getMessage());
                    }
                }
            } else {
                apiSendData.setData(jsonReturn);
                apiSendData.setCode(CenterApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(CenterApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setData(jsonReturn);
            apiSendData.setCode(CenterApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(CenterApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("呼叫中心桥接电话接口响应参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }
}
