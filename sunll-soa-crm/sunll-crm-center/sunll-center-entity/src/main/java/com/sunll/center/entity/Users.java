package com.sunll.center.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Users implements Serializable {
    /**   id **/
    private Integer id;

    /**   sip_id **/
    private String sipId;

    /**   sip_password **/
    private String sipPassword;

    /**   sip_key **/
    private String sipKey;

    /**   sip_domain **/
    private String sipDomain;

    /**   sip_callgroup **/
    private String sipCallgroup;

    /**   bind_phone **/
    private Integer bindPhone;

    /** 绑定服务器  bind_server **/
    private Integer bindServer;

    /** 挂机短信id  bind_hangup **/
    private Integer bindHangup;

    /** 外呼权限：1是市话，2是市话+长途  call_auth **/
    private Byte callAuth;

    /**   status **/
    private Integer status;

    /**   fee **/
    private BigDecimal fee;

    /** 0未结算，1已结算，月初自动重置为0  is_settle **/
    private Byte isSettle;

    /**   ctime **/
    private Integer ctime;

    /**   tableName: users   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**     sip_id   **/
    public String getSipId() {
        return sipId;
    }

    /**     sip_id   **/
    public void setSipId(String sipId) {
        this.sipId = sipId == null ? null : sipId.trim();
    }

    /**     sip_password   **/
    public String getSipPassword() {
        return sipPassword;
    }

    /**     sip_password   **/
    public void setSipPassword(String sipPassword) {
        this.sipPassword = sipPassword == null ? null : sipPassword.trim();
    }

    /**     sip_key   **/
    public String getSipKey() {
        return sipKey;
    }

    /**     sip_key   **/
    public void setSipKey(String sipKey) {
        this.sipKey = sipKey == null ? null : sipKey.trim();
    }

    /**     sip_domain   **/
    public String getSipDomain() {
        return sipDomain;
    }

    /**     sip_domain   **/
    public void setSipDomain(String sipDomain) {
        this.sipDomain = sipDomain == null ? null : sipDomain.trim();
    }

    /**     sip_callgroup   **/
    public String getSipCallgroup() {
        return sipCallgroup;
    }

    /**     sip_callgroup   **/
    public void setSipCallgroup(String sipCallgroup) {
        this.sipCallgroup = sipCallgroup == null ? null : sipCallgroup.trim();
    }

    /**     bind_phone   **/
    public Integer getBindPhone() {
        return bindPhone;
    }

    /**     bind_phone   **/
    public void setBindPhone(Integer bindPhone) {
        this.bindPhone = bindPhone;
    }

    /**   绑定服务器  bind_server   **/
    public Integer getBindServer() {
        return bindServer;
    }

    /**   绑定服务器  bind_server   **/
    public void setBindServer(Integer bindServer) {
        this.bindServer = bindServer;
    }

    /**   挂机短信id  bind_hangup   **/
    public Integer getBindHangup() {
        return bindHangup;
    }

    /**   挂机短信id  bind_hangup   **/
    public void setBindHangup(Integer bindHangup) {
        this.bindHangup = bindHangup;
    }

    /**   外呼权限：1是市话，2是市话+长途  call_auth   **/
    public Byte getCallAuth() {
        return callAuth;
    }

    /**   外呼权限：1是市话，2是市话+长途  call_auth   **/
    public void setCallAuth(Byte callAuth) {
        this.callAuth = callAuth;
    }

    /**     status   **/
    public Integer getStatus() {
        return status;
    }

    /**     status   **/
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**     fee   **/
    public BigDecimal getFee() {
        return fee;
    }

    /**     fee   **/
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**   0未结算，1已结算，月初自动重置为0  is_settle   **/
    public Byte getIsSettle() {
        return isSettle;
    }

    /**   0未结算，1已结算，月初自动重置为0  is_settle   **/
    public void setIsSettle(Byte isSettle) {
        this.isSettle = isSettle;
    }

    /**     ctime   **/
    public Integer getCtime() {
        return ctime;
    }

    /**     ctime   **/
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sipId=").append(sipId);
        sb.append(", sipPassword=").append(sipPassword);
        sb.append(", sipKey=").append(sipKey);
        sb.append(", sipDomain=").append(sipDomain);
        sb.append(", sipCallgroup=").append(sipCallgroup);
        sb.append(", bindPhone=").append(bindPhone);
        sb.append(", bindServer=").append(bindServer);
        sb.append(", bindHangup=").append(bindHangup);
        sb.append(", callAuth=").append(callAuth);
        sb.append(", status=").append(status);
        sb.append(", fee=").append(fee);
        sb.append(", isSettle=").append(isSettle);
        sb.append(", ctime=").append(ctime);
        sb.append("]");
        return sb.toString();
    }
}