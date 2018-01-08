package com.sunll.center.entity;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
    /**   id **/
    private Integer id;

    /** 绑定的企业大号ID  bind_phone **/
    private Integer bindPhone;

    /**   record_phone **/
    private String recordPhone;

    /** 主叫  caller **/
    private String caller;

    /** 被叫  called **/
    private String called;

    /** 开始时间  starttime **/
    private Date starttime;

    /** 录音时长  duration **/
    private Integer duration;

    /** 文件路径及名称  filepath **/
    private String filepath;

    /**   tableName: record   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   绑定的企业大号ID  bind_phone   **/
    public Integer getBindPhone() {
        return bindPhone;
    }

    /**   绑定的企业大号ID  bind_phone   **/
    public void setBindPhone(Integer bindPhone) {
        this.bindPhone = bindPhone;
    }

    /**     record_phone   **/
    public String getRecordPhone() {
        return recordPhone;
    }

    /**     record_phone   **/
    public void setRecordPhone(String recordPhone) {
        this.recordPhone = recordPhone == null ? null : recordPhone.trim();
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

    /**   开始时间  starttime   **/
    public Date getStarttime() {
        return starttime;
    }

    /**   开始时间  starttime   **/
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**   录音时长  duration   **/
    public Integer getDuration() {
        return duration;
    }

    /**   录音时长  duration   **/
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**   文件路径及名称  filepath   **/
    public String getFilepath() {
        return filepath;
    }

    /**   文件路径及名称  filepath   **/
    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bindPhone=").append(bindPhone);
        sb.append(", recordPhone=").append(recordPhone);
        sb.append(", caller=").append(caller);
        sb.append(", called=").append(called);
        sb.append(", starttime=").append(starttime);
        sb.append(", duration=").append(duration);
        sb.append(", filepath=").append(filepath);
        sb.append("]");
        return sb.toString();
    }
}