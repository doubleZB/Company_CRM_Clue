package com.sunll.systemset.entity.template;

import java.io.Serializable;

public class BusinessTem implements Serializable {
    /**   id **/
    private Integer id;

    /** 业务名称  name **/
    private String name;

    /**   show_name **/
    private String showName;

    /**   is_enabled **/
    private Integer isEnabled;

    /** 行业类别  industry_type **/
    private Integer industryType;

    /**   tableName: business_tem   **/
    private static final long serialVersionUID = 1L;

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

    /**     show_name   **/
    public String getShowName() {
        return showName;
    }

    /**     show_name   **/
    public void setShowName(String showName) {
        this.showName = showName == null ? null : showName.trim();
    }

    /**     is_enabled   **/
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**     is_enabled   **/
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", showName=").append(showName);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", industryType=").append(industryType);
        sb.append("]");
        return sb.toString();
    }
}