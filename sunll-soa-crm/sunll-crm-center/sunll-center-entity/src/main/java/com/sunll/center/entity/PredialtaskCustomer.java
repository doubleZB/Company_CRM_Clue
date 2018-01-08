package com.sunll.center.entity;

import java.io.Serializable;

public class PredialtaskCustomer implements Serializable {
    /**   id **/
    private Integer id;

    /** 绑定的任务ID  bind_task_id **/
    private Integer bindTaskId;

    /** 客服号码  custom_caller **/
    private String customCaller;

    /**   tableName: predialtask_customer   **/
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

    /**   客服号码  custom_caller   **/
    public String getCustomCaller() {
        return customCaller;
    }

    /**   客服号码  custom_caller   **/
    public void setCustomCaller(String customCaller) {
        this.customCaller = customCaller == null ? null : customCaller.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bindTaskId=").append(bindTaskId);
        sb.append(", customCaller=").append(customCaller);
        sb.append("]");
        return sb.toString();
    }
}