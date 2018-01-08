package com.sunll.common.util;

/**
 * Created by Administrator on 2017/12/6.
 */
public class DataEnumerate {
    public static final Integer INT_NUM_ZERO = 0;
    public static final String STRING_NUM_ZERO = "0";
    public static final Integer INT_NUM_ONE = 1;
    public static final String STRING_NUM_ONE = "1";
    public static final Integer INT_NUM_TWO = 2;
    public static final String STRING_NUM_TWO = "2";
    public static final Double DOUBLE_COST=0.3;//短信每条价格
    public static final String TO_STORE="pointStore";//专向门店别名

    //应用编号 1：云通讯 2：人脉旺 3:PM
    public static class APP {
        public static final Integer YUNTONGXUN = 1;
        public static final String STRING_YUNTONGXUN = "1";
        public static final Integer CRM = 2;
        public static final String STRING_CRM = "2";
        public static final Integer PM = 3;
        public static final String STRING_PM = "3";
    }
    //极光推送类型 1 消息推送
    public static class JPUSH_TYPE {
        public static final String MESSAGE_TYPE = "1";
    }
}
