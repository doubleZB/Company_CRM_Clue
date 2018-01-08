package com.sunll.clue.entity.message;

import java.io.Serializable;
import java.util.Date;

public class Sms implements Serializable {
    /** 消息提醒id  id **/
    private Integer id;

    /**   sms_text **/
    private String smsText;

    /**   sms_date **/
    private Date smsDate;

    /** 业务id  business_id **/
    private Integer businessId;

    /** 待用栏目  type_id **/
    private Integer typeId;

    /** 模块id 如具体线索id  specific_id **/
    private String specificId;

    /** 短信发送 1未发送 2 已发送  sms_status **/
    private String smsStatus;

    /** 首页展示 1未展示 2 已展示  show_status **/
    private String showStatus;

    /** 待用字段  fieldone **/
    private String fieldone;

    /** 待用字段  fieldtwo **/
    private String fieldtwo;

    //-------------扩展字段-----------------

    private String smsDateShow;

    //-------------扩展字段-----------------

    /**   tableName: sms   **/
    private static final long serialVersionUID = 1L;

    public String getSmsDateShow() {
        return smsDateShow;
    }

    public void setSmsDateShow(String smsDateShow) {
        this.smsDateShow = smsDateShow;
    }

    /**   消息提醒id  id   **/
    public Integer getId() {
        return id;
    }

    /**   消息提醒id  id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**     sms_text   **/
    public String getSmsText() {
        return smsText;
    }

    /**     sms_text   **/
    public void setSmsText(String smsText) {
        this.smsText = smsText == null ? null : smsText.trim();
    }

    /**     sms_date   **/
    public Date getSmsDate() {
        return smsDate;
    }

    /**     sms_date   **/
    public void setSmsDate(Date smsDate) {
        this.smsDate = smsDate;
    }

    /**   业务id  business_id   **/
    public Integer getBusinessId() {
        return businessId;
    }

    /**   业务id  business_id   **/
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**   待用栏目  type_id   **/
    public Integer getTypeId() {
        return typeId;
    }

    /**   待用栏目  type_id   **/
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**   模块id 如具体线索id  specific_id   **/
    public String getSpecificId() {
        return specificId;
    }

    /**   模块id 如具体线索id  specific_id   **/
    public void setSpecificId(String specificId) {
        this.specificId = specificId == null ? null : specificId.trim();
    }

    /**   短信发送 1未发送 2 已发送  sms_status   **/
    public String getSmsStatus() {
        return smsStatus;
    }

    /**   短信发送 1未发送 2 已发送  sms_status   **/
    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus == null ? null : smsStatus.trim();
    }

    /**   首页展示 1未展示 2 已展示  show_status   **/
    public String getShowStatus() {
        return showStatus;
    }

    /**   首页展示 1未展示 2 已展示  show_status   **/
    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus == null ? null : showStatus.trim();
    }

    /**   待用字段  fieldone   **/
    public String getFieldone() {
        return fieldone;
    }

    /**   待用字段  fieldone   **/
    public void setFieldone(String fieldone) {
        this.fieldone = fieldone == null ? null : fieldone.trim();
    }

    /**   待用字段  fieldtwo   **/
    public String getFieldtwo() {
        return fieldtwo;
    }

    /**   待用字段  fieldtwo   **/
    public void setFieldtwo(String fieldtwo) {
        this.fieldtwo = fieldtwo == null ? null : fieldtwo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", smsText=").append(smsText);
        sb.append(", smsDate=").append(smsDate);
        sb.append(", businessId=").append(businessId);
        sb.append(", typeId=").append(typeId);
        sb.append(", specificId=").append(specificId);
        sb.append(", smsStatus=").append(smsStatus);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", fieldone=").append(fieldone);
        sb.append(", fieldtwo=").append(fieldtwo);
        sb.append("]");
        return sb.toString();
    }
}