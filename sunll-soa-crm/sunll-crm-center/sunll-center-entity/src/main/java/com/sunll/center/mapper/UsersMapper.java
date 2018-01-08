package com.sunll.center.mapper;

import com.sunll.center.entity.Users;
import com.sunll.center.entity.UsersExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    long countByExample(UsersExample example);
    Integer selectMaxId();
    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}