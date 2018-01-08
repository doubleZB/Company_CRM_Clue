package com.sunll.web.controller.index;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunll.common.util.Result;
import com.sunll.systemset.api.login.LoginService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5.
 */
@Controller
@RequestMapping("/menuShow")
public class MenuShowController {
    @Reference(check = false,timeout = 100000)
    LoginService loginService;

    /**
     * 获取用户信息
     * @param CRM_TOKEN TOKEN
     * @return          用户map
     */
    @RequestMapping(value = "/user",method= RequestMethod.POST)
    @ResponseBody
    public Object login(String CRM_TOKEN){
        Map user = loginService.getClassData(CRM_TOKEN, "user");
        return user;
    }

    /**
     * 获取菜单列表
     * @param CRM_TOKEN TOKEN
     * @return          菜单列表
     */
    @RequestMapping(value = "/menuShow",method= RequestMethod.POST)
    @ResponseBody
    public Object menuShow(String CRM_TOKEN){
        List<Object> menuList = loginService.getMenuAndPerClassData(CRM_TOKEN, "menuList");
        return menuList;
    }

    /**
     * 上次登录时间
     * @param CRM_TOKEN TOKEN
     * @return          上次登录时间
     */
    @RequestMapping(value = "/lastLoginTime",method= RequestMethod.POST)
    @ResponseBody
    public Result lastLoginTime(String CRM_TOKEN){
        String loginTime = (String)loginService.getPropertyData(CRM_TOKEN, "userCompany", "loginTime");
        return Result.build(200,"获取上次登陆时间成功",loginTime);
    }
}
