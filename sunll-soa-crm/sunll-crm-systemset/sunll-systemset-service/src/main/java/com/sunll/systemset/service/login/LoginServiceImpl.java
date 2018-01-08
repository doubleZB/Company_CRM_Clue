package com.sunll.systemset.service.login;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunll.common.redis.JedisClient;
import com.sunll.common.util.*;
import com.sunll.systemset.api.login.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/4.
 */
@Service
public class LoginServiceImpl implements LoginService{
    private Logger log = Logger.getLogger(this.getClass());
    private static final String UNITE_ACCOUNT_BASE_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("UNITE_ACCOUNT_BASE_URL");//统一账户基础url
    private static final String LOGIN_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("LOGIN_URL");//登录接口
    private static final String LOGIN_TIME = (String) PropertiesUtils.
            loadProperties("config.properties").get("LOGIN_TIME");//登录接口
    private static final String GET_LOGIN_ACCOUNT_ID = (String) PropertiesUtils.
            loadProperties("config.properties").get("GET_LOGIN_ACCOUNT_ID");//登录接口
    private static final String COMPANY_LIST_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("COMPANY_LIST_URL");//企业列表接口
    private static final String CHOSE_COMPANY_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("CHOSE_COMPANY_URL");//选择企业接口
    private static final String MENU_PERMISSION = (String) PropertiesUtils.
            loadProperties("config.properties").get("MENU_PERMISSION");//权限接口
    private static final String CRM_USER_SESSION_KEY = (String) PropertiesUtils.
            loadProperties("config.properties").get("CRM_USER_SESSION_KEY");//用户redis key值
    private static final String CRM_MENU_SESSION_KEY = (String) PropertiesUtils.
            loadProperties("config.properties").get("CRM_MENU_SESSION_KEY");//权限redis key值
    private static final Integer CRM_SESSION_EXPIRE = Integer.parseInt((String)PropertiesUtils.
            loadProperties("config.properties").get("CRM_SESSION_EXPIRE"));//redis超时时间
    private static final String GET_CONPANY_LIST_BY_ACCOUNT_ID = (String)PropertiesUtils.
            loadProperties("config.properties").get("GET_CONPANY_LIST_BY_ACCOUNT_ID");//根据accountid获取企业列表
    //add by szq
    private static final String CENTER_ACCOUNT_MOBILE_URL = (String) PropertiesUtils.
            loadProperties("config.properties").get("CENTER_ACCOUNT_MOBILE_URL");
    //add by szq
    @Autowired
    private JedisClient jedisClient;

    /**
     *
     * @param accountNumber 用户名
     * @param password
     * @return user
     */
    public Result getLoginAccountId(String accountNumber, String password, String applicationId) {
        try {
            //------------获取登录信息----------------START
            Map<String ,Object> map = new HashMap<>();
            map.put("accountNumber",accountNumber);
            map.put("password",password);
            map.put("applicationId",applicationId);
            //调取远程接口--返回企业列表
            String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL+GET_LOGIN_ACCOUNT_ID, map);
            Result result = JsonUtils.jsonToPojo(s, Result.class);//获取result
            if(result.getStatus() != 200){
                log.info(result.getMsg());
                return Result.build(500,result.getMsg(),null);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500,"登录异常，请重试",null);
        }
    }
    /**
     *
     * @return user
     */
    public Result getCompanyListByAccountId(Integer accountId) {
        try {
            //------------获取登录信息----------------START
            Map<String ,Object> map = new HashMap<>();
            map.put("accountId",accountId);
            //调取远程接口--返回企业列表
            String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL+GET_CONPANY_LIST_BY_ACCOUNT_ID, map);
            Result result = JsonUtils.jsonToPojo(s, Result.class);//获取result
            if(result.getStatus() != 200){
                log.info(result.getMsg());
                return Result.build(500,result.getMsg(),null);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500,"登录异常，请重试",null);
        }
    }

    @Override
    public Result loginOut(String key) {
        jedisClient.expire(CRM_MENU_SESSION_KEY+":"+key,1800);
        jedisClient.expire(CRM_USER_SESSION_KEY+":"+key,1800);
        return Result.build(200,"退出登录",null);
    }

    @Override
    public Result choseCompany( String companyId, String userId, String applicationId) {
        try {
            //--------------获取登录信息------------------START
            Map<String ,Object> map = new HashMap<>();
            map.put("companyId",companyId);
            map.put("userId",userId);
            map.put("applicationId",applicationId);
            //调取远程接口--登录信息
            String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL+CHOSE_COMPANY_URL, map);
            Result result = JsonUtils.jsonToPojo(s, Result.class);//获取result
            if(result.getStatus() != 200){
                log.info(result.getMsg());
                return Result.build(500,result.getMsg(),null);
            }
            //--------------获取登录信息------------------END

            //------------获取权限菜单信息----------------START
            Map<String ,Object> map1 = new HashMap<>();
            map1.put("userId",userId);
            map1.put("companyId",companyId);
            map1.put("appId",applicationId);
            //调取远程接口--权限信息
            String s1 = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL+MENU_PERMISSION, map1);
