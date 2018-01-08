package com.sunll.web.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunll.clue.api.smsSupply.SmsSupplyService;
import com.sunll.common.util.Result;
import com.sunll.quartz.api.sms.SmsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/12/5.
 */
@Controller
@RequestMapping("/sms")
public class SmsController {
    @Reference(check = false,timeout = 100000)
    SmsService smsService;
    @Reference(check = false,timeout = 100000)
    SmsSupplyService smsSupplyService;

    /**
     * 获取消息列表
     * @param userId    用户id
     * @return          消息列表
     */
    @ResponseBody
    @RequestMapping("/selectSmsList")
    public Object selectMessageList(Integer userId) {
        Result result = smsService.smsShow(userId);
        return result;
    }

    /**
     * 获取所有消息列表
     * @param userId    用户id
     * @return          所有消息列表
     */
    @ResponseBody
    @RequestMapping("/selectAllSmsList")
    public Object selectAllMessageList(Integer userId) {
        Result result = smsService.smsAllShow(userId);
        return result;
    }

    /**
     * 修改所有消息状态
     * @param userId    用户id
     * @return          result
     */
    @ResponseBody
    @RequestMapping("/updateAllSmsList")
    public Object updateAllMessageList(Integer userId) {
        Result result = smsService.smsAllupdate(userId);
        return result;
    }

    /**
     * 修改某条消息状态
     * @param mmsId 消息id
     * @return      result
     */
    @ResponseBody
    @RequestMapping("/updateMmsOne")
    public Object updateMmsOne(Integer mmsId) {
        Result result = smsService.updateMmsOne(mmsId);
        return result;
    }

    /**
     * 修改某条消息状态
     * @return      result
     */
    @ResponseBody
    @RequestMapping("/checkIdentify")
    public Object checkIdentify(String identifyId) {
        return smsSupplyService.checkIdentify(identifyId);
    }

}
