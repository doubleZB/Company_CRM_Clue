package com.sunll.web.controller.jspSkip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/12/5.
 */
@Controller
@RequestMapping("/jspSkip")
public class JspSkipComtroller {

    /**
     * 登录首页
     * @return 登录地址
     */
    @RequestMapping("/loginOut")
    public String loginOut(){
        return "login/login";
    }

    /**
     * 登录首页
     * @return 登录地址
     */
    @RequestMapping("/clue")
    public String clue(){
        return "clue/clue";
    }

    /**
     * 跳转选择企业
     * @return 选择企业地址
     */
    @RequestMapping("/chooseEnterprise")
    public String chooseEnterprise(){
        return "login/chooseEnterprise";
    }

    /**
     * 跳转首页
     * @return 首页地址
     */
    @RequestMapping("/index")
    public String login(){
        return "index/index";
    }

    /**
     * 跳转短信
     * @return 页面地址
     */
    @RequestMapping("/mms")
    public String mms(){
        return "message/mms";
    }

    /**
     * 跳转费用统计
     * @return 页面地址
     */
    @RequestMapping("/statisticCost")
    public String statisticCost(){
        return "statistic/statistic-cost";
    }

    /**
     * 跳转费用统计
     * @return 页面地址
     */
    @RequestMapping("/statisticDuration")
    public String statisticDuration(){
        return "statistic/statistic-duration";
    }

    /**
     * 跳转短信费用统计
     * @return 页面地址
     */
    @RequestMapping("/statisticMms")
    public String statisticMms(){
        return "statistic/statistic-mms";
    }

    /**
     * 跳转系统设置
     * @return 页面地址
     */
    @RequestMapping("/setup")
    public String setup(){
        return "systemset/setup";
    }

    /**
     * 跳转选择部门或者人员
     * @return 页面地址
     */
    @RequestMapping("/selectDepAndUser")
    public String selectDepAndUser(){
        return "select/RoleAdd";
    }

    /**
     * 跳转选择人员
     * @return 页面地址
     */
    @RequestMapping("/selectDepAndUser1")
    public String selectDepAndUser1(){
        return "clue/RoleAdd1";
    }

    /**
     * 跳转选择部门或者人员
     * @return 页面地址
     */
    @RequestMapping("/business")
    public String business(){
        return "systemset/business";
    }

    /**
     * 跳转选择部门或者人员
     * @return 页面地址
     */
    @RequestMapping("/businessType")
    public String businessType(){
        return "systemset/businessType";
    }

    /**
     * 跳转选择部门或者人员
     * @return 页面地址
     */
    @RequestMapping("/user")
    public String user(){
        return "user/user";
    }

    /**
     * 跳转选择部门或者人员
     * @return 页面地址
     */
    @RequestMapping("/updateClue")
    public String updateClue(){
        return "clue/clue-update";
    }

}
