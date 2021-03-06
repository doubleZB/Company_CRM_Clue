package com.sunll.clue.entity.business;

import java.io.Serializable;

public class CompanyOrganizationBusiness implements Serializable {
    /**   id **/
    private Integer id;

    /** 企业id  company_id **/
    private Integer companyId;

    /** 组织id  organization_id **/
    private Integer organizationId;

    /** 业务id  business_id **/
    private Integer businessId;

    /** 是否删除 1未删除  is_del **/
    private Integer isDel;

    /**   tableName: company_organization_business   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   企业id  company_id   **/
    public Integer getCompanyId() {
        return companyId;
    }

    /**   企业id  company_id   **/
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**   组织id  organization_id   **/
    public Integer getOrganizationId() {
        return organizationId;
    }

    /**   组织id  organization_id   **/
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    /**   业务id  business_id   **/
    public Integer getBusinessId() {
        return businessId;
    }

    /**   业务id  business_id   **/
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**   是否删除 1未删除  is_del   **/
    public Integer getIsDel() {
        return isDel;
    }

    /**   是否删除 1未删除  is_del   **/
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", organizationId=").append(organizationId);
        sb.append(", businessId=").append(businessId);
        sb.append(", isDel=").append(isDel);
        sb.append("]");
        return sb.toString();
    }
}