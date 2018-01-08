package com.sunll.web.controller.clue;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunll.clue.api.ClueService;
import com.sunll.clue.entity.business.*;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.common.util.*;
import com.sunll.systemset.api.login.LoginService;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.sunll.common.util.ImportExcel.isRowEmpty;


/**
 * 线索主页查询
 * Created by double on 2017/12/2.
 */
@RequestMapping("/clue")
@Controller
public class ClueController {

    private Logger log = Logger.getLogger(this.getClass());

    @Reference(check = false, timeout = 100000)
    private ClueService clueService;
    @Reference(check = false, timeout = 100000)
    private LoginService loginService;

    /*@Reference(check = false,timeout = 100000)
    SmsService smsService;*/


    /**
     * 跳转新建线索页面
     */
    @RequestMapping("/skipAddClue")
    public String skipAddClue() {
        return "clue/clue-add";
    }

    @RequestMapping("/login")
    public String login() {
        return "clue/clue";
    }

    @RequestMapping("/skipCall")
    public String skipCall(Integer businessTypeId, String clueId, Model model) {
        model.addAttribute("businessTypeId", businessTypeId);
        model.addAttribute("clueId", clueId);
        return "clue/call";
    }

    @RequestMapping("/skipTransfer")
    public String skipTransfer() {
        return "clue/RoleAdd";
    }

    @RequestMapping("skipDetail")
    public String skipDetail(Integer businessTypeId, String clueId, Model model) {
        model.addAttribute("businessTypeId", businessTypeId);
        model.addAttribute("clueId", clueId);
        return "clue/clue-details";
    }


    /**
     * 线索详情
     */
    @RequestMapping("/clueDetail")
    @ResponseBody
    public ApiSendData clueDetail(@RequestBody ApiAcceptData apiAcceptData) {
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        apiSendData = clueService.getClueDetail(apiSendData, apiAcceptData);
        return apiSendData;
    }


