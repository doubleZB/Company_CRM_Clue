package com.sunll.center.mapper;

import com.sunll.center.entity.CallCdr;
import com.sunll.center.entity.CallCdrExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CallCdrMapper {
    long countByExample(CallCdrExample example);

    int deleteByExample(CallCdrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CallCdr record);

    int insertSelective(CallCdr record);

    List<CallCdr> selectByExample(CallCdrExample example);

    CallCdr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CallCdr record, @Param("example") CallCdrExample example);

    int updateByExample(@Param("record") CallCdr record, @Param("example") CallCdrExample example);

    int updateByPrimaryKeySelective(CallCdr record);

    int updateByPrimaryKey(CallCdr record);
}