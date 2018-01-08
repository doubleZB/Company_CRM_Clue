package com.sunll.systemset.api.business;

import com.sunll.systemset.entity.business.BusinessTypeFieldValueInt;
import com.sunll.systemset.entity.business.BusinessTypeFieldValueIntExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeFieldValueIntService {
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
