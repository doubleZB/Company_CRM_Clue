
package com.sunll.clue.api.message;

import com.github.pagehelper.PageInfo;
import com.sunll.clue.entity.business.MessageTemplate;
import com.sunll.clue.entity.business.SendMessage;
import com.sunll.clue.entity.message.ShowCallCost;
import com.sunll.clue.entity.message.ShowCallDuration;
import com.sunll.clue.entity.message.ShowMessage;
import com.sunll.clue.entity.message.ShowMessageCosts;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/6.
 */
public interface MessageService {
    PageInfo<SendMessage> selectMessageTogether(String startTime, String endTime, Integer PageNum, Integer PageSize, List<Map> UserIdDepIds,Integer type);
    List<SendMessage> exportMessageTogether(String startTime, String endTime, Integer PageNum, Integer PageSize, List<Map> UserIdDepIds,Integer type);
    PageInfo selectMessageDetail(PageInfo<SendMessage> pageInfo);
    List<ShowMessage> exportMessageDetail(List<SendMessage> pageInfo);
    PageInfo selectMessageUserDetail(PageInfo<Map<String,Object>> pageInfo, String startTime, String endTime);
    PageInfo selectTotalExpensesDetail(PageInfo<Map<String,Object>> pageInfo, String startTime, String endTime);
    PageInfo selectTimeStatisticsDetail(PageInfo<Map<String,Object>> pageInfo, String startTime, String endTime);
    List<ShowMessageCosts> selectMessageUserDetailTotal(List<Map> pageInfo, String startTime, String endTime);
    List<ShowCallCost> selectTotalExpensesDetailTotal(List<Map> pageInfo, String startTime, String endTime);
    List<ShowCallDuration> selectTimeStatisticsDetailTotal(List<Map> pageInfo, String startTime, String endTime);
    List<String> getidentifyIds(Integer messageId);
    Double getConversionRateByUserId(Integer userId);
    Double getAllConversionRateByUserId(List<Integer> userIdList);
}
