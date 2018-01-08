package com.sunll.web.controller.tld;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunll.common.util.ApplicationContextUtil;
import com.sunll.systemset.api.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */
public class PermissionsTag extends TagSupport {
    //权限代码
    private String permissionsCode;

    private String CRM_TOKEN;

    public void setCRM_TOKEN(String CRM_TOKEN) {
        this.CRM_TOKEN = CRM_TOKEN;
    }

    public void setPermissionsCode(String permissionsCode) {
        this.permissionsCode = permissionsCode;
    }

    private LoginService loginService = (LoginService) ApplicationContextUtil.getContext().getBean("loginService");
    @Override
    public int doStartTag() throws JspException {
        //判断是否有权限
        if (isHasPermissions(permissionsCode,CRM_TOKEN)){
            return Tag.EVAL_BODY_INCLUDE;//标签体内容向浏览器输出
        }else {
            return Tag.SKIP_BODY; //标签体内容不向浏览器输出
        }
    }

    /**
     * 判断是否有权限
     * @param permissionsCode
     * @return
     */


    public boolean isHasPermissions(String permissionsCode,String CRM_TOKEN){
        List<Object> permissiones = loginService.getMenuAndPerClassData(CRM_TOKEN, "permissiones");
        System.out.println(permissiones);
//        HttpSession session = pageContext.getSession();
//        List<Map> mapList = (List<Map>) session.getAttribute("adminPermissionsList");
//        for (Map map : mapList) {
//            if (permissionsCode.equals(adminPermissions.getPermissionsCode())){
//                return true;
//            }
//        }
        return false;
    }

}
