package com.sunll.clue.entity.message;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/15.
 */

/**
 * 短信展示
 */
public class ShowMessage implements Serializable {
    // 导出Excel的标题、文件名开始部分、后缀名
    public static class Excel {
        public static final String[] EXCEL_EXPORT_HEADERS = {
                "name:发信人", "smsStatus:短信状态", "submitTime:提交时间", "smsContent:短信内容",
                "sendNumber:发送条数", "arrivalNumber:到达条数"
        };
        public static final String EXCEL_EXPORT_FILE_NAME_START = "短信群发";
        public static final String EXCEL_FILE_SUFFIX_NAME = ".xls";
    }
    private Integer id;//短信id
    private String name;//发信人
    private String smsStatus;//短信状态
    private String submitTime;//提交时间
    private String smsContent;//短信内容
    private Integer sendNumber;//发送条数
    private Integer arrivalNumber;//到达条数
    private Integer sendContentTem;//模板id
    public ShowMessage() {
    }

    public ShowMessage(Integer id, String name, String smsStatus, String submitTime, String smsContent, Integer sendNumber, Integer arrivalNumber) {
        this.id = id;
        this.name = name;
        this.smsStatus = smsStatus;
        this.submitTime = submitTime;
        this.smsContent = smsContent;
        this.sendNumber = sendNumber;
        this.arrivalNumber = arrivalNumber;
    }

    public Integer getSendContentTem() {
        return sendContentTem;
    }

    public void setSendContentTem(Integer sendContentTem) {
        this.sendContentTem = sendContentTem;
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

    public String getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
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
}
