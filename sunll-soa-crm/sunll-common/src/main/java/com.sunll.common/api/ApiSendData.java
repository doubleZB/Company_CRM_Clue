package com.sunll.common.api;

import java.io.Serializable;

/**
 * API接口数据返回的实体类
 */
public class ApiSendData implements Serializable{

    private ApiSendData() {
    }

    private static volatile ApiSendData instance;

    private Integer code; //数据返回状态码

    private String message; //数据返回的信息

    private String version; //返回数据的版本号

    private Object data;  //返回的数据

    public static ApiSendData getIstance() {
        if (instance == null) {
            synchronized (ApiSendData.class) {
                if (instance == null) {
                    instance = new ApiSendData();
                }
            }
        }
        return instance;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
}
