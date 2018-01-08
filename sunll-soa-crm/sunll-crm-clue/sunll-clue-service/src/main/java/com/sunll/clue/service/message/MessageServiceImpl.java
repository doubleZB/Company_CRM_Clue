package com.sunll.clue.service.message;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunll.clue.api.message.MessageService;
import com.sunll.clue.api.smsSupply.SmsSupplyService;
import com.sunll.clue.dao.business.*;
import com.sunll.clue.entity.business.*;
import com.sunll.clue.entity.message.ShowCallCost;
import com.sunll.clue.entity.message.ShowCallDuration;
import com.sunll.clue.entity.message.ShowMessage;
import com.sunll.clue.entity.message.ShowMessageCosts;
import com.sunll.common.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/12/6.
 */
@Service
public class MessageServiceImpl implements MessageService {
    private String CLUE_FIELD = (String) PropertiesUtils.loadProperties("config.properties").get("clueFiled");

    private static final String CALL_CENTER_BASE_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("CALL_CENTER_BASE_URL");
    private static final String CALL_COST_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("CALL_COST_URL");
    private static final String CALL_TIME_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("CALL_TIME_URL");
    private static final String UNITE_ACCOUNT_BASE_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("UNITE_ACCOUNT_BASE_URL");
    private static final String GET_USER_BY_USERID = (String) PropertiesUtils
            .loadProperties("config.properties").get("GET_USER_BY_USERID");
    @Autowired
    SendMessageMapper sendMessageMapper;
    @Autowired
    SendReceiveMapper sendReceiveMapper;
    @Autowired
    MessageTemplateMapper messageTemplateMapper;
    @Autowired
    SmsSupplyService smsSupplyService;
    @Autowired
    BusinessTypeFieldValueVarcharMapper businessTypeFieldValueVarcharMapper;
    @Autowired
    BusinessTypeFieldMapper businessTypeFieldMapper;

