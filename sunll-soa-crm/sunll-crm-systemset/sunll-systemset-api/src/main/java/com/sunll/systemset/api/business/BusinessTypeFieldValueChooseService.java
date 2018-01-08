package com.sunll.systemset.api.business;

import com.sunll.systemset.entity.business.BusinessTypeFieldValueChoose;
import com.sunll.systemset.entity.business.BusinessTypeFieldValueChooseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeFieldValueChooseService {

    long countByExample(BusinessTypeFieldValueChooseExample example);

    int deleteByExample(BusinessTypeFieldValueChooseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFieldValueChoose record);

    int insertSelective(BusinessTypeFieldValueChoose record);

    List<BusinessTypeFieldValueChoose> selectByExample(BusinessTypeFieldValueChooseExample example);

    BusinessTypeFieldValueChoose selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFieldValueChoose record, @Param("example") BusinessTypeFieldValueChooseExample example);

    int updateByExample(@Param("record") BusinessTypeFieldValueChoose record, @Param("example") BusinessTypeFieldValueChooseExample example);

    int updateByPrimaryKeySelective(BusinessTypeFieldValueChoose record);

    int updateByPrimaryKey(BusinessTypeFieldValueChoose record);
}
