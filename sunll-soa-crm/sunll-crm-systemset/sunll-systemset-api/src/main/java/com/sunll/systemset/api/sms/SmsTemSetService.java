package com.sunll.systemset.api.sms;

import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;

/**
 * Created by sunll
 * on 2017/12/5.
 */
public interface SmsTemSetService {

    /**
     * 获取企业下的所有短信模板
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData selectSmsTemList(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 该企业下是否存在模板信息
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData isExistSmsTemName(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 新建模板信息
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData insertSmsTem(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 编辑模板信息
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData updateSmsTem(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 根据id获取模板信息
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData getSmsTemById(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 短信发送状态更新
     * @return
     */
    String updateSmsStatus(String par);

}
