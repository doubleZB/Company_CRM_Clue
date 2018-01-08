package com.sunll.systemset.service.business;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.systemset.api.business.BusinessTypeFieldService;
import com.sunll.systemset.dao.business.BusinessTypeFieldMapper;
import com.sunll.systemset.dao.business.BusinessTypeFieldValueChooseMapper;
import com.sunll.systemset.entity.business.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunll
 * on 2017/11/30
 */
@Service
public class BusinessTypeFieldServiceImpl implements BusinessTypeFieldService {

    @Autowired
    private BusinessTypeFieldMapper businessTypeFieldMapper;
    @Autowired
    private BusinessTypeFieldValueChooseMapper businessTypeFieldValueChooseMapper;

    @Override
    public ApiSendData selectByBusinessTypeIdAndPid(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型id
        Integer businessTypeId = (Integer) data.get("businessTypeId");
        //获取业务类型字段pid
        Integer pid = (Integer) data.get("pid");
        Map<String, Object> map = new HashMap<>();
        map.put("businessTypeId", businessTypeId);
        map.put("pid", pid);
        List<BusinessTypeField> businessTypeFieldList = businessTypeFieldMapper.selectByBusinessTypeIdAndPid(map);
        for (BusinessTypeField businessTypeField : businessTypeFieldList) {
            for (BusinessTypeField businessTypeField1:businessTypeField.getBusinessTypeFieldList()){
                if ("select".equals(businessTypeField1.getFieldType()) || "checkbox".equals(businessTypeField1.getFieldType())) {
                    BusinessTypeFieldValueChooseExample chooseExample = new BusinessTypeFieldValueChooseExample();
                    chooseExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField1.getId());
                    List<BusinessTypeFieldValueChoose> chooseList = businessTypeFieldValueChooseMapper.selectByExample(chooseExample);
                    businessTypeField1.setBusinessTypeFieldValueChooseList(chooseList);
                }
            }

        }
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        apiSendData.setData(businessTypeFieldList);
        return apiSendData;
    }

    @Override
    public ApiSendData insertOrEditBusinessTypeField(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型
        BusinessTypeField businessTypeField = JSON.parseObject(
                JSON.toJSONString(data.get("businessTypeField")), BusinessTypeField.class);

        if (businessTypeField.getId() != null) {
            //更新字段信息信息
            businessTypeField.setUpdateTime(new Date());
            int num = businessTypeFieldMapper.updateByPrimaryKeySelective(businessTypeField);
            if (num > 0) {
                if ("select".equals(businessTypeField.getFieldType()) ||
                        "checkbox".equals(businessTypeField.getFieldType())) {
                    //删除业务类型字段对应的选择值
                    BusinessTypeFieldValueChooseExample businessTypeFieldValueChooseExample = new BusinessTypeFieldValueChooseExample();
                    businessTypeFieldValueChooseExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                    businessTypeFieldValueChooseMapper.deleteByExample(businessTypeFieldValueChooseExample);
                    for (BusinessTypeFieldValueChoose businessTypeFieldValueChoose : businessTypeField
                            .getBusinessTypeFieldValueChooseList()) {
                        //插入业务类型字段对应的选择值
                        businessTypeFieldValueChoose.setBusinessTypeFieldId(businessTypeField.getId());
                        businessTypeFieldValueChoose.setIsDel(1);
                        businessTypeFieldValueChoose.setCreateUserId(businessTypeField.getUpdateUserId());
                        businessTypeFieldValueChoose.setCreateTime(new Date());
                        businessTypeFieldValueChooseMapper.insertSelective(businessTypeFieldValueChoose);
                    }
                }
                apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
            } else {
                apiSendData.setCode(ApiReturnCode.ERROR.getCode());
                apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            }
        } else {
            //插入字段信息
            businessTypeField.setIsDel(1);
            businessTypeField.setCreateTime(new Date());
            businessTypeField.setIsShow(2);
            int num1 = businessTypeFieldMapper.insertSelective(businessTypeField);
            BusinessTypeField businessTypeField1 = new BusinessTypeField();
            businessTypeField1.setId(businessTypeField.getId());
            businessTypeField1.setSort(businessTypeField.getId());
            businessTypeFieldMapper.updateByPrimaryKeySelective(businessTypeField1);
            if (num1 > 0) {
                if ("select".equals(businessTypeField.getFieldType()) ||
                        "checkbox".equals(businessTypeField.getFieldType())) {
                    for (BusinessTypeFieldValueChoose businessTypeFieldValueChoose : businessTypeField
                            .getBusinessTypeFieldValueChooseList()) {
                        //插入业务类型字段对应的选择值
                        businessTypeFieldValueChoose.setBusinessTypeFieldId(businessTypeField.getId());
                        businessTypeFieldValueChoose.setIsDel(1);
                        businessTypeFieldValueChoose.setCreateUserId(businessTypeField.getCreateUserId());
                        businessTypeFieldValueChoose.setCreateTime(new Date());
                        businessTypeFieldValueChooseMapper.insertSelective(businessTypeFieldValueChoose);
                    }
                }
                apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
            } else {
                apiSendData.setCode(ApiReturnCode.ERROR.getCode());
                apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            }
        }
        return apiSendData;
    }

    @Override
    public ApiSendData selectBusinessTypeField(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型id
        Integer businesTypeFieldId = (Integer) data.get("businesTypeFieldId");
        BusinessTypeField businessTypeField = businessTypeFieldMapper.selectByPrimaryKey(businesTypeFieldId);
        if ("select".equals(businessTypeField.getFieldType()) || "checkbox".equals(businessTypeField.getFieldType())) {
            //获取复选框或者单选框的数据
            BusinessTypeFieldValueChooseExample businessTypeFieldValueChooseExample = new BusinessTypeFieldValueChooseExample();
            businessTypeFieldValueChooseExample.createCriteria()
                    .andBusinessTypeFieldIdEqualTo(businessTypeField.getId())
                    .andIsDelEqualTo(1);
            List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooseList = businessTypeFieldValueChooseMapper
                    .selectByExample(businessTypeFieldValueChooseExample);
            businessTypeField.setBusinessTypeFieldValueChooseList(businessTypeFieldValueChooseList);
        }
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        apiSendData.setData(businessTypeField);
        return apiSendData;
    }

    @Override
    public ApiSendData isEnabledBusinessTypeField(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型id
        Integer businessTypeFieldId = (Integer) data.get("businessTypeFieldId");
        //获取是否启用字段数据
        Integer isEnabled = (Integer) data.get("isEnabled");
        //获取更新人数据
        Integer updateUserId = Integer.valueOf(data.get("updateUserId").toString());
        //更新数据
        BusinessTypeField businessTypeField = new BusinessTypeField();
        businessTypeField.setId(businessTypeFieldId);
        businessTypeField.setIsEnabled(isEnabled);
        businessTypeField.setUpdateTime(new Date());
        businessTypeField.setUpdateUserId(updateUserId);
        int num = businessTypeFieldMapper.updateByPrimaryKeySelective(businessTypeField);
        if (num > 0) {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData isRequiredBusinessTypeField(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型id
        Integer businessTypeFieldId = (Integer) data.get("businessTypeFieldId");
        //获取是否启用字段数据
        Integer isRequired = (Integer) data.get("isRequired");
        //获取更新人数据
        Integer updateUserId = Integer.valueOf(data.get("updateUserId").toString());
        //更新数据
        BusinessTypeField businessTypeField = new BusinessTypeField();
        businessTypeField.setId(businessTypeFieldId);
        businessTypeField.setIsRequired(isRequired);
        businessTypeField.setUpdateTime(new Date());
        businessTypeField.setUpdateUserId(updateUserId);
        int num = businessTypeFieldMapper.updateByPrimaryKeySelective(businessTypeField);
        if (num > 0) {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData delBusinessTypeField(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型id
        Integer businesTypeFieldId = (Integer) data.get("businesTypeFieldId");
        //获取更新人数据
        Integer updateUserId = Integer.valueOf(data.get("updateUserId").toString());
        //更新数据
        BusinessTypeField businessTypeField = new BusinessTypeField();
        businessTypeField.setId(businesTypeFieldId);
        businessTypeField.setIsDel(2);
        businessTypeField.setUpdateTime(new Date());
        businessTypeField.setUpdateUserId(updateUserId);
        int num = businessTypeFieldMapper.updateByPrimaryKeySelective(businessTypeField);
        if (num > 0) {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData selBlockByBusinessTypeId(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型id
        Integer businesTypeId = (Integer) data.get("businesTypeId");
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria()
                .andBusinessTypeIdEqualTo(businesTypeId)
                .andPidEqualTo(0);
        List<BusinessTypeField> businessTypeFieldList = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        apiSendData.setData(businessTypeFieldList);
        return apiSendData;
    }

    @Override
    public ApiSendData isExistBlock(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));

        Integer businessTypeId = (Integer) data.get("businessTypeId");
        String blockName = (String) data.get("blockName");
        String businessTypeFieldId = (String) data.get("businessTypeFieldId");
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria()
                .andBusinessTypeIdEqualTo(businessTypeId)
                .andFieldNameEqualTo(blockName)
                .andPidEqualTo(0)
                .andIsDelEqualTo(1);
        List<BusinessTypeField> businessTypeFieldList = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
        if (businessTypeFieldList.size() > 0) {
            if ("".equals(businessTypeFieldId)) {
                apiSendData.setCode(ApiReturnCode.INFO_IS_EXIST.getCode());
            } else {
                if (String.valueOf(businessTypeFieldList.get(0).getId()).equals(businessTypeFieldId)) {
                    apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                } else {
                    apiSendData.setCode(ApiReturnCode.INFO_IS_EXIST.getCode());
                }
            }

        } else {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        }
        return apiSendData;
    }

}
