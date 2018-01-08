package com.sunll.center.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountPhone implements Serializable {
    /**   id **/
    private Integer id;

    /**   phone **/
    private String phone;

    /**   fee **/
    private BigDecimal fee;

    /**   status **/
    private Byte status;

    /**   bind_account **/
    private Integer bindAccount;

    /**   bind_flow **/
    private Integer bindFlow;

    /** 绑定服务器  bind_server **/
    private Integer bindServer;

    /**   stime **/
    private Integer stime;

    /**   etime **/
    private Integer etime;

    /** 0未结算，1已结算，月初自动重置为0  is_settle **/
    private Byte isSettle;

    /**   ctime **/
    private Integer ctime;

    /** 前缀号码，外呼时，该前缀添加在被叫号码前  calloutprefix **/
    private String calloutprefix;

    /** 做为主叫，号码是否允许透传;0:不透传；1：透传  istransparent **/
    private Byte istransparent;

    /** 主叫替换号码，当istransparent=2时，此字段有效  transfercaller **/
    private String transfercaller;

    /** 收费模式：0：呼出收费；1：双向收费  charge_mode **/
    private Byte chargeMode;

    /**   tableName: account_phone   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**     phone   **/
    public String getPhone() {
        return phone;
    }

    /**     phone   **/
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**     fee   **/
    public BigDecimal getFee() {
        return fee;
    }

    /**     fee   **/
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**     status   **/
    public Byte getStatus() {
        return status;
    }

    /**     status   **/
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**     bind_account   **/
    public Integer getBindAccount() {
        return bindAccount;
    }

    /**     bind_account   **/
    public void setBindAccount(Integer bindAccount) {
        this.bindAccount = bindAccount;
    }

    /**     bind_flow   **/
    public Integer getBindFlow() {
        return bindFlow;
    }

    /**     bind_flow   **/
    public void setBindFlow(Integer bindFlow) {
        this.bindFlow = bindFlow;
    }

    /**   绑定服务器  bind_server   **/
    public Integer getBindServer() {
        return bindServer;
    }

    /**   绑定服务器  bind_server   **/
    public void setBindServer(Integer bindServer) {
        this.bindServer = bindServer;
    }

    /**     stime   **/
    public Integer getStime() {
        return stime;
    }

    /**     stime   **/
    public void setStime(Integer stime) {
        this.stime = stime;
    }

    /**     etime   **/
    public Integer getEtime() {
        return etime;
    }

    /**     etime   **/
    public void setEtime(Integer etime) {
        this.etime = etime;
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

    /**   前缀号码，外呼时，该前缀添加在被叫号码前  calloutprefix   **/
    public String getCalloutprefix() {
        return calloutprefix;
    }

    /**   前缀号码，外呼时，该前缀添加在被叫号码前  calloutprefix   **/
    public void setCalloutprefix(String calloutprefix) {
        this.calloutprefix = calloutprefix == null ? null : calloutprefix.trim();
    }

    /**   做为主叫，号码是否允许透传;0:不透传；1：透传  istransparent   **/
    public Byte getIstransparent() {
        return istransparent;
    }

    /**   做为主叫，号码是否允许透传;0:不透传；1：透传  istransparent   **/
    public void setIstransparent(Byte istransparent) {
        this.istransparent = istransparent;
    }

    /**   主叫替换号码，当istransparent=2时，此字段有效  transfercaller   **/
    public String getTransfercaller() {
        return transfercaller;
    }

    /**   主叫替换号码，当istransparent=2时，此字段有效  transfercaller   **/
    public void setTransfercaller(String transfercaller) {
        this.transfercaller = transfercaller == null ? null : transfercaller.trim();
    }

    /**   收费模式：0：呼出收费；1：双向收费  charge_mode   **/
    public Byte getChargeMode() {
        return chargeMode;
    }

    /**   收费模式：0：呼出收费；1：双向收费  charge_mode   **/
    public void setChargeMode(Byte chargeMode) {
        this.chargeMode = chargeMode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", fee=").append(fee);
        sb.append(", status=").append(status);
        sb.append(", bindAccount=").append(bindAccount);
        sb.append(", bindFlow=").append(bindFlow);
        sb.append(", bindServer=").append(bindServer);
        sb.append(", stime=").append(stime);
        sb.append(", etime=").append(etime);
        sb.append(", isSettle=").append(isSettle);
        sb.append(", ctime=").append(ctime);
        sb.append(", calloutprefix=").append(calloutprefix);
        sb.append(", istransparent=").append(istransparent);
        sb.append(", transfercaller=").append(transfercaller);
        sb.append(", chargeMode=").append(chargeMode);
        sb.append("]");
        return sb.toString();
    }
}