    /**
     * 分页查询短信
     *
     * @param startTime
     * @param endTime
     * @param PageNum
     * @param PageSize
     * @param UserIdDepIds
     * @param type
     * @return
     */
    @Override
    public PageInfo<SendMessage> selectMessageTogether(String startTime, String endTime, Integer PageNum, Integer PageSize, List<Map> UserIdDepIds, Integer type) {
        if (UserIdDepIds.size() == 0) {
            return new PageInfo<>();
        }
        try {
            PageHelper.startPage(PageNum, PageSize);
            //拼装数据
            SendMessageExample example = new SendMessageExample();
            SendMessageExample.Criteria criteria = example.createCriteria();
            List<Integer> iii = new ArrayList<>();
            for (Map<String, Object> mmm : UserIdDepIds) {
                iii.add((int) mmm.get("id"));
            }
            criteria.andSendUseridIn(iii);
            if (StringUtils.isNoneBlank(startTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = sdf.parse(startTime+" 00:00:00");
                Date endDate = sdf.parse(endTime+" 23:59:59");
                criteria.andSendTimeBetween(startDate, endDate);
            }
            //1成功2失败0全部
            if (type != 0 ) {
                criteria.andStatusEqualTo(type);
            }
            List<SendMessage> messages = sendMessageMapper.selectByExample(example);
            PageInfo<SendMessage> pageInfo = new PageInfo<>(messages);
            return pageInfo;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 导出---
     *
     * @param startTime
     * @param endTime
     * @param PageNum
     * @param PageSize
     * @param UserIdDepIds
     * @param type
     * @return
     */
    @Override
    public List<SendMessage> exportMessageTogether(String startTime, String endTime, Integer PageNum, Integer PageSize, List<Map> UserIdDepIds, Integer type) {
        if (UserIdDepIds.size() == 0) {
            return new ArrayList<>();
        }
        try {
            //拼装数据
            SendMessageExample example = new SendMessageExample();
            SendMessageExample.Criteria criteria = example.createCriteria();
            List<Integer> iii = new ArrayList<>();
            for (Map<String, Object> mmm : UserIdDepIds) {
                iii.add((int) mmm.get("id"));
            }
            criteria.andSendUseridIn(iii);
            if (StringUtils.isNoneBlank(startTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = sdf.parse(startTime+" 00:00:00");
                Date endDate = sdf.parse(endTime+" 23:59:59");
                criteria.andSendTimeBetween(startDate, endDate);
            }
            //1成功2失败0全部
            if (type != 0) {
                criteria.andStatusEqualTo(type);
            }
            List<SendMessage> messages = sendMessageMapper.selectByExample(example);
            return messages;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 封装短信数据
     *
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfo selectMessageDetail(PageInfo<SendMessage> pageInfo) {
        List<ShowMessage> list = new ArrayList<>();
        if (pageInfo == null) {
            return new PageInfo();
        }
        for (SendMessage s : pageInfo.getList()) {
            MessageTemplateExample example = new MessageTemplateExample();
            example.createCriteria().andIdEqualTo(s.getSendContentTem());
            List<MessageTemplate> messageTemplates = messageTemplateMapper.selectByExample(example);
            String content = "";
            if (messageTemplates != null && messageTemplates.size() > 0) {
                MessageTemplate messageTemplate = messageTemplates.get(0);
                content = messageTemplate.getContent();
            }

            SendReceiveExample sendReceiveExample = new SendReceiveExample();
            SendReceiveExample.Criteria criteria = sendReceiveExample.createCriteria();
            criteria.andSendIdEqualTo(s.getId());
            criteria.andMessageTypeEqualTo(DataEnumerate.INT_NUM_ZERO);
            List<SendReceive> receives = sendReceiveMapper.selectByExample(sendReceiveExample);//总条数
            SendReceiveExample sendReceiveExample1 = new SendReceiveExample();
            SendReceiveExample.Criteria criteria1 = sendReceiveExample1.createCriteria();
            criteria1.andSendIdEqualTo(s.getId());
            criteria1.andMessageTypeEqualTo(DataEnumerate.INT_NUM_ZERO);
            criteria1.andStatusEqualTo(DataEnumerate.INT_NUM_ONE);
            List<SendReceive> receives1 = sendReceiveMapper.selectByExample(sendReceiveExample1);//成功的条数
            String a = "";
            if (s.getStatus() == 1) {
                a = "成功";
            } else if(s.getStatus() == 2){
                a = "失败";
            } else if(s.getStatus() == 3){
                a = "发送中";
            }
            Map<String, Object> map = new HashMap<>();
            map.put("userId", s.getSendUserid());
            String s1 = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + GET_USER_BY_USERID, map);
            Map map1 = JsonUtils.jsonToPojo(s1, Map.class);
            String name = (String) map1.get("name");
            ;
            ShowMessage ss = new ShowMessage(s.getId(), name, a, DateUtil.Date2String(s.getSendTime()), content, receives.size(), receives1.size());
            ss.setSendContentTem(s.getSendContentTem());
            list.add(ss);
        }
        PageInfo p = new PageInfo();//从新拼装分页
        p.setList(list);
        p.setPages(pageInfo.getPages());
        p.setTotal(pageInfo.getTotal());
        p.setPageNum(pageInfo.getPageNum());
        p.setPageSize(pageInfo.getPageSize());
        return p;
    }

    /**
     * 导出---封装短信数据
     *
     * @return
     */
    @Override
    public List<ShowMessage> exportMessageDetail(List<SendMessage> pageInfo) {
        List<ShowMessage> list = new ArrayList<>();
        if (pageInfo == null) {
            return new ArrayList<>();
        }
        for (SendMessage s : pageInfo) {
            MessageTemplateExample example = new MessageTemplateExample();
            example.createCriteria().andIdEqualTo(s.getSendContentTem());
            List<MessageTemplate> messageTemplates = messageTemplateMapper.selectByExample(example);
            String content = "";
            if (messageTemplates != null && messageTemplates.size() > 0) {
                MessageTemplate messageTemplate = messageTemplates.get(0);
                content = messageTemplate.getContent();
            }
            SendReceiveExample sendReceiveExample = new SendReceiveExample();
            SendReceiveExample.Criteria criteria = sendReceiveExample.createCriteria();
            criteria.andSendIdEqualTo(s.getId());
            criteria.andMessageTypeEqualTo(DataEnumerate.INT_NUM_ZERO);
            List<SendReceive> receives = sendReceiveMapper.selectByExample(sendReceiveExample);//总条数
            SendReceiveExample sendReceiveExample1 = new SendReceiveExample();
            SendReceiveExample.Criteria criteria1 = sendReceiveExample1.createCriteria();
            criteria1.andSendIdEqualTo(s.getId());
            criteria1.andMessageTypeEqualTo(DataEnumerate.INT_NUM_ZERO);
            criteria1.andStatusEqualTo(DataEnumerate.INT_NUM_ONE);
            List<SendReceive> receives1 = sendReceiveMapper.selectByExample(sendReceiveExample1);//成功的条数
            String a = "";
            if (s.getStatus() == 1) {
                a = "成功";
            } else if(s.getStatus() == 2){
                a = "失败";
            } else if(s.getStatus() == 3){
                a = "发送中";
            }
            Map<String, Object> map = new HashMap<>();
            map.put("userId", s.getSendUserid());
            String s1 = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + GET_USER_BY_USERID, map);
            Map map1 = JsonUtils.jsonToPojo(s1, Map.class);
            String name = (String) map1.get("name");
            ShowMessage ss = new ShowMessage(s.getId(), name, a, DateUtil.Date2String(s.getSendTime()), content, receives.size(), receives1.size());
            list.add(ss);
        }
        return list;
    }

    /**
     * 短信费用统计
     *
     * @param pageInfo
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public PageInfo selectMessageUserDetail(PageInfo<Map<String, Object>> pageInfo, String startTime, String endTime) {
        List<ShowMessageCosts> list = new ArrayList<>();
        if (pageInfo.getList().size() == 0) {
            return pageInfo;
        }
        //userList
        for (Map<String, Object> i : pageInfo.getList()) {
            SendMessage s = new SendMessage();
            SendMessageExample sendMessageExample = new SendMessageExample();
            SendMessageExample.Criteria criteria = sendMessageExample.createCriteria();
            criteria.andSendUseridEqualTo((int) i.get("id"));
            try {//添加时间限制
                if (StringUtils.isNoneBlank(startTime)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date startDate = sdf.parse(startTime+" 00:00:00");
                    Date endDate = sdf.parse(endTime+" 23:59:59");
                    criteria.andSendTimeBetween(startDate, endDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<SendMessage> messageList = sendMessageMapper.selectByExample(sendMessageExample);
            if (messageList.size() == 0) {//没有短信
                ShowMessageCosts messageCosts = new ShowMessageCosts((int) i.get("id"), (String) i.get("name"), 0, 0, 0.00);
                list.add(messageCosts);
                continue;
            }
            List<Integer> iii = new ArrayList<>();
            for (SendMessage ii : messageList) {
                iii.add(ii.getId());
            }
            SendReceiveExample sendReceiveExampleAll = new SendReceiveExample();
            SendReceiveExample.Criteria criteriaAll = sendReceiveExampleAll.createCriteria();
            criteriaAll.andSendIdIn(iii);
            List<SendReceive> receivesAll = sendReceiveMapper.selectByExample(sendReceiveExampleAll);
            SendReceiveExample sendReceiveExampleSuccess = new SendReceiveExample();
            SendReceiveExample.Criteria criteriaSuccess = sendReceiveExampleSuccess.createCriteria();
            criteriaSuccess.andSendIdIn(iii);
            criteriaSuccess.andStatusEqualTo(DataEnumerate.INT_NUM_ONE);
            List<SendReceive> receivesSuccess = sendReceiveMapper.selectByExample(sendReceiveExampleSuccess);
            double v = DataEnumerate.DOUBLE_COST * receivesAll.size();
            BigDecimal a1 = new BigDecimal(Double.toString(DataEnumerate.DOUBLE_COST));
            BigDecimal b1 = new BigDecimal(Double.toString(receivesAll.size()));
            BigDecimal multiply = a1.multiply(b1);
            String s1 = multiply.toString();
            double v1 = Double.parseDouble(s1);
            ShowMessageCosts messageCosts = new ShowMessageCosts((int) i.get("id"), (String) i.get("name"), receivesAll.size(), receivesSuccess.size(), v1);
            list.add(messageCosts);
        }
        PageInfo p = new PageInfo();//从新拼装分页
        p.setList(list);
        p.setPages(pageInfo.getPages());
        p.setTotal(pageInfo.getTotal());
        p.setPageNum(pageInfo.getPageNum());
        p.setPageSize(pageInfo.getPageSize());
        return p;
    }

    /**
     * 通话费用统计
     *
     * @param pageInfo
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public PageInfo selectTotalExpensesDetail(PageInfo<Map<String, Object>> pageInfo, String startTime, String endTime) {
        List<ShowCallCost> list = new ArrayList<>();
        if (pageInfo.getList().size() == 0) {
            return pageInfo;
        }
        //userList
        for (Map<String, Object> i : pageInfo.getList()) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", String.valueOf((int) i.get("id")));
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            String s = HttpclientUtils.doPostByJson(CALL_CENTER_BASE_URL + CALL_COST_URL, map);
            if (StringUtils.isBlank(s)) {
                //userId/用户名/坐席费用总计/通话总时长/通话时长费用总计/总计
                ShowCallCost ss = new ShowCallCost((int) i.get("id"), (String) i.get("name"), 0.00, 0, 0.00, 0.00);
                list.add(ss);
                continue;
            }
            Map map1 = JsonUtils.jsonToPojo(s, Map.class);
            double callCostTotal = 0.00;
            if (map1.get("callCostTotal") instanceof Integer) {
                int aaa = (int) map1.get("callCostTotal");
                callCostTotal = aaa;
            } else {
                callCostTotal = (double) map1.get("callCostTotal");//费用总计
            }
            int callTotalTime = (int) map1.get("callTotalTime");//通话总时长
            double seatCostTotal = 0.00;
            if (map1.get("seatCostTotal") instanceof Integer) {
                int aaa = (int) map1.get("seatCostTotal");
                seatCostTotal = aaa;
            } else {
                seatCostTotal = (double) map1.get("callCostTotal");//坐席费用总计
            }
            double total = 0.00;
            if (map1.get("total") instanceof Integer) {
                int aaa = (int) map1.get("total");
                total = aaa;
            } else {
                total = (double) map1.get("total");//总计
            }
            //userId/用户名/坐席费用总计/通话总时长/通话时长费用总计/总计
            ShowCallCost ss = new ShowCallCost((int) i.get("id"), (String) i.get("name"), seatCostTotal, callTotalTime, callCostTotal, total);
            list.add(ss);
        }
        PageInfo p = new PageInfo();//从新拼装分页
        p.setList(list);
        p.setPages(pageInfo.getPages());
        p.setTotal(pageInfo.getTotal());
        p.setPageNum(pageInfo.getPageNum());
        p.setPageSize(pageInfo.getPageSize());
        return p;
    }

    /**
     * 通话时长统计
     *
     * @param pageInfo
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public PageInfo selectTimeStatisticsDetail(PageInfo<Map<String, Object>> pageInfo, String startTime, String endTime) {
        List<ShowCallDuration> list = new ArrayList<>();
        if (pageInfo.getList().size() == 0) {
            return pageInfo;
        }
        //userList
        for (Map<String, Object> i : pageInfo.getList()) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", String.valueOf((int) i.get("id")));
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            String s = HttpclientUtils.doPostByJson(CALL_CENTER_BASE_URL + CALL_TIME_URL, map);
            if (StringUtils.isBlank(s)) {
                //userId/用户名/外呼总数/外呼成功数/外呼失败数/外呼成功率/转化率/通话总时长
                ShowCallDuration ss = new ShowCallDuration((int) i.get("id"), (String) i.get("name"), 0, 0, 0, 0.00, 0.00, 0);
                ss.setOutCallSuccessRateShow("0%");
                list.add(ss);
                continue;
            }
            Map map1 = JsonUtils.jsonToPojo(s, Map.class);
            int callTimeTotal = (int) map1.get("callTimeTotal");//呼叫时间总计
            int outBoundFailure = (int) map1.get("outBoundFailure");//外呼失败数
            String outBoundPercentage = "0%";
            double i1 = 0.00;
            int outBoundSuccess = (int) map1.get("outBoundSuccess");//外呼成功数
            int outBoundTotal = (int) map1.get("outBoundTotal");//外呼总数
            if ((int) map1.get("outBoundSuccess") != 0 && (int) map1.get("outBoundTotal") != 0) {
                outBoundPercentage = (String) map1.get("outBoundPercentage");//外呼成功率
                i1 = (double) outBoundTotal / (double) outBoundSuccess;//计算外呼成功率
            }
            List<BusinessTypeValueIdentify> idsss = smsSupplyService.selectSpecificIdListByUserId((int) i.get("id"));
            double v = 0.00;
            if (idsss.size() != 0) {
                List<String> iii = new ArrayList<>();
                for (BusinessTypeValueIdentify bb : idsss) {
                    iii.add(bb.getId());
                }
                BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                BusinessTypeFieldValueVarcharExample.Criteria criteria = businessTypeFieldValueVarcharExample.createCriteria();
                criteria.andIdentifyIdIn(iii);
                List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                if (idsss.size() != 0) {
                    int ii = 0;
                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
                        BusinessTypeFieldExample.Criteria criteria1 = businessTypeFieldExample.createCriteria();
                        criteria1.andIdEqualTo(b.getBusinessTypeFieldId());
                        criteria1.andFieldAliasEqualTo(DataEnumerate.TO_STORE);
                        List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
                        if (businessTypeFields.size() > 0) {
                            ii = ii + 1;
                        }
                    }
                    v = (double) ii / (double) idsss.size();
                }
            }
            //userId/用户名/外呼总数/外呼成功数/外呼失败数/外呼成功率/转化率/通话总时长
            ShowCallDuration ss = new ShowCallDuration((int) i.get("id"), (String) i.get("name"), outBoundTotal, outBoundSuccess, outBoundFailure, i1, v, callTimeTotal);
            ss.setOutCallSuccessRateShow(outBoundPercentage);
            list.add(ss);
        }
        PageInfo p = new PageInfo();//从新拼装分页
        p.setList(list);
        p.setPages(pageInfo.getPages());
        p.setTotal(pageInfo.getTotal());
        p.setPageNum(pageInfo.getPageNum());
        p.setPageSize(pageInfo.getPageSize());
        return p;
    }

    /**
     * 短信费用统计 总计
     *
     * @param pageInfo
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<ShowMessageCosts> selectMessageUserDetailTotal(List<Map> pageInfo, String startTime, String endTime) {
        List<ShowMessageCosts> list = new ArrayList<>();
        if (pageInfo.size() == 0 || pageInfo == null) {
            return new ArrayList<>();
        }
        //userList
        for (Map<String, Object> i : pageInfo) {
            SendMessage s = new SendMessage();
            SendMessageExample sendMessageExample = new SendMessageExample();
            SendMessageExample.Criteria criteria = sendMessageExample.createCriteria();
            criteria.andSendUseridEqualTo((int) i.get("id"));
            try {//添加时间限制
                if (StringUtils.isNoneBlank(startTime)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date startDate = sdf.parse(startTime+" 00:00:00");
                    Date endDate = sdf.parse(endTime+" 23:59:59");
                    criteria.andSendTimeBetween(startDate, endDate);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<SendMessage> messageList = sendMessageMapper.selectByExample(sendMessageExample);
            if (messageList.size() == 0) {//没有短信
                ShowMessageCosts messageCosts = new ShowMessageCosts((int) i.get("id"), (String) i.get("name"), 0, 0, 0.00);
                list.add(messageCosts);
                continue;
            }
            List<Integer> iii = new ArrayList<>();
            for (SendMessage ii : messageList) {
                iii.add(ii.getId());
            }
            SendReceiveExample sendReceiveExampleAll = new SendReceiveExample();
            SendReceiveExample.Criteria criteriaAll = sendReceiveExampleAll.createCriteria();
            criteriaAll.andSendIdIn(iii);
            List<SendReceive> receivesAll = sendReceiveMapper.selectByExample(sendReceiveExampleAll);
            SendReceiveExample sendReceiveExampleSuccess = new SendReceiveExample();
            SendReceiveExample.Criteria criteriaSuccess = sendReceiveExampleSuccess.createCriteria();
            criteriaSuccess.andSendIdIn(iii);
            criteriaSuccess.andStatusEqualTo(DataEnumerate.INT_NUM_ONE);
            List<SendReceive> receivesSuccess = sendReceiveMapper.selectByExample(sendReceiveExampleSuccess);
            double v = DataEnumerate.DOUBLE_COST * receivesAll.size();
            BigDecimal a1 = new BigDecimal(Double.toString(DataEnumerate.DOUBLE_COST));
            BigDecimal b1 = new BigDecimal(Double.toString(receivesAll.size()));
            BigDecimal multiply = a1.multiply(b1);
            String s1 = multiply.toString();
            double v1 = Double.parseDouble(s1);
            ShowMessageCosts messageCosts = new ShowMessageCosts((int) i.get("id"), (String) i.get("name"), receivesAll.size(), receivesSuccess.size(), v1);
            list.add(messageCosts);
        }
        return list;
    }

    /**
     * 通话费用统计 总计
     *
     * @param pageInfo
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<ShowCallCost> selectTotalExpensesDetailTotal(List<Map> pageInfo, String startTime, String endTime) {
        List<ShowCallCost> list = new ArrayList<>();
        if (pageInfo.size() == 0 || pageInfo == null) {
            return new ArrayList<>();
        }
        //userList
        for (Map<String, Object> i : pageInfo) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", String.valueOf((int) i.get("id")));
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            String s = HttpclientUtils.doPostByJson(CALL_CENTER_BASE_URL + CALL_COST_URL, map);
            if (StringUtils.isBlank(s)) {
                continue;
            }
            Map map1 = JsonUtils.jsonToPojo(s, Map.class);
            double callCostTotal = 0.00;
            if (map1.get("callCostTotal") instanceof Integer) {
                int aaa = (int) map1.get("callCostTotal");
                callCostTotal = aaa;
            } else {
                callCostTotal = (double) map1.get("callCostTotal");//费用总计
            }
            int callTotalTime = (int) map1.get("callTotalTime");//通话总时长
            double seatCostTotal = 0.00;
            if (map1.get("seatCostTotal") instanceof Integer) {
                int aaa = (int) map1.get("seatCostTotal");
                seatCostTotal = aaa;
            } else {
                seatCostTotal = (double) map1.get("callCostTotal");//坐席费用总计
            }
            double total = 0.00;
            if (map1.get("total") instanceof Integer) {
                int aaa = (int) map1.get("total");
                total = aaa;
            } else {
                total = (double) map1.get("total");//总计
            }
            //userId/用户名/坐席费用总计/通话总时长/通话时长费用总计/总计
            ShowCallCost ss = new ShowCallCost((int) i.get("id"), (String) i.get("name"), seatCostTotal, callTotalTime, callCostTotal, total);
            list.add(ss);
        }
        return list;
    }

    /**
     * 通话时长统计 总计
     *
     * @param pageInfo
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<ShowCallDuration> selectTimeStatisticsDetailTotal(List<Map> pageInfo, String startTime, String endTime) {
        List<ShowCallDuration> list = new ArrayList<>();
        if (pageInfo.size() == 0) {
            return new ArrayList<>();
        }
        //userList
        for (Map<String, Object> i : pageInfo) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", String.valueOf((int) i.get("id")));
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            String s = HttpclientUtils.doPostByJson(CALL_CENTER_BASE_URL + CALL_TIME_URL, map);
            if (StringUtils.isBlank(s)) {
                continue;
            }
            Map map1 = JsonUtils.jsonToPojo(s, Map.class);
            int callTimeTotal = (int) map1.get("callTimeTotal");//呼叫时间总计
            int outBoundFailure = (int) map1.get("outBoundFailure");//外呼失败数
            String outBoundPercentage = "0%";
            double i1 = 0.00;
            int outBoundSuccess = (int) map1.get("outBoundSuccess");//外呼成功数
            int outBoundTotal = (int) map1.get("outBoundTotal");//外呼总数
            if ((int) map1.get("outBoundSuccess") != 0 && (int) map1.get("outBoundTotal") != 0) {
                outBoundPercentage = (String) map1.get("outBoundPercentage");//外呼成功率
                i1 = (double) outBoundTotal / (double) outBoundSuccess;//计算外呼成功率
            }
            List<BusinessTypeValueIdentify> idsss = smsSupplyService.selectSpecificIdListByUserId((int) i.get("id"));
            double v = 0.00;
            if (idsss.size() != 0) {
                List<String> iii = new ArrayList<>();
                for (BusinessTypeValueIdentify bb : idsss) {
                    iii.add(bb.getId());
                }
                BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                BusinessTypeFieldValueVarcharExample.Criteria criteria = businessTypeFieldValueVarcharExample.createCriteria();
                criteria.andIdentifyIdIn(iii);
                List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                if (idsss.size() != 0) {
                    int ii = 0;
                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
                        BusinessTypeFieldExample.Criteria criteria1 = businessTypeFieldExample.createCriteria();
                        criteria1.andIdEqualTo(b.getBusinessTypeFieldId());
                        criteria1.andFieldAliasEqualTo(DataEnumerate.TO_STORE);
                        List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
                        if (businessTypeFields.size() > 0) {
                            ii = ii + 1;
                        }
                    }
                    v = (double) ii / (double) idsss.size();
                }
            }
            //userId/用户名/外呼总数/外呼成功数/外呼失败数/外呼成功率/转化率/通话总时长
            ShowCallDuration ss = new ShowCallDuration((int) i.get("id"), (String) i.get("name"), outBoundTotal, outBoundSuccess, outBoundFailure, i1, v, callTimeTotal);
            ss.setOutCallSuccessRateShow(outBoundPercentage);
            list.add(ss);
        }
        return list;
    }

    /**
     * 通过消息id获取唯一标识列表
     *
     * @param messageId
     * @return
     */
    @Override
    public List<String> getidentifyIds(Integer messageId) {
        SendReceiveExample example = new SendReceiveExample();
        SendReceiveExample.Criteria criteria = example.createCriteria();
        criteria.andSendIdEqualTo(messageId);
        List<SendReceive> sendReceives = sendReceiveMapper.selectByExample(example);
        List<String> i = new ArrayList<>();
        for (SendReceive s : sendReceives) {
            i.add(s.getClueId());
        }
        return i;
    }

    public Double getConversionRateByUserId(Integer userId) {
        List<BusinessTypeValueIdentify> identifyClassListByUserId = smsSupplyService.selectSpecificIdListByUserId(userId);//查询唯一标识类集合
        double conversionRate = 0.00;
        if (identifyClassListByUserId.size() != 0) {
            List<String> identifyListByUserId = new ArrayList<>();//唯一标识集合
            for (BusinessTypeValueIdentify b : identifyClassListByUserId) {
                identifyListByUserId.add(b.getId());
            }
            BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
            BusinessTypeFieldValueVarcharExample.Criteria criteria = businessTypeFieldValueVarcharExample.createCriteria();
            criteria.andIdentifyIdIn(identifyListByUserId);
            List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.
                    selectByExample(businessTypeFieldValueVarcharExample);//通过唯一标识查varchar表
            int i = 0;//指向门店的数目
            for (BusinessTypeFieldValueVarchar bb : businessTypeFieldValueVarchars) {
                BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
                BusinessTypeFieldExample.Criteria criteria1 = businessTypeFieldExample.createCriteria();
                criteria1.andIdEqualTo(bb.getBusinessTypeFieldId());
                criteria1.andFieldAliasEqualTo(DataEnumerate.TO_STORE);//限制指向门店
                List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
                if (businessTypeFields.size() != 0) {
                    i = i + 1;
                }
            }
            conversionRate = ((double) i / (double) identifyListByUserId.size())*100;
        }
        return conversionRate;
    }

    private Double getConversionRateByUserIdOnly(Integer userId) {
        List<BusinessTypeValueIdentify> identifyClassListByUserId = smsSupplyService.selectSpecificIdListByUserId(userId);//查询唯一标识类集合
        double conversionRate = 0.00;
        if (identifyClassListByUserId.size() != 0) {
            List<String> identifyListByUserId = new ArrayList<>();//唯一标识集合
            for (BusinessTypeValueIdentify b : identifyClassListByUserId) {
                identifyListByUserId.add(b.getId());
            }
            BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
            BusinessTypeFieldValueVarcharExample.Criteria criteria = businessTypeFieldValueVarcharExample.createCriteria();
            criteria.andIdentifyIdIn(identifyListByUserId);
            List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.
                    selectByExample(businessTypeFieldValueVarcharExample);//通过唯一标识查varchar表
            int i = 0;//指向门店的数目
            for (BusinessTypeFieldValueVarchar bb : businessTypeFieldValueVarchars) {
                BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
                BusinessTypeFieldExample.Criteria criteria1 = businessTypeFieldExample.createCriteria();
                criteria1.andIdEqualTo(bb.getBusinessTypeFieldId());
                criteria1.andFieldAliasEqualTo(DataEnumerate.TO_STORE);//限制指向门店
                List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
                if (businessTypeFields.size() != 0) {
                    i = i + 1;
                }
            }
            conversionRate = ((double) i / (double) identifyListByUserId.size())*100;
        }
        return conversionRate;
    }

    public Double getAllConversionRateByUserId(List<Integer> userIdList) {
        double allConversionRate = 0.00;
        for (Integer i : userIdList) {
            Double conversionRateByUserId = getConversionRateByUserIdOnly(i);
            allConversionRate = allConversionRate + conversionRateByUserId;
        }
        return (allConversionRate/userIdList.size());
    }
}
