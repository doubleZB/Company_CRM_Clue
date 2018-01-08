package com.sunll.systemset.entity.template;

import java.io.Serializable;

public class BusinessBusinesstypeTem implements Serializable {
    /**   id **/
    private Integer id;

    /** 业务模板id  business_template_id **/
    private Integer businessTemplateId;

    /** 业务类别模板id  business_type_template_id **/
    private Integer businessTypeTemplateId;

    /**   tableName: business_businesstype_tem   **/
    private static final long serialVersionUID = 1L;

    //扩展字段
    private BusinessTypeTem businessTypeTem;

    public BusinessTypeTem getBusinessTypeTem() {
        return businessTypeTem;
    }

    public void setBusinessTypeTem(BusinessTypeTem businessTypeTem) {
        this.businessTypeTem = businessTypeTem;
    }

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   业务模板id  business_template_id   **/
    public Integer getBusinessTemplateId() {
        return businessTemplateId;
    }

    /**   业务模板id  business_template_id   **/
    public void setBusinessTemplateId(Integer businessTemplateId) {
        this.businessTemplateId = businessTemplateId;
    }

    /**   业务类别模板id  business_type_template_id   **/
    public Integer getBusinessTypeTemplateId() {
        return businessTypeTemplateId;
    }

    /**   业务类别模板id  business_type_template_id   **/
    public void setBusinessTypeTemplateId(Integer businessTypeTemplateId) {
        this.businessTypeTemplateId = businessTypeTemplateId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", businessTemplateId=").append(businessTemplateId);
        sb.append(", businessTypeTemplateId=").append(businessTypeTemplateId);
        sb.append("]");
        return sb.toString();
    }
}