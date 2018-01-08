package com.sunll.systemset.api.business;

import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeFieldService {
    /**
     * 通过业务类型id以及父id获取业务类型下的所有字段
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData selectByBusinessTypeIdAndPid(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 添加、编辑区块
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData insertOrEditBusinessTypeField(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 获取字段信息
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData selectBusinessTypeField(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 启用、停用字段
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData isEnabledBusinessTypeField(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 是否必填
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData isRequiredBusinessTypeField(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 删除字段
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData delBusinessTypeField(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 根据业务类型id获取对应的区块信息
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData selBlockByBusinessTypeId(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 改业务类型下是否存在改区块
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData isExistBlock(ApiSendData apiSendData, ApiAcceptData apiAcceptData);
}
