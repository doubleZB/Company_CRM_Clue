package com.sunll.clue.entity.business;

import java.io.Serializable;
import java.util.Date;

public class BusinessType implements Serializable {
    /**   id **/
    private Integer id;

    /** 业务类型名称  name **/
    private String name;

    /** 业务类型显示名称  show_name **/
    private String showName;

    /** 是否开启  is_enabled **/
    private Integer isEnabled;

    /** 业务id  business_id **/
    private Integer businessId;

    /** 类别 1.线索 2.客户 3.联系人 4.商机  5.合同  6.产品  type_status **/
    private Integer typeStatus;

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

    /**   tableName: business_type   **/
    private static final long serialVersionUID = 1L;

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

    /**   业务id  business_id   **/
    public Integer getBusinessId() {
        return businessId;
    }

    /**   业务id  business_id   **/
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**   类别 1.线索 2.客户 3.联系人 4.商机  5.合同  6.产品  type_status   **/
    public Integer getTypeStatus() {
        return typeStatus;
    }

    /**   类别 1.线索 2.客户 3.联系人 4.商机  5.合同  6.产品  type_status   **/
    public void setTypeStatus(Integer typeStatus) {
        this.typeStatus = typeStatus;
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
        sb.append(", name=").append(name);
        sb.append(", showName=").append(showName);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", businessId=").append(businessId);
        sb.append(", typeStatus=").append(typeStatus);
        sb.append(", isDel=").append(isDel);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}