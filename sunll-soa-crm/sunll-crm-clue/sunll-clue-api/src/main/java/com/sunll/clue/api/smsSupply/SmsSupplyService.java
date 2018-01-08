package com.sunll.clue.api.smsSupply;

import com.sunll.clue.entity.business.BusinessTypeValueIdentify;
import com.sunll.common.util.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 */
public interface SmsSupplyService {
    Integer selectUserIdBySpecificId(String specificId);
    List<BusinessTypeValueIdentify> selectSpecificIdListByUserId(Integer userId);
    String selectbusinessNameBybusinessId(Integer businessId);
    Result checkIdentify(String identifyId);
}
