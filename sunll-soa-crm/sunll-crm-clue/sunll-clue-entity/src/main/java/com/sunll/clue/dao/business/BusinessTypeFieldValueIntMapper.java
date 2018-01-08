package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.BusinessTypeFieldValueInt;
import com.sunll.clue.entity.business.BusinessTypeFieldValueIntExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTypeFieldValueIntMapper {
    long countByExample(BusinessTypeFieldValueIntExample example);

    int deleteByExample(BusinessTypeFieldValueIntExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFieldValueInt record);

    int insertSelective(BusinessTypeFieldValueInt record);

    List<BusinessTypeFieldValueInt> selectByExample(BusinessTypeFieldValueIntExample example);

    BusinessTypeFieldValueInt selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFieldValueInt record, @Param("example") BusinessTypeFieldValueIntExample example);

    int updateByExample(@Param("record") BusinessTypeFieldValueInt record, @Param("example") BusinessTypeFieldValueIntExample example);

    int updateByPrimaryKeySelective(BusinessTypeFieldValueInt record);

    int updateByPrimaryKey(BusinessTypeFieldValueInt record);
}