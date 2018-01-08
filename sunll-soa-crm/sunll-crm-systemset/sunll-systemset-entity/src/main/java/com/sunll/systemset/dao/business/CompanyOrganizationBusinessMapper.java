package com.sunll.systemset.dao.business;

import com.sunll.systemset.entity.business.CompanyOrganizationBusiness;
import com.sunll.systemset.entity.business.CompanyOrganizationBusinessExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<CompanyOrganizationBusiness> businessIdListByCompanyId(Integer companyId);

    int deleteByBusinessId(CompanyOrganizationBusiness record);
}