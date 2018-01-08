package com.sunll.clue.entity.business;

import java.io.Serializable;
import java.util.Date;

public class MessageTemplate implements Serializable {
    /**   id **/
    private Integer id;

    /** 企业id  company_id **/
    private Integer companyId;

    /** 短信模板名称  name **/
    private String name;

    /** 短信模板内容  content **/
    private String content;

    /** 模板签名  signature **/
    private String signature;

    /** s审核状态 1 审核通过  2 审核失败  status **/
    private Integer status;

    /** 是否删除  1 未删除  2 已删除  is_del **/
    private Integer isDel;

    /**   create_user_id **/
    private Integer createUserId;

    /**   create_time **/
    private Date createTime;

    /**   update_user_id **/
    private Integer updateUserId;

    /**   update_time **/
    private Date updateTime;

    /**   tableName: message_template   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   企业id  company_id   **/
    public Integer getCompanyId() {
        return companyId;
    }

    /**   企业id  company_id   **/
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**   短信模板名称  name   **/
    public String getName() {
        return name;
    }

    /**   短信模板名称  name   **/
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**   短信模板内容  content   **/
    public String getContent() {
        return content;
    }

    /**   短信模板内容  content   **/
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**   模板签名  signature   **/
    public String getSignature() {
        return signature;
    }

    /**   模板签名  signature   **/
    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    /**   s审核状态 1 审核通过  2 审核失败  status   **/
    public Integer getStatus() {
        return status;
    }

    /**   s审核状态 1 审核通过  2 审核失败  status   **/
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**   是否删除  1 未删除  2 已删除  is_del   **/
    public Integer getIsDel() {
        return isDel;
    }

    /**   是否删除  1 未删除  2 已删除  is_del   **/
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**     create_user_id   **/
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**     create_user_id   **/
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**     create_time   **/
    public Date getCreateTime() {
        return createTime;
    }

    /**     create_time   **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**     update_user_id   **/
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**     update_user_id   **/
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**     update_time   **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**     update_time   **/
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
        sb.append(", companyId=").append(companyId);
        sb.append(", name=").append(name);
        sb.append(", content=").append(content);
        sb.append(", signature=").append(signature);
        sb.append(", status=").append(status);
        sb.append(", isDel=").append(isDel);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}