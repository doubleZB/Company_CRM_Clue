package com.sunll.web.controller.center;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.sunll.center.entity.*;
import com.sunll.center.mapper.*;
import com.sunll.common.util.PropertiesUtils;
import com.sunll.systemset.api.login.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
@RequestMapping("/extensionApi")
public class ExtensionApiAction {
    private Logger log = Logger.getLogger(this.getClass());

    private static final String PHP_CENTER_ADRESS = (String) PropertiesUtils.loadProperties
            ("config.properties").get("PHP_CENTER_ADRESS");

    @Reference(check = false, timeout = 100000)
    LoginService loginService;

    @Autowired
    BillMapper billMapper;

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountInfoMapper accountInfoMapper;
    @Autowired
    AccountPhoneMapper accountPhoneMapper;
    @Autowired
    UsersMapper usersMapper;

    /**
     * 应用APP登录接口
     * @param acceptMap 接受参数 账号 密码s
     * @return user对象
     */
    @ResponseBody
    @RequestMapping(value = "/sipDial", method = RequestMethod.POST)
    public String sipDial(@RequestBody Map<String, Object>  acceptMap) {
        log.info("呼叫中心SIP电话接口请求参数：" + JSON.toJSONString(acceptMap));
        Map<String,String> ret = new HashMap<>();

        String companyName = (String) acceptMap.get("companyName");
        String extTel = (String) acceptMap.get("extTel");
        String pwd = "";//(String) acceptMap.get("pwd");

        try{
            UsersExample usersE = new UsersExample();
            UsersExample.Criteria criteria = usersE.createCriteria();
            criteria.andSipIdEqualTo(extTel);
            //criteria.andSipPasswordEqualTo(pwd);

            List<Users> users = usersMapper.selectByExample(usersE);
            if(users.size()>0){
                Users user = users.get(0);
                pwd = user.getSipPassword();
                Integer bindPhone = user.getBindPhone();
                AccountPhone accountPhone = accountPhoneMapper.selectByPrimaryKey(bindPhone);
                Integer bindAccount = accountPhone.getBindAccount();
                AccountInfoExample accountInfoE = new AccountInfoExample();
                AccountInfoExample.Criteria criteria1 = accountInfoE.createCriteria();
                criteria1.andBindAccountEqualTo(bindAccount);
                List<AccountInfo> accountInfos = accountInfoMapper.selectByExample(accountInfoE);
                if (accountInfos.size()>0){
                    AccountInfo accountInfo = accountInfos.get(0);
                    String company = accountInfo.getCompany();
                    if(companyName.equals(company)){
                        ret.put("success","true");
                        ret.put("msg","呼叫中心验证成功");
                        ret.put("pwd",pwd);
                        return JSON.toJSONString(ret);
                    }else{
                        ret.put("success","false");
                        ret.put("msg","呼叫中心验证失败（公司名称有误）");
                        return JSON.toJSONString(ret);
                    }
                }
            }else{
                ret.put("success","false");
                ret.put("msg","呼叫中心验证失败（分机号有误）");
                return JSON.toJSONString(ret);
            }
        }catch(Exception e){
            e.printStackTrace();
            ret.put("success","false");
            ret.put("msg","呼叫中心验证失败（内部错误）");
            return JSON.toJSONString(ret);
        }

        log.info("呼叫中心SIP电话接口响应参数：" + JSON.toJSONString(ret));
        return JSON.toJSONString(ret);
    }

    //116.62.54.47:8890
    @ResponseBody
    @RequestMapping(value = "/getPhpCenterAdress", method = RequestMethod.POST)
    public String getPhpCenterAdress() {
        Map<String,String> ret = new HashMap<>();
        ret.put("success","true");
        ret.put("phpCenterAdress",PHP_CENTER_ADRESS);
        return JSON.toJSONString(ret);
    }

    public static void main(String[] args){
        String companyTel = "010-53827816";
        //分机拨打 showcaller 01053827816 打不通app端，必须改成不带区号的53827816 葛建民
        String phone ="";
        String transfercaller = "";
        if(companyTel.indexOf("-")!=-1){
            String[] tempArr = companyTel.split("-");
            phone =tempArr[1];
            transfercaller = companyTel.replaceAll("-","");
        }else{
            phone = companyTel;
            transfercaller = companyTel.replaceAll("-","");
        }
        //log.info("phone : " + phone + " , transfercaller : " + transfercaller);
        System.out.println("phone : " + phone + " , transfercaller : " + transfercaller);
        //分机拨打 showcaller 01053827816 打不通app端，必须改成不带区号的53827816 葛建民
    }

