package com.sunll.systemset.entity.template;

import java.io.Serializable;

public class BusinessTypeFieldTem implements Serializable {
    /**   id **/
    private Integer id;

    /** 字段名称  field_name **/
    private String fieldName;

    /** 字段显示名称  field_show_name **/
    private String fieldShowName;

    /** 字段类型  field_type **/
    private String fieldType;

    /** 字段别名  field_alias **/
    private String fieldAlias;

    /** 是否启用 1.启用  is_enabled **/
    private Integer isEnabled;

    /** 是否必填 1.必填  is_required **/
    private Integer isRequired;

    /** 输入提示  enter_prompt **/
    private String enterPrompt;

    /** 是否允许修改 1.不允许  2.允许修改  is_modify **/
    private Integer isModify;

    /** 是否显示 1 显示  2不显示  is_show **/
    private Integer isShow;

    /** 排序  sort **/
    private Integer sort;

    /**   tableName: business_type_field_tem   **/
    private static final long serialVersionUID = 1L;

    public String getFieldShowName() {
        return fieldShowName;
    }

    public void setFieldShowName(String fieldShowName) {
        this.fieldShowName = fieldShowName;
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

    /**   输入提示  enter_prompt   **/
    public String getEnterPrompt() {
        return enterPrompt;
    }

    /**   输入提示  enter_prompt   **/
    public void setEnterPrompt(String enterPrompt) {
        this.enterPrompt = enterPrompt == null ? null : enterPrompt.trim();
    }

    /**   是否允许修改 1.不允许  2.允许修改  is_modify   **/
    public Integer getIsModify() {
        return isModify;
    }

    /**   是否允许修改 1.不允许  2.允许修改  is_modify   **/
    public void setIsModify(Integer isModify) {
        this.isModify = isModify;
    }

    /**   是否显示 1 显示  2不显示  is_show   **/
    public Integer getIsShow() {
        return isShow;
    }

    /**   是否显示 1 显示  2不显示  is_show   **/
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**   排序  sort   **/
    public Integer getSort() {
        return sort;
    }

    /**   排序  sort   **/
    public void setSort(Integer sort) {
        this.sort = sort;
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
        sb.append(", enterPrompt=").append(enterPrompt);
        sb.append(", isModify=").append(isModify);
        sb.append(", isShow=").append(isShow);
        sb.append(", sort=").append(sort);
        sb.append("]");
        return sb.toString();
    }
}