package com.sunll.center.mapper;

import com.sunll.center.entity.PredialTask;
import com.sunll.center.entity.PredialTaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PredialTaskMapper {
    long countByExample(PredialTaskExample example);
    Integer selectMaxId();

    int deleteByExample(PredialTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PredialTask record);

    int insertSelective(PredialTask record);

    List<PredialTask> selectByExample(PredialTaskExample example);

    PredialTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PredialTask record, @Param("example") PredialTaskExample example);

    int updateByExample(@Param("record") PredialTask record, @Param("example") PredialTaskExample example);

    int updateByPrimaryKeySelective(PredialTask record);

    int updateByPrimaryKey(PredialTask record);
}