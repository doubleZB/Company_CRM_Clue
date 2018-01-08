package com.sunll.clue.service.smsSupply;

import com.sunll.clue.api.smsSupply.SmsSupplyService;
import com.sunll.clue.dao.follow.FollowRecordMapper;
import com.sunll.common.util.DataEnumerate;
import com.sunll.clue.entity.message.Sms;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/22.
 */
@Service
public class InsertSmsZ {
    @Autowired
    private FollowRecordMapper followRecordMapper;
    @Autowired
    SmsSupplyService smsSupplyService;

    /**
     *
     * @param name           线索名称 没有传null
     * @param date           跟进日期
     * @param businessId     业务id
     * @param specificId     唯一标识
     * @param smsText        跟进提示内容
     * @param businessTypeId 业务类型id
     * @return
     */
    public int insertSms1(String name, Date date, Integer businessId, String specificId, String smsText,Integer businessTypeId) {
        String s = smsText;
        if(s ==null){
            String s1 = smsSupplyService.selectbusinessNameBybusinessId(businessId);//查询来源名称
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
        int i = followRecordMapper.insertSmsSelective(sms);
        return i;
    }
}
