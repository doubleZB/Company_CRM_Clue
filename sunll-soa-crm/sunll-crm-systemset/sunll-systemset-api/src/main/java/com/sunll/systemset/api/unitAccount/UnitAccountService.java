package com.sunll.systemset.api.unitAccount;

import com.github.pagehelper.PageInfo;
import com.sunll.common.util.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5.
 */
public interface UnitAccountService {
    List<Map> listUserByPerMissions(String identifier, Object userId, Object companyId);
    List<Map> listUserByDepIdAndUserId(Object userId, Object companyId, Object checkedUserId, Object checkedDepartmentId);
    PageInfo<Map<String,Object>> listUserByDepIdAndUserIdPage(Object userId, Object companyId, Object checkedUserId,
                                                              Object checkedDepartmentId, Integer PageNum, Integer PageSize);
    String getUserbyUserId(Integer userId);
    String updateUserbyUserId(Integer userId,String name,String userEmail,Integer sex);
    List<Object> getDepartmentListbyUserId(int userId,int companyId);
}
