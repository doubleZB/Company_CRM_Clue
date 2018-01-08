package com.sunll.web.controller.center;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.sunll.center.entity.Record;
import com.sunll.center.entity.RecordExample;
import com.sunll.center.mapper.RecordMapper;
import com.sunll.systemset.api.login.LoginService;
import com.sunll.systemset.api.unitAccount.UnitAccountService;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
@RequestMapping("/record")
public class RecordAction {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    RecordMapper recordMapper;
/*    @Autowired
    private JedisClient jedisClient;*/
    @Reference(check = false, timeout = 100000)
    LoginService loginService;
    @Reference(check = false,timeout = 100000)
    UnitAccountService unitAccountService;

    @ResponseBody
    @RequestMapping(value = "/getData")
    public Object getData(Integer pageNumber, Integer pageSize,HttpServletRequest request) {
        log.info("--- RecordAction getData() start ---");
        RecordExample recordE = new RecordExample();
        RecordExample.Criteria criteria = recordE.createCriteria();

        //String CRM_TOKEN = request.getParameter("CRM_TOKEN");
        //Object userId = loginService.getPropertyData(CRM_TOKEN, "user", "id");
        String currentUserId = request.getParameter("USER_TOKEN");
        String currentCompanyId = request.getParameter("companyId");
        String scopeFlag = request.getParameter("scopeFlag");

        List<Map> userNmIdMobile = null;

        if("1".equals(scopeFlag)){
            //String accountMobile = loginService.getAccountMobileByUserId(currentUserId);
        }else if("2".equals(scopeFlag)){
            userNmIdMobile =unitAccountService.listUserByDepIdAndUserId(currentUserId,currentCompanyId,currentUserId,null);
        }else if("3".equals(scopeFlag)){
            userNmIdMobile =unitAccountService.listUserByDepIdAndUserId(currentUserId,currentCompanyId,currentUserId,null);
        }

        log.info("--- RecordAction getData() userId ---" + currentUserId);
        String accountMobile = loginService.getAccountMobileByUserId(currentUserId);
        log.info("--- RecordAction getData() accountMobile ---" + accountMobile);

        String createTimeFrom = request.getParameter("createTimeFrom");
        String createTimeTo = request.getParameter("createTimeTo");
        String direction = request.getParameter("direction");
        String telNumber = request.getParameter("telNumber");
        String seatName = request.getParameter("seatName");

        if(StringUtil.isNotEmpty(createTimeFrom)){
            System.out.println(createTimeFrom);
            log.info(createTimeFrom);
            //yy-MM-dd HH:mm:ss
            createTimeFrom = createTimeFrom +" 00:00:00";
            Date from = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            try {
                from = sdf.parse(createTimeFrom);
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
            }

            criteria.andStarttimeGreaterThanOrEqualTo(from);
        }

        if(StringUtil.isNotEmpty(createTimeTo)){
            //yy-MM-dd HH:mm:ss
            createTimeTo = createTimeTo +" 23:59:59";
            Date to = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            try {
                to = sdf.parse(createTimeTo);
            } catch (ParseException pe) {
                System.out.println(pe.getMessage());
            }
            criteria.andStarttimeLessThanOrEqualTo(to);
        }

        if(StringUtil.isNotEmpty(direction)){
            //criteria.andDirectionEqualTo(new Byte(direction));
        }

        //我的通话记录 1
        if("1".equals(scopeFlag)){
            if(StringUtil.isNotEmpty(accountMobile)&& NumberUtils.isNumber(accountMobile)){
                criteria.multiColumnOrClause(accountMobile);
            }
        }else{
            criteria.multiColumnOrClauseLst(userNmIdMobile);
        }

        //检索条件 电话号码
        if(StringUtil.isNotEmpty(telNumber)){
            criteria.multiColumnOrClause(telNumber);
        }

        if(StringUtil.isNotEmpty(seatName)){
            //TODO: 2017/12/13
            //criteria.andDirectionEqualTo(new Byte(direction));
        }

        PageHelper.startPage(pageNumber, pageSize, "id desc");
        List<Record> records = recordMapper.selectByExample(recordE);
        PageInfo<Record> pageInfo = new PageInfo<Record>(records);
        log.info("--- RecordAction getData() end ---");
        return pageInfo;
        //String result = "";
        //log.info("--- getData() end ---");
        //return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request) {
        log.info("--- RecordAction listPage() start ---");

        log.info("--- RecordAction listPage() end ---");
        return "center/record";
    }
}