    @ResponseBody
    @RequestMapping(value = "/registerCompany",method = RequestMethod.POST)
    public String registerCompany(@RequestBody Map<String, Object> acceptMap) {

        Map<String,String> ret = new HashMap<>();

        long now = new Date().getTime();
        long nowSec = now/1000;
        String nowSecStr =  Long.toString(nowSec);

        try{
            log.info("--- For CRM : registerCompany() start ---");
            String companyName = (String) acceptMap.get("companyName");
            String companyTel = (String) acceptMap.get("companyTel");

            AccountInfoExample accountInfoE = new AccountInfoExample();
            AccountInfoExample.Criteria cri = accountInfoE.createCriteria();
            cri.andCompanyEqualTo(companyName);

            List<AccountInfo> lst = accountInfoMapper.selectByExample(accountInfoE);
            if(lst!=null&&lst.size()>0){
                ret.put("success","false");
                ret.put("msg","企业账户注册同步呼叫中心失败：企业名称已经存在");
                return JSON.toJSONString(ret);
            }


            // ---------------------account-------------------------
            Integer totalAccount = accountMapper.selectMaxId();
            int maxIdIntAccount = totalAccount.intValue()+1;

            Account account = new Account();
            account.setId(maxIdIntAccount);
            account.setBalance(new BigDecimal(500));
            account.setSetMealRemaind(new BigDecimal("0.00"));
            int ret1 = accountMapper.insert(account);

            // ---------------------account_phone-------------------------
            Integer totalPhone = accountPhoneMapper.selectMaxId();
            int maxIdIntPhone = totalPhone.intValue()+1;

            //分机拨打 showcaller 01053827816 打不通app端，必须改成不带区号的53827816 葛建民
            String phone ="";
            String transfercaller = "";
            if(companyTel.indexOf("-")!=-1){
                log.info("包含");
                String[] tempArr = companyTel.split("-");
                phone =tempArr[1];
                transfercaller = companyTel.replaceAll("-","");
            }else{
                log.info("不包含");
                phone = companyTel;
                transfercaller = companyTel.replaceAll("-","");
            }
            log.info("phone : " + phone + " , transfercaller : " + transfercaller);
            System.out.println("phone : " + phone + " , transfercaller : " + transfercaller);
            //分机拨打 showcaller 01053827816 打不通app端，必须改成不带区号的53827816 葛建民

            AccountPhone accountPhone = new AccountPhone();
            accountPhone.setId(maxIdIntPhone);
            accountPhone.setPhone(phone);
            accountPhone.setBindAccount(maxIdIntAccount);
            accountPhone.setBindFlow(11);
            accountPhone.setBindServer(0);
            accountPhone.setCtime(Integer.parseInt(nowSecStr));
            byte istrans = 2;
            accountPhone.setIstransparent(istrans);
            accountPhone.setTransfercaller(transfercaller);

            int ret2 = accountPhoneMapper.insert(accountPhone);

            // ---------------------account_info-------------------------
            Integer total = accountInfoMapper.selectMaxId();
            int maxIdInt = total.intValue()+1;

            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setId(maxIdInt);
            accountInfo.setCompany(companyName);
            accountInfo.setContact("石总");
            accountInfo.setPhone("13261702440");
            accountInfo.setAddress("北京");
            accountInfo.setBindGroup(1);
            accountInfo.setBindAccount(maxIdIntAccount);//// TODO: 2017/12/25
            accountInfo.setBindPartner(1);
            accountInfo.setBindRate(2);
            accountInfo.setBindDiscount(1);
            accountInfo.setBindSmsCodelist(0);
            accountInfo.setPid(0);//can not be null
            byte fp = 1;
            accountInfo.setFinalpay(fp);
            byte is = 0;
            accountInfo.setIsSettle(is);
            //1 0 1 0 default value in database
            /*  long now = new Date().getTime();
            long nowSec = now/1000;
            String nowSecStr =  Long.toString(nowSec);*/
            accountInfo.setCtime(Integer.parseInt(nowSecStr));

            int ret3 = accountInfoMapper.insert(accountInfo);

            if(ret1==1&&ret2==1&&ret3==1){
                ret.put("success","true");
                ret.put("msg","企业账户注册同步呼叫中心成功");
                //return JSON.toJSONString(ret);
            }else{
                ret.put("success","false");
                ret.put("msg","企业账户注册同步呼叫中心失败");
                //return JSON.toJSONString(ret);
            }
        }catch (Exception e){
            e.printStackTrace();
            ret.put("success","false");
            ret.put("msg","企业账户注册同步呼叫中心失败");
        }



        log.info("--- For CRM : registerCompany() end ---");
        return JSON.toJSONString(ret);
    }


