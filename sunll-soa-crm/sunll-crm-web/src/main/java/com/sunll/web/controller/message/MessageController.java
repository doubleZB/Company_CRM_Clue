package com.sunll.web.controller.message;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sunll.clue.api.ClueService;
import com.sunll.clue.api.message.MessageService;
import com.sunll.clue.entity.business.MessageTemplate;
import com.sunll.clue.entity.business.SendMessage;
import com.sunll.clue.entity.message.ShowMessage;
import com.sunll.clue.entity.message.ShowMessageCosts;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.common.util.DataEnumerate;
import com.sunll.common.util.DateUtil;
import com.sunll.common.util.ExportExcelUtil;
import com.sunll.systemset.api.login.LoginService;
import com.sunll.systemset.api.unitAccount.UnitAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/6.
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @Reference(check = false, timeout = 100000)
    MessageService messageService;
    @Reference(check = false, timeout = 100000)
    LoginService loginService;
    @Reference(check = false, timeout = 100000)
    UnitAccountService unitAccountService;
    @Reference(check = false, timeout = 100000)
    ClueService clueService;

    /**
     * 短信列表
     * flag 1我的 2 我下属 3 全公司 场景
     * type 0全部 1发送成功 2发送失败 3发送中 发送状态
     *
     * @param CRM_TOKEN
     * @param startTime
     * @param endTime
     * @param PageNum
     * @param PageSize
     * @param checkedDepartmentId
     * @param checkedUserId
     * @param flag
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectMessageList")
    public Object selectMessageList(String CRM_TOKEN, String startTime, String endTime, Integer PageNum, Integer PageSize,
                                    Integer checkedDepartmentId, Integer checkedUserId, String flag, Integer type) {
        Object userId = loginService.getPropertyData(CRM_TOKEN, "user", "id");
        Object companyId = loginService.getPropertyData(CRM_TOKEN, "company", "id");
        List<Map> integers = new ArrayList<>();
        PageInfo sendMessagePageInfo = new PageInfo();
        //场景为我发送的短信
        if (flag.equals(DataEnumerate.STRING_NUM_ONE)) {
            integers = unitAccountService.listUserByDepIdAndUserId(userId, companyId, userId, null);

            //场景不为我发送的短信
        } else {
            //场景不为我发送的短信且选择企业和选择部门为空
            if (checkedUserId == null && checkedDepartmentId == null) {
                //按照场景查询数据
                integers = unitAccountService.listUserByPerMissions(flag, userId, companyId);
            } else if (checkedUserId != null) {
                //选择用户
                integers = unitAccountService.listUserByDepIdAndUserId(userId, companyId, checkedUserId, null);
            } else if (checkedDepartmentId != null) {
                //选择部门
                integers = unitAccountService.listUserByDepIdAndUserId(userId, companyId, null, checkedDepartmentId);
            } else {
                //自己
                integers = unitAccountService.listUserByDepIdAndUserId(userId, companyId, userId, null);
            }
        }
        if (integers.size() != 0) {
            PageInfo<SendMessage> pageInfo = messageService.selectMessageTogether(startTime, endTime, PageNum, PageSize, integers, type);
            //封装数据
            sendMessagePageInfo = messageService.selectMessageDetail(pageInfo);
        }
        return sendMessagePageInfo;
    }

    /**
     * 短信列表 导出
     *
     * @param CRM_TOKEN
     * @param startTime
     * @param endTime
     * @param PageNum
     * @param PageSize
     * @param checkedDepartmentId
     * @param checkedUserId
     * @param flag
     * @param type
     * @param response
     */
    @ResponseBody
    @RequestMapping("/exportMessageList")
    public void exportMessageList(String CRM_TOKEN, String startTime, String endTime, Integer PageNum, Integer PageSize,
                                  Integer checkedDepartmentId, Integer checkedUserId, String flag, Integer type, HttpServletResponse response) {
        Object userId = loginService.getPropertyData(CRM_TOKEN, "user", "id");
        Object companyId = loginService.getPropertyData(CRM_TOKEN, "company", "id");
        List<Map> integers = new ArrayList<>();
        List<ShowMessage> messages = new ArrayList<>();
        //场景为我发送的短信
        if (flag.equals(DataEnumerate.STRING_NUM_ONE)) {
            integers = unitAccountService.listUserByDepIdAndUserId(userId, companyId, userId, null);

            //场景不为我发送的短信
        } else {
            //场景不为我发送的短信且选择企业和选择部门为空
            if (checkedUserId == null && checkedDepartmentId == null) {
                //按照场景查询数据
                integers = unitAccountService.listUserByPerMissions(flag, userId, companyId);
            } else if (checkedUserId != null) {
                //选择用户
                integers = unitAccountService.listUserByDepIdAndUserId(userId, companyId, checkedUserId, null);
            } else if (checkedDepartmentId != null) {
                //选择部门
                integers = unitAccountService.listUserByDepIdAndUserId(userId, companyId, null, checkedDepartmentId);
            } else {
                //自己
                integers = unitAccountService.listUserByDepIdAndUserId(userId, companyId, userId, null);
            }
        }
        if (integers.size() != 0) {
            List<SendMessage> pageInfo = messageService.exportMessageTogether(startTime, endTime, PageNum, PageSize, integers, type);
            //封装数据
            messages = messageService.exportMessageDetail(pageInfo);
        }
        //设置excel标题
        String[] headers = ShowMessage.Excel.EXCEL_EXPORT_HEADERS;
        //设置excel文件名
        StringBuilder fileName = new StringBuilder();
        //销售漏斗报表
        fileName.append(ShowMessage.Excel.EXCEL_EXPORT_FILE_NAME_START);
        //文件中无连接符的时间
        fileName.append(DateUtil.currentTime2String());
        // .xls
        fileName.append(ShowMessage.Excel.EXCEL_FILE_SUFFIX_NAME);
        try {
            ExportExcelUtil.exportExcel(response, fileName.toString(), headers, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 短信费用统计
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectMessageUserTogether", method = RequestMethod.POST)
    public Object selectMessageUserTogether(String CRM_TOKEN, String startTime, String endTime,
                                            Integer PageNum, Integer PageSize, Integer checkedDepartmentId, Integer checkedUserId) {
        Object userId = loginService.getPropertyData(CRM_TOKEN, "user", "id");
        Object companyId = loginService.getPropertyData(CRM_TOKEN, "company", "id");
        PageInfo<Map<String, Object>> pageInfo = new PageInfo();
        PageInfo pageInforeturn = new PageInfo();
        //具体用户
        if (checkedUserId != null) {
            pageInfo = unitAccountService.listUserByDepIdAndUserIdPage(userId, companyId, checkedUserId, null, PageNum, PageSize);
            //部门
        } else if (checkedDepartmentId != null) {
            pageInfo = unitAccountService.listUserByDepIdAndUserIdPage(userId, companyId, null, checkedDepartmentId, PageNum, PageSize);
        } else {
            pageInfo = unitAccountService.listUserByDepIdAndUserIdPage(userId, companyId, userId, null, PageNum, PageSize);
        }
        if (pageInfo.getList() != null) {
            pageInforeturn = messageService.selectMessageUserDetail(pageInfo, startTime, endTime);
        }
        return pageInforeturn;
    }

    /**
     * 短信费用统计 总计
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectMessageUserTogetherTotal", method = RequestMethod.POST)
    public Object selectMessageUserTogetherTotal(String CRM_TOKEN, String startTime, String endTime,
                                                 Integer PageNum, Integer PageSize, Integer checkedDepartmentId, Integer checkedUserId) {
        Object userId = loginService.getPropertyData(CRM_TOKEN, "user", "id");
        Object companyId = loginService.getPropertyData(CRM_TOKEN, "company", "id");
        List<Map> pageInfo = new ArrayList<>();
        List<ShowMessageCosts> list = new ArrayList<>();
        //具体用户
        if (checkedUserId != null) {
            pageInfo = unitAccountService.listUserByDepIdAndUserId(userId, companyId, checkedUserId, null);
            //部门
        } else if (checkedDepartmentId != null) {
            pageInfo = unitAccountService.listUserByDepIdAndUserId(userId, companyId, null, checkedDepartmentId);
        } else {
            pageInfo = unitAccountService.listUserByDepIdAndUserId(userId, companyId, userId, null);
        }
        if (pageInfo.size() != 0 && pageInfo != null) {
            list = messageService.selectMessageUserDetailTotal(pageInfo, startTime, endTime);
        }
        double totalExpenses = 0.00;
        int sendNumber = 0;
        int arrivalNumber = 0;
        if (list.size() != 0) {
            for (ShowMessageCosts m : list) {
                totalExpenses = totalExpenses + m.getTotalExpenses();
                sendNumber = sendNumber + m.getSendNumber();
                arrivalNumber = arrivalNumber + m.getArrivalNumber();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("totalExpenses", totalExpenses);
        map.put("sendNumber", sendNumber);
        map.put("arrivalNumber", arrivalNumber);
        return map;
    }

    /**
     * 短信费用统计 导出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exportMessageUserTogetherTotal", method = RequestMethod.POST)
    public void exportMessageUserTogetherTotal(String CRM_TOKEN, String startTime, String endTime, Integer PageNum,
                                               Integer PageSize, Integer checkedDepartmentId, Integer checkedUserId, HttpServletResponse response) {
        Object userId = loginService.getPropertyData(CRM_TOKEN, "user", "id");
        Object companyId = loginService.getPropertyData(CRM_TOKEN, "company", "id");
        List<Map> pageInfo = new ArrayList<>();
        List<ShowMessageCosts> list = new ArrayList<>();
        //具体用户
        if (checkedUserId != null) {
            pageInfo = unitAccountService.listUserByDepIdAndUserId(userId, companyId, checkedUserId, null);
            //部门
        } else if (checkedDepartmentId != null) {
            pageInfo = unitAccountService.listUserByDepIdAndUserId(userId, companyId, null, checkedDepartmentId);
        } else {
            pageInfo = unitAccountService.listUserByDepIdAndUserId(userId, companyId, userId, null);
        }
        if (pageInfo.size() != 0 && pageInfo != null) {
            list = messageService.selectMessageUserDetailTotal(pageInfo, startTime, endTime);
        }
        //设置excel标题
        String[] headers = ShowMessageCosts.Excel.EXCEL_EXPORT_HEADERS;
        //设置excel文件名
        StringBuilder fileName = new StringBuilder();
        //销售漏斗报表
        fileName.append(ShowMessageCosts.Excel.EXCEL_EXPORT_FILE_NAME_START);
        //文件中无连接符的时间
        fileName.append(DateUtil.currentTime2String());
        // .xls
        fileName.append(ShowMessageCosts.Excel.EXCEL_FILE_SUFFIX_NAME);
        try {
            ExportExcelUtil.exportExcel(response, fileName.toString(), headers, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 短信重发
     *
     * @param apiAcceptData ：接收数据
     * @return
     */
    @RequestMapping("/sendMsg")
    @ResponseBody
    public ApiSendData sendMsg(@RequestBody ApiAcceptData apiAcceptData) {
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                //测试
                JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
                Integer userId = (Integer) data.get("userId");//用户id
                Integer contentId = (Integer) data.get("contentId");//短信模板id
                Integer messageId = (Integer) data.get("messageId");//消息id
                String content = (String) data.get("content");//内容
                String signature = (String) data.get("signature");//签名
                List<String> list = messageService.getidentifyIds(messageId);
                apiSendData = clueService.sendMsg(apiSendData, userId, list, contentId, content, signature);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    /**
     * 短信重发
     *
     * @param apiAcceptData ：接收数据
     * @return
     */
    @RequestMapping("/sendMsgBySendContentTem")
    @ResponseBody
    public ApiSendData sendMsgBySendContentTem(@RequestBody ApiAcceptData apiAcceptData) {
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                //测试
                JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
                Integer userId = (Integer) data.get("userId");//用户id
                Integer contentId = (Integer) data.get("contentId");//短信模板id
                Integer messageId = (Integer) data.get("messageId");//消息id
                List<String> list = messageService.getidentifyIds(messageId);
                MessageTemplate messageTemplate = clueService.getMessageTemplate(contentId);
                String content = messageTemplate.getContent();
                String signature = messageTemplate.getSignature();
                apiSendData = clueService.sendMsg(apiSendData, userId, list, contentId, content, signature);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

}
