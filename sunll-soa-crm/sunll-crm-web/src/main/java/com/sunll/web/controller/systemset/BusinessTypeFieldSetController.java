package com.sunll.web.controller.systemset;

import com.alibaba.fastjson.JSON;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;
import com.sunll.systemset.api.business.BusinessTypeFieldService;
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
 * on 2017/12/4
 */
@Controller
@RequestMapping("/BusinessTypeFieldSet")
public class BusinessTypeFieldSetController {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private BusinessTypeFieldService businessTypeFieldService;

    /**
     * 跳转自定义业务设置页面
     *
     * @return
     */
    @RequestMapping(value = "skipBusinessTypeFieldSetPage")
    public String skipBusinessTypeFieldSetPage(Model model,Integer businessTypeId) {
        model.addAttribute("businessTypeId",businessTypeId);
        return "/systemset/businessTypeFieldSet";
    }

    /**
     * 获取自定义业务类型字段
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectByBusinessTypeIdAndPid", method = RequestMethod.POST)
    public ApiSendData selectByBusinessTypeIdAndPid(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取获取自定义业务类型字段列表接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeFieldService.selectByBusinessTypeIdAndPid(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 添加或者编辑区块
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insertOrEditBlock", method = RequestMethod.POST)
    public ApiSendData insertOrEditBlock(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取添加或者编辑区块接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeFieldService.insertOrEditBusinessTypeField(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 根据字段id获取字段的信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectBusinessTypeField", method = RequestMethod.POST)
    public ApiSendData selectBusinessTypeField(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取字段信息接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeFieldService.selectBusinessTypeField(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 启用、停用字段
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "isEnabledBusinessTypeField", method = RequestMethod.POST)
    public ApiSendData isEnabledBusinessTypeField(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取启用、停用字段信息接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeFieldService.isEnabledBusinessTypeField(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 是否必填字段
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "isRequiredBusinessTypeField", method = RequestMethod.POST)
    public ApiSendData isRequiredBusinessTypeField(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取是否必填字段信息接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeFieldService.isRequiredBusinessTypeField(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 删除字段
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delBusinessTypeField", method = RequestMethod.POST)
    public ApiSendData delBusinessTypeField(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取删除字段信息接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeFieldService.delBusinessTypeField(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 获取区块信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selBlockByBusinessTypeId", method = RequestMethod.POST)
    public ApiSendData selBlockByBusinessTypeId(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取区块信息接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = businessTypeFieldService.selBlockByBusinessTypeId(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 验证区块是否存在
     */
    @ResponseBody
    @RequestMapping(value = "isExistBlock", method = RequestMethod.POST)
    public ApiSendData isExistBlock(@RequestBody ApiAcceptData apiAcceptData){
        log.info("验证区块是否存在接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData = businessTypeFieldService.isExistBlock(apiSendData, apiAcceptData);
        return apiSendData;
    }
}
