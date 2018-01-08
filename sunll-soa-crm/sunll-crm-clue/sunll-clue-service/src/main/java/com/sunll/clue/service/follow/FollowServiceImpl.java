package com.sunll.clue.service.follow;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunll.clue.api.follow.FollowService;
import com.sunll.clue.api.smsSupply.SmsSupplyService;
import com.sunll.clue.dao.business.BusinessTypeMapper;
import com.sunll.clue.dao.follow.FollowRecordMapper;
import com.sunll.clue.entity.business.BusinessType;
import com.sunll.clue.entity.business.BusinessTypeExample;
import com.sunll.clue.entity.follow.FollowRecord;
import com.sunll.clue.entity.follow.FollowRecordTimeline;
import com.sunll.clue.entity.message.Sms;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.common.util.DataEnumerate;
import com.sunll.common.util.HttpclientUtils;
import com.sunll.common.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator
 * on 2017/11/30
 */
@Service
public class FollowServiceImpl implements FollowService {

    private Logger log = Logger.getLogger(this.getClass());

    private static final String UNITE_ACCOUNT_BASE_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("UNITE_ACCOUNT_BASE_URL");
    private static final String GET_USER = (String) PropertiesUtils.
            loadProperties("config.properties").get("GET_USER");

    @Autowired
    private FollowRecordMapper followRecordMapper;
    @Autowired
    BusinessTypeMapper businessTypeMapper;
    @Override
    public ApiSendData insertFollowRecord(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型id
        Integer businessTypeId = Integer.valueOf(data.get("businessTypeId").toString());
        //获取业务类型
        FollowRecord followRecord = JSON.parseObject(JSON.toJSONString(data.get("followRecord")), FollowRecord.class);
        followRecord.setIsDel(1);
        followRecord.setCreateTime(new Date());
        if(followRecord.getFollowNextTime() != null){
            Date aaa = followRecord.getFollowNextTime();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(aaa);
            try {
                Date parse = sdf.parse(format);
                followRecord.setFollowNextTime(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        int num = followRecordMapper.insertSelective(followRecord);
        Date followNextTime = followRecord.getFollowNextTime();
        System.out.println(followNextTime);
        if(followRecord.getFollowNextTime() != null){
            insertSms("",followRecord.getFollowNextTime(),
                    followRecord.getFollowSourceType(),followRecord.getFollowSourceId(),
                    followRecord.getFollowReminderContent(),businessTypeId);
        }
        if(num>0){
            apiSendData.setData(new JSONObject());
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        }else {
            apiSendData.setData(new JSONObject());
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData updateFollowRecord(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        return null;
    }

    @Override
    public ApiSendData selectFollowRecord(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取跟进记录的id
        String followSourceId = (String) data.get("followSourceId");
        List<String> createTimeList = followRecordMapper.selectCreateTimeGroupBy(followSourceId);
        List<FollowRecordTimeline> followRecordTimelineList = new ArrayList<>();
        int total = 0;
        for(String createTime:createTimeList){
            FollowRecordTimeline followRecordTimeline = new FollowRecordTimeline();
            followRecordTimeline.setYear(createTime.substring(0,4));
            followRecordTimeline.setMonthAndDay(createTime.substring(5));
            //获取创建时间下的跟进记录
            Map<String,Object> map = new HashMap<>();
            map.put("followSourceId",followSourceId);
            map.put("createTime",createTime);
            List<FollowRecord> followRecordList = followRecordMapper.selectFollowRecordList(map);
            followRecordTimeline.setTotal(followRecordList.size());
            total = total+followRecordList.size();
            for(FollowRecord followRecord:followRecordList){
                //获取时间时分
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                String showCreateTime = simpleDateFormat.format(followRecord.getCreateTime());
                followRecord.setShowCreateTime(showCreateTime);
                //获取创建人姓名
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("userId",followRecord.getCreateUserId());
                String userDate = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + GET_USER, userMap);
                JSONObject user = JSON.parseObject(userDate);
                if (user.get("name") != null){
                    String createName = (String) user.get("name");
                    followRecord.setCreateName(createName);
                }else {
                    followRecord.setCreateName("");
                }

            }
            followRecordTimeline.setFollowRecordList(followRecordList);
            followRecordTimelineList.add(followRecordTimeline);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("followRecordTimelineList",followRecordTimelineList);
        apiSendData.setData(map);
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        return apiSendData;
    }

    @Override
    public ApiSendData delFollowRecord(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取跟进记录的id
        Integer followRecordId = (Integer) data.get("followRecordId");
        FollowRecord followRecord = new FollowRecord();
        followRecord.setIsDel(2);
        followRecord.setId(followRecordId);
        int num = followRecordMapper.updateByPrimaryKeySelective(followRecord);
        if(num>0){
            apiSendData.setData(new JSONObject());
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        }else {
            apiSendData.setData(new JSONObject());
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }
    private void insertSms(String name, Date date, Integer businessId, String specificId, String smsText,Integer businessTypeId) {
        String s = smsText;
        if(StringUtils.isBlank(s)){
            BusinessTypeExample businessTypeExample = new BusinessTypeExample();
            BusinessTypeExample.Criteria criteria = businessTypeExample.createCriteria();
            criteria.andBusinessIdEqualTo(businessId);
            List<BusinessType> types = businessTypeMapper.selectByExample(businessTypeExample);
            BusinessType businessType = types.get(0);
            String s1 =  businessType.getName();
            if(StringUtils.isNoneBlank(name)){
                s = "您的"+s1+"《"+name+"》需要跟进";//插入消息内容 提示或者发短信的时候拼装时间
            }else {
                s = "您的"+s1+"需要跟进";//插入消息内容 提示或者发短信的时候拼装时间
            }
        }
        Sms sms = new Sms();//新建一条消息
        sms.setSmsText(s);
        sms.setSmsDate(date);
        sms.setBusinessId(businessId);
        sms.setSpecificId(specificId);
        sms.setTypeId(businessTypeId);
        sms.setShowStatus(DataEnumerate.STRING_NUM_ONE);//未展示
        sms.setSmsStatus(DataEnumerate.STRING_NUM_ONE);//未发送短信
        followRecordMapper.insertSmsSelective(sms);
    }
}
