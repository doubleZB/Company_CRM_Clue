package com.sunll.center.mapper;

import com.sunll.center.entity.AdmUser;
import com.sunll.center.entity.AdmUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdmUserMapper {
    long countByExample(AdmUserExample example);

    int deleteByExample(AdmUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdmUser record);

    int insertSelective(AdmUser record);

    List<AdmUser> selectByExampleWithBLOBs(AdmUserExample example);

    List<AdmUser> selectByExample(AdmUserExample example);

    AdmUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdmUser record, @Param("example") AdmUserExample example);

    int updateByExampleWithBLOBs(@Param("record") AdmUser record, @Param("example") AdmUserExample example);

    int updateByExample(@Param("record") AdmUser record, @Param("example") AdmUserExample example);

    int updateByPrimaryKeySelective(AdmUser record);

    int updateByPrimaryKeyWithBLOBs(AdmUser record);

    int updateByPrimaryKey(AdmUser record);
}