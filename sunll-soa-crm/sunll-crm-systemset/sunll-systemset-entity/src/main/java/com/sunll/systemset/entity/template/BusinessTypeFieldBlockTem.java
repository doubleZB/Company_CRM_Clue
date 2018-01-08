package com.sunll.systemset.entity.template;

import java.io.Serializable;
import java.util.List;

public class BusinessTypeFieldBlockTem implements Serializable {
    /**   id **/
    private Integer id;

    /** 字段名称  field_name **/
    private String fieldName;

    /** 字段类型  field_type **/
    private String fieldType;

    /** 字段别名  field_alias **/
    private String fieldAlias;

    /** 是否启用 1.启用  is_enabled **/
    private Integer isEnabled;

    /** 是否必填 1.必填  is_required **/
    private Integer isRequired;

    /**   tableName: business_type_field_block_tem   **/
    private static final long serialVersionUID = 1L;

    //扩展字段
    private List<BusinessTypeFieldTem> businessTypeFieldTemList;

    public List<BusinessTypeFieldTem> getBusinessTypeFieldTemList() {
        return businessTypeFieldTemList;
    }

    public void setBusinessTypeFieldTemList(List<BusinessTypeFieldTem> businessTypeFieldTemList) {
        this.businessTypeFieldTemList = businessTypeFieldTemList;
    }

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   字段名称  field_name   **/
    public String getFieldName() {
        return fieldName;
    }

    /**   字段名称  field_name   **/
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    /**   字段类型  field_type   **/
    public String getFieldType() {
        return fieldType;
    }

    /**   字段类型  field_type   **/
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType == null ? null : fieldType.trim();
    }

    /**   字段别名  field_alias   **/
    public String getFieldAlias() {
        return fieldAlias;
    }

    /**   字段别名  field_alias   **/
    public void setFieldAlias(String fieldAlias) {
        this.fieldAlias = fieldAlias == null ? null : fieldAlias.trim();
    }

    /**   是否启用 1.启用  is_enabled   **/
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**   是否启用 1.启用  is_enabled   **/
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**   是否必填 1.必填  is_required   **/
    public Integer getIsRequired() {
        return isRequired;
    }

    /**   是否必填 1.必填  is_required   **/
    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fieldName=").append(fieldName);
        sb.append(", fieldType=").append(fieldType);
        sb.append(", fieldAlias=").append(fieldAlias);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", isRequired=").append(isRequired);
        sb.append("]");
        return sb.toString();
    }
}