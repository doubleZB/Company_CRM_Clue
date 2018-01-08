package com.sunll.center.entity;

import java.io.Serializable;

public class PredialtaskCalled implements Serializable {
    /**   id **/
    private Integer id;

    /** 绑定的任务ID  bind_task_id **/
    private Integer bindTaskId;

    /** 外呼的被叫号码  called **/
    private String called;

    /**   tableName: predialtask_called   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   绑定的任务ID  bind_task_id   **/
    public Integer getBindTaskId() {
        return bindTaskId;
    }

    /**   绑定的任务ID  bind_task_id   **/
    public void setBindTaskId(Integer bindTaskId) {
        this.bindTaskId = bindTaskId;
    }

    /**   外呼的被叫号码  called   **/
    public String getCalled() {
        return called;
    }

    /**   外呼的被叫号码  called   **/
    public void setCalled(String called) {
        this.called = called == null ? null : called.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bindTaskId=").append(bindTaskId);
        sb.append(", called=").append(called);
        sb.append("]");
        return sb.toString();
    }
}