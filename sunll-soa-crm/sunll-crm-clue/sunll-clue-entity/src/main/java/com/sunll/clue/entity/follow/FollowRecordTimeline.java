package com.sunll.clue.entity.follow;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator
 * on 2017/12/20
 * 跟进记录时间轴格式
 */
public class FollowRecordTimeline implements Serializable{

    private String year;

    private String monthAndDay;

    private List<FollowRecord> followRecordList;

    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonthAndDay() {
        return monthAndDay;
    }

    public void setMonthAndDay(String monthAndDay) {
        this.monthAndDay = monthAndDay;
    }

    public List<FollowRecord> getFollowRecordList() {
        return followRecordList;
    }

    public void setFollowRecordList(List<FollowRecord> followRecordList) {
        this.followRecordList = followRecordList;
    }
}