    /**
     * 新建线索
     */
    @RequestMapping(value = "/createClue", method = RequestMethod.POST)
    @ResponseBody
    public ApiSendData createClue(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("新建线索请求参数：" + JSON.toJSONString(apiAcceptData));
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
                apiSendData.setVersion(apiAcceptData.getVersion());
                //获取业务字段
                apiSendData = clueService.createClue(apiSendData, apiAcceptData);
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
     * 保存新建线索
     *
     * @param apiAcceptData
     * @return
     */
    @RequestMapping("/saveClue")
    @ResponseBody
    public ApiSendData saveClue(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("接受到的线索数据" + apiAcceptData.getData());
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
                apiSendData.setVersion(apiAcceptData.getVersion());
                //保存线索
                apiSendData = clueService.saveClue(apiSendData, apiAcceptData);
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
     * 保存编辑线索
     *
     * @param apiAcceptData
     * @return
     */
    @RequestMapping("/updateEditClue")
    @ResponseBody
    public ApiSendData updateEditClue(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("接受到的线索数据" + apiAcceptData.getData());
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
                apiSendData.setVersion(apiAcceptData.getVersion());
                //编辑线索
                apiSendData = clueService.updateEditClue(apiSendData, apiAcceptData);
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
     * 转移线索
     *
     * @param apiAcceptData
     * @return
     */
    @RequestMapping("/transferClue")
    @ResponseBody
    public ApiSendData transferClue(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("转移线索：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData(new JSONObject());
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (org.apache.commons.lang3.StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData = clueService.transferClue(apiSendData, apiAcceptData);
                apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                apiAcceptData.setData(new JSONObject());
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
            e.printStackTrace();
        }
        return apiSendData;
    }

    /***
     * 短信模板显示
     */
    @RequestMapping("/showMessageTemplate")
    @ResponseBody
    public ApiSendData showMessageTemplate(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("短信模板参数：" + JSON.toJSONString(apiAcceptData));
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
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
                Integer companyId = (Integer) jsonObject.get("companyId");
                apiSendData = clueService.showMessageTemplate(apiSendData, companyId);
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
     * 短信发送
     *
     * @param apiAcceptData
     * @return
     */
    @RequestMapping("/sendMsgApi")
    @ResponseBody
    public ApiSendData sendMsgApi(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取前台发送短信信息" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                //测试
                JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
                Integer userId = (Integer) data.get("userId");
                List<String> clueId = (List) (data.get("clueId"));
                Integer contentId = (Integer) data.get("contentId");
                String content = (String) data.get("content");
                String signature = (String) data.get("signature");
                apiSendData = clueService.sendMsg(apiSendData, userId, clueId, contentId, content, signature);
                apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                apiSendData.setData(new JSONObject());
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
        }
        return apiSendData;
    }

    /**
     * 短信发送
     *
     * @param apiAcceptData
     * @return
     */
    @RequestMapping("/sendMsg")
    @ResponseBody
    public ApiSendData sendMsg(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取前台发送短信信息" + apiAcceptData);
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
                List<String> clueId = (List) (data.get("clueId"));
                Integer userId = (Integer) data.get("userId");
                Integer contentId = (Integer) data.get("contentId");
                String content = (String) data.get("content");
                String signature = (String) data.get("signature");
                apiSendData = clueService.sendMsg(apiSendData, userId, clueId, contentId, content, signature);
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

    @RequestMapping(value = "/listClue", method = RequestMethod.POST)
    @ResponseBody
    public ApiSendData listClue(@RequestBody ApiAcceptData apiAcceptData) {
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        //查询线索
        apiSendData = clueService.listClue(apiSendData, apiAcceptData);
        return apiSendData;
    }

    @RequestMapping(value = "/listClueApp", method = RequestMethod.POST)
    @ResponseBody
    public ApiSendData listClueApp(@RequestBody ApiAcceptData apiAcceptData) {
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        //查询线索
        apiSendData = clueService.listClueApp(apiSendData, apiAcceptData);
        return apiSendData;
    }

    @RequestMapping(value = "/exportClue")
    @ResponseBody
    public void exportClue(String apiAcceptData, HttpServletResponse response) {
        System.out.println(apiAcceptData);
        ApiSendData apiSendData = ApiSendData.getIstance();
        ApiAcceptData apiAcceptData1 = JsonUtils.jsonToPojo(apiAcceptData, ApiAcceptData.class);
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        //查询线索
        Map<String, Object> result = clueService.exportClue(apiSendData, apiAcceptData1);//获取需要导出的数据
        List<Map> resultMapList = (List<Map>) result.get("resultMapList");
        List<String> headList = (List<String>) result.get("headList");
        int size = headList.size();
        String[] headers = headList.toArray(new String[size]);
        StringBuffer sb = new StringBuffer();
        sb.append(BusinessTypeValueIdentify.ExportExcilClue.EXPORT_FILE_NAME_START).append(DateUtil.currentTime2String())
                .append(BusinessTypeValueIdentify.ExportExcilClue.EXPORT_FILE_SUFFIX_NAME);
        String fileName = sb.toString();
        try {
            ExportExcelMapUtil.exportExcel(response, fileName, headers, resultMapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/importClues/{userId}/{companyId}/{checkBox}")
    @ResponseBody
    public ApiSendData importClues(@RequestParam(value = "file", required = false) MultipartFile file
            , @PathVariable String userId, @PathVariable String companyId,@PathVariable String checkBox) {
   /*     List<Object> importList = new ArrayList<>();
        List<Map<String,Object>> errorList =  new ArrayList<>();
        boolean isNullFile =false;
        int num = 0;*/
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            validateData(file, userId, companyId,checkBox);
            apiSendData.setData(new JSONObject());
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());

        } catch (Exception e) {
            apiSendData.setData(new JSONObject());
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            e.printStackTrace();
        }

        return apiSendData;
    }

    public Map<String, Object> validateData(MultipartFile file, String userId, String companyId,String checkBox) {
        List<Map<String, Object>> templateListMap = new ArrayList<>();
        List<List> list = new ArrayList<>();
        String errorMsg = "";
        ImportExcel<Object> excel = new ImportExcel<>();
        String[] tableHeader = excel.getTableHeader(file);
        CompanyOrganizationBusiness cob = clueService.selectByCompanyId(Integer.parseInt(companyId));
        Integer businessId = cob.getBusinessId();
        List<BusinessType> businessTypes = clueService.selectByBusinessId(cob.getBusinessId());
        Integer businessTypeId = businessTypes.get(0).getId();
        boolean flag=true;
        for(String st: tableHeader){
            try{
                List<BusinessTypeField> businessTypeFields = clueService.
                        selectByBusinessTypeIdAndTableHeader(businessTypeId,st);
                Map<String,Object> map = new HashMap<>();
                map.put("fieldId",businessTypeFields.get(0).getId().toString());
                map.put("value",null);
                templateListMap.add(map);
            }
            catch (Exception e){
                e.printStackTrace();
                flag=false;
                templateListMap.clear();
                errorMsg="表头信息有误";
                break;
            }
        }
        String fileName = file.getOriginalFilename();
        try {
            Workbook wb;
            if (fileName.toLowerCase().endsWith("xls")) {
                wb = new HSSFWorkbook(file.getInputStream());
            } else {
                wb = new XSSFWorkbook(file.getInputStream());
            }
            Sheet sheet = wb.getSheetAt(0);
            if (sheet.getPhysicalNumberOfRows() != 0) {
                //获得总列数
                int clos = sheet.getRow(0).getPhysicalNumberOfCells();
                //得到所有的行
                int rows = sheet.getLastRowNum();
                for (int i = 1; i < rows + 1; i++) {
                    Row row = sheet.getRow(i);
                    List<Map<String, Object>> contentListMap = new ArrayList<>();
                    //判断是否为空行
                    if (row != null && !isRowEmpty(row)) {
                        for (int j = 0; j < clos; j++) {
                            Cell cell = row.getCell(j);
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            String value =cell.getStringCellValue();
                            if (value == null || value == "") {
                                errorMsg = "所填内容存在空值";
                            } else {
                                Map<String, Object> m = new HashMap<>();
                                m.putAll(templateListMap.get(j));
                                String  fieldId =(String)m.get("fieldId");
                                m.put("value", Validate.clearSpace(value));
                                contentListMap.add(m);
                            }
                        }
                        list.add(contentListMap);
                    }
                }
                if(errorMsg==""){
                    clueService.batchSaveClues(Integer.parseInt(userId),businessTypeId,businessId,list,checkBox);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("/checkBusinessStatus")
    @ResponseBody
    public ApiSendData checkBusinessStatus(@RequestBody ApiAcceptData apiAcceptData){
        log.info("获取自定义业务列表接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (org.apache.commons.lang3.StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData=clueService.checkBusinessStatus(apiSendData,apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
            e.printStackTrace();
        }
        return apiSendData;
    }

    @RequestMapping(value = "/exportClueExcel")
    @ResponseBody
    public void exportClueExcel(String apiAcceptData, HttpServletResponse response) {
        System.out.println(apiAcceptData);
        ApiSendData apiSendData = ApiSendData.getIstance();
        ApiAcceptData apiAcceptData1 = JsonUtils.jsonToPojo(apiAcceptData, ApiAcceptData.class);
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        //查询线索
        Map<String, Object> result = clueService.exportClueExcel(apiSendData, apiAcceptData1);//获取需要导出的数据
        List<String> headList = (List<String>) result.get("headList");
        int size = headList.size();
        String[] headers = headList.toArray(new String[size]);
        StringBuffer sb = new StringBuffer();
        sb.append(BusinessTypeValueIdentify.ExportExcilClue.EXPORT_FILE_NAME_START).append(DateUtil.currentTime2String())
                .append(BusinessTypeValueIdentify.ExportExcilClue.EXPORT_FILE_SUFFIX_NAME);
        String fileName = sb.toString();
        try {
            ExportExcelMapUtil.exportExcel(response, fileName, headers, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取自定义业务列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectBusiness", method = RequestMethod.POST)
    public ApiSendData selectBusiness(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取自定义业务列表接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (org.apache.commons.lang3.StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData = clueService.selectBusiness(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
            e.printStackTrace();
        }
        return apiSendData;
    }

    /**
     * 改变更新状态
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "changeFollowStatus", method = RequestMethod.POST)
    public ApiSendData changeFollowStatus(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("获取自定义业务列表接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (org.apache.commons.lang3.StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData = clueService.changeFollowStatus(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
            e.printStackTrace();
        }
        return apiSendData;
    }

    /**
     * 删除线索
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delClue", method = RequestMethod.POST)
    public ApiSendData delClue(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("删除线索接口请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (org.apache.commons.lang3.StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //开通对应的行业模板信息
                apiSendData = clueService.delClue(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
            e.printStackTrace();
        }
        return apiSendData;
    }

    /***
     * 短信模板显示
     */
    @RequestMapping(value = "/getSmsTem", method = RequestMethod.POST)
    @ResponseBody
    public ApiSendData getSmsTem(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("短信模板参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData = clueService.getSmsTem(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
        }
        return apiSendData;
    }

    @RequestMapping(value = "/insertClueUserShowField", method = RequestMethod.POST)
    @ResponseBody
    public ApiSendData insertClueUserShowField(@RequestBody ApiAcceptData apiAcceptData) {
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        //查询线索
        apiSendData = clueService.insertClueUserShowField(apiSendData, apiAcceptData);
        return apiSendData;
    }

    /**
     * 拨打线索电话
     */
    @RequestMapping(value = "/callClue", method = RequestMethod.POST)
    @ResponseBody
    public ApiSendData callClue(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("新建线索请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //获取业务字段
                apiSendData = clueService.callClue(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
        }
        return apiSendData;

    }

    /**
     * 获取跟进记录以及指向门店列表
     */
    @RequestMapping(value = "/getFollowRecordOrPointStore", method = RequestMethod.POST)
    @ResponseBody
    public ApiSendData getFollowRecordOrPointStore(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("新建线索请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new HashMap<>());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //获取业务字段
                apiSendData = clueService.getFollowRecordOrPointStore(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new HashMap<>());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new HashMap<>());
        }
        return apiSendData;
    }

    /**
     * 线索详情API
     */
    @RequestMapping("/clueDetailApi")
    @ResponseBody
    public ApiSendData getClueDetailApi(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("新建线索请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //获取业务字段
                apiSendData = clueService.getClueDetailApi(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
        }
        return apiSendData;
    }

    /**
     * 获取线索的系统信息
     */
    @RequestMapping("/getClueSystemInfo")
    @ResponseBody
    public ApiSendData getClueSystemInfo(@RequestBody ApiAcceptData apiAcceptData) {
        log.info("新建线索请求参数：" + JSON.toJSONString(apiAcceptData));
        ApiSendData apiSendData = ApiSendData.getIstance();
        apiSendData.setVersion("");
        apiSendData.setData("");
        apiSendData.setMessage("");
        try {
            //验证版本号
            if (StringUtils.isBlank(apiAcceptData.getVersion())) {
                apiSendData.setCode(ApiReturnCode.VERSION_IS_EMPTY.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_IS_EMPTY.getMessage());
                apiSendData.setData(new JSONObject());
                return apiSendData;
            }
            if ("V1.0".equals(apiAcceptData.getVersion())) {
                apiSendData.setVersion(apiAcceptData.getVersion());
                //获取业务字段
                apiSendData = clueService.getClueSystemInfo(apiSendData, apiAcceptData);
            } else {
                apiSendData.setCode(ApiReturnCode.VERSION_NO_EXIST.getCode());
                apiSendData.setMessage(ApiReturnCode.VERSION_NO_EXIST.getMessage());
                apiSendData.setData(new JSONObject());
            }
        } catch (Exception e) {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
        }
        return apiSendData;
    }

}
