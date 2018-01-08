package com.sunll.web.controller.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.common.util.DataEnumerate;
import com.sunll.common.util.JsonUtils;
import com.sunll.common.util.Result;
import com.sunll.quartz.api.sms.SmsService;
import com.sunll.quartz.entity.sms.Sms;
import com.sunll.systemset.api.login.LoginService;
import com.sunll.systemset.api.unitAccount.UnitAccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/12/8.
 */
@Controller
@RequestMapping("/api")
public class ApiAction {
    private Logger log = Logger.getLogger(this.getClass());
    @Reference(check = false,timeout = 100000)
    LoginService loginService;
    @Reference(check = false,timeout = 100000)
    SmsService smsService;
    @Reference(check = false,timeout = 100000)
    UnitAccountService unitAccountService;

    /**
     * 应用APP登录接口
     * @param apiAcceptData 接受参数 账号 密码
     * @return user对象
     */
    @ResponseBody
    @RequestMapping(value = "/getLoginAccountId", method = RequestMethod.POST)
    public ApiSendData getLoginAccountId(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("登录接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        JSONObject returnjson = new JSONObject();
        apiSendData.setData(returnjson);
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                Map map = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()), Map.class);
                if (map != null) {
                    JSONObject json = new JSONObject();
                    String accountNumber = map.get("accountNumber").toString();
                    String password = map.get("password").toString();
                    Result result = loginService.getLoginAccountId(accountNumber, password, DataEnumerate.APP.STRING_CRM);
                    if (result.getStatus() == 200) {
                        json.put("account",result.getData());
                        apiSendData.setData(json);
                        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                    } else {
                        apiSendData.setCode(ApiReturnCode.USER_PASSWORD_ERROR.getCode());
                        apiSendData.setMessage(result.getMsg());
                    }
                } else {
                    apiSendData.setCode(ApiReturnCode.PARAM_FORMAT_ERROR.getCode());
                    apiSendData.setMessage(ApiReturnCode.PARAM_FORMAT_ERROR.getMessage());
                }
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }

    /**
     * 获取企业列表
     * @param apiAcceptData 接受参数 账号 密码
     * @return 企业列表
     */
    @ResponseBody
    @RequestMapping(value = "/getCompanyListByAccountId", method = RequestMethod.POST)
    public ApiSendData getCompanyListByAccountId(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取企业列表接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        JSONObject jsonReturn = new JSONObject();
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                Map map = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()), Map.class);
                if (map != null) {
                    JSONObject json = new JSONObject();
                    String accountId = map.get("accountId").toString();
                    Integer accountIdInteger = Integer.parseInt(accountId);
                    Result result = loginService.getCompanyListByAccountId(accountIdInteger);
                    if (result.getStatus() == 200) {
                        json.put("companyList",result.getData());
                        apiSendData.setData(json);
                        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                    } else {
                        apiSendData.setData(jsonReturn);
                        apiSendData.setCode(ApiReturnCode.USER_NO_EXIST.getCode());
                        apiSendData.setMessage(ApiReturnCode.USER_NO_EXIST.getMessage());
                    }
                } else {
                    apiSendData.setData(jsonReturn);
                    apiSendData.setCode(ApiReturnCode.PARAM_FORMAT_ERROR.getCode());
                    apiSendData.setMessage(ApiReturnCode.PARAM_FORMAT_ERROR.getMessage());
                }
            } else {
                apiSendData.setData(jsonReturn);
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setData(jsonReturn);
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }

    /**
     * 选择企业
     * @param apiAcceptData 接受参数 账号 密码
     * @return user对象
     */
    @ResponseBody
    @RequestMapping(value = "/choseCompany", method = RequestMethod.POST)
    public ApiSendData choseCompany(HttpServletResponse response,@RequestBody ApiAcceptData apiAcceptData) {
        log.info("选择企业接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        JSONObject jsonReturn = new JSONObject();
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                Map map = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()), Map.class);
                if (map != null) {
                    JSONObject json = new JSONObject();
                    String userId = map.get("userId").toString();
                    String companyId = map.get("companyId").toString();
                    Result result = loginService.choseCompany(companyId, userId, DataEnumerate.APP.STRING_CRM);
                    if (result.getStatus() == 200) {
                        int userid = Integer.parseInt(userId);
                        int companyid = Integer.parseInt(companyId);
                        Integer userIdInteger = Integer.parseInt(userId);
                        String userbyUserId = unitAccountService.getUserbyUserId(userIdInteger);
                        Map map1 = JsonUtils.jsonToPojo(userbyUserId, Map.class);
                        List<Object> departmentListbyUserId = unitAccountService.getDepartmentListbyUserId(userid, companyid);
                        json.put("departments",departmentListbyUserId);
                        json.put("user",map1);
                        apiSendData.setData(json);
                        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                        apiSendData.setMessage(result.getMsg());
                    } else {
                        apiSendData.setData(jsonReturn);
                        apiSendData.setCode(ApiReturnCode.USER_OR_ENTERPRISE_NO_EXIST.getCode());
                        apiSendData.setMessage(result.getMsg());
                    }
                } else {
                    apiSendData.setData(jsonReturn);
                    apiSendData.setCode(ApiReturnCode.PARAM_FORMAT_ERROR.getCode());
                    apiSendData.setMessage(ApiReturnCode.PARAM_FORMAT_ERROR.getMessage());
                }
            } else {
                apiSendData.setData(jsonReturn);
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setData(jsonReturn);
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }

    /**
     * 更新某条消息状态
     * @param apiAcceptData 接受参数
     * @return user对象
     */
    @ResponseBody
    @RequestMapping(value = "/updateMmsOne", method = RequestMethod.POST)
    public ApiSendData updateMmsOne(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("更新某条消息状态接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        JSONObject jsonReturn = new JSONObject();
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                Map map = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()), Map.class);
                if (map != null) {
                    JSONObject json = new JSONObject();
                    String mmsId = (String)map.get("mmsId");
                    int mmsid = Integer.parseInt(mmsId);
                    Result result = smsService.updateMmsOne(mmsid);
                    if(result.getStatus()==200){
                        apiSendData.setData(json);
                        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                    } else {
                        apiSendData.setData(jsonReturn);
                        apiSendData.setCode(ApiReturnCode.UPDATE_FAIL.getCode());
                        apiSendData.setMessage(ApiReturnCode.UPDATE_FAIL.getMessage());
                    }

                } else {
                    apiSendData.setData(jsonReturn);
                    apiSendData.setCode(ApiReturnCode.PARAM_FORMAT_ERROR.getCode());
                    apiSendData.setMessage(ApiReturnCode.PARAM_FORMAT_ERROR.getMessage());
                }
            } else {
                apiSendData.setData(jsonReturn);
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setData(jsonReturn);
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }


    /**
     * 查询上次登录时间
     * @param apiAcceptData 接受参数
     * @return user对象
     */
    @ResponseBody
    @RequestMapping(value = "/lastLoginTime", method = RequestMethod.POST)
    public ApiSendData lastLoginTime(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("查询上次登录时间接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        JSONObject jsonReturn = new JSONObject();
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                Map map = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()), Map.class);
                if (map != null) {
                    JSONObject json = new JSONObject();
                    String userId = map.get("userId").toString();
                    String companyId = map.get("companyId").toString();
//                    String CRM_TOKEN = (String)map.get("CRM_TOKEN");
//                    String loginTime = (String)loginService.getPropertyData(CRM_TOKEN, "userCompany", "loginTime");
                    Object loginTime = loginService.lastLoginTime(companyId,userId);
                    if(loginTime !=null){
                        json.put("lastLoginTime",loginTime);
                        apiSendData.setData(json);
                        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                    } else {
                        apiSendData.setData(jsonReturn);
                        apiSendData.setCode(ApiReturnCode.ERROR.getCode());
                        apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
                    }

                } else {
                    apiSendData.setData(jsonReturn);
                    apiSendData.setCode(ApiReturnCode.PARAM_FORMAT_ERROR.getCode());
                    apiSendData.setMessage(ApiReturnCode.PARAM_FORMAT_ERROR.getMessage());
                }
            } else {
                apiSendData.setData(jsonReturn);
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setData(jsonReturn);
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }

    /**
     * 按类型获取消息列表
     * @param apiAcceptData 接受参数
     * @return user对象
     */
    @ResponseBody
    @RequestMapping(value = "/getSmsAllShowByType", method = RequestMethod.POST)
    public ApiSendData getSmsAllShowByType(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("按类型获取消息列表接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        JSONObject jsonReturn = new JSONObject();
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                Map map = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()), Map.class);
                if (map != null) {
                    JSONObject json = new JSONObject();
                    String type = (String)map.get("type");
                    String userId = map.get("userId").toString();
                    String appPageSizeStr = map.get("appPageSizeStr").toString();
                    String appPageNumberStr = map.get("appPageNumberStr").toString();
                    int userid = Integer.parseInt(userId);
                    PageInfo<Sms> pageInfo = smsService.getSmsAllShowByTypeApp(userid, type, appPageSizeStr, appPageNumberStr);
                    if(pageInfo!=null){
                        json.put("mmsList",pageInfo.getList());
                        apiSendData.setData(json);
                        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                    } else {
                        apiSendData.setData(jsonReturn);
                        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                    }

                } else {
                    apiSendData.setData(jsonReturn);
                    apiSendData.setCode(ApiReturnCode.PARAM_FORMAT_ERROR.getCode());
                    apiSendData.setMessage(ApiReturnCode.PARAM_FORMAT_ERROR.getMessage());
                }
            } else {
                apiSendData.setData(jsonReturn);
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setData(jsonReturn);
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }

    /**
     * 查询未读消息数目
     * @param apiAcceptData 接受参数
     * @return user对象
     */
    @ResponseBody
    @RequestMapping(value = "/getSmsAllShowNum", method = RequestMethod.POST)
    public ApiSendData getSmsAllShowNum(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("查询未读消息数目请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        JSONObject jsonReturn = new JSONObject();
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                Map map = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()), Map.class);
                if (map != null) {
                    JSONObject json = new JSONObject();
//                    String CRM_TOKEN = (String)map.get("CRM_TOKEN");
//                    Object data = loginService.getPropertyData(CRM_TOKEN, "user", "id");
//                    int userId = (int)data;
                    String userId = map.get("userId").toString();
                    int userid = Integer.parseInt(userId);
                    Result result = smsService.getSmsAllShowNum(userid);//未读
                    Object data1 = result.getData();
                    if(result.getStatus()==200){
                        json.put("smsNum",data1);
                        apiSendData.setData(json);
                        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                    } else {
                        apiSendData.setData(jsonReturn);
                        apiSendData.setCode(ApiReturnCode.ERROR.getCode());
                        apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
                    }

                } else {
                    apiSendData.setData(jsonReturn);
                    apiSendData.setCode(ApiReturnCode.PARAM_FORMAT_ERROR.getCode());
                    apiSendData.setMessage(ApiReturnCode.PARAM_FORMAT_ERROR.getMessage());
                }
            } else {
                apiSendData.setData(jsonReturn);
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setData(jsonReturn);
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }

    /**
     * 修改个人中心接口
     * @param apiAcceptData 接受参数
     * @return user对象
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserbyUserId", method = RequestMethod.POST)
    public ApiSendData updateUserbyUserId(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("修改个人中心接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        JSONObject jsonReturn = new JSONObject();
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                Map map = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()), Map.class);
                if (map != null) {
                    JSONObject json = new JSONObject();
//                    String CRM_TOKEN = (String)map.get("CRM_TOKEN");
                    String name = (String)map.get("name");
                    String userEmail = (String)map.get("userEmail");
                    int sex = (int)map.get("sex");
//                    Object data = loginService.getPropertyData(CRM_TOKEN, "user", "id");
//                    int userId = (int)data;
                    String userId = map.get("userId").toString();
                    int userid = Integer.parseInt(userId);
                    String s = unitAccountService.updateUserbyUserId(userid, name, userEmail, sex);
                    if(StringUtils.isNoneBlank(s)){
                        apiSendData.setData(json);
                        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                    } else {
                        apiSendData.setData(jsonReturn);
                        apiSendData.setCode(ApiReturnCode.ERROR.getCode());
                        apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
                    }

                } else {
                    apiSendData.setData(jsonReturn);
                    apiSendData.setCode(ApiReturnCode.PARAM_FORMAT_ERROR.getCode());
                    apiSendData.setMessage(ApiReturnCode.PARAM_FORMAT_ERROR.getMessage());
                }
            } else {
                apiSendData.setData(jsonReturn);
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setData(jsonReturn);
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("登录接口响应参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }


}