    @ResponseBody
    @RequestMapping(value = "/editCompany",method = RequestMethod.POST)
    public String editCompany(@RequestBody Map<String, Object> acceptMap) {
        Map<String,String> ret = new HashMap<>();
        try {
            log.info("--- For CRM : editCompany() start ---");
            String companyName = (String) acceptMap.get("companyName");
            String companyTel = (String) acceptMap.get("companyTel");

            //分机拨打 showcaller 01053827816 打不通app端，必须改成不带区号的53827816 葛建民
            String phone ="";
            String transfercaller = "";
            if(companyTel.indexOf("-")!=-1){
                log.info("包含");
                String[] tempArr = companyTel.split("-");
                phone =tempArr[1];
                transfercaller = companyTel.replaceAll("-","");
            }else{
                log.info("不包含");
                phone = companyTel;
                transfercaller = companyTel.replaceAll("-","");
            }
            log.info("phone : " + phone + " , transfercaller : " + transfercaller);
            System.out.println("phone : " + phone + " , transfercaller : " + transfercaller);
            //分机拨打 showcaller 01053827816 打不通app端，必须改成不带区号的53827816 葛建民

            AccountInfoExample accountInfoE = new AccountInfoExample();
            AccountInfoExample.Criteria cri = accountInfoE.createCriteria();
            cri.andCompanyEqualTo(companyName);

            List<AccountInfo> lst = accountInfoMapper.selectByExample(accountInfoE);
            if (lst != null && lst.size() > 0) {
                Integer bindAccount = lst.get(0).getBindAccount();
                AccountPhoneExample accountPhoneE = new AccountPhoneExample();
                AccountPhoneExample.Criteria cr = accountPhoneE.createCriteria();
                cr.andBindAccountEqualTo(bindAccount);

                List<AccountPhone> list = accountPhoneMapper.selectByExample(accountPhoneE);
                if (list != null && list.size() > 0) {
                    AccountPhone accountPhone = list.get(0);
                    accountPhone.setPhone(phone);
                    accountPhone.setTransfercaller(transfercaller);
                    int ret2 = accountPhoneMapper.updateByPrimaryKeySelective(accountPhone);
                    if(ret2>0){
                        ret.put("success", "true");
                        ret.put("msg", "企业账户修改座机号同步呼叫中心成功");
                        return JSON.toJSONString(ret);
                    }else{
                        ret.put("success", "false");
                        ret.put("msg", "企业账户修改座机号同步呼叫中心失败：数据库更新失败");
                        return JSON.toJSONString(ret);
                    }
                }
            }else{
                ret.put("success", "false");
                ret.put("msg", "企业账户修改座机号同步呼叫中心失败：企业名称找不到");
                return JSON.toJSONString(ret);
            }
        }catch(Exception e){
                e.printStackTrace();
                ret.put("success", "false");
                ret.put("msg", "企业账户修改座机号同步呼叫中心失败：发生未知错误");
                return JSON.toJSONString(ret);
        }

        log.info("--- For CRM : editCompany() end ---");
        return JSON.toJSONString(ret);
    }


