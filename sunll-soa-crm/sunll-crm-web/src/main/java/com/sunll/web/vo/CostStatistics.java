package com.sunll.web.vo;

/**
 * Created by Administrator on 2017/12/18.
 */
public class CostStatistics {
    private String userId = "";
    private String userName = "";
    private float seatCostTotal = 0f;
    private int callTotalTime = 0;
    private float callCostTotal = 0f;
    private float total = 0f;

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

    public float getSeatCostTotal() {
        return seatCostTotal;
    }

    public void setSeatCostTotal(float seatCostTotal) {
        this.seatCostTotal = seatCostTotal;
    }

    public int getCallTotalTime() {
        return callTotalTime;
    }

    public void setCallTotalTime(int callTotalTime) {
        this.callTotalTime = callTotalTime;
    }

    public float getCallCostTotal() {
        return callCostTotal;
    }

    public void setCallCostTotal(float callCostTotal) {
        this.callCostTotal = callCostTotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }


}
