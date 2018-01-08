package com.sunll.quartz.api.sms;

import com.github.pagehelper.PageInfo;
import com.sunll.common.util.Result;
import com.sunll.quartz.entity.sms.Sms;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/7.
 */
public interface SmsService {
    Result smsShow(Integer userId);
    Result smsAllShow(Integer userId);
    Result smsAllupdate(Integer userId);
    Result updateMmsOne(Integer mmsId);
    int insertSms(String name,Date date,Integer businessId,String specificId,String smsText,Integer businessTypeId);
    int updateSms(String name,Date date,Integer businessId,String specificId,String smsText);
    Result getSmsAllShowByType(Integer userId,String type);
    PageInfo<Sms> getSmsAllShowByTypeApp(Integer userId, String type, String appPageSizeStr, String appPageNumberStr);
    Result getSmsAllShowNum(Integer userId);
}
