package com.sunll.center.mapper;

import com.sunll.center.entity.Bill;
import com.sunll.center.entity.BillExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    long countByExample(BillExample example);

    int deleteByExample(BillExample example);

    int insert(Bill record);

    int insertSelective(Bill record);

    List<Bill> selectByExample(BillExample example);

    int updateByExampleSelective(@Param("record") Bill record, @Param("example") BillExample example);

    int updateByExample(@Param("record") Bill record, @Param("example") BillExample example);
}