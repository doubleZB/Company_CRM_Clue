package com.sunll.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义响应结构
 */
public class CenterResult implements Serializable {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer statuscode;

    // 响应消息
    private String msge;

    // 响应中的数据
    private Object callback;

    public static CenterResult build(Integer statuscode, String msge, Object callback) {
        return new CenterResult(statuscode, msge, callback);
    }

    public static CenterResult ok(Object callback) {
        return new CenterResult(callback);
    }

    public static CenterResult ok() {
        return new CenterResult(null);
    }

    public CenterResult() {

    }

    public static CenterResult build(Integer statuscode, String msge) {
        return new CenterResult(statuscode, msge, null);
    }

    public CenterResult(Integer statuscode, String msge, Object callback) {
        this.statuscode = statuscode;
        this.msge = msge;
        this.callback = callback;
    }

    public CenterResult(Object callback) {
        this.statuscode = 1000;
        this.msge = "成功";
        this.callback = callback;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public String getMsge() {
        return msge;
    }

    public void setMsge(String msge) {
        this.msge = msge;
    }

    public Object getCallback() {
        return callback;
    }

    public void setCallback(Object callback) {
        this.callback = callback;
    }

    /**
     * 将json结果集转化为Result对象
     *
     * @param jsoncallback json数据
     * @param clazz Result中的object类型
     * @return
     */
    public static CenterResult formatToPojo(String jsoncallback, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsoncallback, CenterResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsoncallback);
            JsonNode callback = jsonNode.get("callback");
            Object obj = null;
            if (clazz != null) {
                if (callback.isObject()) {
                    obj = MAPPER.readValue(callback.traverse(), clazz);
                } else if (callback.isTextual()) {
                    obj = MAPPER.readValue(callback.asText(), clazz);
                }
            }
            return build(jsonNode.get("statuscode").intValue(), jsonNode.get("msge").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static CenterResult format(String json) {
        try {
            return MAPPER.readValue(json, CenterResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsoncallback json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static CenterResult formatToList(String jsoncallback, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsoncallback);
            JsonNode callback = jsonNode.get("callback");
            Object obj = null;
            if (callback.isArray() && callback.size() > 0) {
                obj = MAPPER.readValue(callback.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("statuscode").intValue(), jsonNode.get("msge").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
