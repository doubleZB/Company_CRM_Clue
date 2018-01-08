package com.sunll.systemset.dao.business;

import com.sunll.systemset.entity.business.BusinessTypeField;
import com.sunll.systemset.entity.business.BusinessTypeFieldExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BusinessTypeFieldMapper {
    long countByExample(BusinessTypeFieldExample example);

    int deleteByExample(BusinessTypeFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeField record);

    int insertSelective(BusinessTypeField record);

    List<BusinessTypeField> selectByExample(BusinessTypeFieldExample example);

    BusinessTypeField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeField record, @Param("example") BusinessTypeFieldExample example);

    int updateByExample(@Param("record") BusinessTypeField record, @Param("example") BusinessTypeFieldExample example);

    int updateByPrimaryKeySelective(BusinessTypeField record);

    int updateByPrimaryKey(BusinessTypeField record);

    List<BusinessTypeField> selectByBusinessTypeIdAndPid(Map<String,Object> map);
}