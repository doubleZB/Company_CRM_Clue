package com.sunll.web.controller.systemset;

import com.alibaba.fastjson.JSON;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;
import com.sunll.systemset.api.business.BusinessTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sunll
 * on 2017/12/4
 */
@Controller
@RequestMapping("/businessType")
public class BusinessTypeController {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private BusinessTypeService businessTypeService;

    /**
     * 跳转到业务类型页面
     * @return
     */
    @RequestMapping(value = "skipBusinessTypePage")
    public String skipBusinessTypePage() {
        return "/systemset/businessType";
    }

    /**
     * 获取自定义业务以及业务字段列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectBusinessAndBusinessType", method = RequestMethod.POST)
    public ApiSendData selectBusinessAndBusinessType(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取业务类型列表接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeService.listBusinessByCompanyId(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 启用，停用业务类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "isEnabledBusinessType", method = RequestMethod.POST)
    public ApiSendData isEnabledBusinessType(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取启用，停用业务类型接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeService.isEnabledBusinessType(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 编辑业务类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editBusinessType", method = RequestMethod.POST)
    public ApiSendData editBusinessType(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取启用，停用业务类型接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeService.editBusinessType(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 删除业务类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delBusinessType", method = RequestMethod.POST)
    public ApiSendData delBusinessType(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取启用，停用业务类型接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeService.delBusinessType(apiSendData, apiAcceptData);
        return apiSendData;
    }

}
