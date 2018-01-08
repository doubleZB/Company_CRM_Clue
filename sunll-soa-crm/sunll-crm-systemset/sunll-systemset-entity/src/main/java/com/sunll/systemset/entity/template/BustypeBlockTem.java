package com.sunll.systemset.entity.template;

import java.io.Serializable;

public class BustypeBlockTem implements Serializable {
    /**   id **/
    private Integer id;

    /** 业务类型模板id  business_type_tem_id **/
    private Integer businessTypeTemId;

    /** 区块模板id  field_block_tem_id **/
    private Integer fieldBlockTemId;

    /**   tableName: bustype_block_tem   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   业务类型模板id  business_type_tem_id   **/
    public Integer getBusinessTypeTemId() {
        return businessTypeTemId;
    }

    /**   业务类型模板id  business_type_tem_id   **/
    public void setBusinessTypeTemId(Integer businessTypeTemId) {
        this.businessTypeTemId = businessTypeTemId;
    }

    /**   区块模板id  field_block_tem_id   **/
    public Integer getFieldBlockTemId() {
        return fieldBlockTemId;
    }

    /**   区块模板id  field_block_tem_id   **/
    public void setFieldBlockTemId(Integer fieldBlockTemId) {
        this.fieldBlockTemId = fieldBlockTemId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", businessTypeTemId=").append(businessTypeTemId);
        sb.append(", fieldBlockTemId=").append(fieldBlockTemId);
        sb.append("]");
        return sb.toString();
    }
}