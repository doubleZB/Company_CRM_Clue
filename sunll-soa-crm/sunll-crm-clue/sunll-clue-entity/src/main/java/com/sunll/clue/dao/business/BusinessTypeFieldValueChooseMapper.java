package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.BusinessTypeFieldValueChoose;
import com.sunll.clue.entity.business.BusinessTypeFieldValueChooseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTypeFieldValueChooseMapper {
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