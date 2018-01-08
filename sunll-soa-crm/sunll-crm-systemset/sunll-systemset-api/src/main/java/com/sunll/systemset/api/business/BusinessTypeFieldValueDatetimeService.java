package com.sunll.systemset.api.business;

import com.sunll.systemset.entity.business.BusinessTypeFieldValueDatetime;
import com.sunll.systemset.entity.business.BusinessTypeFieldValueDatetimeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeFieldValueDatetimeService {
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
