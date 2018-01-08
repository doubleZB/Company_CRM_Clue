package com.sunll.systemset.api.template;

import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface OpenTemplateService {

    ApiSendData openTemplate(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

}
