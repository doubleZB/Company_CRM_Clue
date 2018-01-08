package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.BusinessType;
import com.sunll.clue.entity.business.BusinessTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTypeMapper {
    long countByExample(BusinessTypeExample example);

    int deleteByExample(BusinessTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessType record);

    int insertSelective(BusinessType record);

    List<BusinessType> selectByExample(BusinessTypeExample example);

    BusinessType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessType record, @Param("example") BusinessTypeExample example);

    int updateByExample(@Param("record") BusinessType record, @Param("example") BusinessTypeExample example);

    int updateByPrimaryKeySelective(BusinessType record);

    int updateByPrimaryKey(BusinessType record);
}