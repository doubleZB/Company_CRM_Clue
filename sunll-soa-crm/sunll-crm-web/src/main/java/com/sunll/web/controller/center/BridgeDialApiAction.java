package com.sunll.web.controller.center;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.StringUtil;
import com.sunll.center.entity.*;
import com.sunll.center.mapper.AdmUserMapper;
import com.sunll.center.mapper.PredialTaskMapper;
import com.sunll.center.mapper.PredialtaskCalledMapper;
import com.sunll.center.mapper.PredialtaskCustomerMapper;
import com.sunll.web.util.MD5;
import com.sunll.web.vo.DialRetMsg;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
@RequestMapping("/bridge")
public class BridgeDialApiAction {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    AdmUserMapper admUserMapper;
    @Autowired
    PredialTaskMapper predialTaskMapper;
    //
    @Autowired
    PredialtaskCustomerMapper predialtaskCustomerMapper;
    @Autowired
    PredialtaskCalledMapper predialtaskCalledMapper;

    @ResponseBody
    @RequestMapping(value = "/dial")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,value="transactionManager",rollbackFor=Exception.class)
    public String bridgeDial(HttpServletRequest request) {
        log.info("--- For Mobile : bridgeDial() start ---");

        String uname = "ywj";//用户名
        String token = "jtd2016";//用户密码
        String showcaller = request.getParameter("showcaller");
        String from = request.getParameter("from");
        String to = request.getParameter("to");

        //Map retMap = new HashMap();
        //retMap.put("uname", uname);

        String statuscode = "";
        String msge = "";
        String tid = "";
        //String from;
        //String to;
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String ctime = sdf.format(new Date());


        DialRetMsg retMsg = null;

        if(StringUtil.isEmpty(uname)||StringUtil.isEmpty(token)||
                StringUtil.isEmpty(from)||StringUtil.isEmpty(to)||StringUtil.isEmpty(showcaller)) {
            statuscode = "1002";
            msge = "请求参数缺少";
            retMsg = new DialRetMsg(statuscode,msge,tid,from,to,ctime);
            return JSON.toJSONString(retMsg);
        }


        try{
            /*        步骤1：用户验证  checkLogin()
        参数：uname  token
        表：adm_user*/
            //// TODO: 2017/12/27
            //php md5 生成的加密密码只有32位，java中shautil类获得的是40位加密密码字符串
            //ywj jtd2016 已经写死值了，先暂时不做这个校验。but $accountid 得取出来 10 也是死的 哈哈
            int accountid = 10;//ywj

            AdmUserExample admUserE = new AdmUserExample();
            AdmUserExample.Criteria criteria = admUserE.createCriteria();

            criteria.andAccountEqualTo(uname);
            criteria.andPasswordEqualTo(MD5.getMD5Str(token));
            List<AdmUser> lst = admUserMapper.selectByExample(admUserE);
            if(lst!=null&& lst.size()>0){
                accountid = lst.get(0).getBindAccount();
            }else{
                statuscode = "1001";
                msge = "用户名或者密码错误";
                retMsg = new DialRetMsg(statuscode,msge,tid,from,to,ctime);
                return JSON.toJSONString(retMsg);
            }

            //admUserMapper

/*        步骤2：任务表数据入库 PredialTask()
        入表字段:accountid,taskname,starttime,endtime,totalcount,callouttype,showcaller,taskstatus,oncurrentcall
        表：predial_task*/
            PredialTask predialTask = new PredialTask();

            int idTask = predialTaskMapper.selectMaxId()+1;
            predialTask.setId(idTask);
            predialTask.setAccountid(accountid);
            predialTask.setTaskname(uname);
            long nowMiliSec = new Date().getTime();
            predialTask.setStarttime(new Date(nowMiliSec - 60*3600*1000));
            predialTask.setEndtime(new Date(nowMiliSec + 60*3600*1000));
            predialTask.setTotalcount(1);
            predialTask.setCallouttype(2);
            predialTask.setShowcaller(showcaller);
            predialTask.setTaskstatus(1);
            predialTask.setConcurrentcall(5);

            int ret1 = predialTaskMapper.insert(predialTask);
            if(ret1==1){
            /*        步骤3：被叫信息入库
        入表字段：bind_task_id,custom_caller
        表：predialtask_customer*/

            /*        步骤4：主叫信息入库
        入表字段：bind_task_id,called
        表：predialtask_called*/
                PredialtaskCustomer customer = new PredialtaskCustomer();
                customer.setBindTaskId(idTask);
                customer.setCustomCaller(to);
                int ret2 = predialtaskCustomerMapper.insert(customer);

                PredialtaskCalled called = new PredialtaskCalled();
                int maxIdCalled = predialtaskCalledMapper.selectMaxId();
                called.setId(maxIdCalled+1);
                called.setBindTaskId(idTask);
                called.setCalled(from);
                int ret3 = predialtaskCalledMapper.insert(called);
                if(ret2==1&&ret3==1){
                    tid = String.valueOf(maxIdCalled);
                    statuscode = "1000";
                    msge = "创建通话成功";
                    retMsg = new DialRetMsg(statuscode,msge,tid,from,to,ctime);
                    return JSON.toJSONString(retMsg);
                }else{
                    statuscode = "1003";
                    msge = "创建通话失败";
                    retMsg = new DialRetMsg(statuscode,msge,tid,from,to,ctime);
                    return JSON.toJSONString(retMsg);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            statuscode = "1003";
            msge = "创建通话失败";
            retMsg = new DialRetMsg(statuscode,msge,tid,from,to,ctime);
            return JSON.toJSONString(retMsg);
        }

        retMsg = new DialRetMsg(statuscode,msge,tid,from,to,ctime);
        System.out.print(JSON.toJSONString(retMsg));
        log.info(JSON.toJSONString(retMsg));
        log.info("--- For Mobile : bridgeDial() end ---");
        return JSON.toJSONString(retMsg);
    }

}
