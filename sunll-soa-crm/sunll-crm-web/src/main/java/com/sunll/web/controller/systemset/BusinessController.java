package com.sunll.web.controller.systemset;

import com.alibaba.fastjson.JSON;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.common.util.PropertiesUtils;
import com.sunll.systemset.api.business.BusinessService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sunll
 * on 2017/11/30
 */
@Controller
@RequestMapping("/business")
public class BusinessController {

    private Logger log = Logger.getLogger(this.getClass());

    private static final String UNITE_ACCOUNT_BASE_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("UNITE_ACCOUNT_BASE_URL");

    @Autowired
    private BusinessService businessService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "skipBusiness")
    public String skipBusiness() {
        return "systemset/business";
    }
    /**
     *
     * @return
     */
    @RequestMapping(value = "skipDepartmentMultiple")
    public String skipDepartmentMultiple(Model model) {
        model.addAttribute("UNITE_ACCOUNT_BASE_URL",UNITE_ACCOUNT_BASE_URL);
        return "systemset/multipleDepartments";
    }

    /**
     * 获取自定义业务列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectBusiness", method = RequestMethod.POST)
    public ApiSendData selectBusiness(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取自定义业务列表接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessService.selectBusiness(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 插入自定义业务
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insertBusiness", method = RequestMethod.POST)
    public ApiSendData insertBusiness(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("插入自定义业务接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("插入自定义业务接口请求参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData = businessService.insertBusiness(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("插入自定义业务接口请求参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }

    /**
     * 编辑自定义业务
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateBusiness", method = RequestMethod.POST)
    public ApiSendData updateBusiness(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("更新自定义业务接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("更新自定义业务接口请求参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData = businessService.updateBusiness(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("更新自定义业务接口请求参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;
    }

    /**
     * 删除自定义业务
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delBusiness", method = RequestMethod.POST)
    public ApiSendData delBusiness(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("删除自定义业务接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData = businessService.delBusiness(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 是否启用自定义业务
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "isEnabledBusiness", method = RequestMethod.POST)
    public ApiSendData isEnabledBusiness(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("删除自定义业务接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData = businessService.isEnabledBusiness(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 根据id获取业务信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getBusinessById", method = RequestMethod.POST)
    public ApiSendData getBusinessById(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("删除自定义业务接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData = businessService.getBusinessById(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 验证该企业下是否存在改业务
     */
    @ResponseBody
    @RequestMapping(value = "isExistBusinessName", method = RequestMethod.POST)
    public ApiSendData isExistBusinessName(@RequestBody ApiAcceptData apiAcceptData){
        log.info("验证该企业下是否存在改业务接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData = businessService.isExistBusinessName(apiSendData, apiAcceptData);
        return apiSendData;
    }
}