//            String s1 = HttpclientUtils.doPostByJson("http://192.168.1.54:8082"+MENU_PERMISSION, map1);
            Result result1 = JsonUtils.jsonToPojo(s1, Result.class);//获取result
            if(result1.getStatus() != 200){
                return Result.build(500,result1.getMsg(),null);
            }
            if(result1.getStatus() == 200){
                Map map2 = (Map)result1.getData();
                List menuList = (List)map2.get("menuList");
                if(menuList.size() == 0){
                    return Result.build(500,"无权限，请设置权限后从新登录",null);
                }
            }
            //------------获取权限菜单信息----------------END
            String token = UUID.randomUUID().toString();
            //把用户信息写入redis--登录信息
            jedisClient.set(CRM_USER_SESSION_KEY+":"+token,s);
            //设置token的过期时间--登录信息
//            jedisClient.expire(CRM_USER_SESSION_KEY+":"+token,CRM_SESSION_EXPIRE);
            //把用户信息写入redis--权限信息
            jedisClient.set(CRM_MENU_SESSION_KEY+":"+token,s1);
            //设置token的过期时间--权限信息
//            jedisClient.expire(CRM_MENU_SESSION_KEY+":"+token,CRM_SESSION_EXPIRE);
            log.info(result.getMsg());
            return Result.build(200,result.getMsg(),token);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500,"登录异常，请重试",null);
        }
    }

    /**
     * 获取登录用户相应的类的相应的属性
     * @param key REDIS KEY 值
     * @param className 类名
     * @param propertyName 属性名
     * @return 属性值
     */
    @Override
    public Object getPropertyData(String key, String className, String propertyName) {
        String s = jedisClient.get(CRM_USER_SESSION_KEY+":"+key);
//        jedisClient.expire(CRM_USER_SESSION_KEY+":"+key,CRM_SESSION_EXPIRE);
        Result result = JsonUtils.jsonToPojo(s, Result.class);
        Map map = (Map)result.getData();
        Map classVal = (Map)map.get(className);
        Object propertyVal = classVal.get(propertyName);
        return propertyVal;
    }

    /**
     * 获取登录用户相应的类
     * @param key REDIS KEY 值
     * @param className 类名
     * @return 类JSON参数
     */
    @Override
    public Map getClassData(String key, String className) {
        String s = jedisClient.get(CRM_USER_SESSION_KEY+":"+key);
//        jedisClient.expire(CRM_USER_SESSION_KEY+":"+key,CRM_SESSION_EXPIRE);
        Result result = JsonUtils.jsonToPojo(s, Result.class);
        Map map = (Map)result.getData();
        Map classVal = (Map)map.get(className);
        return classVal;
    }

    /**
     * 获取登录用户权限相应的list
     * @param key REDIS KEY 值
     * @param className 类名
     * @return 类JSON参数
     */
    @Override
    public List<Object> getMenuAndPerClassData(String key, String className) {
        String s = jedisClient.get(CRM_MENU_SESSION_KEY+":"+key);
//        jedisClient.expire(CRM_MENU_SESSION_KEY+":"+key,CRM_SESSION_EXPIRE);
        Result result = JsonUtils.jsonToPojo(s, Result.class);
        Map map = (Map)result.getData();
        List<Object> classVal = (List<Object>)map.get(className);
        return classVal;
    }

    /**
     *
     * @param key REDIS KEY 值
     * @return 登录信息JSON串
     */
    @Override
    public Map getAllData(String key) {
        String s = jedisClient.get(CRM_USER_SESSION_KEY+":"+key);
//        jedisClient.expire(CRM_USER_SESSION_KEY+":"+key,CRM_SESSION_EXPIRE);
        Result result = JsonUtils.jsonToPojo(s, Result.class);
        Map map = (Map)result.getData();
        return map;
    }

    /**
     *
     * @param key REDIS KEY 值
     * @return 权限菜单JSON串
     */
    @Override
    public Map getMenuAndPerAllData(String key) {
        String s = jedisClient.get(CRM_MENU_SESSION_KEY+":"+key);
//        jedisClient.expire(CRM_MENU_SESSION_KEY+":"+key,CRM_SESSION_EXPIRE);
        Result result = JsonUtils.jsonToPojo(s, Result.class);
        Map map = (Map)result.getData();
        return map;
    }

    @Override
    public Object lastLoginTime(String companyId, String userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("companyId",companyId);
        map.put("userId",userId);
        String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + LOGIN_TIME, map);
//        String s = HttpclientUtils.doPostByJson("http://localhost:8086" + LOGIN_TIME, map);
        Result result = JsonUtils.jsonToPojo(s, Result.class);
        if(result.getStatus()==200){
            return result.getData();
        }
        return null;
    }

    @Override
    public String getAccountMobileByUserId(String userId) {
        try {
/*            Map typeMap = new HashMap<>();
            typeMap.put("userId", "1");*/
            log.info("getAccountMobileByUserId start : " + userId);
            Map<String ,Object> map = new HashMap<>();
            map.put("userId",userId);
            //调取远程接口--返回企业列表
            String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL+CENTER_ACCOUNT_MOBILE_URL, map);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return "error occurred in getting mobile";
        }

    }


}
