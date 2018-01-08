package com.sunll.systemset.dao.business;

import com.sunll.systemset.entity.business.BusinessTypeFieldValueText;
import com.sunll.systemset.entity.business.BusinessTypeFieldValueTextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTypeFieldValueTextMapper {
    long countByExample(BusinessTypeFieldValueTextExample example);

    int deleteByExample(BusinessTypeFieldValueTextExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFieldValueText record);

    int insertSelective(BusinessTypeFieldValueText record);

    List<BusinessTypeFieldValueText> selectByExampleWithBLOBs(BusinessTypeFieldValueTextExample example);

    List<BusinessTypeFieldValueText> selectByExample(BusinessTypeFieldValueTextExample example);

    BusinessTypeFieldValueText selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFieldValueText record, @Param("example") BusinessTypeFieldValueTextExample example);

    int updateByExampleWithBLOBs(@Param("record") BusinessTypeFieldValueText record, @Param("example") BusinessTypeFieldValueTextExample example);

    int updateByExample(@Param("record") BusinessTypeFieldValueText record, @Param("example") BusinessTypeFieldValueTextExample example);

    int updateByPrimaryKeySelective(BusinessTypeFieldValueText record);

    int updateByPrimaryKeyWithBLOBs(BusinessTypeFieldValueText record);

    int updateByPrimaryKey(BusinessTypeFieldValueText record);
}