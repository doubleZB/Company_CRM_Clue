package com.sunll.web.controller.call;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sunll.clue.api.message.MessageService;
import com.sunll.clue.entity.message.ShowCallCost;
import com.sunll.clue.entity.message.ShowCallDuration;
import com.sunll.common.util.*;
import com.sunll.systemset.api.login.LoginService;
import com.sunll.systemset.api.unitAccount.UnitAccountService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14.
 */
@Controller
@RequestMapping("/call")
public class CallController {
    @Reference(check = false, timeout = 100000)
    MessageService messageService;
    private Logger log = Logger.getLogger(this.getClass());
    private static final String CALL_CENTER_BASE_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("CALL_CENTER_BASE_URL");
    private static final String STATISTIC_COST = (String) PropertiesUtils
            .loadProperties("config.properties").get("STATISTIC_COST");
    private static final String STATISTIC_DURATION = (String) PropertiesUtils
            .loadProperties("config.properties").get("STATISTIC_DURATION");

    /**
     * 通话费用统计
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectTotalExpensesOther", method = RequestMethod.POST)
    public Object selectTotalExpensesOther(Integer userId, Integer companyId, String startTime, String endTime, Integer PageNum, Integer PageSize,
                                           Integer checkedDepartmentId, Integer checkedUserId, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageNumber",PageNum);
        map.put("pageSize",PageSize);
        map.put("USER_TOKEN",String.valueOf(userId));
        map.put("companyId",String.valueOf(companyId));
        map.put("createTimeFrom",String.valueOf(startTime));
        map.put("createTimeTo",String.valueOf(endTime));
        map.put("checkedDepartmentId",String.valueOf(checkedDepartmentId));
        map.put("checkedUserId",String.valueOf(checkedUserId));
//        String s = HttpclientUtils.doPostByJson(CALL_CENTER_BASE_URL+ STATISTIC_COST, map);
        String sss = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        log.info("【"+sss+"】");
        String s = HttpclientUtils.doPostByJson( sss+STATISTIC_COST, map);
        PageInfo pageInfo = JsonUtils.jsonToPojo(s, PageInfo.class);
        List<Map<String,Object>> a = (List<Map<String,Object>>)(pageInfo.getList());
        List<ShowCallCost> aa = new ArrayList<>();
        for (Map<String,Object> m:a) {
            Object userName = m.get("userName");
            Object seatCostTotal = m.get("seatCostTotal");
            Object callTotalTime = m.get("callTotalTime");
            Object callCostTotal = m.get("callCostTotal");
            Object total = m.get("total");
            //userId/用户名/坐席费用总计/通话总时长/通话时长费用总计/总计
            ShowCallCost ss = new ShowCallCost(null,(String) userName,(Double) seatCostTotal,
                    (int)callTotalTime,(Double)callCostTotal,(Double) total);
            aa.add(ss);
        }
        //----------------------------------字段名称变化----------------------------------
        pageInfo.setList(aa);
        return pageInfo;
    }

    /**
     * 通话费用统计 总计
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectTotalExpensesTotalOther", method = RequestMethod.POST)
    public Object selectTotalExpensesTotalOther(Integer userId,Integer companyId, String startTime, String endTime, Integer PageNum, Integer PageSize,
                                                Integer checkedDepartmentId, Integer checkedUserId, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageNumber",0);
        map.put("pageSize",0);
        map.put("USER_TOKEN",String.valueOf(userId));
        map.put("companyId",String.valueOf(companyId));
        map.put("createTimeFrom",String.valueOf(startTime));
        map.put("createTimeTo",String.valueOf(endTime));
        map.put("checkedDepartmentId",String.valueOf(checkedDepartmentId));
        map.put("checkedUserId",String.valueOf(checkedUserId));
//        String s = HttpclientUtils.doPostByJson(CALL_CENTER_BASE_URL+ STATISTIC_COST, map);
        String sss = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        log.info("【"+sss+"】");
        String s = HttpclientUtils.doPostByJson(sss+ STATISTIC_COST, map);
        PageInfo pageInfo = JsonUtils.jsonToPojo(s, PageInfo.class);
        List<Map<String,Object>> a = (List<Map<String,Object>>)(pageInfo.getList());
        List<ShowCallCost> aa = new ArrayList<>();
        for (Map<String,Object> m:a) {
            Object userName = m.get("userName");
            Object seatCostTotal = m.get("seatCostTotal");
            Object callTotalTime = m.get("callTotalTime");
            Object callCostTotal = m.get("callCostTotal");
            Object total = m.get("total");
            //userId/用户名/坐席费用总计/通话总时长/通话时长费用总计/总计
            ShowCallCost ss = new ShowCallCost(null,(String) userName,(Double) seatCostTotal,
                    (int)callTotalTime,(Double)callCostTotal,(Double) total);
            aa.add(ss);
        }
        //----------------------------------字段名称变化----------------------------------
        pageInfo.setList(aa);
        double totalSeatCosts = 0.00;
        int totalCallDuration = 0;
        double callDurationTotalCost = 0.00;
        double total = 0.00;
        if(aa.size() != 0){
            for (ShowCallCost m : aa) {
                totalSeatCosts = totalSeatCosts + m.getTotalSeatCosts();
                totalCallDuration = totalCallDuration + m.getTotalCallDuration();
                callDurationTotalCost = callDurationTotalCost + m.getCallDurationTotalCost();
                total = total + m.getTotal();
            }
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("totalSeatCosts", totalSeatCosts);
        map1.put("totalCallDuration", totalCallDuration);
        map1.put("callDurationTotalCost", callDurationTotalCost);
        map1.put("total", total);
        return map1;
    }

    /**
     * 通话费用统计 导出
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exportTotalExpensesTotalOther", method = RequestMethod.POST)
    public void exportTotalExpensesTotalOther(Integer userId,Integer companyId, String startTime, String endTime, Integer PageNum, Integer PageSize,
                                              Integer checkedDepartmentId, Integer checkedUserId, HttpServletResponse response, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageNumber",0);
        map.put("pageSize",0);
        map.put("USER_TOKEN",String.valueOf(userId));
        map.put("companyId",String.valueOf(companyId));
        map.put("createTimeFrom",String.valueOf(startTime));
        map.put("createTimeTo",String.valueOf(endTime));
        map.put("checkedDepartmentId",String.valueOf(checkedDepartmentId));
        map.put("checkedUserId",String.valueOf(checkedUserId));
//        String s = HttpclientUtils.doPostByJson(CALL_CENTER_BASE_URL+ STATISTIC_COST, map);
        String sss = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        log.info("【"+sss+"】");
        String s = HttpclientUtils.doPostByJson(sss+ STATISTIC_COST, map);
        PageInfo pageInfo = JsonUtils.jsonToPojo(s, PageInfo.class);
        List<Map<String,Object>> a = (List<Map<String,Object>>)(pageInfo.getList());
        List<ShowCallCost> aa = new ArrayList<>();
        for (Map<String,Object> m:a) {
            Object userName = m.get("userName");
            Object seatCostTotal = m.get("seatCostTotal");
            Object callTotalTime = m.get("callTotalTime");
            Object callCostTotal = m.get("callCostTotal");
            Object total = m.get("total");
            //userId/用户名/坐席费用总计/通话总时长/通话时长费用总计/总计
            ShowCallCost ss = new ShowCallCost(null,(String) userName,(Double) seatCostTotal,
                    (int)callTotalTime,(Double)callCostTotal,(Double) total);
            aa.add(ss);
        }
        //----------------------------------字段名称变化----------------------------------
        //设置excel标题
        String[] headers = ShowCallCost.Excel.EXCEL_EXPORT_HEADERS;
        //设置excel文件名
        StringBuilder fileName = new StringBuilder();
        //销售漏斗报表
        fileName.append(ShowCallCost.Excel.EXCEL_EXPORT_FILE_NAME_START);
        //文件中无连接符的时间
        fileName.append(DateUtil.currentTime2String());
        // .xls
        fileName.append(ShowCallCost.Excel.EXCEL_FILE_SUFFIX_NAME);
        try {
            ExportExcelUtil.exportExcel(response, fileName.toString(), headers, aa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通话时长统计
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectTimeStatisticsOther", method = RequestMethod.POST)
    public Object selectTimeStatisticsOther(Integer userId,Integer companyId, String startTime, String endTime, Integer PageNum, Integer PageSize,
                                            Integer checkedDepartmentId, Integer checkedUserId, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageNumber",PageNum);
        map.put("pageSize",PageSize);
        map.put("USER_TOKEN",String.valueOf(userId));
        map.put("companyId",String.valueOf(companyId));
        map.put("createTimeFrom",String.valueOf(startTime));
        map.put("createTimeTo",String.valueOf(endTime));
        map.put("checkedDepartmentId",String.valueOf(checkedDepartmentId));
        map.put("checkedUserId",String.valueOf(checkedUserId));
//        String s = HttpclientUtils.doPostByJson( CALL_CENTER_BASE_URL+ STATISTIC_DURATION, map);
        String sss = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        log.info("【"+sss+"】");
        String s = HttpclientUtils.doPostByJson( sss+ STATISTIC_DURATION, map);
        PageInfo pageInfo = JsonUtils.jsonToPojo(s, PageInfo.class);
        List<Map<String,Object>> a = (List<Map<String,Object>>)(pageInfo.getList());
        List<ShowCallDuration> aa = new ArrayList<>();
        for (Map<String,Object> m:a) {
            Object userName = m.get("userName");
            Object outBoundTotal = m.get("outBoundTotal");
            Object outBoundSuccess = m.get("outBoundSuccess");
            Object outBoundFailure = m.get("outBoundFailure");
            Object callTimeTotal = m.get("callTimeTotal");
            Object outBoundPercentage = m.get("outBoundPercentage");
            Object id = m.get("userId");
            if(outBoundPercentage.equals("")){
                outBoundPercentage = "0";
            }
            Integer userId1 = Integer.parseInt((String)id);
            Double conversionRateByUserId = messageService.getConversionRateByUserId(userId1);
            //userId/用户名/外呼总数/外呼成功数/外呼失败数/外呼成功率/转化率/通话总时长
            ShowCallDuration ss = new ShowCallDuration(null,(String)userName,(int)outBoundTotal,
                    (int)outBoundSuccess,(int)outBoundFailure,Double.parseDouble((String)outBoundPercentage),conversionRateByUserId,(int)callTimeTotal);
            aa.add(ss);
        }
        //----------------------------------字段名称变化----------------------------------
        pageInfo.setList(aa);
        return pageInfo;
    }

    /**
     * 通话时长统计 总计
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectTimeStatisticsTotalOther", method = RequestMethod.POST)
    public Object selectTimeStatisticsTotalOther(Integer userId,Integer companyId, String startTime, String endTime, Integer PageNum, Integer PageSize,
                                                 Integer checkedDepartmentId, Integer checkedUserId, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageNumber",0);
        map.put("pageSize",0);
        map.put("USER_TOKEN",String.valueOf(userId));
        map.put("companyId",String.valueOf(companyId));
        map.put("createTimeFrom",String.valueOf(startTime));
        map.put("createTimeTo",String.valueOf(endTime));
        map.put("checkedDepartmentId",String.valueOf(checkedDepartmentId));
        map.put("checkedUserId",String.valueOf(checkedUserId));
//        String s = HttpclientUtils.doPostByJson( CALL_CENTER_BASE_URL+ STATISTIC_DURATION, map);
        String sss = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        log.info("【"+sss+"】");
        String s = HttpclientUtils.doPostByJson( sss+ STATISTIC_DURATION, map);
        PageInfo pageInfo = JsonUtils.jsonToPojo(s, PageInfo.class);
        List<Map<String,Object>> a = (List<Map<String,Object>>)(pageInfo.getList());
        List<ShowCallDuration> aa = new ArrayList<>();
        List<Integer> userIdList = new ArrayList<>();
        for (Map<String,Object> m:a) {
            Object userName = m.get("userName");
            Object outBoundTotal = m.get("outBoundTotal");
            Object outBoundSuccess = m.get("outBoundSuccess");
            Object outBoundFailure = m.get("outBoundFailure");
            Object callTimeTotal = m.get("callTimeTotal");
            Object outBoundPercentage = m.get("outBoundPercentage");
            Object id = m.get("userId");
            if(outBoundPercentage.equals("")){
                outBoundPercentage = "0";
            }
            Integer userId1 = Integer.parseInt((String)id);
            userIdList.add(userId1);
            //userId/用户名/外呼总数/外呼成功数/外呼失败数/外呼成功率/转化率/通话总时长
            ShowCallDuration ss = new ShowCallDuration(null,(String)userName,(int)outBoundTotal,
                    (int)outBoundSuccess,(int)outBoundFailure,Double.parseDouble((String)outBoundPercentage),0.00,(int)callTimeTotal);
            aa.add(ss);
        }
        //----------------------------------字段名称变化----------------------------------
        pageInfo.setList(aa);
        int outCallNumber = 0;
        int outCallSuccessNumber = 0;
        int outCallFailNumber = 0;
        double outCallSuccessRate = 0.00;
        double conversionRate = 0.00;
        int totalCallDuration = 0;
        Double allConversionRateByUserId = messageService.getAllConversionRateByUserId(userIdList);
        if(allConversionRateByUserId != null){
            conversionRate = allConversionRateByUserId;
        }
        if(aa.size() != 0){
            for (ShowCallDuration m : aa) {
                outCallNumber = outCallNumber + m.getOutCallNumber();
                outCallSuccessNumber = outCallSuccessNumber + m.getOutCallSuccessNumber();
                outCallFailNumber = outCallFailNumber + m.getOutCallFailNumber();
                outCallSuccessRate = outCallSuccessRate + m.getOutCallSuccessRate();
                conversionRate = conversionRate + m.getConversionRate();
                totalCallDuration = totalCallDuration + m.getTotalCallDuration();
            }
            outCallSuccessRate = outCallSuccessRate/aa.size();
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("outCallNumber", outCallNumber);
        map1.put("outCallSuccessNumber", outCallSuccessNumber);
        map1.put("outCallFailNumber", outCallFailNumber);
        map1.put("outCallSuccessRate", outCallSuccessRate);//外呼成功率
        map1.put("conversionRate", conversionRate);
        map1.put("totalCallDuration", totalCallDuration);
        return map1;
    }

    /**
     * 通话时长统计 导出
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exportTimeStatisticsTotalOther", method = RequestMethod.POST)
    public void exportTimeStatisticsTotalOther(Integer userId,Integer companyId, String startTime, String endTime, Integer PageNum, Integer PageSize,
                                               Integer checkedDepartmentId, Integer checkedUserId, HttpServletResponse response, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageNumber",0);
        map.put("pageSize",0);
        map.put("USER_TOKEN",String.valueOf(userId));
        map.put("companyId",String.valueOf(companyId));
        map.put("createTimeFrom",String.valueOf(startTime));
        map.put("createTimeTo",String.valueOf(endTime));
        map.put("checkedDepartmentId",String.valueOf(checkedDepartmentId));
        map.put("checkedUserId",String.valueOf(checkedUserId));
//        String s = HttpclientUtils.doPostByJson( CALL_CENTER_BASE_URL+ STATISTIC_DURATION, map);
        String sss = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        log.info("【"+sss+"】");
        String s = HttpclientUtils.doPostByJson( sss+ STATISTIC_DURATION, map);
        PageInfo pageInfo = JsonUtils.jsonToPojo(s, PageInfo.class);
        List<Map<String,Object>> a = (List<Map<String,Object>>)(pageInfo.getList());
        List<ShowCallDuration> aa = new ArrayList<>();
        for (Map<String,Object> m:a) {
            Object userName = m.get("userName");
            Object outBoundTotal = m.get("outBoundTotal");
            Object outBoundSuccess = m.get("outBoundSuccess");
            Object outBoundFailure = m.get("outBoundFailure");
            Object callTimeTotal = m.get("callTimeTotal");
            Object outBoundPercentage = m.get("outBoundPercentage");
            Object id = m.get("userId");
            if(outBoundPercentage.equals("")){
                outBoundPercentage = "0";
            }
            Integer userId1 = Integer.parseInt((String)id);
            Double conversionRateByUserId = messageService.getConversionRateByUserId(userId1);
            //userId/用户名/外呼总数/外呼成功数/外呼失败数/外呼成功率/转化率/通话总时长
            ShowCallDuration ss = new ShowCallDuration(null,(String)userName,(int)outBoundTotal,
                    (int)outBoundSuccess,(int)outBoundFailure,Double.parseDouble((String)outBoundPercentage),conversionRateByUserId,(int)callTimeTotal);
            ss.setOutCallSuccessRateShow(((String)outBoundPercentage)+"%");
            aa.add(ss);
        }
        //----------------------------------字段名称变化----------------------------------
        //设置excel标题
        String[] headers = ShowCallDuration.Excel.EXCEL_EXPORT_HEADERS;
        //设置excel文件名
        StringBuilder fileName = new StringBuilder();
        //销售漏斗报表
        fileName.append(ShowCallDuration.Excel.EXCEL_EXPORT_FILE_NAME_START);
        //文件中无连接符的时间
        fileName.append(DateUtil.currentTime2String());
        // .xls
        fileName.append(ShowCallDuration.Excel.EXCEL_FILE_SUFFIX_NAME);
        try {
            ExportExcelUtil.exportExcel(response, fileName.toString(), headers, aa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
