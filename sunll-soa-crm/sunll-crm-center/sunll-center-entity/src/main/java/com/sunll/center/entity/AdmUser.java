package com.sunll.center.entity;

import java.io.Serializable;

public class AdmUser implements Serializable {
    /**   id **/
    private Integer id;

    /**   account **/
    private String account;

    /**   nickname **/
    private String nickname;

    /**   password **/
    private String password;

    /**   bind_account **/
    private Integer bindAccount;

    /**   bind_partner **/
    private Integer bindPartner;

    /**   last_login_time **/
    private Integer lastLoginTime;

    /**   last_login_ip **/
    private String lastLoginIp;

    /**   login_count **/
    private Integer loginCount;

    /**   verify **/
    private String verify;

    /**   email **/
    private String email;

    /**   remark **/
    private String remark;

    /**   create_time **/
    private Integer createTime;

    /**   update_time **/
    private Integer updateTime;

    /**   status **/
    private Boolean status;

    /**   type_id **/
    private Byte typeId;

    /**   info **/
    private String info;

    /**   tableName: adm_user   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**     account   **/
    public String getAccount() {
        return account;
    }

    /**     account   **/
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**     nickname   **/
    public String getNickname() {
        return nickname;
    }

    /**     nickname   **/
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**     password   **/
    public String getPassword() {
        return password;
    }

    /**     password   **/
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    /**     last_login_time   **/
    public Integer getLastLoginTime() {
        return lastLoginTime;
    }

    /**     last_login_time   **/
    public void setLastLoginTime(Integer lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**     last_login_ip   **/
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**     last_login_ip   **/
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**     login_count   **/
    public Integer getLoginCount() {
        return loginCount;
    }

    /**     login_count   **/
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    /**     verify   **/
    public String getVerify() {
        return verify;
    }

    /**     verify   **/
    public void setVerify(String verify) {
        this.verify = verify == null ? null : verify.trim();
    }

    /**     email   **/
    public String getEmail() {
        return email;
    }

    /**     email   **/
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**     remark   **/
    public String getRemark() {
        return remark;
    }

    /**     remark   **/
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**     create_time   **/
    public Integer getCreateTime() {
        return createTime;
    }

    /**     create_time   **/
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**     update_time   **/
    public Integer getUpdateTime() {
        return updateTime;
    }

    /**     update_time   **/
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    /**     status   **/
    public Boolean getStatus() {
        return status;
    }

    /**     status   **/
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**     type_id   **/
    public Byte getTypeId() {
        return typeId;
    }

    /**     type_id   **/
    public void setTypeId(Byte typeId) {
        this.typeId = typeId;
    }

    /**     info   **/
    public String getInfo() {
        return info;
    }

    /**     info   **/
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", nickname=").append(nickname);
        sb.append(", password=").append(password);
        sb.append(", bindAccount=").append(bindAccount);
        sb.append(", bindPartner=").append(bindPartner);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", verify=").append(verify);
        sb.append(", email=").append(email);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", typeId=").append(typeId);
        sb.append(", info=").append(info);
        sb.append("]");
        return sb.toString();
    }
}