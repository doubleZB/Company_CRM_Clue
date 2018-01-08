package com.sunll.quartz.service.sms;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunll.clue.api.smsSupply.SmsSupplyService;
import com.sunll.clue.entity.business.BusinessTypeValueIdentify;
import com.sunll.quartz.dao.sms.SmsMapper;
import com.sunll.quartz.entity.sms.Sms;
import com.sunll.quartz.entity.sms.SmsExample;
import com.sunll.common.util.DataEnumerate;
import com.sunll.common.util.DateUtil;
import com.sunll.common.util.Result;
import com.sunll.quartz.api.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 */
@Service
public class SmsServiceImpl implements SmsService{

    @Autowired
    SmsMapper smsMapper;
    @Autowired
    SmsSupplyService smsSupplyService;

    /**
     * 查询指定时间内的消息
     * @param userId    用户id
     * @return          指定时间消息列表
     */
    public Result smsShow(Integer userId){
        List<BusinessTypeValueIdentify> list = smsSupplyService.selectSpecificIdListByUserId(userId);//唯一标识中间表list
        if(list.size() == 0 || list == null){//没有唯一标识集合
            return Result.build(200,"暂未有消息",null);
        }
        List<String> specificIdList = new ArrayList<>();//唯一标识list
        for (BusinessTypeValueIdentify b:list) {
            specificIdList.add(b.getId());
        }
        List<Sms> smsesReturn = new ArrayList<>();//返回值list
        try {
            String startTime = DateUtil.getTimeByMinute(15);//开始时间 15分钟后
            String endTime = DateUtil.getTimeByMinute(20);//结束时间 20分钟后
            System.out.println(startTime);
            System.out.println(endTime);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate=sdf.parse(startTime);
            Date endDate=sdf.parse(endTime);
            SmsExample smsExample = new SmsExample();
            SmsExample.Criteria criteria = smsExample.createCriteria();
            criteria.andSpecificIdIn(specificIdList);//限制为本人
            criteria.andSmsDateBetween(startDate,endDate);//限制时间
            criteria.andShowStatusEqualTo(DataEnumerate.STRING_NUM_ONE);//限制未展示的
            smsesReturn = smsMapper.selectByExample(smsExample);//本人的未展示的十五分钟后消息
            for (Sms s:smsesReturn) {
                s.setSmsDateShow(DateUtil.Date2StringZZZ(s.getSmsDate()));
                //更新数据库状态
//                s.setShowStatus(DataEnumerate.STRING_NUM_TWO);//弹出不修改状态
                SmsExample smsExampleU = new SmsExample();
                SmsExample.Criteria criteriaU = smsExampleU.createCriteria();
                criteriaU.andIdEqualTo(s.getId());
                smsMapper.updateByExampleSelective(s,smsExampleU);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.build(200,"消息轮训查询成功",smsesReturn);
    }

    /**
     * 显示所有本人消息
     * @param userId    用户id
     * @return          本人消息列表
     */
    @Override
    public Result smsAllShow(Integer userId) {
        List<BusinessTypeValueIdentify> list = smsSupplyService.selectSpecificIdListByUserId(userId);//唯一标识中间表list
        if(list.size() == 0 || list == null){//没有唯一标识集合
            return Result.build(200,"暂未有消息",null);
        }
        List<String> specificIdList = new ArrayList<>();//唯一标识list
        for (BusinessTypeValueIdentify b:list) {
            specificIdList.add(b.getId());
        }
        List<Sms> smsesReturn = new ArrayList<>();//返回值list
        try {
            SmsExample smsExample = new SmsExample();
            SmsExample.Criteria criteria = smsExample.createCriteria();
            criteria.andSpecificIdIn(specificIdList);
            smsesReturn = smsMapper.selectByExample(smsExample);
            for (Sms s:smsesReturn) {
                s.setSmsDateShow(DateUtil.Date2StringZZZ(s.getSmsDate()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.build(200,"所有消息轮训查询成功",smsesReturn);
    }

    /**
     * 所有消息设为已读
     * @param userId    用户id
     * @return          设为已读消息列表
     */
    @Override
    public Result smsAllupdate(Integer userId) {
        List<BusinessTypeValueIdentify> list = smsSupplyService.selectSpecificIdListByUserId(userId);//唯一标识中间表list
        if(list.size() == 0 || list == null){//没有唯一标识集合
            return Result.build(200,"暂未有消息",null);
        }
        List<String> specificIdList = new ArrayList<>();//唯一标识list
        for (BusinessTypeValueIdentify b:list) {
            specificIdList.add(b.getId());
        }
        List<Sms> smsesReturn = new ArrayList<>();//返回值list
        try {
            SmsExample smsExample = new SmsExample();
            SmsExample.Criteria criteria = smsExample.createCriteria();
            criteria.andSpecificIdIn(specificIdList);//限制为本人
            criteria.andShowStatusEqualTo(DataEnumerate.STRING_NUM_ONE);//限制未展示的
            smsesReturn = smsMapper.selectByExample(smsExample);
            for (Sms s:smsesReturn) {
                s.setSmsDateShow(DateUtil.Date2StringZZZ(s.getSmsDate()));
                //更新数据库状态
                s.setShowStatus(DataEnumerate.STRING_NUM_TWO);
                SmsExample smsExampleU = new SmsExample();
                SmsExample.Criteria criteriaU = smsExampleU.createCriteria();
                criteriaU.andIdEqualTo(s.getId());
                smsMapper.updateByExampleSelective(s,smsExampleU);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.build(200,"消息轮训更新成功",smsesReturn);
    }

    /**
     * 更新一一条消息的状态
     * @param mmsId 消息id
     * @return      null
     */
    @Override
    public Result updateMmsOne( Integer mmsId) {
        Sms s = new Sms();
        //更新数据库状态
        s.setShowStatus(DataEnumerate.STRING_NUM_TWO);
        SmsExample smsExampleU = new SmsExample();
        SmsExample.Criteria criteriaU = smsExampleU.createCriteria();
        criteriaU.andIdEqualTo(mmsId);
        smsMapper.updateByExampleSelective(s,smsExampleU);
        return Result.build(200,"消息轮训更新成功",null);
    }

    /**
     * 插入消息             :clue经常注入失败，已提到clue模块下
     * @param name         来源名称
     * @param date         跟进时间
     * @param businessId   来源类型id
     * @param specificId   来源id
     * @param smsText      消息内容 不传为 您的"+s1+"《"+name+"》需要您跟进。
     * @return             1
     */
    @Override
    public int insertSms(String name,Date date,Integer businessId,String specificId,String smsText,Integer businessTypeId) {
        String s = smsText;
        if(s ==null){
            String s1 = smsSupplyService.selectbusinessNameBybusinessId(businessId);//查询来源名称
            s = "您的"+s1+"《"+name+"》需要您跟进。";//插入消息内容 提示或者发短信的时候拼装时间
        }
        Sms sms = new Sms();//新建一条消息
        sms.setSmsText(s);
        sms.setSmsDate(date);
        sms.setBusinessId(businessId);
        sms.setSpecificId(specificId);
        sms.setTypeId(businessTypeId);
        sms.setShowStatus(DataEnumerate.STRING_NUM_ONE);//未展示
        sms.setSmsStatus(DataEnumerate.STRING_NUM_ONE);//未发送短信
        int i = smsMapper.insertSelective(sms);
        return i;
    }

    /**
     * 修改消息
     * @param name         来源名称
     * @param date         跟进时间
     * @param businessId   来源类型id
     * @param specificId   来源id
     * @param smsText      消息内容 不传为 您的"+s1+"《"+name+"》需要您跟进。
     * @return              1
     */
    @Override
    public int updateSms(String name,Date date,Integer businessId,String specificId,String smsText) {
        String s = smsText;
        if(s ==null){
            String s1 = smsSupplyService.selectbusinessNameBybusinessId(businessId);//查询来源名称
            s = "您的"+s1+"《"+name+"》需要您跟进。";//插入消息内容 提示或者发短信的时候拼装时间
        }
        Sms sms = new Sms();//新建一条消息
        sms.setSmsText(s);
        sms.setSmsDate(date);
        sms.setBusinessId(businessId);
        sms.setSpecificId(specificId);
        sms.setShowStatus(DataEnumerate.STRING_NUM_ONE);//未展示
        sms.setSmsStatus(DataEnumerate.STRING_NUM_ONE);//未发送短信
        SmsExample smsExample = new SmsExample();//创建修改条件 根据specificId 修改
        SmsExample.Criteria criteria = smsExample.createCriteria();
        criteria.andSpecificIdEqualTo(specificId);
        int i = smsMapper.updateByExampleSelective(sms, smsExample);
        return i;
    }

    /**
     * 根据状态查询列表
     * @param userId    用户id
     * @param type      读取状态 1未读 2已读
     * @return          消息列表
     */
    @Override
    public Result getSmsAllShowByType(Integer userId, String type) {
        List<BusinessTypeValueIdentify> list = smsSupplyService.selectSpecificIdListByUserId(userId);//唯一标识中间表list
        List<String> specificIdList = new ArrayList<>();//唯一标识list
        for (BusinessTypeValueIdentify b:list) {
            specificIdList.add(b.getId());
        }
        List<Sms> smsesReturn = new ArrayList<>();//返回值list
        try {
            SmsExample smsExample = new SmsExample();
            SmsExample.Criteria criteria = smsExample.createCriteria();
            criteria.andSpecificIdIn(specificIdList);
            criteria.andShowStatusEqualTo(type);//添加查询状态
            smsesReturn = smsMapper.selectByExample(smsExample);
            for (Sms s:smsesReturn) {
                s.setSmsDateShow(DateUtil.Date2StringZZZ(s.getSmsDate()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.build(200,"所有消息轮训查询成功",smsesReturn);
    }

    /**
     * 根据状态查询列表App
     * @param userId    用户id
     * @param type      读取状态 1未读 2已读
     * @return          消息列表
     */
    @Override
    public PageInfo<Sms> getSmsAllShowByTypeApp(Integer userId, String type,String appPageSizeStr,String appPageNumberStr) {
        List<BusinessTypeValueIdentify> list = smsSupplyService.selectSpecificIdListByUserId(userId);//唯一标识中间表list
        List<String> specificIdList = new ArrayList<>();//唯一标识list
        for (BusinessTypeValueIdentify b:list) {
            specificIdList.add(b.getId());
        }
        List<Sms> smsesReturn = new ArrayList<>();//返回值list
        try {
            SmsExample smsExample = new SmsExample();
            SmsExample.Criteria criteria = smsExample.createCriteria();
            criteria.andSpecificIdIn(specificIdList);
            criteria.andShowStatusEqualTo(type);//添加查询状态
            int pageNumber = Integer.parseInt(appPageNumberStr);
            int pageSize = Integer.parseInt(appPageSizeStr);
            PageHelper.startPage(pageNumber, pageSize);
            smsesReturn = smsMapper.selectByExample(smsExample);
            PageInfo<Sms> pageInfo = new PageInfo<>(smsesReturn);
            if(pageInfo.getPages()<pageNumber){
                return null;
            }
            for (Sms s:smsesReturn) {
                s.setSmsDateShow(DateUtil.Date2StringZZZ(s.getSmsDate()));
            }
            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 展示未读消息数目
     * @param userId    用户id
     * @return          未读消息数目
     */
    @Override
    public Result getSmsAllShowNum(Integer userId) {
        List<BusinessTypeValueIdentify> list = smsSupplyService.selectSpecificIdListByUserId(userId);//唯一标识中间表list
        List<String> specificIdList = new ArrayList<>();//唯一标识list
        for (BusinessTypeValueIdentify b:list) {
            specificIdList.add(b.getId());
        }
        List<Sms> smsesReturn = new ArrayList<>();//返回值list
        try {
            SmsExample smsExample = new SmsExample();
            SmsExample.Criteria criteria = smsExample.createCriteria();
            criteria.andSpecificIdIn(specificIdList);
            criteria.andShowStatusEqualTo(DataEnumerate.STRING_NUM_ONE);//添加查询状态
            smsesReturn = smsMapper.selectByExample(smsExample);
            if(smsesReturn.size()<1){
                return Result.build(200,"所有消息轮训查询成功",0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.build(200,"所有消息轮训查询成功",smsesReturn.size());
    }
}
