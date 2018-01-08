package com.sunll.center.entity;

import java.io.Serializable;

public class AccountInfo implements Serializable {
    /**   id **/
    private Integer id;

    /**   company **/
    private String company;

    /**   contact **/
    private String contact;

    /**   phone **/
    private String phone;

    /**   address **/
    private String address;

    /**   email **/
    private String email;

    /**   zip **/
    private String zip;

    /**   bind_group **/
    private Integer bindGroup;

    /**   bind_account **/
    private Integer bindAccount;

    /**   bind_partner **/
    private Integer bindPartner;

    /**   bind_rate **/
    private Integer bindRate;

    /**   bind_discount **/
    private Integer bindDiscount;

    /** 绑定的短信费率  bind_rate_sms **/
    private Integer bindRateSms;

    /** 企业签名，主要为短信用  corp_idiograph **/
    private String corpIdiograph;

    /** 绑定sms_codelist的ID  bind_sms_codelist **/
    private Integer bindSmsCodelist;

    /**   status **/
    private Byte status;

    /**   pid **/
    private Integer pid;

    /** 1是最终付费，0不是最终付费  finalpay **/
    private Byte finalpay;

    /** 0未结算，1已结算，月初自动重置为0  is_settle **/
    private Byte isSettle;

    /**   ctime **/
    private Integer ctime;

    /**   tableName: account_info   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**     company   **/
    public String getCompany() {
        return company;
    }

    /**     company   **/
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**     contact   **/
    public String getContact() {
        return contact;
    }

    /**     contact   **/
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**     phone   **/
    public String getPhone() {
        return phone;
    }

    /**     phone   **/
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**     address   **/
    public String getAddress() {
        return address;
    }

    /**     address   **/
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**     email   **/
    public String getEmail() {
        return email;
    }

    /**     email   **/
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**     zip   **/
    public String getZip() {
        return zip;
    }

    /**     zip   **/
    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    /**     bind_group   **/
    public Integer getBindGroup() {
        return bindGroup;
    }

    /**     bind_group   **/
    public void setBindGroup(Integer bindGroup) {
        this.bindGroup = bindGroup;
    }

    /**     bind_account   **/
    public Integer getBindAccount() {
        return bindAccount;
    }

    /**     bind_account   **/
    public void setBindAccount(Integer bindAccount) {
        this.bindAccount = bindAccount;
    }

    /**     bind_partner   **/
    public Integer getBindPartner() {
        return bindPartner;
    }

    /**     bind_partner   **/
    public void setBindPartner(Integer bindPartner) {
        this.bindPartner = bindPartner;
    }

    /**     bind_rate   **/
    public Integer getBindRate() {
        return bindRate;
    }

    /**     bind_rate   **/
    public void setBindRate(Integer bindRate) {
        this.bindRate = bindRate;
    }

    /**     bind_discount   **/
    public Integer getBindDiscount() {
        return bindDiscount;
    }

    /**     bind_discount   **/
    public void setBindDiscount(Integer bindDiscount) {
        this.bindDiscount = bindDiscount;
    }

    /**   绑定的短信费率  bind_rate_sms   **/
    public Integer getBindRateSms() {
        return bindRateSms;
    }

    /**   绑定的短信费率  bind_rate_sms   **/
    public void setBindRateSms(Integer bindRateSms) {
        this.bindRateSms = bindRateSms;
    }

    /**   企业签名，主要为短信用  corp_idiograph   **/
    public String getCorpIdiograph() {
        return corpIdiograph;
    }

    /**   企业签名，主要为短信用  corp_idiograph   **/
    public void setCorpIdiograph(String corpIdiograph) {
        this.corpIdiograph = corpIdiograph == null ? null : corpIdiograph.trim();
    }

    /**   绑定sms_codelist的ID  bind_sms_codelist   **/
    public Integer getBindSmsCodelist() {
        return bindSmsCodelist;
    }

    /**   绑定sms_codelist的ID  bind_sms_codelist   **/
    public void setBindSmsCodelist(Integer bindSmsCodelist) {
        this.bindSmsCodelist = bindSmsCodelist;
    }

    /**     status   **/
    public Byte getStatus() {
        return status;
    }

    /**     status   **/
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**     pid   **/
    public Integer getPid() {
        return pid;
    }

    /**     pid   **/
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**   1是最终付费，0不是最终付费  finalpay   **/
    public Byte getFinalpay() {
        return finalpay;
    }

    /**   1是最终付费，0不是最终付费  finalpay   **/
    public void setFinalpay(Byte finalpay) {
        this.finalpay = finalpay;
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
        sb.append(", company=").append(company);
        sb.append(", contact=").append(contact);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", email=").append(email);
        sb.append(", zip=").append(zip);
        sb.append(", bindGroup=").append(bindGroup);
        sb.append(", bindAccount=").append(bindAccount);
        sb.append(", bindPartner=").append(bindPartner);
        sb.append(", bindRate=").append(bindRate);
        sb.append(", bindDiscount=").append(bindDiscount);
        sb.append(", bindRateSms=").append(bindRateSms);
        sb.append(", corpIdiograph=").append(corpIdiograph);
        sb.append(", bindSmsCodelist=").append(bindSmsCodelist);
        sb.append(", status=").append(status);
        sb.append(", pid=").append(pid);
        sb.append(", finalpay=").append(finalpay);
        sb.append(", isSettle=").append(isSettle);
        sb.append(", ctime=").append(ctime);
        sb.append("]");
        return sb.toString();
    }
}