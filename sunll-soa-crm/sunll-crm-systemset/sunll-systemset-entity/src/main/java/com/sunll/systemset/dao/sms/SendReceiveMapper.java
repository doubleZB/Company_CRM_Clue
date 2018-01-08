package com.sunll.systemset.dao.sms;

import com.sunll.systemset.entity.sms.SendReceive;
import com.sunll.systemset.entity.sms.SendReceiveExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SendReceiveMapper {
    long countByExample(SendReceiveExample example);

    int deleteByExample(SendReceiveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SendReceive record);

    int insertSelective(SendReceive record);

    List<SendReceive> selectByExample(SendReceiveExample example);

    SendReceive selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SendReceive record, @Param("example") SendReceiveExample example);

    int updateByExample(@Param("record") SendReceive record, @Param("example") SendReceiveExample example);

    int updateByPrimaryKeySelective(SendReceive record);

    int updateByPrimaryKey(SendReceive record);
}