package com.sunll.clue.entity.business;

import java.io.Serializable;
import java.util.Date;

public class SendMessage implements Serializable {
    /**   id **/
    private Integer id;

    /** 发送人id  send_userid **/
    private Integer sendUserid;

    /** 发送状态 1.发送成功，2.失败  status **/
    private Integer status;

    /** 发送时间  send_time **/
    private Date sendTime;

    /** 发送内容id  send_content_tem **/
    private Integer sendContentTem;

    /** 发送条数 **/
    private Integer sentMessageNum;

    /** 发送成功条数 **/
    private Integer sentMessageSuccessNum;

    public Integer getSentMessageNum() {
        return sentMessageNum;
    }

    public void setSentMessageNum(Integer sentMessageNum) {
        this.sentMessageNum = sentMessageNum;
    }

    public Integer getSentMessageSuccessNum() {
        return sentMessageSuccessNum;
    }

    public void setSentMessageSuccessNum(Integer sentMessageSuccessNum) {
        this.sentMessageSuccessNum = sentMessageSuccessNum;
    }

    /**   tableName: send_message   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   发送人id  send_userid   **/
    public Integer getSendUserid() {
        return sendUserid;
    }

    /**   发送人id  send_userid   **/
    public void setSendUserid(Integer sendUserid) {
        this.sendUserid = sendUserid;
    }

    /**   发送状态 1.发送成功，2.失败  status   **/
    public Integer getStatus() {
        return status;
    }

    /**   发送状态 1.发送成功，2.失败  status   **/
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**   发送时间  send_time   **/
    public Date getSendTime() {
        return sendTime;
    }

    /**   发送时间  send_time   **/
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**   发送内容id  send_content_tem   **/
    public Integer getSendContentTem() {
        return sendContentTem;
    }

    /**   发送内容id  send_content_tem   **/
    public void setSendContentTem(Integer sendContentTem) {
        this.sendContentTem = sendContentTem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sendUserid=").append(sendUserid);
        sb.append(", status=").append(status);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", sendContentTem=").append(sendContentTem);
        sb.append("]");
        return sb.toString();
    }
}