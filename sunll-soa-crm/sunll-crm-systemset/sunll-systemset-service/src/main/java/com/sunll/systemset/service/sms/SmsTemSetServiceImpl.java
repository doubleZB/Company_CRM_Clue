package com.sunll.systemset.service.sms;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.common.util.SmsUtil;
import com.sunll.systemset.api.sms.SmsTemSetService;
import com.sunll.systemset.dao.sms.MessageTemplateMapper;
import com.sunll.systemset.dao.sms.SendReceiveMapper;
import com.sunll.systemset.entity.sms.MessageTemplate;
import com.sunll.systemset.entity.sms.MessageTemplateExample;
import com.sunll.systemset.entity.sms.SendReceive;
import com.sunll.systemset.entity.sms.SendReceiveExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by sunll
 * on 2017/12/5.
 */

@Service
public class SmsTemSetServiceImpl implements SmsTemSetService {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;
    @Autowired
    private SendReceiveMapper sendReceiveMapper;

    @Override
    public ApiSendData selectSmsTemList(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {

        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));

        //获取企业id
        Integer companyId = Integer.valueOf(data.get("companyId").toString());

        MessageTemplateExample messageTemplateExample = new MessageTemplateExample();
        messageTemplateExample.createCriteria().andCompanyIdEqualTo(companyId).andIsDelEqualTo(1);
        List<MessageTemplate> messageTemplateList = messageTemplateMapper.selectByExample(messageTemplateExample);
        apiSendData.setData(messageTemplateList);
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        return apiSendData;
    }

    @Override
    public ApiSendData isExistSmsTemName(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));

        Integer companyId = Integer.valueOf(data.get("companyId").toString());
        String smsTemName = (String) data.get("smsTemName");
        String smsTemId = (String) data.get("smsTemId");
        MessageTemplateExample messageTemplateExample = new MessageTemplateExample();
        messageTemplateExample.createCriteria()
                .andCompanyIdEqualTo(companyId)
                .andNameEqualTo(smsTemName)
                .andIsDelEqualTo(1);
        List<MessageTemplate> messageTemplateList = messageTemplateMapper.selectByExample(messageTemplateExample);
        if (messageTemplateList.size() > 0) {
            if ("".equals(smsTemId)) {
                apiSendData.setCode(ApiReturnCode.INFO_IS_EXIST.getCode());
            } else {
                if (String.valueOf(messageTemplateList.get(0).getId()).equals(smsTemId)) {
                    apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                } else {
                    apiSendData.setCode(ApiReturnCode.INFO_IS_EXIST.getCode());
                }
            }

        } else {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData insertSmsTem(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型
        MessageTemplate messageTemplate = JSON.parseObject(JSON.toJSONString(data.get("smsTem")), MessageTemplate.class);
        messageTemplate.setCreateTime(new Date());
        messageTemplate.setIsDel(1);
        messageTemplate.setStatus(2);
        int num = messageTemplateMapper.insertSelective(messageTemplate);
        //发送短信
        String smsContent = "【" + messageTemplate.getSignature() + "】" + messageTemplate.getContent();
        String smsId = SmsUtil.SendSms(messageTemplate.getTestPhone(), smsContent);
        SendReceive sendReceive = new SendReceive();
        sendReceive.setSendId(messageTemplate.getId());
        sendReceive.setMessageReturn(smsId);
        sendReceive.setMessageType(1);
        int smsNum = sendReceiveMapper.insertSelective(sendReceive);
        if (num > 0 && smsNum > 0) {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData updateSmsTem(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型
        MessageTemplate messageTemplate = JSON.parseObject(JSON.toJSONString(data.get("smsTem")), MessageTemplate.class);
        messageTemplate.setUpdateTime(new Date());
        messageTemplate.setStatus(2);
        int num = messageTemplateMapper.updateByPrimaryKeySelective(messageTemplate);
        //发送短信
        String smsContent = "【" + messageTemplate.getSignature() + "】" + messageTemplate.getContent();
        String smsId = SmsUtil.SendSms(messageTemplate.getTestPhone(), smsContent);
        SendReceive sendReceive = new SendReceive();
        sendReceive.setSendId(messageTemplate.getId());
        sendReceive.setMessageReturn(smsId);
        sendReceive.setMessageType(1);
        SendReceiveExample sendReceiveExample = new SendReceiveExample();
        sendReceiveExample.createCriteria().andSendIdEqualTo(messageTemplate.getId());
        int smsNum = sendReceiveMapper.updateByExampleSelective(sendReceive, sendReceiveExample);
        if (num > 0 && smsNum > 0) {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData getSmsTemById(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型
        Integer smsTemId = (Integer) data.get("smsTemId");
        MessageTemplateExample messageTemplateExample = new MessageTemplateExample();
        messageTemplateExample.createCriteria()
                .andIdEqualTo(smsTemId);
        List<MessageTemplate> messageTemplateList = messageTemplateMapper.selectByExample(messageTemplateExample);
        if (messageTemplateList.size() > 0) {
            apiSendData.setData(messageTemplateList.get(0));
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public String updateSmsStatus(String par) {
        //通过逗号分隔各参数
        String[] rePar = par.split(",");
        //手机号
        String phone = rePar[3];
        //短信请求的rid
        String rid = rePar[4];
        //短信发送状态
        String isSuccess = rePar[5];
        //获取发送短信的信息
        SendReceiveExample sendReceiveExample = new SendReceiveExample();
        sendReceiveExample.createCriteria().andMessageReturnEqualTo(rid);
        List<SendReceive> sendReceiveList = sendReceiveMapper.selectByExample(sendReceiveExample);
        if (sendReceiveList.size() > 0) {
            SendReceive sendReceive = sendReceiveList.get(0);
            //模板短信测试
            if (sendReceive.getMessageType() == 1) {
                MessageTemplate messageTemplate = new MessageTemplate();
                messageTemplate.setId(sendReceive.getSendId());
                if ("DELIVRD".equals(isSuccess) || "0".equals(isSuccess)) {
                    messageTemplate.setStatus(1);
                } else {
                    messageTemplate.setStatus(3);
                }
                int num = messageTemplateMapper.updateByPrimaryKeySelective(messageTemplate);
                if (num > 0) {
                    return "0";
                }
            }else {
                //添加短信群发状态更新代码
                log.info("进入短信群发模板");
            }
        }
        return "0";
    }

}
