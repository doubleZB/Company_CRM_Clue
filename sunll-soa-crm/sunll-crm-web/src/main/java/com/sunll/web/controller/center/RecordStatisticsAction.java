package com.sunll.web.controller.center;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.sunll.center.entity.CallCdr;
import com.sunll.center.entity.CallCdrExample;
import com.sunll.center.mapper.CallCdrMapper;
import com.sunll.systemset.api.login.LoginService;
import com.sunll.systemset.api.unitAccount.UnitAccountService;
import com.sunll.web.vo.RecordStatistics;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
@RequestMapping("/records")
public class RecordStatisticsAction {
    private Logger log = Logger.getLogger(this.getClass());
/*    @Autowired
    RecordMapper recordMapper;
    @Autowired
    BillMapper billMapper;*/
    @Autowired
    CallCdrMapper callCdrMapper;
    /* @Autowired
    private JedisClient jedisClient;*/
    @Reference(check = false,timeout = 100000)
    UnitAccountService unitAccountService;
    @Reference(check = false, timeout = 100000)
    LoginService loginService;

    private PageInfo<RecordStatistics> allData = null;

    @ResponseBody
    @RequestMapping(value = "/allData")
    public Object allData() {
        return allData;
    }

    @ResponseBody
    @RequestMapping(value = "/getData")
    public Object getData(Integer pageNumber, Integer pageSize,HttpServletRequest request) {
        log.info("--- getData() start ---");
        String currentUserId = request.getParameter("USER_TOKEN");
        String currentCompanyId = request.getParameter("companyId");

        String createTimeFrom = request.getParameter("createTimeFrom");
        String createTimeTo = request.getParameter("createTimeTo");
        String direction = request.getParameter("direction");
        String checkedDepartmentId = request.getParameter("checkedDepartmentId");
        String checkedUserId = request.getParameter("checkedUserId");

        List<Map> userNmId = null;

        if(StringUtil.isNotEmpty(checkedUserId)){
            userNmId = unitAccountService.listUserByDepIdAndUserId(currentUserId,currentCompanyId,checkedUserId,null);
        }else if(StringUtil.isNotEmpty(checkedDepartmentId)){
            userNmId =  unitAccountService.listUserByDepIdAndUserId(currentUserId,currentCompanyId,null,checkedDepartmentId);
        }else{
            userNmId =unitAccountService.listUserByDepIdAndUserId(currentUserId,currentCompanyId,currentUserId,null);
        }

        String userId = "";
        String userName = "";
        RecordStatistics temp = null;
        List<RecordStatistics> listRecordStatistics = new ArrayList<>();
        //for zhangkexin by szq
        List<RecordStatistics> listRecordStatistics2 = new ArrayList<>();
        if(userNmId!=null){
            for(int i=0;i<userNmId.size();i++){
                userId = userNmId.get(i).get("id").toString();
                userName = userNmId.get(i).get("name").toString();
                temp = recordStatistics(userId,userName,createTimeFrom,createTimeTo,direction);
                if(temp!=null){
                    listRecordStatistics.add(temp);
                }
            }
        }
        //PageHelper.startPage(pageNumber, pageSize);

        //for zhangkexin add by szq start
        int total = listRecordStatistics.size();
        if(total>0){
            int startIndex = ((pageNumber-1)*pageSize -1)<0?0:(pageNumber-1)*pageSize;
            int endIndex = startIndex + pageSize;
            if(endIndex+1>total) endIndex = startIndex + total%pageSize;

            for(int i=startIndex;i<endIndex;i++){
                listRecordStatistics2.add(listRecordStatistics.get(i));
            }

        }
        //PageInfo<RecordStatistics> pageInfo = new PageInfo<RecordStatistics>(listRecordStatistics);
        allData = new PageInfo<RecordStatistics>(listRecordStatistics);
        PageInfo<RecordStatistics> pageInfo = new PageInfo<RecordStatistics>(listRecordStatistics2);

        if(total>0){
            int pages = total%pageSize==0?total/pageSize:total/pageSize+1;
            pageInfo.setPages(pages);
            pageInfo.setTotal(total);
            pageInfo.setPageNum(pageNumber);
            pageInfo.setPageSize(pageSize);
        }
        //for zhangkexin add by szq end

        log.info("--- getData() end ---");
        return pageInfo;

    }


