package com.sunll.systemset.api.login;

import com.sunll.common.util.Result;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/4.
 */
public interface LoginService {
    Result getLoginAccountId(String accountNumber, String password, String applicationId);//登录
    Result getCompanyListByAccountId(Integer accountId);//登录
    Result loginOut(String key);//退出登录
    Result choseCompany(String companyId, String userId, String applicationId);//选择企业
    Object getPropertyData(String key, String className, String propertyName);
    Map getClassData(String key, String className);
    List<Object> getMenuAndPerClassData(String key, String className);
    Map getAllData(String key);
    Map getMenuAndPerAllData(String key);
    Object lastLoginTime(String companyId,String userId);
    String getAccountMobileByUserId(String userId);
}