    @ResponseBody
    @RequestMapping(value = "/registerEmployee",method = RequestMethod.POST)
    public String registerEmployee(@RequestBody Map<String, Object> acceptMap) {
        Map<String,String> ret = new HashMap<>();

        long now = new Date().getTime();
        long nowSec = now/1000;
        String nowSecStr =  Long.toString(nowSec);

        try{
            log.info("--- For CRM : registerEmployee() start ---");
            String companyName = (String) acceptMap.get("companyName");
            String extTel = (String) acceptMap.get("extTel");
            //System.out.println((int)((Math.random()*9+1)*100000));
            int six = (int)((Math.random()*9+1)*100000);
            String pwd = String.valueOf(six);//(String) acceptMap.get("pwd");

            AccountInfoExample accountInfoE = new AccountInfoExample();
            AccountInfoExample.Criteria criteria = accountInfoE.createCriteria();
            criteria.andCompanyEqualTo(companyName);
            List<AccountInfo> list = accountInfoMapper.selectByExample(accountInfoE);
            
            Integer bindPhone = null;
            if(list.size()>0){
                Integer bindAccount = list.get(0).getBindAccount();

                AccountPhoneExample accountPhoneE = new AccountPhoneExample();
                AccountPhoneExample.Criteria cr = accountPhoneE.createCriteria();
                cr.andBindAccountEqualTo(bindAccount);
                List<AccountPhone> lst = accountPhoneMapper.selectByExample(accountPhoneE);
                if(lst.size()>0){
                    bindPhone = lst.get(0).getId();
                }
            }

            // ---------------------users-------------------------
            Integer totalUsers = usersMapper.selectMaxId();
            int maxIdIntUsers = totalUsers.intValue() + 1;

            Users users = new Users();
            users.setId(maxIdIntUsers);
            users.setSipId(extTel);
            users.setSipPassword(pwd);
            users.setBindPhone(bindPhone);
            users.setBindServer(0);
            byte ca = 2;//
            users.setCallAuth(ca);
            users.setStatus(1);
            users.setCtime(Integer.parseInt(nowSecStr));

            int ret1 = usersMapper.insert(users);

            if(ret1==1){
                ret.put("success","true");
                ret.put("msg","员工账户注册同步呼叫中心成功");
                //return JSON.toJSONString(ret);
            }else{
                ret.put("success","false");
                ret.put("msg","员工账户注册同步呼叫中心失败");
                //return JSON.toJSONString(ret);
            }
        }catch (Exception e){
            e.printStackTrace();
            ret.put("success","false");
            ret.put("msg","员工账户注册同步呼叫中心失败");
        }



        log.info("--- For CRM : registerEmployee() end ---");
        return JSON.toJSONString(ret);
    }


    @ResponseBody
    @RequestMapping(value = "/editEmployee",method = RequestMethod.POST)
    public String editEmployee(@RequestBody Map<String, Object> acceptMap) {
        Map<String,String> ret = new HashMap<>();
        try{
            log.info("--- For CRM : editEmployee() start ---");
            String companyName = (String) acceptMap.get("companyName");
            String extTel = (String) acceptMap.get("extTel");
            String oldExtTel = (String) acceptMap.get("oldExtTel");

            AccountInfoExample accountInfoE = new AccountInfoExample();
            AccountInfoExample.Criteria criteria = accountInfoE.createCriteria();
            criteria.andCompanyEqualTo(companyName);
            List<AccountInfo> list = accountInfoMapper.selectByExample(accountInfoE);

            Integer bindPhone = null;
            if(list.size()>0){
                Integer bindAccount = list.get(0).getBindAccount();

                AccountPhoneExample accountPhoneE = new AccountPhoneExample();
                AccountPhoneExample.Criteria cr = accountPhoneE.createCriteria();
                cr.andBindAccountEqualTo(bindAccount);
                List<AccountPhone> lst = accountPhoneMapper.selectByExample(accountPhoneE);
                if(lst.size()>0){
                    bindPhone = lst.get(0).getId();
                }
            }

            // ---------------------users-------------------------
            UsersExample usersE = new UsersExample();
            UsersExample.Criteria cri = usersE.createCriteria();
            cri.andBindPhoneEqualTo(bindPhone);
            //oldExtTel
            cri.andSipIdEqualTo(oldExtTel);
            List<Users> lst = usersMapper.selectByExample(usersE);
            if (lst != null && lst.size() > 0) {
                Users users = lst.get(0);
                users.setSipId(extTel);
                int ret1 = usersMapper.updateByPrimaryKeySelective(users);
                if(ret1==1){
                    ret.put("success","true");
                    ret.put("msg","员工账户修改同步呼叫中心成功");
                    //return JSON.toJSONString(ret);
                }else{
                    ret.put("success","false");
                    ret.put("msg","员工账户修改同步呼叫中心失败：数据库更新失败");
                    //return JSON.toJSONString(ret);
                }
            }else{
                ret.put("success","false");
                ret.put("msg","员工账户注册同步呼叫中心失败：旧分机号找不到");
            }

        }catch (Exception e){
            e.printStackTrace();
            ret.put("success","false");
            ret.put("msg","员工账户注册同步呼叫中心失败：发生内部错误");
        }

        log.info("--- For CRM : editEmployee() end ---");
        return JSON.toJSONString(ret);
    }

}
