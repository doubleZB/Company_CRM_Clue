package com.sunll.clue.entity.message;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/14.
 */

/**
 * 短信费用统计展示
 */
public class ShowMessageCosts implements Serializable {

    // 导出Excel的标题、文件名开始部分、后缀名
    public static class Excel {
        public static final String[] EXCEL_EXPORT_HEADERS = {
                "name:发信人", "sendNumber:发送条数", "arrivalNumber:到达条数", "totalExpenses:费用统计"
        };
        public static final String EXCEL_EXPORT_FILE_NAME_START = "短信费用统计";
        public static final String EXCEL_FILE_SUFFIX_NAME = ".xls";
    }

    private Integer id;//发信人id
    private String name;//发信人
    private Integer sendNumber;//发送条数
    private Integer arrivalNumber;//到达条数
    private Double totalExpenses;//费用统计

    public ShowMessageCosts(Integer id, String name, Integer sendNumber, Integer arrivalNumber, Double totalExpenses) {
        this.id = id;
        this.name = name;
        this.sendNumber = sendNumber;
        this.arrivalNumber = arrivalNumber;
        this.totalExpenses = totalExpenses;
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

    public Integer getSendNumber() {
        return sendNumber;
    }

    public void setSendNumber(Integer sendNumber) {
        this.sendNumber = sendNumber;
    }

    public Integer getArrivalNumber() {
        return arrivalNumber;
    }

    public void setArrivalNumber(Integer arrivalNumber) {
        this.arrivalNumber = arrivalNumber;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
}
