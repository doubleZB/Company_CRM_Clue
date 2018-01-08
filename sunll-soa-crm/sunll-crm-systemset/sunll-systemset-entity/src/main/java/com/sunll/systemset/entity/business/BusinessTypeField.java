package com.sunll.systemset.entity.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BusinessTypeField implements Serializable {
    /**   id **/
    private Integer id;

    /** 字段名称  field_name **/
    private String fieldName;

    /** 字段名称  field_show_name **/
    private String fieldShowName;

    /** 字段类型  field_type **/
    private String fieldType;

    /** 字段别名  field_alias **/
    private String fieldAlias;

    /** 是否启用  is_enabled **/
    private Integer isEnabled;

    /**   is_required **/
    private Integer isRequired;

    /**   pid **/
    private Integer pid;

    /** 业务类别id  business_type_id **/
    private Integer businessTypeId;

    /** 是否删除 1，为删除  is_del **/
    private Integer isDel;

    /** 创建人id  create_user_id **/
    private Integer createUserId;

    /** 创建时间  create_time **/
    private Date createTime;

    /** 更新人id  update_user_id **/
    private Integer updateUserId;

    /** 更新时间  update_time **/
    private Date updateTime;

    /**   tableName: business_type_field   **/
    private static final long serialVersionUID = 1L;

    /** 输入提示  enter_prompt **/
    private String enterPrompt;

    /** 是否允许修改 1.不允许  2.允许修改  is_modify **/
    private Integer isModify;

    /** 是否展示 1.展示  2.不展示  is_show **/
    private Integer isShow;

    /** 排序  sort **/
    private Integer sort;

    /** 扩展字段 **/
    private List<BusinessTypeField> businessTypeFieldList;

    private List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooseList;

    public String getFieldShowName() {
        return fieldShowName;
    }

    public void setFieldShowName(String fieldShowName) {
        this.fieldShowName = fieldShowName;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsModify() {
        return isModify;
    }

    public void setIsModify(Integer isModify) {
        this.isModify = isModify;
    }

    public String getEnterPrompt() {
        return enterPrompt;
    }

    public void setEnterPrompt(String enterPrompt) {
        this.enterPrompt = enterPrompt;
    }

    public List<BusinessTypeFieldValueChoose> getBusinessTypeFieldValueChooseList() {
        return businessTypeFieldValueChooseList;
    }

    public void setBusinessTypeFieldValueChooseList(List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooseList) {
        this.businessTypeFieldValueChooseList = businessTypeFieldValueChooseList;
    }

    public List<BusinessTypeField> getBusinessTypeFieldList() {
        return businessTypeFieldList;
    }

    public void setBusinessTypeFieldList(List<BusinessTypeField> businessTypeFieldList) {
        this.businessTypeFieldList = businessTypeFieldList;
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

    /**   是否启用  is_enabled   **/
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**   是否启用  is_enabled   **/
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**     is_required   **/
    public Integer getIsRequired() {
        return isRequired;
    }

    /**     is_required   **/
    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    /**     pid   **/
    public Integer getPid() {
        return pid;
    }

    /**     pid   **/
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**   业务类别id  business_type_id   **/
    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    /**   业务类别id  business_type_id   **/
    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    /**   是否删除 1，为删除  is_del   **/
    public Integer getIsDel() {
        return isDel;
    }

    /**   是否删除 1，为删除  is_del   **/
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**   创建人id  create_user_id   **/
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**   创建人id  create_user_id   **/
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
        sb.append(", fieldName=").append(fieldName);
        sb.append(", fieldType=").append(fieldType);
        sb.append(", fieldAlias=").append(fieldAlias);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", isRequired=").append(isRequired);
        sb.append(", pid=").append(pid);
        sb.append(", businessTypeId=").append(businessTypeId);
        sb.append(", isDel=").append(isDel);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}