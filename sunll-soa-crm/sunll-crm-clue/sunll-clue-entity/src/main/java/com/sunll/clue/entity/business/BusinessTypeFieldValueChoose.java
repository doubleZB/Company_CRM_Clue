package com.sunll.clue.entity.business;

import java.io.Serializable;
import java.util.Date;

public class BusinessTypeFieldValueChoose implements Serializable {
    /**   id **/
    private Integer id;

    /**   name **/
    private String name;

    /**   value **/
    private String value;

    /**   business_type_field_id **/
    private Integer businessTypeFieldId;

    /**   is_enabled **/
    private Integer isEnabled;

    /**   is_del **/
    private Integer isDel;

    /**   create_time **/
    private Date createTime;

    /**   create_user_id **/
    private Integer createUserId;

    /**   update_time **/
    private Date updateTime;

    /**   update_user_id **/
    private Integer updateUserId;

    /**   tableName: business_type_field_value_choose   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**     name   **/
    public String getName() {
        return name;
    }

    /**     name   **/
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**     value   **/
    public String getValue() {
        return value;
    }

    /**     value   **/
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**     business_type_field_id   **/
    public Integer getBusinessTypeFieldId() {
        return businessTypeFieldId;
    }

    /**     business_type_field_id   **/
    public void setBusinessTypeFieldId(Integer businessTypeFieldId) {
        this.businessTypeFieldId = businessTypeFieldId;
    }

    /**     is_enabled   **/
    public Integer getIsEnabled() {
        return isEnabled;
    }

    /**     is_enabled   **/
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**     is_del   **/
    public Integer getIsDel() {
        return isDel;
    }

    /**     is_del   **/
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**     create_time   **/
    public Date getCreateTime() {
        return createTime;
    }

    /**     create_time   **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**     create_user_id   **/
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**     create_user_id   **/
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**     update_time   **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**     update_time   **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**     update_user_id   **/
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**     update_user_id   **/
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
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
        sb.append(", businessTypeFieldId=").append(businessTypeFieldId);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", isDel=").append(isDel);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append("]");
        return sb.toString();
    }
}