package com.sunll.web.controller.unitAccountInterface;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunll.common.util.PropertiesUtils;
import com.sunll.systemset.api.login.LoginService;
import com.sunll.systemset.api.unitAccount.UnitAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5.
 */
@Controller
@RequestMapping("/unitAccountInterface")
public class UnitAccountController {
    @Reference(check = false,timeout = 100000)
    UnitAccountService unitAccountService;
    @Reference(check = false,timeout = 100000)
    LoginService loginService;

    private static final String UNITE_ACCOUNT_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("UNITE_ACCOUNT_URL");
    /**
     * 通过企业ID 用户ID CRM_TOKEN 权限范围标识查询用户list
     * @param identifier 权限范围
     * @param CRM_TOKEN CRM_TOKEN
     * @return integers
     */
    public Object listUserByPerMissions(String identifier,String CRM_TOKEN){
        Integer userId = Integer.parseInt((String)loginService.getPropertyData(CRM_TOKEN, "user", "id"));
        Integer companyId = Integer.parseInt((String)loginService.getPropertyData(CRM_TOKEN, "company", "id"));
        List<Map> integers = unitAccountService.listUserByPerMissions(identifier, userId, companyId);
        return integers;
    }

    /**
     * 根据查询信息获取部门或者个人id 集合   serchUserId\departmentId 只能传一个
     * @param CRM_TOKEN 用户信息
     * @param serchUserId 要查询的USERID
     * @param departmentId 要查询的DEPID
     * @return integers
     */
    public Object listUserByDepIdAndUserId(String CRM_TOKEN,Integer serchUserId,Integer departmentId){
        Integer userId = Integer.parseInt((String)loginService.getPropertyData(CRM_TOKEN, "user", "id"));
        Integer companyId = Integer.parseInt((String)loginService.getPropertyData(CRM_TOKEN, "company", "id"));
        List<Map> integers = unitAccountService.listUserByDepIdAndUserId(userId, companyId,serchUserId,departmentId);
        return integers;
    }

    @RequestMapping(value = "/getUserbyUserId",method= RequestMethod.POST)
    @ResponseBody
    public Object getUserbyUserId(Integer userId){
        return unitAccountService.getUserbyUserId(userId);
    }

    @RequestMapping(value = "/getUnitAccountUrl",method= RequestMethod.POST)
    @ResponseBody
    public Object getUnitAccountUrl(){
        Map<String,Object> map = new HashMap<>();
        map.put("url",UNITE_ACCOUNT_URL);
        return map;
    }

    @RequestMapping(value = "/updateUserbyUserId",method= RequestMethod.POST)
    @ResponseBody
    public Object updateUserbyUserId(Integer userId,String name,String userEmail,Integer sex){
        return unitAccountService.updateUserbyUserId(userId,name,userEmail,sex);
    }

    /**
     * 根据用户id查询部门集合
     * @param userId    ：用户id
     * @param companyId ：企业id
     * @return          ：部门id集合
     */
    @RequestMapping(value = "/getDepartmentListbyUserId",method= RequestMethod.POST)
    @ResponseBody
    public Object getDepartmentListbyUserId(Integer userId,Integer companyId){
        List<Object> departmentListbyUserId = unitAccountService.getDepartmentListbyUserId(userId, companyId);
        return departmentListbyUserId;
    }

    /**
     * 根据用户id查询部门集合2
     * @return          ：部门id集合
     */
    @RequestMapping(value = "/getDepartmentListbyUserId2",method= RequestMethod.POST)
    @ResponseBody
    public Object getDepartmentListbyUserId2(String CRM_TOKEN){
        List<Object> departments = loginService.getMenuAndPerClassData(CRM_TOKEN, "departments");
        return departments;
    }
}
