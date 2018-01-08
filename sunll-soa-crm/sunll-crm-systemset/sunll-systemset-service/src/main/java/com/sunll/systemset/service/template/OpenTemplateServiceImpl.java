package com.sunll.systemset.service.template;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.common.redis.JedisClient;
import com.sunll.common.util.JsonUtils;
import com.sunll.systemset.api.template.OpenTemplateService;
import com.sunll.systemset.dao.business.*;
import com.sunll.systemset.dao.template.BusinessBusinesstypeTemMapper;
import com.sunll.systemset.dao.template.BusinessTemMapper;
import com.sunll.systemset.dao.template.BusinessTypeFieldBlockTemMapper;
import com.sunll.systemset.dao.template.BusinessTypeFieldValueChooseTemMapper;
import com.sunll.systemset.entity.business.*;
import com.sunll.systemset.entity.template.*;
import org.apache.log4j.Logger;
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
public class OpenTemplateServiceImpl implements OpenTemplateService {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private BusinessTemMapper businessTemMapper;
    @Autowired
    private BusinessBusinesstypeTemMapper businessBusinesstypeTemMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessTypeFieldBlockTemMapper businessTypeFieldBlockTemMapper;
    @Autowired
    private CompanyOrganizationBusinessMapper companyOrganizationBusinessMapper;
    @Autowired
    private BusinessTypeMapper businessTypeMapper;
    @Autowired
    private BusinessTypeFieldMapper businessTypeFieldMapper;
    @Autowired
    private BusinessTypeFieldValueChooseTemMapper businessTypeFieldValueChooseTemMapper;
    @Autowired
    private BusinessTypeFieldValueChooseMapper businessTypeFieldValueChooseMapper;
    @Autowired
    private JedisClient jedisClient;

    /**
     * 根据模板开通相应的模板
     *
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ApiSendData openTemplate(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {

        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取用户id
        Integer userId = (Integer) data.get("userId");
        //获取企业id
        Integer companyId = (Integer) data.get("companyId");
        //获取所有组织id
        List<Integer> organizationList = (List<Integer>) data.get("organizationList");
        //获取行业类别
        Integer industryType = (Integer) data.get("industryType");
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
                Business business = new Business();
                business.setCompanyId(companyId);
                business.setCreateTime(new Date());
                business.setCreateUserId(userId);
                business.setName(businessTem.getName());
                business.setShowName(businessTem.getShowName());
                business.setIsDel(1);
                business.setIndustryType(industryType);
                business.setIsEnabled(businessTem.getIsEnabled());
                int num = businessMapper.insertSelective(business);
                int businessId = business.getId();
                //插入数据企业组织和业务关联表中
                for (Integer orgId : organizationList) {
                    CompanyOrganizationBusiness companyOrganizationBusiness = new CompanyOrganizationBusiness();
                    companyOrganizationBusiness.setBusinessId(businessId);
                    companyOrganizationBusiness.setCompanyId(companyId);
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
                            businessType.setCreateUserId(userId);
                            businessType.setIsDel(1);
                            businessType.setName(businessTypeTem.getName());
                            businessType.setShowName(businessTypeTem.getShowName());
                            businessType.setIsEnabled(businessTypeTem.getIsEnabled());
                            businessType.setTypeStatus(businessTypeTem.getTypeStatus());
                            businessTypeMapper.insertSelective(businessType);
                            int businessTypeId = businessType.getId();
                            //获取业务类型对应的区块以及字段信息

                            List<BusinessTypeFieldBlockTem> businessTypeFieldBlockTemList = new ArrayList<>();
                            if (jedisClient.get("BusinessTypeFieldBlockTem_" + businessBusinesstypeTem
                                    .getBusinessTypeTem().getId()) != null) {
                                businessTypeFieldBlockTemList = JsonUtils.jsonToList(jedisClient.
                                        get("BusinessTypeFieldBlockTem_" + businessBusinesstypeTem
                                                .getBusinessTypeTem().getId()), BusinessTypeFieldBlockTem.class);
                            } else {
                                businessTypeFieldBlockTemList =
                                        businessTypeFieldBlockTemMapper.selectBlockAndFieldByBusinessTypeId(
                                                businessBusinesstypeTem.getBusinessTypeTem().getId());
                                jedisClient.set("BusinessTypeFieldBlockTem_" + businessBusinesstypeTem
                                                .getBusinessTypeTem().getId(),
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
                                    businessTypeField.setCreateUserId(userId);
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
                                        businessTypeField1.setCreateUserId(userId);
                                        businessTypeField1.setEnterPrompt(businessTypeFieldTem.getEnterPrompt());
                                        businessTypeField1.setIsModify(businessTypeFieldTem.getIsModify());
                                        businessTypeField1.setIsShow(businessTypeFieldTem.getIsShow());
                                        businessTypeField1.setSort(businessTypeFieldTem.getSort());
                                        businessTypeFieldMapper.insertSelective(businessTypeField1);
                                        int businessTypeFieldId1 = businessTypeField1.getId();
                                        if (("select").equals(businessTypeFieldTem.getFieldType()) ||
                                                ("checkbox").equals(businessTypeFieldTem.getFieldType())) {

                                            List<BusinessTypeFieldValueChooseTem> businessTypeFieldValueChooseTems = new ArrayList<>();
                                            if (jedisClient.get("BusinessTypeFieldValueChooseTem_" + businessTypeFieldTem
                                                    .getId()) != null) {
                                                businessTypeFieldValueChooseTems = JsonUtils.jsonToList(jedisClient.
                                                        get("BusinessTypeFieldValueChooseTem_" + businessTypeFieldTem
                                                                .getId()), BusinessTypeFieldValueChooseTem.class);
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
                                                businessTypeFieldValueChoose.setCreateUserId(userId);
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
}
