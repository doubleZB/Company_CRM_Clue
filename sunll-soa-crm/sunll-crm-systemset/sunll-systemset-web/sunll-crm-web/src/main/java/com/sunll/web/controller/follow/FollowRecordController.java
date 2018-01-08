package com.sunll.web.controller.follow;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunll.clue.api.follow.FollowService;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Administrator
 * on 2017/12/20
 */
@Controller
@RequestMapping("/followRecord")
public class FollowRecordController {

    private Logger log = Logger.getLogger(this.getClass());

    @Reference(check = false,timeout = 100000)
    private FollowService followService;


    /**
     * 获取跟进记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectFollowRecord", method = RequestMethod.POST)
    public ApiSendData selectFollowRecord(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取跟进记录接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (org.apache.commons.lang3.StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new ArrayList<>());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData = followService.selectFollowRecord(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new ArrayList<>());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new ArrayList<>());
            e.printStackTrace();
        }
        return apiSendData;
    }

    /**
     * 新建跟进记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insertFollowRecord", method = RequestMethod.POST)
    public ApiSendData changeFollowStatus(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("新建跟进记录接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (org.apache.commons.lang3.StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData = followService.insertFollowRecord(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
            e.printStackTrace();
        }
        return apiSendData;
    }

    /**
     * 删除跟进记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delFollowRecord", method = RequestMethod.POST)
    public ApiSendData delFollowRecord(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("新建跟进记录接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (org.apache.commons.lang3.StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData = followService.delFollowRecord(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
            e.printStackTrace();
        }
        return apiSendData;
    }

}
