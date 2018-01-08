package com.sunll.center.mapper;

import com.sunll.center.entity.AccountPhone;
import com.sunll.center.entity.AccountPhoneExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountPhoneMapper {
    long countByExample(AccountPhoneExample example);
    Integer selectMaxId();

    int deleteByExample(AccountPhoneExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountPhone record);

    int insertSelective(AccountPhone record);

    List<AccountPhone> selectByExample(AccountPhoneExample example);

    AccountPhone selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountPhone record, @Param("example") AccountPhoneExample example);

    int updateByExample(@Param("record") AccountPhone record, @Param("example") AccountPhoneExample example);

    int updateByPrimaryKeySelective(AccountPhone record);

    int updateByPrimaryKey(AccountPhone record);
}