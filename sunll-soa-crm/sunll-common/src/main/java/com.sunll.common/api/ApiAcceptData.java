package com.sunll.common.api;

import java.io.Serializable;

/**
 * API接口接受参数的实体类
 */
public class ApiAcceptData implements Serializable{

    private ApiAcceptData() {
    }

    private static volatile ApiAcceptData instance;

    private String version;   //接受数据的版本号

    private Object data;      //接受的数据

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ApiAcceptData getIstance() {
        if (instance == null) {
            synchronized (ApiAcceptData.class) {
                if (instance == null) {
                    instance = new ApiAcceptData();
                }
            }
        }
        return instance;
    }

}
