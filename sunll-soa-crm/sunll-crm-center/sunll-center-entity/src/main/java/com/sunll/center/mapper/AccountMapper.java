package com.sunll.center.mapper;

import com.sunll.center.entity.Account;
import com.sunll.center.entity.AccountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {
    long countByExample(AccountExample example);
    Integer selectMaxId();
    int deleteByExample(AccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}