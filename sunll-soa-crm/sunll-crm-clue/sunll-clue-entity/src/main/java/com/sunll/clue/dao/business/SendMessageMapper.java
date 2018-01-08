package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.SendMessage;
import com.sunll.clue.entity.business.SendMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SendMessageMapper {
    long countByExample(SendMessageExample example);

    int deleteByExample(SendMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SendMessage record);

    int insertSelective(SendMessage record);

    List<SendMessage> selectByExample(SendMessageExample example);

    SendMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SendMessage record, @Param("example") SendMessageExample example);

    int updateByExample(@Param("record") SendMessage record, @Param("example") SendMessageExample example);

    int updateByPrimaryKeySelective(SendMessage record);

    int updateByPrimaryKey(SendMessage record);
}