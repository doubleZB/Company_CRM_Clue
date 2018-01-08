package com.sunll.systemset.entity.template;

import java.io.Serializable;

public class FieldblockFieldTem implements Serializable {
    /**   id **/
    private Integer id;

    /** 区块模板id  field_block_tem_id **/
    private Integer fieldBlockTemId;

    /** 字段模板id  field_tem_id **/
    private Integer fieldTemId;

    /**   tableName: fieldblock_field_tem   **/
    private static final long serialVersionUID = 1L;

    /**     id   **/
    public Integer getId() {
        return id;
    }

    /**     id   **/
    public void setId(Integer id) {
        this.id = id;
    }

    /**   区块模板id  field_block_tem_id   **/
    public Integer getFieldBlockTemId() {
        return fieldBlockTemId;
    }

    /**   区块模板id  field_block_tem_id   **/
    public void setFieldBlockTemId(Integer fieldBlockTemId) {
        this.fieldBlockTemId = fieldBlockTemId;
    }

    /**   字段模板id  field_tem_id   **/
    public Integer getFieldTemId() {
        return fieldTemId;
    }

    /**   字段模板id  field_tem_id   **/
    public void setFieldTemId(Integer fieldTemId) {
        this.fieldTemId = fieldTemId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fieldBlockTemId=").append(fieldBlockTemId);
        sb.append(", fieldTemId=").append(fieldTemId);
        sb.append("]");
        return sb.toString();
    }
}