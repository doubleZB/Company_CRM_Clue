package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.BusinessTypeFieldValueDecimal;
import com.sunll.clue.entity.business.BusinessTypeFieldValueDecimalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTypeFieldValueDecimalMapper {
    long countByExample(BusinessTypeFieldValueDecimalExample example);

    int deleteByExample(BusinessTypeFieldValueDecimalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFieldValueDecimal record);

    int insertSelective(BusinessTypeFieldValueDecimal record);

    List<BusinessTypeFieldValueDecimal> selectByExample(BusinessTypeFieldValueDecimalExample example);

    BusinessTypeFieldValueDecimal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFieldValueDecimal record, @Param("example") BusinessTypeFieldValueDecimalExample example);

    int updateByExample(@Param("record") BusinessTypeFieldValueDecimal record, @Param("example") BusinessTypeFieldValueDecimalExample example);

    int updateByPrimaryKeySelective(BusinessTypeFieldValueDecimal record);

    int updateByPrimaryKey(BusinessTypeFieldValueDecimal record);
}