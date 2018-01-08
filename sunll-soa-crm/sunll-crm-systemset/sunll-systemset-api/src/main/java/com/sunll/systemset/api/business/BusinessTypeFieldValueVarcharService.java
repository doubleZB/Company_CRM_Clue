package com.sunll.systemset.api.business;

import com.sunll.systemset.entity.business.BusinessTypeFieldValueVarchar;
import com.sunll.systemset.entity.business.BusinessTypeFieldValueVarcharExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeFieldValueVarcharService {
    long countByExample(BusinessTypeFieldValueVarcharExample example);

    int deleteByExample(BusinessTypeFieldValueVarcharExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFieldValueVarchar record);

    int insertSelective(BusinessTypeFieldValueVarchar record);

    List<BusinessTypeFieldValueVarchar> selectByExample(BusinessTypeFieldValueVarcharExample example);

    BusinessTypeFieldValueVarchar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFieldValueVarchar record, @Param("example") BusinessTypeFieldValueVarcharExample example);

    int updateByExample(@Param("record") BusinessTypeFieldValueVarchar record, @Param("example") BusinessTypeFieldValueVarcharExample example);

    int updateByPrimaryKeySelective(BusinessTypeFieldValueVarchar record);

    int updateByPrimaryKey(BusinessTypeFieldValueVarchar record);
}
