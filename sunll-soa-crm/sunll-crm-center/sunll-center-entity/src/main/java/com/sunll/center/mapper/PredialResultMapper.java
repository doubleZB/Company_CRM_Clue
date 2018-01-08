package com.sunll.center.mapper;

import com.sunll.center.entity.PredialResult;
import com.sunll.center.entity.PredialResultExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PredialResultMapper {
    long countByExample(PredialResultExample example);

    int deleteByExample(PredialResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PredialResult record);

    int insertSelective(PredialResult record);

    List<PredialResult> selectByExample(PredialResultExample example);

    PredialResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PredialResult record, @Param("example") PredialResultExample example);

    int updateByExample(@Param("record") PredialResult record, @Param("example") PredialResultExample example);

    int updateByPrimaryKeySelective(PredialResult record);

    int updateByPrimaryKey(PredialResult record);
}