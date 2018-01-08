package com.sunll.clue.entity.business;

import java.io.Serializable;

public class BusinessTypeFieldValueVarchar implements Serializable {
    /**   id **/
    private Integer id;

    /**   business_type_id **/
    private Integer businessTypeId;

    /**   business_type_field_id **/
    private Integer businessTypeFieldId;

    /**   value **/
    private String value;

    /**   identify_id **/
    private String identifyId;

    /**   tableName: business_type_field_value_varchar   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**     business_type_id   **/
    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    /**     business_type_id   **/
    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    /**     business_type_field_id   **/
    public Integer getBusinessTypeFieldId() {
        return businessTypeFieldId;
    }

    /**     business_type_field_id   **/
    public void setBusinessTypeFieldId(Integer businessTypeFieldId) {
        this.businessTypeFieldId = businessTypeFieldId;
    }

    /**     value   **/
    public String getValue() {
        return value;
    }

    /**     value   **/
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**     identify_id   **/
    public String getIdentifyId() {
        return identifyId;
    }

    /**     identify_id   **/
    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId == null ? null : identifyId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", businessTypeId=").append(businessTypeId);
        sb.append(", businessTypeFieldId=").append(businessTypeFieldId);
        sb.append(", value=").append(value);
        sb.append(", identifyId=").append(identifyId);
        sb.append("]");
        return sb.toString();
    }
}