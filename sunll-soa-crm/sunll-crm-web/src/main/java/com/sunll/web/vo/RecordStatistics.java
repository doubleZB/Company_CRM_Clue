package com.sunll.web.vo;

/**
 * Created by Administrator on 2017/12/19.
 */
public class RecordStatistics {
    /*部门/人员 外呼总数 外呼成功数 外呼失败数 外呼成功率  转化率 通话总时长*/
    private String userId = "";
    String userName = "";
    int outBoundTotal = 0;
    int outBoundSuccess = 0;
    int outBoundFailure = 0;
    String outBoundPercentage = "";
    int callTimeTotal = 0;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOutBoundTotal() {
        return outBoundTotal;
    }

    public void setOutBoundTotal(int outBoundTotal) {
        this.outBoundTotal = outBoundTotal;
    }

    public int getOutBoundSuccess() {
        return outBoundSuccess;
    }

    public void setOutBoundSuccess(int outBoundSuccess) {
        this.outBoundSuccess = outBoundSuccess;
    }

    public int getOutBoundFailure() {
        return outBoundFailure;
    }

    public void setOutBoundFailure(int outBoundFailure) {
        this.outBoundFailure = outBoundFailure;
    }

    public String getOutBoundPercentage() {
        return outBoundPercentage;
    }

    public void setOutBoundPercentage(String outBoundPercentage) {
        this.outBoundPercentage = outBoundPercentage;
    }

    public int getCallTimeTotal() {
        return callTimeTotal;
    }

    public void setCallTimeTotal(int callTimeTotal) {
        this.callTimeTotal = callTimeTotal;
    }
}
