package com.sunll.web.controller.systemset;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;
import com.sunll.systemset.api.sms.SmsTemSetService;
import org.apache.log4j.Logger;
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
@RequestMapping("/smsTemSet")
public class SmsTemSetController {

    private Logger log = Logger.getLogger(this.getClass());

    @Reference
    private SmsTemSetService smsTemSetService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "skipSmsTemSetPage")
    public String skipSmsTemSetPage() {
        return "systemset/smsTemSet";
    }

    /**
     * 获取自定义短信列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectSmsTemList", method = RequestMethod.POST)
    public ApiSendData selectSmsTemList(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取自定义短信列表接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = smsTemSetService.selectSmsTemList(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 新建模板短信模板
     */
    @ResponseBody
    @RequestMapping(value = "insertSmsTem", method = RequestMethod.POST)
    public ApiSendData insertSmsTem(@RequestBody ApiAcceptData apiAcceptData){
        log.info("验证该企业下是否存在改业务接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData = smsTemSetService.insertSmsTem(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 编辑短信模板
     */
    @ResponseBody
    @RequestMapping(value = "updateSmsTem", method = RequestMethod.POST)
    public ApiSendData updateSmsTem(@RequestBody ApiAcceptData apiAcceptData){
        log.info("验证该企业下是否存在改业务接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData = smsTemSetService.updateSmsTem(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 根据id获取信息
     */
    @ResponseBody
    @RequestMapping(value = "getSmsTemById", method = RequestMethod.POST)
    public ApiSendData getSmsTemById(@RequestBody ApiAcceptData apiAcceptData){
        log.info("验证该企业下是否存在改业务接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData = smsTemSetService.getSmsTemById(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 验证该企业下是否存在改短信模板
     */
    @ResponseBody
    @RequestMapping(value = "isExistSmsTemName", method = RequestMethod.POST)
    public ApiSendData isExistSmsTemName(@RequestBody ApiAcceptData apiAcceptData){
        log.info("验证该企业下是否存在改业务接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData = smsTemSetService.isExistSmsTemName(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 短信发送回调函数
     * @param par
     * @return
     */
    @ResponseBody
    @RequestMapping("/reNotify")
    public String reNotify(String par){
        log.info("短信回调par参数。。。。。"+par);
        return  smsTemSetService.updateSmsStatus(par);
    }

}
