package com.sunll.common.util;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/3.
 */
public class ResultMap {
    // 响应中的数据
    private Map<String,Object> data;

    public ResultMap() {
    }

    public ResultMap(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}