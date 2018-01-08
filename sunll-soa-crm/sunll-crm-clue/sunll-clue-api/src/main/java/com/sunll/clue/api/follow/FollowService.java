package com.sunll.clue.api.follow;

import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface FollowService {

    /**
     * 新建跟进信息
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData insertFollowRecord(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    ApiSendData updateFollowRecord(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    ApiSendData selectFollowRecord(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 删除跟进信息
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData delFollowRecord(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

}
