package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.BusinessTypeFiledUser;
import com.sunll.clue.entity.business.BusinessTypeFiledUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTypeFiledUserMapper {
    long countByExample(BusinessTypeFiledUserExample example);

    int deleteByExample(BusinessTypeFiledUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFiledUser record);

    int insertSelective(BusinessTypeFiledUser record);

    List<BusinessTypeFiledUser> selectByExample(BusinessTypeFiledUserExample example);

    BusinessTypeFiledUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFiledUser record, @Param("example") BusinessTypeFiledUserExample example);

    int updateByExample(@Param("record") BusinessTypeFiledUser record, @Param("example") BusinessTypeFiledUserExample example);

    int updateByPrimaryKeySelective(BusinessTypeFiledUser record);

    int updateByPrimaryKey(BusinessTypeFiledUser record);
}