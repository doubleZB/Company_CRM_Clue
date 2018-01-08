package com.sunll.systemset.api.business;

import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;
import com.sunll.systemset.entity.business.Business;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessService {

    int insertSelective(Business record);

    int updateByPrimaryKeySelective(Business record);

    ApiSendData insertBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    ApiSendData updateBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    ApiSendData selectBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    ApiSendData delBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    ApiSendData isEnabledBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    ApiSendData getBusinessById(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    ApiSendData isExistBusinessName(ApiSendData apiSendData, ApiAcceptData apiAcceptData);
}
