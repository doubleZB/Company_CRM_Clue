package com.sunll.center.mapper;

import com.sunll.center.entity.PredialtaskCalled;
import com.sunll.center.entity.PredialtaskCalledExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PredialtaskCalledMapper {
    long countByExample(PredialtaskCalledExample example);
    Integer selectMaxId();

    int deleteByExample(PredialtaskCalledExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PredialtaskCalled record);

    int insertSelective(PredialtaskCalled record);

    List<PredialtaskCalled> selectByExample(PredialtaskCalledExample example);

    PredialtaskCalled selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PredialtaskCalled record, @Param("example") PredialtaskCalledExample example);

    int updateByExample(@Param("record") PredialtaskCalled record, @Param("example") PredialtaskCalledExample example);

    int updateByPrimaryKeySelective(PredialtaskCalled record);

    int updateByPrimaryKey(PredialtaskCalled record);
}