package com.sunll.center.entity;

import java.io.Serializable;
import java.util.Date;

public class PredialTask implements Serializable {
    /**   id **/
    private Integer id;

    /** 任务管理的企业帐号  accountid **/
    private Integer accountid;

    /**   taskname **/
    private String taskname;

    /**   starttime **/
    private Date starttime;

    /**   endtime **/
    private Date endtime;

    /** 外呼总记录数  totalcount **/
    private Integer totalcount;

    /** 外呼类型:1:外呼后放音；2：外呼后放音，再转座席；3：外呼后放音收键转座席;4:外呼后直接转座席;5:外呼后播放字符串  callouttype **/
    private Integer callouttype;

    /** 播放的语音路径及名称  wavfile **/
    private String wavfile;

    /** 放音的字符串，TTS播放  playstr **/
    private String playstr;

    /** 外呼显示号码，如不填，则显示帐号对应的任意企业号码  showcaller **/
    private String showcaller;

    /** 任务状态：0：任务新建；1：任务进行中；2：任务暂停；3：任务完成  taskstatus **/
    private Integer taskstatus;

    /** 希望的最大并发外呼数  concurrentcall **/
    private Integer concurrentcall;

    /** 绑定的业务流程  bind_flow_id **/
    private Integer bindFlowId;

    /**   tableName: predial_task   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   任务管理的企业帐号  accountid   **/
    public Integer getAccountid() {
        return accountid;
    }

    /**   任务管理的企业帐号  accountid   **/
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**     taskname   **/
    public String getTaskname() {
        return taskname;
    }

    /**     taskname   **/
    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }

    /**     starttime   **/
    public Date getStarttime() {
        return starttime;
    }

    /**     starttime   **/
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**     endtime   **/
    public Date getEndtime() {
        return endtime;
    }

    /**     endtime   **/
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**   外呼总记录数  totalcount   **/
    public Integer getTotalcount() {
        return totalcount;
    }

    /**   外呼总记录数  totalcount   **/
    public void setTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
    }

    /**   外呼类型:1:外呼后放音；2：外呼后放音，再转座席；3：外呼后放音收键转座席;4:外呼后直接转座席;5:外呼后播放字符串  callouttype   **/
    public Integer getCallouttype() {
        return callouttype;
    }

    /**   外呼类型:1:外呼后放音；2：外呼后放音，再转座席；3：外呼后放音收键转座席;4:外呼后直接转座席;5:外呼后播放字符串  callouttype   **/
    public void setCallouttype(Integer callouttype) {
        this.callouttype = callouttype;
    }

    /**   播放的语音路径及名称  wavfile   **/
    public String getWavfile() {
        return wavfile;
    }

    /**   播放的语音路径及名称  wavfile   **/
    public void setWavfile(String wavfile) {
        this.wavfile = wavfile == null ? null : wavfile.trim();
    }

    /**   放音的字符串，TTS播放  playstr   **/
    public String getPlaystr() {
        return playstr;
    }

    /**   放音的字符串，TTS播放  playstr   **/
    public void setPlaystr(String playstr) {
        this.playstr = playstr == null ? null : playstr.trim();
    }

    /**   外呼显示号码，如不填，则显示帐号对应的任意企业号码  showcaller   **/
    public String getShowcaller() {
        return showcaller;
    }

    /**   外呼显示号码，如不填，则显示帐号对应的任意企业号码  showcaller   **/
    public void setShowcaller(String showcaller) {
        this.showcaller = showcaller == null ? null : showcaller.trim();
    }

    /**   任务状态：0：任务新建；1：任务进行中；2：任务暂停；3：任务完成  taskstatus   **/
    public Integer getTaskstatus() {
        return taskstatus;
    }

    /**   任务状态：0：任务新建；1：任务进行中；2：任务暂停；3：任务完成  taskstatus   **/
    public void setTaskstatus(Integer taskstatus) {
        this.taskstatus = taskstatus;
    }

    /**   希望的最大并发外呼数  concurrentcall   **/
    public Integer getConcurrentcall() {
        return concurrentcall;
    }

    /**   希望的最大并发外呼数  concurrentcall   **/
    public void setConcurrentcall(Integer concurrentcall) {
        this.concurrentcall = concurrentcall;
    }

    /**   绑定的业务流程  bind_flow_id   **/
    public Integer getBindFlowId() {
        return bindFlowId;
    }

    /**   绑定的业务流程  bind_flow_id   **/
    public void setBindFlowId(Integer bindFlowId) {
        this.bindFlowId = bindFlowId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accountid=").append(accountid);
        sb.append(", taskname=").append(taskname);
        sb.append(", starttime=").append(starttime);
        sb.append(", endtime=").append(endtime);
        sb.append(", totalcount=").append(totalcount);
        sb.append(", callouttype=").append(callouttype);
        sb.append(", wavfile=").append(wavfile);
        sb.append(", playstr=").append(playstr);
        sb.append(", showcaller=").append(showcaller);
        sb.append(", taskstatus=").append(taskstatus);
        sb.append(", concurrentcall=").append(concurrentcall);
        sb.append(", bindFlowId=").append(bindFlowId);
        sb.append("]");
        return sb.toString();
    }
}