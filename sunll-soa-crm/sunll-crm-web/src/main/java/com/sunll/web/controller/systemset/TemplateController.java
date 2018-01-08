package com.sunll.web.controller.systemset;

import com.alibaba.fastjson.JSON;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.systemset.api.template.OpenTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sunll
 * on 2017/11/30
 */
@Controller
@RequestMapping("/template")
public class TemplateController {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private OpenTemplateService openTemplateService;

    @ResponseBody
    @RequestMapping(value = "/openTemplate", method = RequestMethod.POST)
    public ApiSendData openTemplate(@RequestBody ApiAcceptData apiAcceptData){

        log.info("开通模板接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                log.info("开通模板接口请求参数：" + JSON.toJSONString(apiSendData));
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData = openTemplateService.openTemplate(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }
        log.info("开通模板接口请求参数：" + JSON.toJSONString(apiSendData));
        return apiSendData;

    }


}
