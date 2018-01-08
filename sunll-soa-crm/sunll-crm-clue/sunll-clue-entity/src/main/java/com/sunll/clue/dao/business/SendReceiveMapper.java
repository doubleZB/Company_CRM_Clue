package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.SendReceive;
import com.sunll.clue.entity.business.SendReceiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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