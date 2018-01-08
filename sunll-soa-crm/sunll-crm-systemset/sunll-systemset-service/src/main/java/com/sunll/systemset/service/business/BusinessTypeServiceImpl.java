package com.sunll.systemset.service.business;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.systemset.api.business.BusinessTypeService;
import com.sunll.systemset.dao.business.BusinessMapper;
import com.sunll.systemset.dao.business.BusinessTypeMapper;
import com.sunll.systemset.dao.business.CompanyOrganizationBusinessMapper;
import com.sunll.systemset.entity.business.Business;
import com.sunll.systemset.entity.business.BusinessExample;
import com.sunll.systemset.entity.business.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {

    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private CompanyOrganizationBusinessMapper companyOrganizationBusinessMapper;
    @Autowired
    private BusinessTypeMapper businessTypeMapper;


    @Override
    public ApiSendData listBusinessByCompanyId(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取登录的企业id
        Integer companyId = Integer.valueOf(data.get("companyId").toString());

        //获取所有的业务以及每个业务对应的业务类型
        BusinessExample businessExample = new BusinessExample();
        businessExample.createCriteria()
                .andCompanyIdEqualTo(companyId).andIsDelEqualTo(1);
        List<Business> businessList = businessMapper.selectByExample(businessExample);
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        apiSendData.setData(businessList);
        return apiSendData;
    }

    @Override
    public ApiSendData isEnabledBusinessType(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取登录的企业id
        Integer businessTypeId = (Integer) data.get("businessTypeId");
        //是否启用或者停用状态
        Integer isEnabled = (Integer) data.get("isEnabled");
        BusinessType businessType = new BusinessType();
        businessType.setIsEnabled(isEnabled);
        businessType.setId(businessTypeId);
        int num = businessTypeMapper.updateByPrimaryKeySelective(businessType);
        if (num>0){
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        }else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData editBusinessType(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {

        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型
        BusinessType businessType = JSON.parseObject(JSON.toJSONString(data.get("businessType")),BusinessType.class);
        int num = businessTypeMapper.updateByPrimaryKeySelective(businessType);
        if (num>0){
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        }else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData delBusinessType(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取登录的企业id
        Integer businessTypeId = (Integer) data.get("businessTypeId");
        BusinessType businessType = new BusinessType();
        businessType.setIsDel(1);
        businessType.setId(businessTypeId);
        int num = businessTypeMapper.updateByPrimaryKeySelective(businessType);
        if (num>0){
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        }else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }
}
