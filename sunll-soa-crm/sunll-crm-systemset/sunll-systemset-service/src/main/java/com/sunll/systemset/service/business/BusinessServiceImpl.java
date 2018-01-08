package com.sunll.systemset.service.business;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.common.redis.JedisClient;
import com.sunll.common.util.HttpclientUtils;
import com.sunll.common.util.JsonUtils;
import com.sunll.common.util.PropertiesUtils;
import com.sunll.systemset.api.business.BusinessService;
import com.sunll.systemset.dao.business.*;
import com.sunll.systemset.dao.template.BusinessBusinesstypeTemMapper;
import com.sunll.systemset.dao.template.BusinessTemMapper;
import com.sunll.systemset.dao.template.BusinessTypeFieldBlockTemMapper;
import com.sunll.systemset.dao.template.BusinessTypeFieldValueChooseTemMapper;
import com.sunll.systemset.entity.business.*;
import com.sunll.systemset.entity.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    private static final String UNITE_ACCOUNT_BASE_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("UNITE_ACCOUNT_BASE_URL");
    private static final String GET_ORG_NAME_BY_ORG_IDS = (String) PropertiesUtils
            .loadProperties("config.properties").get("GET_ORG_NAME_BY_ORG_IDS");

    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private BusinessTemMapper businessTemMapper;
    @Autowired
    private BusinessTypeFieldBlockTemMapper businessTypeFieldBlockTemMapper;
    @Autowired
    private CompanyOrganizationBusinessMapper companyOrganizationBusinessMapper;
    @Autowired
    private BusinessBusinesstypeTemMapper businessBusinesstypeTemMapper;
    @Autowired
    private BusinessTypeMapper businessTypeMapper;
    @Autowired
    private BusinessTypeFieldMapper businessTypeFieldMapper;
    @Autowired
    private BusinessTypeFieldValueChooseTemMapper businessTypeFieldValueChooseTemMapper;
    @Autowired
    private BusinessTypeFieldValueChooseMapper businessTypeFieldValueChooseMapper;

    @Override
    public int insertSelective(Business business) {
        business.setIsDel(1);
        business.setCreateTime(new Date());
        return businessMapper.insertSelective(business);
    }

    /**
     * 自定义业务类型
     *
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public ApiSendData insertBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {

        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型
        Business business = JSON.parseObject(JSON.toJSONString(data.get("business")), Business.class);
        //获取所有组织id
        List<Integer> organizationList = (List<Integer>) data.get("organizationList");
        //获取行业类别
        Integer industryType = business.getIndustryType();
        //查询公司下的业务
        List<BusinessTem> businessTemList = new ArrayList<>();
        if (jedisClient.get("BusinessTem_" + industryType) != null) {
            businessTemList = JsonUtils.jsonToList(jedisClient.get("BusinessTem_" + industryType), BusinessTem.class);
        } else {
            BusinessTemExample businessTemExample = new BusinessTemExample();
            businessTemExample.createCriteria().andIndustryTypeEqualTo(industryType);
            businessTemList = businessTemMapper.selectByExample(businessTemExample);
            jedisClient.set("BusinessTem_" + industryType, JsonUtils.objectToJson(businessTemList));
        }
        assert businessTemList != null;
        if (!businessTemList.isEmpty()) {
            for (BusinessTem businessTem : businessTemList) {
                //插入业务模板数据
                insertSelective(business);
                int businessId = business.getId();
                //插入数据企业组织和业务关联表中
                for (Integer orgId : organizationList) {
                    CompanyOrganizationBusiness companyOrganizationBusiness = new CompanyOrganizationBusiness();
                    companyOrganizationBusiness.setBusinessId(businessId);
                    companyOrganizationBusiness.setCompanyId(business.getCompanyId());
                    companyOrganizationBusiness.setOrganizationId(orgId);
                    companyOrganizationBusiness.setIsDel(1);
                    companyOrganizationBusinessMapper.insertSelective(companyOrganizationBusiness);
                }
                //查询业务下的所有业务类型


                List<BusinessBusinesstypeTem> businessBusinesstypeTemList = new ArrayList<>();
                if (jedisClient.get("BusinessBusinesstypeTem_" + businessTem.getId()) != null) {
                    businessBusinesstypeTemList = JsonUtils.jsonToList(jedisClient.
                            get("BusinessBusinesstypeTem_" + businessTem.getId()), BusinessBusinesstypeTem.class);
                } else {
                    BusinessBusinesstypeTemExample businessBusinesstypeTemExample = new BusinessBusinesstypeTemExample();
                    businessBusinesstypeTemExample.createCriteria().andBusinessTemplateIdEqualTo(businessTem.getId());
                    businessBusinesstypeTemList = businessBusinesstypeTemMapper.
                            listByExample(businessBusinesstypeTemExample);
                    jedisClient.set("BusinessBusinesstypeTem_" + businessTem.getId(), JsonUtils.objectToJson(businessBusinesstypeTemList));
                }

                //业务类型
                assert businessBusinesstypeTemList != null;
                if (!businessBusinesstypeTemList.isEmpty()) {
                    for (BusinessBusinesstypeTem businessBusinesstypeTem : businessBusinesstypeTemList) {
                        if (businessBusinesstypeTem.getBusinessTypeTem() != null) {
                            //插入业务类型
                            BusinessTypeTem businessTypeTem = businessBusinesstypeTem.getBusinessTypeTem();
                            BusinessType businessType = new BusinessType();
                            businessType.setBusinessId(businessId);
                            businessType.setCreateTime(new Date());
                            businessType.setCreateUserId(business.getCreateUserId());
                            businessType.setIsDel(1);
                            businessType.setName(businessTypeTem.getName());
                            businessType.setShowName(businessTypeTem.getShowName());
                            businessType.setIsEnabled(businessTypeTem.getIsEnabled());
                            businessType.setTypeStatus(businessTypeTem.getTypeStatus());
                            businessTypeMapper.insertSelective(businessType);
                            int businessTypeId = businessType.getId();
                            //获取业务类型对应的区块以及字段信息

                            List<BusinessTypeFieldBlockTem> businessTypeFieldBlockTemList = new ArrayList<>();
                            if (jedisClient.get("BusinessTypeFieldBlockTem_" + businessBusinesstypeTem.getBusinessTypeTem().getId()) != null) {
                                businessTypeFieldBlockTemList = JsonUtils.jsonToList(jedisClient.
                                        get("BusinessTypeFieldBlockTem_" + businessBusinesstypeTem.getBusinessTypeTem().getId()), BusinessTypeFieldBlockTem.class);
                            } else {
                                businessTypeFieldBlockTemList =
                                        businessTypeFieldBlockTemMapper.selectBlockAndFieldByBusinessTypeId(
                                                businessBusinesstypeTem.getBusinessTypeTem().getId());
                                jedisClient.set("BusinessTypeFieldBlockTem_" + businessBusinesstypeTem.getBusinessTypeTem().getId(),
                                        JsonUtils.objectToJson(businessTypeFieldBlockTemList));
                            }
                            assert businessTypeFieldBlockTemList != null;
                            if (!businessTypeFieldBlockTemList.isEmpty()) {
                                for (BusinessTypeFieldBlockTem businessTypeFieldBlockTem : businessTypeFieldBlockTemList) {
                                    //插入业务区块字段
                                    BusinessTypeField businessTypeField = new BusinessTypeField();
                                    businessTypeField.setBusinessTypeId(businessTypeId);
                                    businessTypeField.setFieldName(businessTypeFieldBlockTem.getFieldName());
                                    businessTypeField.setFieldType(businessTypeFieldBlockTem.getFieldType());
                                    businessTypeField.setFieldAlias(businessTypeFieldBlockTem.getFieldAlias());
                                    businessTypeField.setIsEnabled(businessTypeFieldBlockTem.getIsEnabled());
                                    businessTypeField.setIsRequired(businessTypeFieldBlockTem.getIsRequired());
                                    businessTypeField.setPid(0);
                                    businessTypeField.setIsDel(1);
                                    businessTypeField.setCreateTime(new Date());
                                    businessTypeField.setCreateUserId(business.getCreateUserId());
                                    businessTypeFieldMapper.insertSelective(businessTypeField);
                                    int businessTypeFieldId = businessTypeField.getId();
                                    for (BusinessTypeFieldTem businessTypeFieldTem : businessTypeFieldBlockTem.
                                            getBusinessTypeFieldTemList()) {
                                        //插入业务类型字段
                                        BusinessTypeField businessTypeField1 = new BusinessTypeField();
                                        businessTypeField1.setBusinessTypeId(businessTypeId);
                                        businessTypeField1.setFieldName(businessTypeFieldTem.getFieldName());
                                        businessTypeField1.setFieldShowName(businessTypeFieldTem.getFieldShowName());
                                        businessTypeField1.setFieldType(businessTypeFieldTem.getFieldType());
                                        businessTypeField1.setFieldAlias(businessTypeFieldTem.getFieldAlias());
                                        businessTypeField1.setIsEnabled(businessTypeFieldTem.getIsEnabled());
                                        businessTypeField1.setIsRequired(businessTypeFieldTem.getIsRequired());
                                        businessTypeField1.setPid(businessTypeFieldId);
                                        businessTypeField1.setIsDel(1);
                                        businessTypeField1.setCreateTime(new Date());
                                        businessTypeField1.setCreateUserId(business.getCreateUserId());
                                        businessTypeField1.setEnterPrompt(businessTypeFieldTem.getEnterPrompt());
                                        businessTypeField1.setIsModify(businessTypeFieldTem.getIsModify());
                                        businessTypeField1.setIsShow(businessTypeFieldTem.getIsShow());
                                        businessTypeField1.setSort(businessTypeFieldTem.getSort());
                                        businessTypeFieldMapper.insertSelective(businessTypeField1);
                                        int businessTypeFieldId1 = businessTypeField1.getId();
                                        if (("select").equals(businessTypeFieldTem.getFieldType()) ||
                                                ("checkbox").equals(businessTypeFieldTem.getFieldType())) {
                                            List<BusinessTypeFieldValueChooseTem> businessTypeFieldValueChooseTems = new ArrayList<>();
                                            if (jedisClient.get("BusinessTypeFieldValueChooseTem_" + businessTypeFieldTem.getId()) != null) {
                                                businessTypeFieldValueChooseTems = JsonUtils.jsonToList(jedisClient.
                                                        get("BusinessTypeFieldValueChooseTem_" + businessTypeFieldTem.getId()), BusinessTypeFieldValueChooseTem.class);
                                            } else {
                                                BusinessTypeFieldValueChooseTemExample byfvcte =
                                                        new BusinessTypeFieldValueChooseTemExample();
                                                byfvcte.createCriteria().andTypeFieldTemIdEqualTo(businessTypeFieldTem.getId());
                                                businessTypeFieldValueChooseTems =
                                                        businessTypeFieldValueChooseTemMapper.selectByExample(byfvcte);
                                                jedisClient.set("BusinessTypeFieldValueChooseTem_" + businessTypeFieldTem.getId(),
                                                        JsonUtils.objectToJson(businessTypeFieldValueChooseTems));
                                            }

                                            assert businessTypeFieldValueChooseTems != null;
                                            for (BusinessTypeFieldValueChooseTem businessTypeFieldValueChooseTem :
                                                    businessTypeFieldValueChooseTems) {
                                                BusinessTypeFieldValueChoose businessTypeFieldValueChoose =
                                                        new BusinessTypeFieldValueChoose();
                                                businessTypeFieldValueChoose.setBusinessTypeFieldId(businessTypeFieldId1);
                                                businessTypeFieldValueChoose.setName(
                                                        businessTypeFieldValueChooseTem.getName());
                                                businessTypeFieldValueChoose.setValue(
                                                        businessTypeFieldValueChooseTem.getValue());
                                                businessTypeFieldValueChoose.setIsEnabled(
                                                        businessTypeFieldValueChooseTem.getIsEnabled());
                                                businessTypeFieldValueChoose.setIsDel(1);
                                                businessTypeFieldValueChoose.setCreateTime(new Date());
                                                businessTypeFieldValueChoose.setCreateUserId(business.getCreateUserId());
                                                businessTypeFieldValueChooseMapper.insertSelective(
                                                        businessTypeFieldValueChoose);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public int updateByPrimaryKeySelective(Business record) {
        return businessMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 自定义业务类型
     *
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public ApiSendData updateBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型
        Business business = JSON.parseObject(JSON.toJSONString(data.get("business")), Business.class);
        //获取所有组织id
        List<Integer> organizationList = (List<Integer>) data.get("organizationList");
        int num = updateByPrimaryKeySelective(business);
        if (num > 0) {
            //通过业务id删除企业部门业务关联表
            CompanyOrganizationBusinessExample companyOrganizationBusinessExample = new CompanyOrganizationBusinessExample();
            companyOrganizationBusinessExample.createCriteria().andBusinessIdEqualTo(business.getId());
            int num1 = companyOrganizationBusinessMapper.deleteByExample(companyOrganizationBusinessExample);

            //插入数据企业组织和业务关联表中
            int count = 0;
            for (Integer orgId : organizationList) {
                CompanyOrganizationBusiness companyOrganizationBusiness = new CompanyOrganizationBusiness();
                companyOrganizationBusiness.setBusinessId(business.getId());
                companyOrganizationBusiness.setCompanyId(business.getCompanyId());
                companyOrganizationBusiness.setOrganizationId(orgId);
                int num2 = companyOrganizationBusinessMapper.insertSelective(companyOrganizationBusiness);
                count++;
            }
            if (count == organizationList.size()) {
                apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
            } else {
                apiSendData.setCode(ApiReturnCode.ERROR.getCode());
                apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            }

        }
        return apiSendData;
    }

    /**
     * 获取业务类型
     *
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    @Override
    public ApiSendData selectBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取企业id
        Integer companyId = Integer.valueOf(data.get("companyId").toString());
        List<Business> businessList = businessMapper.getBusinessListByCompanyId(companyId);
        if (businessList.size() > 0) {
            for (Business business : businessList) {
                if (business.getOrgIds().size() > 0) {
                    if (business.getOrgIds().get(0) == 0) {
                        List<String> orgNames = new ArrayList();
                        orgNames.add("全公司");
                        business.setOrgNames(orgNames);
                    } else {
                        //调用接口查询部门名称
                        ApiAcceptData apiAcceptData1 = ApiAcceptData.getIstance();
                        apiAcceptData1.setVersion("V1.0");
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("departmentIds", business.getOrgIds());
                        apiAcceptData1.setData(jsonObject);
                        String reDataString = HttpclientUtils.doPostOnApiAcceptData(UNITE_ACCOUNT_BASE_URL + GET_ORG_NAME_BY_ORG_IDS, apiAcceptData1);
                        JSONObject reData = JSON.parseObject(reDataString);
                        JSONObject data1 = JSON.parseObject(JSON.toJSONString(reData.get("data")));
                        List<String> orgNames = (List<String>) data1.get("departmentNameList");
                        business.setOrgNames(orgNames);
                    }
                }
            }
            apiSendData.setData(businessList);
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        } else {
            apiSendData.setData("");
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        }
        return apiSendData;
    }

    /**
     * 删除业务类型
     *
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public ApiSendData delBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型
        Integer businessId = (Integer) data.get("businessId");
        CompanyOrganizationBusiness companyOrganizationBusiness = new CompanyOrganizationBusiness();
        companyOrganizationBusiness.setBusinessId(businessId);
        companyOrganizationBusiness.setIsDel(2);
        int num = companyOrganizationBusinessMapper.deleteByBusinessId(companyOrganizationBusiness);
        Business business = new Business();
        business.setId(businessId);
        business.setIsDel(2);
        if (num > 0) {
            if (businessMapper.updateByPrimaryKeySelective(business) > 0) {
                apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
            } else {
                apiSendData.setCode(ApiReturnCode.ERROR.getCode());
                apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            }
        }
        return apiSendData;
    }

    /**
     * 启用、停用业务类型
     *
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public ApiSendData isEnabledBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型
        Integer businessId = (Integer) data.get("businessId");
        //获取是否启用标志位
        Integer isEnabled = (Integer) data.get("isEnabled");
        Business business = new Business();
        business.setId(businessId);
        business.setIsEnabled(isEnabled);
        if (businessMapper.updateByPrimaryKeySelective(business) > 0) {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData getBusinessById(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型
        Integer businessId = (Integer) data.get("businessId");
        BusinessExample businessExample = new BusinessExample();
        businessExample.createCriteria().andIdEqualTo(businessId).andIsDelEqualTo(1);
        List<Business> businessList = businessMapper.selectByExample(businessExample);
        if (businessList.size() > 0) {
            for (Business business : businessList) {
                if (business.getOrgIds() != null){
                    if (business.getOrgIds().size() > 0) {
                        if (business.getOrgIds().get(0) == 0) {
                            List<String> orgNames = new ArrayList();
                            orgNames.add("全公司");
                            business.setOrgNames(orgNames);
                        } else {
                            //调用接口查询部门名称
                            ApiAcceptData apiAcceptData1 = ApiAcceptData.getIstance();
                            apiAcceptData1.setVersion("V1.0");
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("departmentIds", business.getOrgIds());
                            apiAcceptData1.setData(jsonObject);
                            String reDataString = HttpclientUtils.doPostOnApiAcceptData(UNITE_ACCOUNT_BASE_URL + GET_ORG_NAME_BY_ORG_IDS, apiAcceptData1);
                            JSONObject reData = JSON.parseObject(reDataString);
                            JSONObject data1 = JSON.parseObject(JSON.toJSONString(reData.get("data")));
                            List<String> orgNames = (List<String>) data1.get("departmentNameList");
                            business.setOrgNames(orgNames);
                        }
                    }
                }
            }
            apiSendData.setData(businessList.get(0));
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        } else {
            apiSendData.setData("");
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData isExistBusinessName(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));

        Integer companyId = Integer.valueOf(data.get("companyId").toString());
        String businessName = (String) data.get("businessName");
        String businessId = (String) data.get("businessId");
        BusinessExample businessExample = new BusinessExample();
        businessExample.createCriteria()
                .andCompanyIdEqualTo(companyId)
                .andNameEqualTo(businessName)
                .andIsDelEqualTo(1);
        List<Business> businessList = businessMapper.selectByExample(businessExample);
        if (businessList.size() > 0) {
            if ("".equals(businessId)) {
                apiSendData.setCode(ApiReturnCode.INFO_IS_EXIST.getCode());
            } else {
                if (String.valueOf(businessList.get(0).getId()).equals(businessId)) {
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
