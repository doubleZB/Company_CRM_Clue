package com.sunll.center.entity;

import java.io.Serializable;
import java.util.Date;

public class PredialResult implements Serializable {
    /**   id **/
    private Integer id;

    /** 绑定帐号ID  accountid **/
    private Integer accountid;

    /** 主叫号码  caller **/
    private String caller;

    /** 被叫号码  called **/
    private String called;

    /** 呼叫开始时间  start_time **/
    private Date startTime;

    /** 呼叫应答时间  answer_time **/
    private Date answerTime;

    /** 呼叫结束时间  end_time **/
    private Date endTime;

    /** 持续时长  duration **/
    private Integer duration;

    /** 呼叫结果：1：外呼异常；2：呼叫不通；3：呼通  result **/
    private Integer result;

    /** 绑定双向呼叫任务id  task_id **/
    private Integer taskId;

    /** 推送状态：0未推送1已推送  send_status **/
    private Integer sendStatus;

    /**   tableName: predial_result   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   绑定帐号ID  accountid   **/
    public Integer getAccountid() {
        return accountid;
    }

    /**   绑定帐号ID  accountid   **/
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**   主叫号码  caller   **/
    public String getCaller() {
        return caller;
    }

    /**   主叫号码  caller   **/
    public void setCaller(String caller) {
        this.caller = caller == null ? null : caller.trim();
    }

    /**   被叫号码  called   **/
    public String getCalled() {
        return called;
    }

    /**   被叫号码  called   **/
    public void setCalled(String called) {
        this.called = called == null ? null : called.trim();
    }

    /**   呼叫开始时间  start_time   **/
    public Date getStartTime() {
        return startTime;
    }

    /**   呼叫开始时间  start_time   **/
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**   呼叫应答时间  answer_time   **/
    public Date getAnswerTime() {
        return answerTime;
    }

    /**   呼叫应答时间  answer_time   **/
    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    /**   呼叫结束时间  end_time   **/
    public Date getEndTime() {
        return endTime;
    }

    /**   呼叫结束时间  end_time   **/
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**   持续时长  duration   **/
    public Integer getDuration() {
        return duration;
    }

    /**   持续时长  duration   **/
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**   呼叫结果：1：外呼异常；2：呼叫不通；3：呼通  result   **/
    public Integer getResult() {
        return result;
    }

    /**   呼叫结果：1：外呼异常；2：呼叫不通；3：呼通  result   **/
    public void setResult(Integer result) {
        this.result = result;
    }

    /**   绑定双向呼叫任务id  task_id   **/
    public Integer getTaskId() {
        return taskId;
    }

    /**   绑定双向呼叫任务id  task_id   **/
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**   推送状态：0未推送1已推送  send_status   **/
    public Integer getSendStatus() {
        return sendStatus;
    }

    /**   推送状态：0未推送1已推送  send_status   **/
    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accountid=").append(accountid);
        sb.append(", caller=").append(caller);
        sb.append(", called=").append(called);
        sb.append(", startTime=").append(startTime);
        sb.append(", answerTime=").append(answerTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", duration=").append(duration);
        sb.append(", result=").append(result);
        sb.append(", taskId=").append(taskId);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append("]");
        return sb.toString();
    }
}