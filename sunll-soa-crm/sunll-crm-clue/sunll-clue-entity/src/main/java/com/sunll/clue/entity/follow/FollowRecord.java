package com.sunll.clue.entity.follow;

import java.io.Serializable;
import java.util.Date;

public class FollowRecord implements Serializable {
    /**   id **/
    private Integer id;

    /** 内容  follow_content **/
    private String followContent;

    /** 1: 电话沟通  2:上门拜访 3:邮件沟通  4:其他方式  follow_up **/
    private Integer followUp;

    /** 下次跟进时间  follow_next_time **/
    private Date followNextTime;

    /** 跟进提醒内容  follow_reminder_content **/
    private String followReminderContent;

    /** 来源id 例如：线索id  follow_source_id **/
    private String followSourceId;

    /** 来源类别 1.线索  follow_source_type **/
    private Integer followSourceType;

    /** 1. 未删除  2.已删除  is_del **/
    private Integer isDel;

    /** 创建时间  create_time **/
    private Date createTime;

    /** 创建人id  create_user_id **/
    private Integer createUserId;

    /** 更新时间  update_time **/
    private Date updateTime;

    /** 更新人id  update_user_id **/
    private Integer updateUserId;

    /**   tableName: follow_record   **/
    private static final long serialVersionUID = 1L;

    /** 扩展字段  **/
    private String createName;
    private String showCreateTime;

    public String getShowCreateTime() {
        return showCreateTime;
    }

    public void setShowCreateTime(String showCreateTime) {
        this.showCreateTime = showCreateTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   内容  follow_content   **/
    public String getFollowContent() {
        return followContent;
    }

    /**   内容  follow_content   **/
    public void setFollowContent(String followContent) {
        this.followContent = followContent == null ? null : followContent.trim();
    }

    /**   1: 电话沟通  2:上门拜访 3:邮件沟通  4:其他方式  follow_up   **/
    public Integer getFollowUp() {
        return followUp;
    }

    /**   1: 电话沟通  2:上门拜访 3:邮件沟通  4:其他方式  follow_up   **/
    public void setFollowUp(Integer followUp) {
        this.followUp = followUp;
    }

    /**   下次跟进时间  follow_next_time   **/
    public Date getFollowNextTime() {
        return followNextTime;
    }

    /**   下次跟进时间  follow_next_time   **/
    public void setFollowNextTime(Date followNextTime) {
        this.followNextTime = followNextTime;
    }

    /**   跟进提醒内容  follow_reminder_content   **/
    public String getFollowReminderContent() {
        return followReminderContent;
    }

    /**   跟进提醒内容  follow_reminder_content   **/
    public void setFollowReminderContent(String followReminderContent) {
        this.followReminderContent = followReminderContent == null ? null : followReminderContent.trim();
    }

    /**   来源id 例如：线索id  follow_source_id   **/
    public String getFollowSourceId() {
        return followSourceId;
    }

    /**   来源id 例如：线索id  follow_source_id   **/
    public void setFollowSourceId(String followSourceId) {
        this.followSourceId = followSourceId == null ? null : followSourceId.trim();
    }

    /**   来源类别 1.线索  follow_source_type   **/
    public Integer getFollowSourceType() {
        return followSourceType;
    }

    /**   来源类别 1.线索  follow_source_type   **/
    public void setFollowSourceType(Integer followSourceType) {
        this.followSourceType = followSourceType;
    }

    /**   1. 未删除  2.已删除  is_del   **/
    public Integer getIsDel() {
        return isDel;
    }

    /**   1. 未删除  2.已删除  is_del   **/
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**   创建时间  create_time   **/
    public Date getCreateTime() {
        return createTime;
    }

    /**   创建时间  create_time   **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**   创建人id  create_user_id   **/
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**   创建人id  create_user_id   **/
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**   更新时间  update_time   **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**   更新时间  update_time   **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**   更新人id  update_user_id   **/
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**   更新人id  update_user_id   **/
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
        sb.append(", followContent=").append(followContent);
        sb.append(", followUp=").append(followUp);
        sb.append(", followNextTime=").append(followNextTime);
        sb.append(", followReminderContent=").append(followReminderContent);
        sb.append(", followSourceId=").append(followSourceId);
        sb.append(", followSourceType=").append(followSourceType);
        sb.append(", isDel=").append(isDel);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append("]");
        return sb.toString();
    }
}