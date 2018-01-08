package com.sunll.center.mapper;

import com.sunll.center.entity.AccountInfo;
import com.sunll.center.entity.AccountInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountInfoMapper {
    long countByExample(AccountInfoExample example);
    Integer selectMaxId();

    int deleteByExample(AccountInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    List<AccountInfo> selectByExample(AccountInfoExample example);

    AccountInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);

    int updateByExample(@Param("record") AccountInfo record, @Param("example") AccountInfoExample example);

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);
}