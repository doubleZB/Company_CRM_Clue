package com.sunll.clue.entity.message;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/15.
 */

/**
 * 通话时长统计展示
 */
public class ShowCallDuration implements Serializable {
    // 导出Excel的标题、文件名开始部分、后缀名
    public static class Excel {
        public static final String[] EXCEL_EXPORT_HEADERS = {
                "name:部门/人员", "outCallNumber:外呼总数", "outCallSuccessNumber:外呼成功数", "outCallFailNumber:外呼失败数",
                "outCallSuccessRateShow:外呼成功率","conversionRate:转化率","totalCallDuration:通话总时长"
        };
        public static final String EXCEL_EXPORT_FILE_NAME_START = "通话时长";
        public static final String EXCEL_FILE_SUFFIX_NAME = ".xls";
    }

    private Integer id;//userId
    private String name;//用户名
    private Integer outCallNumber ;//外呼总数
    private Integer outCallSuccessNumber;//外呼成功数
    private Integer outCallFailNumber;//外呼失败数
    private Double outCallSuccessRate;//外呼成功率
    private Double conversionRate;//转化率
    private Integer totalCallDuration;//通话总时长

    //------------------扩展字段-----------------------

    private String outCallSuccessRateShow;//外呼成功率展示

    //------------------扩展字段-----------------------

    public ShowCallDuration(Integer id, String name, Integer outCallNumber, Integer outCallSuccessNumber, Integer outCallFailNumber, Double outCallSuccessRate, Double conversionRate, Integer totalCallDuration) {
        this.id = id;
        this.name = name;
        this.outCallNumber = outCallNumber;
        this.outCallSuccessNumber = outCallSuccessNumber;
        this.outCallFailNumber = outCallFailNumber;
        this.outCallSuccessRate = outCallSuccessRate;
        this.conversionRate = conversionRate;
        this.totalCallDuration = totalCallDuration;
    }

    public String getOutCallSuccessRateShow() {
        return outCallSuccessRateShow;
    }

    public void setOutCallSuccessRateShow(String outCallSuccessRateShow) {
        this.outCallSuccessRateShow = outCallSuccessRateShow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOutCallNumber() {
        return outCallNumber;
    }

    public void setOutCallNumber(Integer outCallNumber) {
        this.outCallNumber = outCallNumber;
    }

    public Integer getOutCallSuccessNumber() {
        return outCallSuccessNumber;
    }

    public void setOutCallSuccessNumber(Integer outCallSuccessNumber) {
        this.outCallSuccessNumber = outCallSuccessNumber;
    }

    public Integer getOutCallFailNumber() {
        return outCallFailNumber;
    }

    public void setOutCallFailNumber(Integer outCallFailNumber) {
        this.outCallFailNumber = outCallFailNumber;
    }

    public Double getOutCallSuccessRate() {
        return outCallSuccessRate;
    }

    public void setOutCallSuccessRate(Double outCallSuccessRate) {
        this.outCallSuccessRate = outCallSuccessRate;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Integer getTotalCallDuration() {
        return totalCallDuration;
    }

    public void setTotalCallDuration(Integer totalCallDuration) {
        this.totalCallDuration = totalCallDuration;
    }
}
