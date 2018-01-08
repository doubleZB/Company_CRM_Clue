package com.sunll.web.controller.tld;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunll.common.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private JedisClient jedisClient = (JedisClient) ContextLoader.getCurrentWebApplicationContext().getBean("jedisClientCluster");

    private boolean isHasPermissions(String permissionsCode, String CRM_TOKEN){
        //获取所有的权限
        String reStr = jedisClient.get(CRM_TOKEN);
        JSONObject reData = JSONObject.parseObject(reStr);
        JSONObject data = JSON.parseObject(JSON.toJSONString(reData.get("data")));
        List<Map<String,Object>> list = (List<Map<String, Object>>) data.get("permissiones");
        for (Map<String,Object> map:list){
            String rePermissionsCode = (String) map.get("permissionsCode");
            if(permissionsCode.equals(rePermissionsCode)){
                return true;
            }
        }
        return false;
    }

}
