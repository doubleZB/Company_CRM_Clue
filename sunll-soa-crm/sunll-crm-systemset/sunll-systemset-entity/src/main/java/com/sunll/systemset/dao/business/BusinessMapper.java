package com.sunll.systemset.dao.business;

import com.sunll.systemset.entity.business.Business;
import com.sunll.systemset.entity.business.BusinessExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessMapper {
    long countByExample(BusinessExample example);

    int deleteByExample(BusinessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Business record);

    int insertSelective(Business record);

    List<Business> selectByExample(BusinessExample example);

    Business selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Business record, @Param("example") BusinessExample example);

    int updateByExample(@Param("record") Business record, @Param("example") BusinessExample example);

    int updateByPrimaryKeySelective(Business record);

    int updateByPrimaryKey(Business record);

    List<Business> getBusinessListByCompanyId(Integer companyId);
}