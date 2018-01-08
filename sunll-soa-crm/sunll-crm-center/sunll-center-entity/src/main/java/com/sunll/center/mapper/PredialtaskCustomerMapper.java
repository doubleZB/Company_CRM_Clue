package com.sunll.center.mapper;

import com.sunll.center.entity.PredialtaskCustomer;
import com.sunll.center.entity.PredialtaskCustomerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PredialtaskCustomerMapper {
    long countByExample(PredialtaskCustomerExample example);

    int deleteByExample(PredialtaskCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PredialtaskCustomer record);

    int insertSelective(PredialtaskCustomer record);

    List<PredialtaskCustomer> selectByExample(PredialtaskCustomerExample example);

    PredialtaskCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PredialtaskCustomer record, @Param("example") PredialtaskCustomerExample example);

    int updateByExample(@Param("record") PredialtaskCustomer record, @Param("example") PredialtaskCustomerExample example);

    int updateByPrimaryKeySelective(PredialtaskCustomer record);

    int updateByPrimaryKey(PredialtaskCustomer record);
}