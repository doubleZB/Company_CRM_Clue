package com.sunll.clue.entity.business;

import java.io.Serializable;
import java.util.Date;

public class BusinessTypeValueIdentify implements Serializable {
    /**   id **/
    private String id;

    /**   user_id **/
    private Integer userId;

    /**   business_type_id **/
    private Integer businessTypeId;

    /** 更新时间  create_time **/
    private Date createTime;

    /** 更新人  update_user_id **/
    private Integer updateUserId;

    /** 更新时间  update_time **/
    private Date updateTime;

    /** 是否删除 ‘0’删除 ‘1’ 未删除  is_del **/
    private Integer isDel;

    /** 创建人id  create_user_id **/
    private Integer createUserId;

    private Object data;      //接受的数据

    /** 创建人名称 **/
    private String createName;

    /** 更新人名称 **/
    private String updateName;

    /** 负责人名称 **/
    private String principalName;

    /**   tableName: business_type_value_identify   **/
    private static final long serialVersionUID = 1L;

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**     id   **/
    public String getId() {
        return id;
    }

    /**     id   **/
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**     user_id   **/
    public Integer getUserId() {
        return userId;
    }

    /**     user_id   **/
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**     business_type_id   **/
    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    /**     business_type_id   **/
    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    /**   更新时间  create_time   **/
    public Date getCreateTime() {
        return createTime;
    }

    /**   更新时间  create_time   **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**   更新人  update_user_id   **/
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**   更新人  update_user_id   **/
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

    /**   是否删除 ‘0’删除 ‘1’ 未删除  is_del   **/
    public Integer getIsDel() {
        return isDel;
    }

    /**   是否删除 ‘0’删除 ‘1’ 未删除  is_del   **/
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", businessTypeId=").append(businessTypeId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDel=").append(isDel);
        sb.append(", createUserId=").append(createUserId);
        sb.append("]");
        return sb.toString();
    }
    public static class ExportExcilClue {
        public static final String EXPORT_FILE_NAME_START = "线索数据";
        public static final String EXPORT_FILE_SUFFIX_NAME = ".xls";
    }

    public static final String ERROR_FILE_SUFFIX_NAME = "xls";
}