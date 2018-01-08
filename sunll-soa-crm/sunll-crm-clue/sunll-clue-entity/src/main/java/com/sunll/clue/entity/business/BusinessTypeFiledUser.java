package com.sunll.clue.entity.business;

import java.io.Serializable;

public class BusinessTypeFiledUser implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer businessTypeId;

    private Integer businessId;

    private Integer businessTypeFieldId;

    private Integer companyId;

    private String showId;

    private static final long serialVersionUID = 1L;

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getBusinessTypeFieldId() {
        return businessTypeFieldId;
    }

    public void setBusinessTypeFieldId(Integer businessTypeFieldId) {
        this.businessTypeFieldId = businessTypeFieldId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", businessTypeId=").append(businessTypeId);
        sb.append(", businessId=").append(businessId);
        sb.append(", businessTypeFieldId=").append(businessTypeFieldId);
        sb.append(", companyId=").append(companyId);
        sb.append(", showId=").append(showId);
        sb.append("]");
        return sb.toString();
    }
}