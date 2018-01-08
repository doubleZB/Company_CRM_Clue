package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.BusinessTypeValueIdentify;
import com.sunll.clue.entity.business.BusinessTypeValueIdentifyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessTypeValueIdentifyMapper {
    long countByExample(BusinessTypeValueIdentifyExample example);

    int deleteByExample(BusinessTypeValueIdentifyExample example);

    int deleteByPrimaryKey(String id);

    int insert(BusinessTypeValueIdentify record);

    int insertSelective(BusinessTypeValueIdentify record);

    List<BusinessTypeValueIdentify> selectByExample(BusinessTypeValueIdentifyExample example);

    BusinessTypeValueIdentify selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BusinessTypeValueIdentify record, @Param("example") BusinessTypeValueIdentifyExample example);

    int updateByExample(@Param("record") BusinessTypeValueIdentify record, @Param("example") BusinessTypeValueIdentifyExample example);

    int updateByPrimaryKeySelective(BusinessTypeValueIdentify record);

    int updateByPrimaryKey(BusinessTypeValueIdentify record);

    int delByIds(List<String> idList);
}