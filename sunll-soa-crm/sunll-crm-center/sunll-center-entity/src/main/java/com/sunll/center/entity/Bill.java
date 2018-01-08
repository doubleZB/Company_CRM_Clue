package com.sunll.center.entity;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {
    /** 计费账户id  account_id **/
    private Integer accountId;

    /** 计费号码  account_phone **/
    private String accountPhone;

    /** 主叫号码  caller_id_number **/
    private String callerIdNumber;

    /** 被叫号码  destination_number **/
    private String destinationNumber;

    /** 原主叫  ori_caller **/
    private String oriCaller;

    /** 原被叫  ori_destination **/
    private String oriDestination;

    /** 开始时间  start_stamp **/
    private Date startStamp;

    /** 接通时间  answer_stamp **/
    private Date answerStamp;

    /** 挂断时间  end_stamp **/
    private Date endStamp;

    /** 通话时长  billsec **/
    private Integer billsec;

    /** 费率  rate **/
    private Double rate;

    /** 计费单元  increment **/
    private Integer increment;

    /** 费用  fee **/
    private Double fee;

    /** 1：市话，2：长途，3：国际长途  is_toll **/
    private Byte isToll;

    /** 话单类型:1,通话费;2,功能费  billtype **/
    private Integer billtype;

    /** 1：呼入；2：呼出  direction **/
    private Byte direction;

    /** 成本费率  cost_rate **/
    private Float costRate;

    /** 成本计费单位(秒)  cost_unit **/
    private Integer costUnit;

    /** 成本费用(元）  cost_fee **/
    private Float costFee;

    /** 涉及成本的网关ID  gatewayid **/
    private Integer gatewayid;

    /**   tableName: bill   **/
    private static final long serialVersionUID = 1L;

    /**   计费账户id  account_id   **/
    public Integer getAccountId() {
        return accountId;
    }

    /**   计费账户id  account_id   **/
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**   计费号码  account_phone   **/
    public String getAccountPhone() {
        return accountPhone;
    }

    /**   计费号码  account_phone   **/
    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone == null ? null : accountPhone.trim();
    }

    /**   主叫号码  caller_id_number   **/
    public String getCallerIdNumber() {
        return callerIdNumber;
    }

    /**   主叫号码  caller_id_number   **/
    public void setCallerIdNumber(String callerIdNumber) {
        this.callerIdNumber = callerIdNumber == null ? null : callerIdNumber.trim();
    }

    /**   被叫号码  destination_number   **/
    public String getDestinationNumber() {
        return destinationNumber;
    }

    /**   被叫号码  destination_number   **/
    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber == null ? null : destinationNumber.trim();
    }

    /**   原主叫  ori_caller   **/
    public String getOriCaller() {
        return oriCaller;
    }

    /**   原主叫  ori_caller   **/
    public void setOriCaller(String oriCaller) {
        this.oriCaller = oriCaller == null ? null : oriCaller.trim();
    }

    /**   原被叫  ori_destination   **/
    public String getOriDestination() {
        return oriDestination;
    }

    /**   原被叫  ori_destination   **/
    public void setOriDestination(String oriDestination) {
        this.oriDestination = oriDestination == null ? null : oriDestination.trim();
    }

    /**   开始时间  start_stamp   **/
    public Date getStartStamp() {
        return startStamp;
    }

    /**   开始时间  start_stamp   **/
    public void setStartStamp(Date startStamp) {
        this.startStamp = startStamp;
    }

    /**   接通时间  answer_stamp   **/
    public Date getAnswerStamp() {
        return answerStamp;
    }

    /**   接通时间  answer_stamp   **/
    public void setAnswerStamp(Date answerStamp) {
        this.answerStamp = answerStamp;
    }

    /**   挂断时间  end_stamp   **/
    public Date getEndStamp() {
        return endStamp;
    }

    /**   挂断时间  end_stamp   **/
    public void setEndStamp(Date endStamp) {
        this.endStamp = endStamp;
    }

    /**   通话时长  billsec   **/
    public Integer getBillsec() {
        return billsec;
    }

    /**   通话时长  billsec   **/
    public void setBillsec(Integer billsec) {
        this.billsec = billsec;
    }

    /**   费率  rate   **/
    public Double getRate() {
        return rate;
    }

    /**   费率  rate   **/
    public void setRate(Double rate) {
        this.rate = rate;
    }

    /**   计费单元  increment   **/
    public Integer getIncrement() {
        return increment;
    }

    /**   计费单元  increment   **/
    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    /**   费用  fee   **/
    public Double getFee() {
        return fee;
    }

    /**   费用  fee   **/
    public void setFee(Double fee) {
        this.fee = fee;
    }

    /**   1：市话，2：长途，3：国际长途  is_toll   **/
    public Byte getIsToll() {
        return isToll;
    }

    /**   1：市话，2：长途，3：国际长途  is_toll   **/
    public void setIsToll(Byte isToll) {
        this.isToll = isToll;
    }

    /**   话单类型:1,通话费;2,功能费  billtype   **/
    public Integer getBilltype() {
        return billtype;
    }

    /**   话单类型:1,通话费;2,功能费  billtype   **/
    public void setBilltype(Integer billtype) {
        this.billtype = billtype;
    }

    /**   1：呼入；2：呼出  direction   **/
    public Byte getDirection() {
        return direction;
    }

    /**   1：呼入；2：呼出  direction   **/
    public void setDirection(Byte direction) {
        this.direction = direction;
    }

    /**   成本费率  cost_rate   **/
    public Float getCostRate() {
        return costRate;
    }

    /**   成本费率  cost_rate   **/
    public void setCostRate(Float costRate) {
        this.costRate = costRate;
    }

    /**   成本计费单位(秒)  cost_unit   **/
    public Integer getCostUnit() {
        return costUnit;
    }

    /**   成本计费单位(秒)  cost_unit   **/
    public void setCostUnit(Integer costUnit) {
        this.costUnit = costUnit;
    }

    /**   成本费用(元）  cost_fee   **/
    public Float getCostFee() {
        return costFee;
    }

    /**   成本费用(元）  cost_fee   **/
    public void setCostFee(Float costFee) {
        this.costFee = costFee;
    }

    /**   涉及成本的网关ID  gatewayid   **/
    public Integer getGatewayid() {
        return gatewayid;
    }

    /**   涉及成本的网关ID  gatewayid   **/
    public void setGatewayid(Integer gatewayid) {
        this.gatewayid = gatewayid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", accountId=").append(accountId);
        sb.append(", accountPhone=").append(accountPhone);
        sb.append(", callerIdNumber=").append(callerIdNumber);
        sb.append(", destinationNumber=").append(destinationNumber);
        sb.append(", oriCaller=").append(oriCaller);
        sb.append(", oriDestination=").append(oriDestination);
        sb.append(", startStamp=").append(startStamp);
        sb.append(", answerStamp=").append(answerStamp);
        sb.append(", endStamp=").append(endStamp);
        sb.append(", billsec=").append(billsec);
        sb.append(", rate=").append(rate);
        sb.append(", increment=").append(increment);
        sb.append(", fee=").append(fee);
        sb.append(", isToll=").append(isToll);
        sb.append(", billtype=").append(billtype);
        sb.append(", direction=").append(direction);
        sb.append(", costRate=").append(costRate);
        sb.append(", costUnit=").append(costUnit);
        sb.append(", costFee=").append(costFee);
        sb.append(", gatewayid=").append(gatewayid);
        sb.append("]");
        return sb.toString();
    }
}