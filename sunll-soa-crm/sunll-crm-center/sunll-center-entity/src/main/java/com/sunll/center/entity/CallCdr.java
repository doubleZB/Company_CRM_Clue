package com.sunll.center.entity;

import java.io.Serializable;

public class CallCdr implements Serializable {
    /**   id **/
    private Integer id;

    /** 绑定的帐号  bind_account **/
    private Integer bindAccount;

    /** 主叫  caller **/
    private String caller;

    /** 被叫  called **/
    private String called;

    /**   start_stamp **/
    private Integer startStamp;

    /**   answer_stamp **/
    private Integer answerStamp;

    /**   end_stamp **/
    private Integer endStamp;

    /**   duration **/
    private Integer duration;

    /**   billsec **/
    private Integer billsec;

    /** 挂机原因  hangup_cause **/
    private String hangupCause;

    /**   direction **/
    private String direction;

    /**   tableName: call_cdr   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   绑定的帐号  bind_account   **/
    public Integer getBindAccount() {
        return bindAccount;
    }

    /**   绑定的帐号  bind_account   **/
    public void setBindAccount(Integer bindAccount) {
        this.bindAccount = bindAccount;
    }

    /**   主叫  caller   **/
    public String getCaller() {
        return caller;
    }

    /**   主叫  caller   **/
    public void setCaller(String caller) {
        this.caller = caller == null ? null : caller.trim();
    }

    /**   被叫  called   **/
    public String getCalled() {
        return called;
    }

    /**   被叫  called   **/
    public void setCalled(String called) {
        this.called = called == null ? null : called.trim();
    }

    /**     start_stamp   **/
    public Integer getStartStamp() {
        return startStamp;
    }

    /**     start_stamp   **/
    public void setStartStamp(Integer startStamp) {
        this.startStamp = startStamp;
    }

    /**     answer_stamp   **/
    public Integer getAnswerStamp() {
        return answerStamp;
    }

    /**     answer_stamp   **/
    public void setAnswerStamp(Integer answerStamp) {
        this.answerStamp = answerStamp;
    }

    /**     end_stamp   **/
    public Integer getEndStamp() {
        return endStamp;
    }

    /**     end_stamp   **/
    public void setEndStamp(Integer endStamp) {
        this.endStamp = endStamp;
    }

    /**     duration   **/
    public Integer getDuration() {
        return duration;
    }

    /**     duration   **/
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**     billsec   **/
    public Integer getBillsec() {
        return billsec;
    }

    /**     billsec   **/
    public void setBillsec(Integer billsec) {
        this.billsec = billsec;
    }

    /**   挂机原因  hangup_cause   **/
    public String getHangupCause() {
        return hangupCause;
    }

    /**   挂机原因  hangup_cause   **/
    public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause == null ? null : hangupCause.trim();
    }

    /**     direction   **/
    public String getDirection() {
        return direction;
    }

    /**     direction   **/
    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bindAccount=").append(bindAccount);
        sb.append(", caller=").append(caller);
        sb.append(", called=").append(called);
        sb.append(", startStamp=").append(startStamp);
        sb.append(", answerStamp=").append(answerStamp);
        sb.append(", endStamp=").append(endStamp);
        sb.append(", duration=").append(duration);
        sb.append(", billsec=").append(billsec);
        sb.append(", hangupCause=").append(hangupCause);
        sb.append(", direction=").append(direction);
        sb.append("]");
        return sb.toString();
    }
}