package com.sunll.clue.entity.message;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/15.
 */

/**
 * 通话费用统计
 */
public class ShowCallCost implements Serializable {
    // 导出Excel的标题、文件名开始部分、后缀名
    public static class Excel {
        public static final String[] EXCEL_EXPORT_HEADERS = {
                "name:部门/人员", "totalSeatCosts:坐席费用总计", "totalCallDuration:通话总时长", "callDurationTotalCost:通话时长费用总计",
                "total:总计"
        };
        public static final String EXCEL_EXPORT_FILE_NAME_START = "通话时长费用";
        public static final String EXCEL_FILE_SUFFIX_NAME = ".xls";
    }

    private Integer id;//userId
    private String name;//用户名
    private Double totalSeatCosts;//坐席费用总计
    private Integer totalCallDuration;//通话总时长
    private Double callDurationTotalCost;//通话时长费用总计
    private Double total;//总计（元）

    public ShowCallCost(Integer id, String name, Double totalSeatCosts, Integer totalCallDuration, Double callDurationTotalCost, Double total) {
        this.id = id;
        this.name = name;
        this.totalSeatCosts = totalSeatCosts;
        this.totalCallDuration = totalCallDuration;
        this.callDurationTotalCost = callDurationTotalCost;
        this.total = total;
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

    public Double getTotalSeatCosts() {
        return totalSeatCosts;
    }

    public void setTotalSeatCosts(Double totalSeatCosts) {
        this.totalSeatCosts = totalSeatCosts;
    }

    public Integer getTotalCallDuration() {
        return totalCallDuration;
    }

    public void setTotalCallDuration(Integer totalCallDuration) {
        this.totalCallDuration = totalCallDuration;
    }

    public Double getCallDurationTotalCost() {
        return callDurationTotalCost;
    }

    public void setCallDurationTotalCost(Double callDurationTotalCost) {
        this.callDurationTotalCost = callDurationTotalCost;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
