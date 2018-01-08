package com.sunll.systemset.api.business;

import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeService {

    /**
     * 通过企业id以及组织获取所有的业务以及业务对应的业务类型
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData listBusinessByCompanyId(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 启用、停用业务类型
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData isEnabledBusinessType(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 编辑业务类型
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData editBusinessType(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 删除业务类型
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData delBusinessType(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

}
