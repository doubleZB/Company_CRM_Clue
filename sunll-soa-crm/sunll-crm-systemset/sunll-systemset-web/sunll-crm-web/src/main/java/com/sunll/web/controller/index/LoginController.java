package com.sunll.web.controller.index;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunll.common.util.CookieUtil;
import com.sunll.common.util.DataEnumerate;
import com.sunll.common.util.PropertiesUtils;
import com.sunll.common.util.Result;
import com.sunll.systemset.api.login.LoginService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by Administrator on 2017/12/4.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static final String CRM_MENU_SESSION_KEY = (String) PropertiesUtils.
            loadProperties("config.properties").get("CRM_MENU_SESSION_KEY");//权限redis key值
    private Logger log = Logger.getLogger(this.getClass());
    @Reference(check = false, timeout = 100000)
    LoginService loginService;

    /**
     * 获取账户id
     *
     * @param accountNumber 账号
     * @param password 密码
     * @return user对象
     */
    @RequestMapping(value = "/getLoginAccountId", method = RequestMethod.POST)
    @ResponseBody
    public Object getLoginAccountId(String accountNumber, String password) {
        Result result = loginService.getLoginAccountId(accountNumber, password, DataEnumerate.APP.STRING_CRM);
        log.info(result.getMsg());
        return result;
    }

    /**
     * 根据账号获取企业列表
     * @return user对象
     */
    @RequestMapping(value = "/getCompanyListByAccountId", method = RequestMethod.POST)
    @ResponseBody
    public Object loginInterZ(Integer accountId) {
        Result result = loginService.getCompanyListByAccountId(accountId);
        log.info(result.getMsg());
        return result;
    }

    /**
     * 退出首页
     * @return 登录地址
     */
    @RequestMapping("/loginOut")
    @ResponseBody
    public Result loginOut(){
        return Result.build(200,"退出登录",null);
    }


    /**
     * 选择企业
     *
     * @param userId    用户id
     * @param companyId 企业id
     * @return token
     */
    @RequestMapping(value = "/choseCompany", method = RequestMethod.POST)
    @ResponseBody
    public Object choseCompany(HttpServletResponse response,String userId, String companyId) {
        Result result = loginService.choseCompany(companyId, userId, DataEnumerate.APP.STRING_CRM);
        CookieUtil.setCookie(response,"permissionsKey",CRM_MENU_SESSION_KEY+":"+result.getData());
        log.info(result.getMsg());
        return result;
    }
}
