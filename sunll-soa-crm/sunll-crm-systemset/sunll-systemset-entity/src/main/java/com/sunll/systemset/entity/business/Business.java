package com.sunll.systemset.entity.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Business implements Serializable {
    /**   id **/
    private Integer id;

    /** 业务名称  name **/
    private String name;

    /** 企业id  company_id **/
    private Integer companyId;

    /** 显示名称  show_name **/
    private String showName;

    /** 是否启用 1.启用  is_enabled **/
    private Integer isEnabled;

    /** 行业类别  industry_type **/
    private Integer industryType;

    /**   is_del **/
    private Integer isDel;

    /** 创建用户id  create_user_id **/
    private Integer createUserId;

    /** 创建时间  create_time **/
    private Date createTime;

    /** 更新人id  update_user_id **/
    private Integer updateUserId;

    /** 更新时间  update_time **/
    private Date updateTime;

    /**   tableName: business   **/
    private static final long serialVersionUID = 1L;

    /**   扩展字段     **/
    private List<BusinessType> businessTypeList;

    /** 业务类型所属的所有组织架构  */
    private List<Integer> orgIds;

    private List<String> orgNames;

    public List<String> getOrgNames() {
        return orgNames;
    }

    public void setOrgNames(List<String> orgNames) {
        this.orgNames = orgNames;
    }

    public List<Integer> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<Integer> orgIds) {
        this.orgIds = orgIds;
    }

    public List<BusinessType> getBusinessTypeList() {
        return businessTypeList;
    }

    public void setBusinessTypeList(List<BusinessType> businessTypeList) {
        this.businessTypeList = businessTypeList;
    }

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   业务名称  name   **/
    public String getName() {
        return name;
    }

    /**   业务名称  name   **/
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**   企业id  company_id   **/
    public Integer getCompanyId() {
        return companyId;
    }

    /**   企业id  company_id   **/
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**   显示名称  show_name   **/
    public String getShowName() {
        return showName;
    }

    /**   显示名称  show_name   **/
    public void setShowName(String showName) {
        this.showName = showName == null ? null : showName.trim();
    }

    /**   是否启用 1.启用  is_enabled   **/
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**   是否启用 1.启用  is_enabled   **/
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**   行业类别  industry_type   **/
    public Integer getIndustryType() {
        return industryType;
    }

    /**   行业类别  industry_type   **/
    public void setIndustryType(Integer industryType) {
        this.industryType = industryType;
    }

    /**     is_del   **/
    public Integer getIsDel() {
        return isDel;
    }

    /**     is_del   **/
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**   创建用户id  create_user_id   **/
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**   创建用户id  create_user_id   **/
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**   创建时间  create_time   **/
    public Date getCreateTime() {
        return createTime;
    }

    /**   创建时间  create_time   **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**   更新人id  update_user_id   **/
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**   更新人id  update_user_id   **/
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**   更新时间  update_time   **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**   更新时间  update_time   **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", companyId=").append(companyId);
        sb.append(", showName=").append(showName);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", industryType=").append(industryType);
        sb.append(", isDel=").append(isDel);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}