    @RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request) {
        log.info("--- listPage() start ---");

        log.info("--- listPage() end ---");
        return "center/recordStatistics";
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
        return result;
    }

    private RecordStatistics recordStatistics(String userId, String userName, String createTimeFrom, String createTimeTo, String direction) {
        log.info("--- For CENTER : recordStatistics() start ---");
        CallCdrExample recordE = new CallCdrExample();
        CallCdrExample.Criteria criteria = recordE.createCriteria();

        log.info("--- For CENTER : recordStatistics() userId ---" + userId);
        //String accountMobile = "13261702440";
        String accountMobile = loginService.getAccountMobileByUserId(userId);
        log.info("--- For CENTER : recordStatistics() accountMobile ---" + accountMobile);

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

        if(StringUtil.isNotEmpty(accountMobile)&& NumberUtils.isNumber(accountMobile)){
            criteria.multiColumnOrClause(accountMobile);
        }

        //criteria.andDirectionEqualTo("outbound");
        if(StringUtil.isNotEmpty(direction)){
            criteria.andDirectionEqualTo(direction);
        }
        List<CallCdr> callCdrs = callCdrMapper.selectByExample(recordE);

        /*部门/人员 外呼总数 外呼成功数 外呼失败数 外呼成功率  转化率 通话总时长*/
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

        RecordStatistics s = null;
        if(callCdrs.size()>0){
            outBoundPercentage = percentageForInt(outBoundSuccess,outBoundTotal);
            s = new RecordStatistics();
            s.setUserId(userId);
            s.setUserName(userName);
            s.setCallTimeTotal(callTimeTotal);
            s.setOutBoundFailure(outBoundFailure);
            s.setOutBoundPercentage(outBoundPercentage);
            s.setOutBoundTotal(outBoundTotal);
            s.setOutBoundSuccess(outBoundSuccess);
        }

        //System.out.print(result);
        //log.info(result);
        log.info("--- For CRM : recordStatistics() end ---");
        return s;
    }


    @ResponseBody
    @RequestMapping(value = "/getDataZ", method = RequestMethod.POST)
    public Object getDataZ(@RequestBody Map<String, Object> acceptMap) {
        log.info("--- getData() start ---");
        String currentUserId = (String) acceptMap.get("USER_TOKEN");
        String currentCompanyId = (String) acceptMap.get("companyId");
        String createTimeFrom = (String) acceptMap.get("createTimeFrom");
        String createTimeTo = (String) acceptMap.get("createTimeTo");
        String checkedDepartmentId = (String) acceptMap.get("checkedDepartmentId");
        String checkedUserId = (String) acceptMap.get("checkedUserId");
        int pageNumber = (int) acceptMap.get("pageNumber");
        int pageSize = (int) acceptMap.get("pageSize");
        List<Map> userNmId = null;
        if(StringUtil.isNotEmpty(checkedUserId)){
            userNmId = unitAccountService.listUserByDepIdAndUserId(currentUserId,currentCompanyId,checkedUserId,null);
        }else if(StringUtil.isNotEmpty(checkedDepartmentId)){
            userNmId =  unitAccountService.listUserByDepIdAndUserId(currentUserId,currentCompanyId,null,checkedDepartmentId);
        }else{
            userNmId =unitAccountService.listUserByDepIdAndUserId(currentUserId,currentCompanyId,currentUserId,null);
        }

        String userId = "";
        String userName = "";
        RecordStatistics temp = null;
        List<RecordStatistics> listRecordStatistics = new ArrayList<>();
        //for zhangkexin by szq
        List<RecordStatistics> listRecordStatistics2 = new ArrayList<>();
        if(userNmId!=null){
            for(int i=0;i<userNmId.size();i++){
                userId = userNmId.get(i).get("id").toString();
                userName = userNmId.get(i).get("name").toString();
                temp = recordStatistics(userId,userName,createTimeFrom,createTimeTo,null);
                if(temp!=null){
                    listRecordStatistics.add(temp);
                }
            }
        }
        //PageHelper.startPage(pageNumber, pageSize);

        if(pageNumber==0&&pageSize==0){
            PageInfo<RecordStatistics> pageInfo = new PageInfo<RecordStatistics>(listRecordStatistics);
            return pageInfo;
        }

        //for zhangkexin add by szq start
        int total = listRecordStatistics.size();
        if(total>0){
            int startIndex = ((pageNumber-1)*pageSize -1)<0?0:(pageNumber-1)*pageSize;
            int endIndex = startIndex + pageSize;
            if(endIndex+1>total) endIndex = startIndex + total%pageSize;

            for(int i=startIndex;i<endIndex;i++){
                listRecordStatistics2.add(listRecordStatistics.get(i));
            }

        }
        //PageInfo<RecordStatistics> pageInfo = new PageInfo<RecordStatistics>(listRecordStatistics);
        allData = new PageInfo<RecordStatistics>(listRecordStatistics);
        PageInfo<RecordStatistics> pageInfo = new PageInfo<RecordStatistics>(listRecordStatistics2);

        if(total>0){
            int pages = total%pageSize==0?total/pageSize:total/pageSize+1;
            pageInfo.setPages(pages);
            pageInfo.setTotal(total);
            pageInfo.setPageNum(pageNumber);
            pageInfo.setPageSize(pageSize);
        }
        //for zhangkexin add by szq end

        log.info("--- getData() end ---");
        return pageInfo;

    }


}
