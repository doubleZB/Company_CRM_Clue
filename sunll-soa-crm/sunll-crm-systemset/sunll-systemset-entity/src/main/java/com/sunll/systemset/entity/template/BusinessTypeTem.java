package com.sunll.systemset.entity.template;

import java.io.Serializable;

public class BusinessTypeTem implements Serializable {
    /**   id **/
    private Integer id;

    /** 业务类型名称  name **/
    private String name;

    /** 业务类型显示名称  show_name **/
    private String showName;

    /** 是否开启  is_enabled **/
    private Integer isEnabled;

    /** 类别：1.线索 2.客户 3.联系人 4.商机  5.合同  6.产品 **/
    private Integer typeStatus;

    /**   tableName: business_type_tem   **/
    private static final long serialVersionUID = 1L;

    public Integer getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(Integer typeStatus) {
        this.typeStatus = typeStatus;
    }

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   业务类型名称  name   **/
    public String getName() {
        return name;
    }

    /**   业务类型名称  name   **/
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**   业务类型显示名称  show_name   **/
    public String getShowName() {
        return showName;
    }

    /**   业务类型显示名称  show_name   **/
    public void setShowName(String showName) {
        this.showName = showName == null ? null : showName.trim();
    }

    /**   是否开启  is_enabled   **/
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**   是否开启  is_enabled   **/
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
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
        sb.append("]");
        return sb.toString();
    }
}