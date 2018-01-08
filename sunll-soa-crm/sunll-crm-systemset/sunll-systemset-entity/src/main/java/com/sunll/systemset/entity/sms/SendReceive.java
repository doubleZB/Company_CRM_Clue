package com.sunll.systemset.entity.sms;

import java.io.Serializable;

public class SendReceive implements Serializable {
    /**   id **/
    private Integer id;

    /** 短信id  send_id **/
    private Integer sendId;

    /** 联系人id  recieve_id **/
    private Integer recieveId;

    /** 单条短信发送状态 1.发送成功，2.失败  status **/
    private Integer status;

    /** 短信返回码  message_return **/
    private String messageReturn;

    /** 类别0短信群发1模板短信  message_type **/
    private Integer messageType;

    /**   tableName: send_receive   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   短信id  send_id   **/
    public Integer getSendId() {
        return sendId;
    }

    /**   短信id  send_id   **/
    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    /**   联系人id  recieve_id   **/
    public Integer getRecieveId() {
        return recieveId;
    }

    /**   联系人id  recieve_id   **/
    public void setRecieveId(Integer recieveId) {
        this.recieveId = recieveId;
    }

    /**   单条短信发送状态 1.发送成功，2.失败  status   **/
    public Integer getStatus() {
        return status;
    }

    /**   单条短信发送状态 1.发送成功，2.失败  status   **/
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**   短信返回码  message_return   **/
    public String getMessageReturn() {
        return messageReturn;
    }

    /**   短信返回码  message_return   **/
    public void setMessageReturn(String messageReturn) {
        this.messageReturn = messageReturn == null ? null : messageReturn.trim();
    }

    /**   类别0短信群发1模板短信  message_type   **/
    public Integer getMessageType() {
        return messageType;
    }

    /**   类别0短信群发1模板短信  message_type   **/
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sendId=").append(sendId);
        sb.append(", recieveId=").append(recieveId);
        sb.append(", status=").append(status);
        sb.append(", messageReturn=").append(messageReturn);
        sb.append(", messageType=").append(messageType);
        sb.append("]");
        return sb.toString();
    }
}