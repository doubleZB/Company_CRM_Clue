package com.sunll.systemset.entity.template;

import java.io.Serializable;

public class BusinessTypeFieldValueChooseTem implements Serializable {
    /**   id **/
    private Integer id;

    /** 显示名称  name **/
    private String name;

    /** 选择值  value **/
    private String value;

    /** 是否启用 1.启用  is_enabled **/
    private Integer isEnabled;

    /** 字段模板id  type_field_tem_id **/
    private Integer typeFieldTemId;

    /**   tableName: business_type_field_value_choose_tem   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   显示名称  name   **/
    public String getName() {
        return name;
    }

    /**   显示名称  name   **/
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**   选择值  value   **/
    public String getValue() {
        return value;
    }

    /**   选择值  value   **/
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**   是否启用 1.启用  is_enabled   **/
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**   是否启用 1.启用  is_enabled   **/
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**   字段模板id  type_field_tem_id   **/
    public Integer getTypeFieldTemId() {
        return typeFieldTemId;
    }

    /**   字段模板id  type_field_tem_id   **/
    public void setTypeFieldTemId(Integer typeFieldTemId) {
        this.typeFieldTemId = typeFieldTemId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", value=").append(value);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", typeFieldTemId=").append(typeFieldTemId);
        sb.append("]");
        return sb.toString();
    }
}