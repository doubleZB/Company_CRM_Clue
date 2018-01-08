package com.sunll.common.api;

/**
 * API接口错误码
 */
public enum ApiReturnCode {

    //20000 状态的为返回成功值,尽量定1个值
    SUCCESS(20000, "成功"),

    //30000~40000 状态为各种已经存在的状态
    INFO_IS_EXIST(30000, "信息已存在"),
    USER_IS_EXIST(30001, "用户已经存在"),
    PHONE_IS_EXIST(30002, "手机号已经存在"),
    NAME_IS_EXIST(30003, "姓名已存在"),

    //40000~50000 状态的为各种查询失败的状态
    NO_DATA(40000, "数据不存在"),
    VERSION_NO_EXIST(40001, "版本号不存在"),
    VERIFICATION_CODE_NO_EXIST(40002, "验证码不存在"),
    USER_NO_EXIST(40100, "用户不存在"),
    USER_OR_ENTERPRISE_NO_EXIST(40200, "用户或企业不存在"),

    //50000~60000 状态的为各种操作失败状态
    ERROR(50000, "处理请求时发生异常"),
    USER_UPDATE_FAIL(50001, "用户信息更新失败"),
    PARAM_FORMAT_ERROR(50002, "参数格式错误"),
    USER_PASSWORD_ERROR(50003, "账号或密码错误"),
    REGISTER_FAIL(50004, "注册失败"),
    RESET_PASSWORD_FAIL(50005, "重置密码失败"),
    ADD_USER_FAIL(50006, "新增用户失败"),
    ADD_FAIL(50007, "新增失败"),
    OLD_PASSWORD_ERROR(50008, "原始密码错误"),
    UPDATE_PASSWORD_ERROR(50009, "修改密码失败"),
    CURRENT_RESOURCE_BEEN_TAKEN_OTHER(50010, "当前资源已被其他人占用"),
    UPDATE_FAIL(50011, "修改失败"),
    DELETE_FAIL(50012, "删除失败"),
    TRIAL_EXPIRED(50013, "试用到期"),
    INITIATE_TELECONFERENCE(50014, "发起电话会议失败"),
    TELECONFERENCE_MANAGE_FAIL(50015, "会议管理失败"),
    TELECONFERENCE_TERMINATION_FAIL(50016, "结束会议失败"),

    //70000~80000 状态的为各种参数为空
    PARAM_IS_EMPTY(70000, "请求参数为空"),
    VERSION_IS_EMPTY(70001, "版本号为空"),
    PARAM_USER_INFO_IS_EMPTY(70002, "请求参数中用户信息为空"),
    PARAM_USER_NAME_IS_EMPTY(70003, "请求参数中账号为空"),
    PARAM_USER_PASSWORD_IS_EMPTY(70004, "请求参数中密码为空"),;

    private int code;//返回状态值
    private String message;//返回信息

    private ApiReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ApiReturnCode resultMessage(int key) {
        switch (key) {
            case 20000:
                return SUCCESS;
            case 30000:
                return INFO_IS_EXIST;
            case 30003:
                return NAME_IS_EXIST;
            case 30100:
                return USER_IS_EXIST;
            case 40000:
                return NO_DATA;
            case 40001:
                return VERSION_NO_EXIST;
            case 40100:
                return USER_NO_EXIST;
            case 40200:
                return USER_OR_ENTERPRISE_NO_EXIST;
            case 50000:
                return ERROR;
            case 50002:
                return PARAM_FORMAT_ERROR;
            case 50003:
                return USER_PASSWORD_ERROR;
            case 50004:
                return REGISTER_FAIL;
            case 50005:
                return RESET_PASSWORD_FAIL;
            case 50006:
                return ADD_USER_FAIL;
            case 50007:
                return ADD_FAIL;
            case 50008:
                return OLD_PASSWORD_ERROR;
            case 50009:
                return UPDATE_PASSWORD_ERROR;
            case 50010:
                return CURRENT_RESOURCE_BEEN_TAKEN_OTHER;
            case 50011:
                return UPDATE_FAIL;
            case 50012:
                return DELETE_FAIL;
            case 50013:
                return TRIAL_EXPIRED;
            case 50014:
                return INITIATE_TELECONFERENCE;
            case 50015:
                return TELECONFERENCE_MANAGE_FAIL;
            case 50016:
                return TELECONFERENCE_TERMINATION_FAIL;
            case 50100:
                return USER_UPDATE_FAIL;
            case 70000:
                return PARAM_IS_EMPTY;
            case 70001:
                return VERSION_IS_EMPTY;
            case 70002:
                return PARAM_USER_INFO_IS_EMPTY;
            case 70003:
                return PARAM_USER_NAME_IS_EMPTY;
            case 70004:
                return PARAM_USER_PASSWORD_IS_EMPTY;
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(ApiReturnCode.SUCCESS);
    }
}
