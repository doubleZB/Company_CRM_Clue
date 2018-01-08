package com.sunll.web.controller.center;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.sunll.center.entity.Bill;
import com.sunll.center.entity.BillExample;
import com.sunll.center.mapper.BillMapper;
import com.sunll.center.mapper.RecordMapper;
import com.sunll.systemset.api.login.LoginService;
import com.sunll.systemset.api.unitAccount.UnitAccountService;
import com.sunll.web.vo.CostStatistics;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
@RequestMapping("/cost")
public class CostStatisticsAction {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    BillMapper billMapper;
    /* @Autowired
    private JedisClient jedisClient;*/
    @Reference(check = false,timeout = 100000)
    UnitAccountService unitAccountService;
    @Reference(check = false, timeout = 100000)
    LoginService loginService;

    private PageInfo<CostStatistics> allData = null;

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
        CostStatistics temp = null;
        List<CostStatistics> listCostStatistics = new ArrayList<>();
        //for zhangkexin by szq
        List<CostStatistics> listCostStatistics2 = new ArrayList<>();
        if(userNmId!=null){
            for(int i=0;i<userNmId.size();i++){
                userId = userNmId.get(i).get("id").toString();
                userName = userNmId.get(i).get("name").toString();
                temp = billStatistics(userId,userName,createTimeFrom,createTimeTo,direction);
                if(temp!=null){
                    listCostStatistics.add(temp);
                }
            }
        }
        log.info("--- listCostStatistics.size() = "+listCostStatistics.size()+" ---");
        //PageHelper.startPage(pageNumber, pageSize);

        //for zhangkexin add by szq start
        int total = listCostStatistics.size();
        if(total>0){
            int startIndex = ((pageNumber-1)*pageSize -1)<0?0:(pageNumber-1)*pageSize;
            int endIndex = startIndex + pageSize;
            if(endIndex+1>total) endIndex = startIndex + total%pageSize;

            for(int i=startIndex;i<endIndex;i++){
                listCostStatistics2.add(listCostStatistics.get(i));
            }

        }

        //PageInfo<CostStatistics> pageInfo = new PageInfo<CostStatistics>(listCostStatistics);
        allData = new PageInfo<CostStatistics>(listCostStatistics);
        PageInfo<CostStatistics> pageInfo = new PageInfo<CostStatistics>(listCostStatistics2);

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

    public static void main(String[]args){
        int a = 8;
        int b = 5;
        int c = a%b==0?a/b:a/b+1;
        System.out.println(c);
    }

    @RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request) {
        log.info("--- listPage() start ---");

        log.info("--- listPage() end ---");
        return "center/costStatistics";
    }

    private float add(float v1,float v2){
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.add(b2).floatValue();
    }

    private CostStatistics billStatistics( String userId,String userName,String createTimeFrom,String createTimeTo,String direction) {
        log.info("--- For CENTER : billStatistics() start ---");
        BillExample billE = new BillExample();
        BillExample.Criteria criteria = billE.createCriteria();

        log.info("--- For CENTER : billStatistics() userId ---" + userId);
        //String accountMobile = "13261702440";
        String accountMobile = loginService.getAccountMobileByUserId(userId);
        log.info("--- For CENTER : billStatistics() accountMobile ---" + accountMobile);

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

        if(StringUtil.isNotEmpty(direction)){
            criteria.andDirectionEqualTo(new Byte(direction));
        }

        if(StringUtil.isNotEmpty(accountMobile)&& NumberUtils.isNumber(accountMobile)){
            criteria.multiColumnOrClause(accountMobile);
        }

        float seatCostTotal = 0f;
        int callTotalTime = 0;
        float callCostTotal = 0f;
        float total = 0f;

        List<Bill> bills = billMapper.selectByExample(billE);
        for(int i=0;i<bills.size();i++){
            callTotalTime = callTotalTime + bills.get(i).getBillsec().intValue();
            callCostTotal = this.add(callCostTotal,bills.get(i).getFee().floatValue());
        }

        CostStatistics cost = null;
        if(bills.size()>0){
            total = this.add(total,seatCostTotal);
            total = this.add(total,callCostTotal);

            cost = new CostStatistics();
            cost.setUserId(userId);
            cost.setUserName(userName);
            cost.setSeatCostTotal(seatCostTotal);
            cost.setCallTotalTime(callTotalTime);
            cost.setCallCostTotal(callCostTotal);
            cost.setTotal(total);
        }

        //System.out.print(result);
        //log.info(result);
        log.info("--- For CENTER : billStatistics() end ---");
        return cost;
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
        CostStatistics temp = null;
        List<CostStatistics> listCostStatistics = new ArrayList<>();
        //for zhangkexin by szq
        List<CostStatistics> listCostStatistics2 = new ArrayList<>();
        if(userNmId!=null){
            for(int i=0;i<userNmId.size();i++){
                userId = userNmId.get(i).get("id").toString();
                userName = userNmId.get(i).get("name").toString();
                temp = billStatistics(userId,userName,createTimeFrom,createTimeTo,null);
                if(temp!=null){
                    listCostStatistics.add(temp);
                }
            }
        }
        //PageHelper.startPage(pageNumber, pageSize);

        if(pageNumber==0&&pageSize==0){
            PageInfo<CostStatistics> pageInfo = new PageInfo<CostStatistics>(listCostStatistics);
            return pageInfo;
        }

        //for zhangkexin add by szq start
        int total = listCostStatistics.size();
        if(total>0){
            int startIndex = ((pageNumber-1)*pageSize -1)<0?0:(pageNumber-1)*pageSize;
            int endIndex = startIndex + pageSize;
            if(endIndex+1>total) endIndex = startIndex + total%pageSize;

            for(int i=startIndex;i<endIndex;i++){
                listCostStatistics2.add(listCostStatistics.get(i));
            }

        }

        //PageInfo<CostStatistics> pageInfo = new PageInfo<CostStatistics>(listCostStatistics);
        allData = new PageInfo<CostStatistics>(listCostStatistics);
        PageInfo<CostStatistics> pageInfo = new PageInfo<CostStatistics>(listCostStatistics2);

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
