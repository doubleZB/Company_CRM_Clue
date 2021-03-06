package com.sunll.systemset.dao.business;

import com.sunll.systemset.entity.business.BusinessTypeFieldValueDatetime;
import com.sunll.systemset.entity.business.BusinessTypeFieldValueDatetimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTypeFieldValueDatetimeMapper {
    long countByExample(BusinessTypeFieldValueDatetimeExample example);

    int deleteByExample(BusinessTypeFieldValueDatetimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFieldValueDatetime record);

    int insertSelective(BusinessTypeFieldValueDatetime record);

    List<BusinessTypeFieldValueDatetime> selectByExample(BusinessTypeFieldValueDatetimeExample example);

    BusinessTypeFieldValueDatetime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFieldValueDatetime record, @Param("example") BusinessTypeFieldValueDatetimeExample example);

    int updateByExample(@Param("record") BusinessTypeFieldValueDatetime record, @Param("example") BusinessTypeFieldValueDatetimeExample example);

    int updateByPrimaryKeySelective(BusinessTypeFieldValueDatetime record);

    int updateByPrimaryKey(BusinessTypeFieldValueDatetime record);
}