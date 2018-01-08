package com.sunll.quartz.service.quartz;
import com.alibaba.fastjson.JSONObject;
import com.sunll.clue.api.smsSupply.SmsSupplyService;
import com.sunll.common.util.*;
import com.sunll.quartz.api.quartz.JobDetailService;
import com.sunll.quartz.dao.sms.SmsMapper;
import com.sunll.quartz.entity.sms.Sms;
import com.sunll.quartz.entity.sms.SmsExample;
import com.sunll.systemset.api.unitAccount.UnitAccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/6.
 */
@Service
public class JobDetailServiceImpl implements JobDetailService{
    private Logger log = Logger.getLogger(this.getClass());
    private static final String SMS_SIGN = (String) PropertiesUtils.
            loadProperties("config.properties").get("smsSign");
    private static final String SSM_SIGN = (String) PropertiesUtils.
            loadProperties("config.properties").get("SSM_SIGN");
    private static final String UNITE_ACCOUNT_BASE_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("UNITE_ACCOUNT_BASE_URL");
    private static final String GET_IPHONE_USERID_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("GET_IPHONE_USERID_URL");
    private static final String JPUSH_APP_KEY = (String) PropertiesUtils.
            loadProperties("config.properties").get("JPUSH_APP_KEY");
    private static final String JPUSH_SECRET_ID = (String) PropertiesUtils.
            loadProperties("config.properties").get("JPUSH_SECRET_ID");
    @Autowired
    SmsMapper smsMapper;
    @Autowired
    SmsSupplyService smsService;
    @Autowired
    UnitAccountService unitAccountService;
    @Override
    public void getMsg() {
        try {
            String startTime = DateUtil.getTimeByMinute(15);
            String endTime = DateUtil.getTimeByMinute(20);
            System.out.println("开始时间："+startTime+"结束时间："+endTime);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate=sdf.parse(startTime);
            Date endDate=sdf.parse(endTime);
            SmsExample smsExample = new SmsExample();
            SmsExample.Criteria criteria = smsExample.createCriteria();
            criteria.andSmsDateBetween(startDate,endDate);
            criteria.andSmsStatusEqualTo(DataEnumerate.STRING_NUM_ONE);
            List<Sms> smses = smsMapper.selectByExample(smsExample);
            if(smses.size() != 0){
                //展示状态1未发送 2 已发送
                for (Sms s:smses) {
                    if((DataEnumerate.STRING_NUM_ONE).equals(s.getSmsStatus())){
                        Integer userId = smsService.selectUserIdBySpecificId(s.getSpecificId());
                        Map<String,Object> map = new HashMap<>();
                        map.put("userId",String.valueOf(userId));
                        String mibleJson = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL+GET_IPHONE_USERID_URL, map);
                        Date smsDate = s.getSmsDate();
                        SimpleDateFormat timeSdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String format = timeSdf.format(smsDate);
                        System.out.println("---------------------手机号码："+mibleJson+",发送短信提醒，进行极光推送---------------------");
                        String userbyUserId = unitAccountService.getUserbyUserId(userId);
                        Map userMap = JsonUtils.jsonToPojo(userbyUserId, Map.class);
                        String eid = "";
                        if(userMap.get("companyId") instanceof Integer){
                            eid = String.valueOf((int)userMap.get("companyId"));
                        }else if (userMap.get("companyId") instanceof String){
                            eid = (String)userMap.get("companyId");
                        }

                        try {
                            String sms = SmsUtil.SendSms(mibleJson, "【聚通达·商户平台】"+s.getSmsText()+",跟进时间:"+format+".");
                            jPush(String.valueOf(userId),eid,String.valueOf(s.getId()),s.getSmsText()+",跟进时间:"+format+".",DataEnumerate.JPUSH_TYPE.MESSAGE_TYPE);
                            if(sms != null){
                                //更新数据库状态
                                s.setSmsStatus(DataEnumerate.STRING_NUM_TWO);
                                SmsExample smsExampleU = new SmsExample();
                                SmsExample.Criteria criteriaU = smsExampleU.createCriteria();
                                criteriaU.andIdEqualTo(s.getId());
                                smsMapper.updateByExampleSelective(s,smsExampleU);
                            }else {
                                log.error("消息提醒短信发送失败");
                            }
                        } catch (Exception e) {
                            log.error("消息提醒短信发送,极光推送异常");
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("消息提醒短信发送,极光推送异常");
        }
    }

    /**
     * 任务推送
     *
     * @param uid       接收者ID
     * @param eid       企业ID
     * @param id        ID
     * @param idType    ID类型
     * @param alert     推送内容
     */
    private void jPush(String uid, String eid, String id, String alert,String idType) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", uid);
        jsonObject.put("eid", eid);
        jsonObject.put("id", id);
        jsonObject.put("idType", idType);
        try {
           // JpushClientUtil.sendToAll(JPUSH_APP_KEY, JPUSH_SECRET_ID, uid + "_" + eid, alert, alert, jsonObject.toJSONString());
        } catch (Exception e) {
            log.error("极光推送失败,失败原因:" + e.getMessage());
        }
    }
}
