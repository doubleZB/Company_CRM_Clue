package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.BusinessTypeFieldValueVarchar;
import com.sunll.clue.entity.business.BusinessTypeFieldValueVarcharExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTypeFieldValueVarcharMapper {
    long countByExample(BusinessTypeFieldValueVarcharExample example);

    int deleteByExample(BusinessTypeFieldValueVarcharExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFieldValueVarchar record);

    int insertSelective(BusinessTypeFieldValueVarchar record);

    List<BusinessTypeFieldValueVarchar> selectByExample(BusinessTypeFieldValueVarcharExample example);

    BusinessTypeFieldValueVarchar selectOneByExample(BusinessTypeFieldValueVarcharExample example);

    BusinessTypeFieldValueVarchar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFieldValueVarchar record, @Param("example") BusinessTypeFieldValueVarcharExample example);

    int updateByExample(@Param("record") BusinessTypeFieldValueVarchar record, @Param("example") BusinessTypeFieldValueVarcharExample example);

    int updateByPrimaryKeySelective(BusinessTypeFieldValueVarchar record);

    int updateByPrimaryKey(BusinessTypeFieldValueVarchar record);
}