package com.sunll.quartz.dao.sms;

import com.sunll.quartz.entity.sms.Sms;
import com.sunll.quartz.entity.sms.SmsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsMapper {
    long countByExample(SmsExample example);

    int deleteByExample(SmsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sms record);

    int insertSelective(Sms record);

    List<Sms> selectByExample(SmsExample example);

    Sms selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sms record, @Param("example") SmsExample example);

    int updateByExample(@Param("record") Sms record, @Param("example") SmsExample example);

    int updateByPrimaryKeySelective(Sms record);

    int updateByPrimaryKey(Sms record);
}