package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.MessageTemplate;
import com.sunll.clue.entity.business.MessageTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageTemplateMapper {
    long countByExample(MessageTemplateExample example);

    int deleteByExample(MessageTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageTemplate record);

    int insertSelective(MessageTemplate record);

    List<MessageTemplate> selectByExample(MessageTemplateExample example);

    MessageTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageTemplate record, @Param("example") MessageTemplateExample example);

    int updateByExample(@Param("record") MessageTemplate record, @Param("example") MessageTemplateExample example);

    int updateByPrimaryKeySelective(MessageTemplate record);

    int updateByPrimaryKey(MessageTemplate record);
}