package com.sunll.common.api;

/**
 * CenterAPI接口错误码
 */
public enum CenterApiReturnCode {
    //通过HTTP POST方式提交请求，云通讯平台收到请求后，向两个电话终端发起呼叫请求，接通电话后进行通话。
    SUCCESS(1000, "成功"),
    USERNAME_PWD_ERROR(1001, "用户名或密码错误"),
    PARAMETER_MISS(1002, "缺少请求参数"),
    CONVERSATION_FAIL(1003, "创建通话失败"),
    SYSTEM_ERROR(1010, "系统错误"),
    //ss

    VERSION_IS_EMPTY(70001, "版本号为空"),
    VERSION_NO_EXIST(40001, "版本号不存在"),
    ERROR(50000, "处理请求时发生异常"),;

    private int code;//返回状态值
    private String message;//返回信息

    private CenterApiReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static CenterApiReturnCode resultMessage(int key) {
        switch (key) {
            case 1000:
                return SUCCESS;
            case 1001:
                return USERNAME_PWD_ERROR;
            case 1002:
                return PARAMETER_MISS;
            case 1003:
                return CONVERSATION_FAIL;
            case 1010:
                return SYSTEM_ERROR;
            case 40001:
                return VERSION_NO_EXIST;
            case 70001:
                return VERSION_IS_EMPTY;
            case 50000:
                return ERROR;
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(CenterApiReturnCode.SUCCESS);
    }
}
