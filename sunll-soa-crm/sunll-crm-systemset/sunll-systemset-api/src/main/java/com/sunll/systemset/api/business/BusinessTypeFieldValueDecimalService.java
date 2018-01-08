package com.sunll.systemset.api.business;

import com.sunll.systemset.entity.business.BusinessTypeFieldValueDecimal;
import com.sunll.systemset.entity.business.BusinessTypeFieldValueDecimalExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeFieldValueDecimalService {
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
