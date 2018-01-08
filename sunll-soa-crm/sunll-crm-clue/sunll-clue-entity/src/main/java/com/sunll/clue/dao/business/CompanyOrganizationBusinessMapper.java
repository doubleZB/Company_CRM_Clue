package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.CompanyOrganizationBusiness;
import com.sunll.clue.entity.business.CompanyOrganizationBusinessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CompanyOrganizationBusinessMapper {
    long countByExample(CompanyOrganizationBusinessExample example);

    int deleteByExample(CompanyOrganizationBusinessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CompanyOrganizationBusiness record);

    int insertSelective(CompanyOrganizationBusiness record);

    List<CompanyOrganizationBusiness> selectByExample(CompanyOrganizationBusinessExample example);

    CompanyOrganizationBusiness selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CompanyOrganizationBusiness record, @Param("example") CompanyOrganizationBusinessExample example);

    int updateByExample(@Param("record") CompanyOrganizationBusiness record, @Param("example") CompanyOrganizationBusinessExample example);

    int updateByPrimaryKeySelective(CompanyOrganizationBusiness record);

    int updateByPrimaryKey(CompanyOrganizationBusiness record);
}