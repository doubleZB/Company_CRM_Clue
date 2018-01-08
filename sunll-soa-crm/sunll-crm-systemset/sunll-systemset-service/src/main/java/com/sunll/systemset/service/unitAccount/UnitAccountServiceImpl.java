package com.sunll.systemset.service.unitAccount;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.sunll.common.redis.JedisClient;
import com.sunll.common.util.*;
import com.sunll.systemset.api.unitAccount.UnitAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5.
 */
@Service
public class UnitAccountServiceImpl implements UnitAccountService {
    @Autowired
    private JedisClient jedisClient;
    private static final String UNITE_ACCOUNT_BASE_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("UNITE_ACCOUNT_BASE_URL");
    private static final String LISTUSER_PERMISSION_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("LISTUSER_PERMISSION_URL");
    private static final String LISTUSER_DEP_USERID_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("LISTUSER_DEP_USERID_URL");
    private static final String LISTUSER_PAGE_DEP_USERID_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("LISTUSER_PAGE_DEP_USERID_URL");
    private static final String GET_USER = (String) PropertiesUtils.
            loadProperties("config.properties").get("GET_USER");
    private static final String UPDATE_USER = (String) PropertiesUtils.
            loadProperties("config.properties").get("UPDATE_USER");
    private static final String GET_DEPARTMENG_LIST_BY_USERID = (String) PropertiesUtils.
            loadProperties("config.properties").get("GET_DEPARTMENG_LIST_BY_USERID");

    /**
     * @param identifier 1我的 2 我下属 3 全公司
     * @param userId
     * @param companyId
     * @return
     */
    public List<Map> listUserByPerMissions(String identifier, Object userId, Object companyId) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNoneBlank(identifier)) {
            map.put("identifier", identifier);
        } else {
            map.put("identifier", DataEnumerate.STRING_NUM_ONE);//没传标识默认为1
        }
        map.put("userId", String.valueOf(userId));
        map.put("companyId", String.valueOf(companyId));
        map.put("appId", DataEnumerate.APP.STRING_CRM);
        String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + LISTUSER_PERMISSION_URL, map);
        List<Map> list = JsonUtils.jsonToList(s, Map.class);
        return list;
    }

    /**
     * 查询部门下id list 或者某个具体的id信息
     *
     * @param userId
     * @param companyId
     * @param checkedUserId
     * @param checkedDepartmentId
     * @return
     */
    @Override
    public List<Map> listUserByDepIdAndUserId(Object userId, Object companyId, Object checkedUserId, Object checkedDepartmentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", String.valueOf(userId));
        map.put("companyId", String.valueOf(companyId));
        map.put("appId", DataEnumerate.APP.STRING_CRM);
        if (checkedUserId != null) {
            map.put("serchUserId", String.valueOf(checkedUserId));
        } else {
            map.put("departmentId", String.valueOf(checkedDepartmentId));
        }
        String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + LISTUSER_DEP_USERID_URL, map);
        List<Map> list = JsonUtils.jsonToList(s, Map.class);
        return list;
    }

    @Override
    public PageInfo<Map<String,Object>> listUserByDepIdAndUserIdPage(Object userId, Object companyId, Object checkedUserId,
                                                 Object checkedDepartmentId, Integer PageNum, Integer PageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", String.valueOf(userId));
        map.put("companyId", String.valueOf(companyId));
        map.put("appId", DataEnumerate.APP.STRING_CRM);
        map.put("pageNumber", String.valueOf(PageNum));
        map.put("pageSize", String.valueOf(PageSize));
        if (checkedUserId != null) {
            map.put("serchUserId", String.valueOf(checkedUserId));
        } else {
            map.put("departmentId", String.valueOf(checkedDepartmentId));
        }
        String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + LISTUSER_PAGE_DEP_USERID_URL, map);
        PageInfo<Map<String,Object>> pageInfo = JsonUtils.jsonToPojo(s, PageInfo.class);
        return pageInfo;
    }

    @Override
    public String getUserbyUserId(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + GET_USER, map);
        return s;
    }

    @Override
    public String updateUserbyUserId(Integer userId, String name, String userEmail, Integer sex) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("name",name);
        map.put("userEmail",userEmail);
        map.put("sex",sex);
        String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + UPDATE_USER, map);
        return s;
    }

    /**
     * 根据用户id查询部门列表
     * @param userId    ：用户id
     * @param companyId ：企业id
     * @return          ：企业列表
     */
    @Override
    public List<Object> getDepartmentListbyUserId(int userId, int companyId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("companyId",companyId);
        String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + GET_DEPARTMENG_LIST_BY_USERID, map);
//        String s = HttpclientUtils.doPostByJson("http://localhost:8086" + GET_DEPARTMENG_LIST_BY_USERID, map);
        List<Object> integers = JsonUtils.jsonToList(s, Object.class);
        return integers;
    }
}
