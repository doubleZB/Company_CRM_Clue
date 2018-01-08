package com.sunll.clue.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunll.clue.api.ClueService;
import com.sunll.clue.dao.business.*;
import com.sunll.clue.entity.business.*;
import com.sunll.clue.service.smsSupply.InsertSmsZ;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiReturnCode;
import com.sunll.common.api.ApiSendData;
import com.sunll.common.redis.JedisClient;
import com.sunll.common.util.*;
import com.sunll.systemset.api.unitAccount.UnitAccountService;
import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by double on 2017/12/4.
 */
@Service
public class ClueServiceImpl implements ClueService {
    private String CLUE_FIELD = (String) PropertiesUtils.loadProperties("config.properties").get("clueFiled");

    private static final String UNITE_ACCOUNT_BASE_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("UNITE_ACCOUNT_BASE_URL");
    private static final String LIST_USER_BY_PERMISSIONS_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("LIST_USER_BY_PERMISSIONS_URL");
    private static final String SEARCH_CLUE_FIELD = (String) PropertiesUtils
            .loadProperties("config.properties").get("SearchClueFiled");

    private static final String SHOW_CALLER = (String) PropertiesUtils
            .loadProperties("config.properties").get("showcaller");
    private static final String CALL_URL = (String) PropertiesUtils
            .loadProperties("config.properties").get("callUrl");
    private static final String GET_USER_BY_USERID = (String) PropertiesUtils
            .loadProperties("config.properties").get("GET_USER_BY_USERID");
    @Autowired
    BusinessTypeMapper businessTypeMapper;
    @Autowired
    BusinessMapper businessMapper;
    @Autowired
    InsertSmsZ insertSmsZ;
    @Autowired
    BusinessTypeFieldMapper businessTypeFieldMapper;
    @Autowired
    BusinessTypeValueIdentifyMapper businessTypeValueIdentifyMapper;
    @Autowired
    BusinessTypeFieldValueTextMapper businessTypeFieldValueTextMapper;
    @Autowired
    BusinessTypeFieldValueDatetimeMapper businessTypeFieldValueDatetimeMapper;
    @Autowired
    BusinessTypeFieldValueVarcharMapper businessTypeFieldValueVarcharMapper;
    @Autowired
    BusinessTypeFieldValueIntMapper businessTypeFieldValueIntMapper;
    @Autowired
    BusinessTypeFiledUserMapper businessTypeFiledUserMapper;
    @Autowired
    BusinessTypeFieldValueDecimalMapper businessTypeFieldValueDecimalMapper;
    @Autowired
    BusinessTypeFieldValueChooseMapper businessTypeFieldValueChooseMapper;
    @Autowired
    SendMessageMapper sendMessageMapper;
    @Autowired
    SendReceiveMapper sendReceiveMapper;
    @Autowired
    MessageTemplateMapper messageTemplateMapper;
    @Autowired
    JedisClient jedisClient;
    @Autowired
    CompanyOrganizationBusinessMapper companyOrganizationBusinessMapper;

    @Reference(check = false, timeout = 100000)
    private UnitAccountService unitAccountService;


    @Override
    public ApiSendData listClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        //查询线索所有字段
        //需要：businessId,companyId，type_status，userId
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        Integer businessId = Integer.valueOf(jsonObject.get("businessId").toString());//类型id
        Integer companyId = Integer.valueOf(jsonObject.get("companyId").toString());//公司id
        Integer typeStatus = Integer.valueOf(jsonObject.get("typeStatus").toString()); //线索模块id
        Integer userId = Integer.valueOf(jsonObject.get("userId").toString());//当前登录用户 ,
        Integer pageNumber = Integer.valueOf(jsonObject.get("pageNumber").toString());//当前页数
        Integer pageSize = Integer.valueOf(jsonObject.get("pageSize").toString());//当前显示条数
        List<Map> mapList1 = (List<Map>) jsonObject.get("data");//当前显示条数
        String identifier = jsonObject.get("identifier").toString();//查询
        Map typeMap = new HashMap<>();
        typeMap.put("appId", "2");
        typeMap.put("userId", userId.toString());
        typeMap.put("identifier", identifier);
        typeMap.put("companyId", companyId.toString());
        String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + LIST_USER_BY_PERMISSIONS_URL, typeMap);
        List<Map> turnMapList = JsonUtils.jsonToList(s, Map.class);
        List<Integer> userIds = new ArrayList<>();

        List<BusinessTypeValueIdentify> businessTypeValueIdentifyLiat = new ArrayList<>();
        PageInfo<BusinessTypeValueIdentify> pageInfo = new PageInfo<>();
        //根据 type_status，businessId，查询查询business_typeId  查询business_type表
        BusinessTypeExample businessTypeExample = new BusinessTypeExample();
        businessTypeExample.createCriteria().andBusinessIdEqualTo(businessId).andTypeStatusEqualTo(typeStatus).andIsDelEqualTo(1).andIsEnabledEqualTo(1);
        List<BusinessType> businessTypeList = businessTypeMapper.selectByExample(businessTypeExample);
        BusinessType businessType = new BusinessType();
        if (businessTypeList.size() > 0) {
            businessType = businessTypeList.get(0);
        }
        //根据business_type_id查询所有线索字段 查询business_type_filed表
        //查询线索显示字段
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria().andBusinessTypeIdEqualTo(businessType.getId()).andPidNotEqualTo(0).andIsEnabledEqualTo(1);
        List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
        //根据userId，business_type_id,business_id,company_id
        //查询当前显示字段 查询business_type_value_identify表
        BusinessTypeFiledUserExample businessTypeFiledUserExample = new BusinessTypeFiledUserExample();
        businessTypeFiledUserExample.createCriteria().andBusinessIdEqualTo(businessId).andUserIdEqualTo(userId).andBusinessTypeIdEqualTo(businessType.getId()).andCompanyIdEqualTo(companyId);
        List<BusinessTypeFiledUser> businessTypeFiledUsers = businessTypeFiledUserMapper.selectByExample(businessTypeFiledUserExample);
        BusinessTypeField businessTypeField1 = new BusinessTypeField();
        businessTypeField1.setFieldName("创建时间");
        businessTypeField1.setFieldShowName("创建时间");
        businessTypeField1.setIsShow(1);
        businessTypeField1.setShowId("1");
        businessTypeField1.setFieldType("createAndUpdate");
        BusinessTypeField businessTypeField2 = new BusinessTypeField();
        businessTypeField2.setFieldName("更新时间");
        businessTypeField2.setFieldShowName("更新时间");
        businessTypeField2.setIsShow(1);
        businessTypeField2.setShowId("2");
        businessTypeField2.setFieldType("createAndUpdate");
        businessTypeFields.add(businessTypeField1);
        businessTypeFields.add(businessTypeField2);
        //判断是否为空，为空显示全部
        if (businessTypeFiledUsers.size() > 0) {
            for (BusinessTypeFiledUser businessTypeFiledUser : businessTypeFiledUsers) {
                for (BusinessTypeField businessTypeField : businessTypeFields) {
                    if (businessTypeFiledUser.getShowId() != null && businessTypeField.getShowId() != null) {
                        if (businessTypeFiledUser.getShowId().equals(businessTypeField.getShowId())) {
                            businessTypeField.setMust(1);
                        }
                    } else {
                        if (businessTypeField.getId() != null && businessTypeFiledUser.getBusinessTypeFieldId() != null) {
                            if (businessTypeFiledUser.getBusinessTypeFieldId().equals(businessTypeField.getId())) {
                                businessTypeField.setMust(1);
                            }
                        }
                    }
                }
            }
        } else {
            for (BusinessTypeField businessTypeField : businessTypeFields) {
                if (businessTypeField.getIsShow().equals(1)) {
                    businessTypeField.setMust(1);
                }
            }
        }
        if (turnMapList != null && turnMapList.size() > 0) {
            for (Map<String, Object> m : turnMapList) {
                userIds.add(Integer.valueOf((m.get("id").toString())));
            }
            //判断查询语句
            List<String> identifyIdList = new ArrayList<>();
            identifyIdList.add("flag");
            if (mapList1 != null && mapList1.size() > 0) {
                boolean flag = false;
                for (Map<String, Object> map : mapList1) {
                    if (identifyIdList.size() > 0) {
                        List<String> ids = new ArrayList<>();
                        BusinessTypeField businessTypeField = businessTypeFieldMapper.selectByPrimaryKey(Integer.valueOf(map.get("fieldId").toString()));
                        if ("input".equals(businessTypeField.getFieldType())) {
                            //查询线索字段类型为varchar的字段与字段所对应的值
                            BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                            businessTypeFieldValueVarcharExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField.getId()).andValueLike(map.get("value").toString());
                            List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                            if (businessTypeFieldValueVarchars.size() > 0) {
                                if (identifyIdList.size() > 0 && !"flag".equals(identifyIdList.get(0))) {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        for (String id : identifyIdList) {
                                            if (b.getIdentifyId().equals(id)) {
                                                ids.add(id);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        if (!flag) {
                                            ids.add(b.getIdentifyId());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if ("select".equals(businessTypeField.getFieldType())) {
                            //查询线索字段类型为varchar的字段与字段所对应的值
                            BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                            businessTypeFieldValueVarcharExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField.getId()).andValueEqualTo(map.get("value").toString());
                            List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                            if (businessTypeFieldValueVarchars.size() > 0) {
                                if (identifyIdList.size() > 0 && !"flag".equals(identifyIdList.get(0))) {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        for (String id : identifyIdList) {
                                            if (b.getIdentifyId().equals(id)) {
                                                ids.add(id);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        if (!flag) {
                                            ids.add(b.getIdentifyId());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        //查询线索字段类型为dataTime的字段与字段所对应的值
                        if ("date".equals(businessTypeField.getFieldType())) {
                            String time = map.get("value").toString();
                            String[] timeArr = time.split("-");
                            BusinessTypeFieldValueDatetimeExample businessTypeFieldValueDatetimeExample = new BusinessTypeFieldValueDatetimeExample();
                            businessTypeFieldValueDatetimeExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField.getId()).andValueBetween(DateUtil.string2Date(timeArr[0]), DateUtil.string2Date(timeArr[1]));
                            List<BusinessTypeFieldValueDatetime> businessTypeFieldValueDatetimes = businessTypeFieldValueDatetimeMapper.selectByExample(businessTypeFieldValueDatetimeExample);
                            if (businessTypeFieldValueDatetimes.size() > 0) {
                                if (identifyIdList.size() > 0 && !"flag".equals(identifyIdList.get(0))) {
                                    for (BusinessTypeFieldValueDatetime b : businessTypeFieldValueDatetimes) {
                                        for (String id : identifyIdList) {
                                            if (b.getIdentifyId().equals(id)) {
                                                ids.add(id);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    for (BusinessTypeFieldValueDatetime b : businessTypeFieldValueDatetimes) {
                                        if (!flag) {
                                            ids.add(b.getIdentifyId());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        identifyIdList.clear();
                        flag = true;
                        if (ids.size() > 0) {
                            identifyIdList.addAll(ids);
                        }
                    }
                }
                if (identifyIdList.size() > 0) {
                    BusinessTypeValueIdentifyExample businessTypeValueIdentifyExample = new BusinessTypeValueIdentifyExample();
                    businessTypeValueIdentifyExample.createCriteria().andIdIn(identifyIdList).andUserIdIn(userIds).andIsDelEqualTo(1);
                    PageHelper.startPage(pageNumber, pageSize, "id asc");
                    List<BusinessTypeValueIdentify> businessTypeValueIdentifyLiat1 = businessTypeValueIdentifyMapper.selectByExample(businessTypeValueIdentifyExample);
                    pageInfo = new PageInfo<>(businessTypeValueIdentifyLiat1);
                    businessTypeValueIdentifyLiat = pageInfo.getList();
                } else {
                    businessTypeValueIdentifyLiat = null;
                }
            } else {
                //分页查询线索
                //上面和获取了线索所有的字段businessTypeFields；
                BusinessTypeValueIdentifyExample businessTypeValueIdentifyExample = new BusinessTypeValueIdentifyExample();
                businessTypeValueIdentifyExample.createCriteria().andUserIdIn(userIds).andBusinessTypeIdEqualTo(businessType.getId()).andIsDelEqualTo(1);
                PageHelper.startPage(pageNumber, pageSize, "id asc");
                List<BusinessTypeValueIdentify> businessTypeValueIdentifyLiat1 = businessTypeValueIdentifyMapper.selectByExample(businessTypeValueIdentifyExample);
                pageInfo = new PageInfo<>(businessTypeValueIdentifyLiat1);
                businessTypeValueIdentifyLiat = pageInfo.getList();
            }
        }
        if (businessTypeValueIdentifyLiat != null && businessTypeValueIdentifyLiat.size() > 0) {
            //循环字段拿到字段所对应的值
            for (BusinessTypeValueIdentify businessTypeValueIdentify : businessTypeValueIdentifyLiat) {
                List<Map> mapList = new ArrayList<>();
                for (BusinessTypeField businessTypeField : businessTypeFields) {
                    if ("createAndUpdate".equals(businessTypeField.getFieldType())) {
                        if ("1".equals(businessTypeField.getShowId())) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("value", DateUtil.date2String(businessTypeValueIdentify.getCreateTime()));
                            map.put("typeId", businessTypeValueIdentify.getBusinessTypeId());
                            map.put("filedId", "a");
                            mapList.add(map);
                        } else if ("2".equals(businessTypeField.getShowId())) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("value", DateUtil.date2String(businessTypeValueIdentify.getCreateTime()));
                            map.put("typeId", businessTypeValueIdentify.getBusinessTypeId());
                            map.put("filedId", "b");
                            mapList.add(map);
                        }
                    }
                    if ("input".equals(businessTypeField.getFieldType())) {
                        //查询线索字段类型为varchar的字段与字段所对应的值
                        BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                        businessTypeFieldValueVarcharExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = businessTypeFieldValueVarcharMapper.selectOneByExample(businessTypeFieldValueVarcharExample);
                        if (businessTypeFieldValueVarchar != null) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("value", businessTypeFieldValueVarchar.getValue());
                            map.put("typeId", businessTypeFieldValueVarchar.getBusinessTypeId());
                            map.put("filedId", businessTypeFieldValueVarchar.getBusinessTypeFieldId());
                            mapList.add(map);
                        } else {
                            Map<String, Object> map = new HashMap<>();
                            map.put("value", null);
                            map.put("typeId", businessType.getId());
                            map.put("filedId", businessTypeField.getId());
                            mapList.add(map);
                        }
                    }
                    if ("select".equals(businessTypeField.getFieldType())) {
                        //查询线索字段类型为varchar的字段与字段所对应的值
                        BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                        businessTypeFieldValueVarcharExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = businessTypeFieldValueVarcharMapper.selectOneByExample(businessTypeFieldValueVarcharExample);
                        if (businessTypeFieldValueVarchar != null) {
                            BusinessTypeFieldValueChooseExample businessTypeFieldValueChooseExample = new BusinessTypeFieldValueChooseExample();
                            businessTypeFieldValueChooseExample.createCriteria().andValueEqualTo(businessTypeFieldValueVarchar.getValue()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                            List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses = businessTypeFieldValueChooseMapper.selectByExample(businessTypeFieldValueChooseExample);
                                if (!businessTypeFieldValueChooses.isEmpty()) {
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("typeId", businessTypeFieldValueVarchar.getBusinessTypeId());
                                    map.put("value", businessTypeFieldValueChooses.get(0).getName());
                                    map.put("filedId", businessTypeFieldValueVarchar.getBusinessTypeFieldId());
                                    mapList.add(map);
                                } else {
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("value", null);
                                    map.put("typeId", businessType.getId());
                                    map.put("filedId", businessTypeField.getId());
                                    mapList.add(map);
                                }
                        }
                    }
                    //查询线索字段类型为dataTime的字段与字段所对应的值
                    if ("date".equals(businessTypeField.getFieldType())) {
                        BusinessTypeFieldValueDatetimeExample businessTypeFieldValueDatetimeExample = new BusinessTypeFieldValueDatetimeExample();
                        businessTypeFieldValueDatetimeExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                        BusinessTypeFieldValueDatetime businessTypeFieldValueDatetime = businessTypeFieldValueDatetimeMapper.selectOneByExample(businessTypeFieldValueDatetimeExample);
                        if (businessTypeFieldValueDatetime != null) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("value", DateUtil.date2String(businessTypeFieldValueDatetime.getValue()));
                            map.put("typeId", businessTypeFieldValueDatetime.getBusinessTypeId());
                            map.put("filedId", businessTypeFieldValueDatetime.getBusinessTypeFieldId());
                            mapList.add(map);
                        } else {
                            Map<String, Object> map = new HashMap<>();
                            map.put("value", null);
                            map.put("typeId", businessType.getId());
                            map.put("filedId", businessTypeField.getId());
                            mapList.add(map);
                        }
                    }
                    if ("text".equals(businessTypeField.getFieldType())) {
                        //查询线索字段类型为Text的字段与字段所对应的值
                        BusinessTypeFieldValueTextExample businessTypeFieldValueTextExample = new BusinessTypeFieldValueTextExample();
                        businessTypeFieldValueTextExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                        BusinessTypeFieldValueText businessTypeFieldValueText = businessTypeFieldValueTextMapper.selectOneByExample(businessTypeFieldValueTextExample);
                        if (businessTypeFieldValueText != null) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("value", businessTypeFieldValueText.getValue());
                            map.put("typeId", businessTypeFieldValueText.getBusinessTypeId());
                            map.put("filedId", businessTypeFieldValueText.getBusinessTypeFieldId());
                            mapList.add(map);
                        } else {
                            Map<String, Object> map = new HashMap<>();
                            map.put("value", null);
                            map.put("typeId", businessType.getId());
                            map.put("filedId", businessTypeField.getId());
                            mapList.add(map);
                        }
                    }
                }
                businessTypeValueIdentify.setData(mapList);
            }
        }
        pageInfo.setList(businessTypeValueIdentifyLiat);
        JSONObject json = new JSONObject();
        json.put("allFiled", businessTypeFields);
        json.put("value", pageInfo);
        apiSendData.setData(json);
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        return apiSendData;
    }

    @Override
    public ApiSendData listClueApp(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        PageInfo<BusinessTypeValueIdentify> pageInfo = new PageInfo<>();
        List<BusinessTypeValueIdentify> businessTypeValueIdentifyList = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        Integer businessId = Integer.valueOf(jsonObject.get("businessId").toString());//类型id
        Integer companyId = Integer.valueOf(jsonObject.get("companyId").toString());//公司id
        Integer typeStatus = Integer.valueOf(jsonObject.get("typeStatus").toString()); //线索模块id
        Integer userId = Integer.valueOf(jsonObject.get("userId").toString());//当前登录用户 ,
        Integer pageNumber = Integer.valueOf(jsonObject.get("pageNumber").toString());//当前页数
        Integer pageSize = Integer.valueOf(jsonObject.get("pageSize").toString());//当前显示条数
        List<Map> searchMap = (List<Map>) jsonObject.get("data");//搜索
        String identifier = jsonObject.get("identifier").toString();//查询
        Map typeMap = new HashMap<>();
        typeMap.put("appId", "2");
        typeMap.put("userId", userId.toString());
        typeMap.put("identifier", identifier.toString());
        typeMap.put("companyId", companyId.toString());
        String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + LIST_USER_BY_PERMISSIONS_URL, typeMap);
        List<Map> mapList1 = JsonUtils.jsonToList(s, Map.class);
        List<Integer> userIds = new ArrayList<>();
        List<String> fieldList = new ArrayList<>();
        if (1 == typeStatus) {
            String[] fields = CLUE_FIELD.split(",");
            for (String field : fields) {
                fieldList.add(field);
            }
        }
        BusinessTypeExample businessTypeExample = new BusinessTypeExample();
        businessTypeExample.createCriteria().andBusinessIdEqualTo(businessId).andTypeStatusEqualTo(typeStatus).andIsDelEqualTo(1).andIsEnabledEqualTo(1);
        List<BusinessType> businessTypeList = businessTypeMapper.selectByExample(businessTypeExample);
        BusinessType businessType = new BusinessType();
        if (businessTypeList.size() > 0) {
            businessType = businessTypeList.get(0);
        }
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria().andBusinessTypeIdEqualTo(businessType.getId()).andPidNotEqualTo(0).andIsShowEqualTo(1).andIsEnabledEqualTo(1);
        List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
        if (mapList1 != null && mapList1.size() > 0) {
            for (Map<String, Object> m : mapList1) {
                userIds.add(Integer.valueOf((m.get("id").toString())));
            }
            //根据查询条件进行查询
            List<String> identifyIdList = new ArrayList<>();
            identifyIdList.add("flag");
            if (searchMap != null && searchMap.size() > 0) {
                boolean flag = false;
                for (Map<String, Object> map : searchMap) {
                    if (identifyIdList.size() > 0) {
                        List<String> ids = new ArrayList<>();
                        BusinessTypeField businessTypeField = businessTypeFieldMapper.selectByPrimaryKey(Integer.valueOf(map.get("fieldId").toString()));
                        if ("input".equals(businessTypeField.getFieldType())) {
                            //查询线索字段类型为varchar的字段与字段所对应的值
                            BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                            businessTypeFieldValueVarcharExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField.getId()).andValueLike(map.get("value").toString());
                            List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                            if (businessTypeFieldValueVarchars.size() > 0) {
                                if (identifyIdList.size() > 0 && !"flag".equals(identifyIdList.get(0))) {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        for (String id : identifyIdList) {
                                            if (b.getIdentifyId().equals(id)) {
                                                ids.add(id);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        if (!flag) {
                                            ids.add(b.getIdentifyId());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if ("select".equals(businessTypeField.getFieldType())) {
                            //查询线索字段类型为varchar的字段与字段所对应的值
                            BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                            businessTypeFieldValueVarcharExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField.getId()).andValueEqualTo(map.get("value").toString());
                            List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                            if (businessTypeFieldValueVarchars.size() > 0) {
                                if (identifyIdList.size() > 0 && !"flag".equals(identifyIdList.get(0))) {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        for (String id : identifyIdList) {
                                            if (b.getIdentifyId().equals(id)) {
                                                ids.add(id);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        if (!flag) {
                                            ids.add(b.getIdentifyId());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        //查询线索字段类型为dataTime的字段与字段所对应的值
                        if ("date".equals(businessTypeField.getFieldType())) {
                            String time = map.get("value").toString();
                            String[] timeArr = time.split("-");
                            BusinessTypeFieldValueDatetimeExample businessTypeFieldValueDatetimeExample = new BusinessTypeFieldValueDatetimeExample();
                            businessTypeFieldValueDatetimeExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField.getId()).andValueBetween(DateUtil.string2Date(timeArr[0]), DateUtil.string2Date(timeArr[1]));
                            List<BusinessTypeFieldValueDatetime> businessTypeFieldValueDatetimes = businessTypeFieldValueDatetimeMapper.selectByExample(businessTypeFieldValueDatetimeExample);
                            if (businessTypeFieldValueDatetimes.size() > 0) {
                                if (identifyIdList.size() > 0 && !"flag".equals(identifyIdList.get(0))) {
                                    for (BusinessTypeFieldValueDatetime b : businessTypeFieldValueDatetimes) {
                                        for (String id : identifyIdList) {
                                            if (b.getIdentifyId().equals(id)) {
                                                ids.add(id);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    for (BusinessTypeFieldValueDatetime b : businessTypeFieldValueDatetimes) {
                                        if (!flag) {
                                            ids.add(b.getIdentifyId());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        identifyIdList.clear();
                        flag = true;
                        if (ids.size() > 0) {
                            identifyIdList.addAll(ids);
                        }
                    }
                }
                if (identifyIdList.size() > 0) {
                    BusinessTypeValueIdentifyExample businessTypeValueIdentifyExample = new BusinessTypeValueIdentifyExample();
                    businessTypeValueIdentifyExample.createCriteria().andIdIn(identifyIdList).andUserIdIn(userIds).andBusinessTypeIdEqualTo(businessType.getId()).andIsDelEqualTo(1);
                    PageHelper.startPage(pageNumber, pageSize, "id asc");
                    List<BusinessTypeValueIdentify> businessTypeValueIdentifyLiat1 = businessTypeValueIdentifyMapper.selectByExample(businessTypeValueIdentifyExample);
                    pageInfo = new PageInfo<>(businessTypeValueIdentifyLiat1);
                    businessTypeValueIdentifyList = pageInfo.getList();
                } else {
                    businessTypeValueIdentifyList.clear();
                }
            } else {
                BusinessTypeValueIdentifyExample businessTypeValueIdentifyExample = new BusinessTypeValueIdentifyExample();
                businessTypeValueIdentifyExample.createCriteria().andUserIdIn(userIds).andBusinessTypeIdEqualTo(businessType.getId()).andIsDelEqualTo(1);
                PageHelper.startPage(pageNumber, pageSize, "id asc");
                List<BusinessTypeValueIdentify> businessTypeValueIdentifyList1 = businessTypeValueIdentifyMapper.selectByExample(businessTypeValueIdentifyExample);
                pageInfo = new PageInfo<>(businessTypeValueIdentifyList1);
                businessTypeValueIdentifyList = pageInfo.getList();
            }
            if (pageNumber > pageInfo.getPageNum()) {
                businessTypeValueIdentifyList.clear();
            }
        }
        if (businessTypeValueIdentifyList != null && businessTypeValueIdentifyList.size() > 0) {
            for (BusinessTypeValueIdentify businessTypeValueIdentify : businessTypeValueIdentifyList) {
                Map<String, Object> map = new HashMap<>();
                List<Map> mapList = new ArrayList<>();
                for (BusinessTypeField businessTypeField : businessTypeFields) {
                    for (String field : fieldList) {
                        if (field.equals(businessTypeField.getFieldAlias())) {
                            if ("input".equals(businessTypeField.getFieldType())) {
                                //查询线索字段类型为varchar的字段与字段所对应的值
                                BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                                businessTypeFieldValueVarcharExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                                BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = businessTypeFieldValueVarcharMapper.selectOneByExample(businessTypeFieldValueVarcharExample);
                                if (businessTypeFieldValueVarchar != null) {
                                    map.put(field, businessTypeFieldValueVarchar.getValue());
                                    break;
                                } else {
                                    map.put(field, null);
                                    break;
                                }
                            }
                            if ("select".equals(businessTypeField.getFieldType())) {
                                //查询线索字段类型为varchar的字段与字段所对应的值
                                BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                                businessTypeFieldValueVarcharExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                                BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = businessTypeFieldValueVarcharMapper.selectOneByExample(businessTypeFieldValueVarcharExample);
                                if (businessTypeFieldValueVarchar != null) {
                                    BusinessTypeFieldValueChooseExample businessTypeFieldValueChooseExample = new BusinessTypeFieldValueChooseExample();
                                    businessTypeFieldValueChooseExample.createCriteria().andValueEqualTo(businessTypeFieldValueVarchar.getValue()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                                    List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses = businessTypeFieldValueChooseMapper.selectByExample(businessTypeFieldValueChooseExample);
                                    if (!businessTypeFieldValueChooses.isEmpty()) {
                                        map.put(field, businessTypeFieldValueChooses.get(0).getName());
                                        break;
                                    } else {
                                        map.put(field, null);
                                        break;
                                    }
                                }
                            }
                            //查询线索字段类型为dataTime的字段与字段所对应的值
                            if ("date".equals(businessTypeField.getFieldType())) {
                                BusinessTypeFieldValueDatetimeExample businessTypeFieldValueDatetimeExample = new BusinessTypeFieldValueDatetimeExample();
                                businessTypeFieldValueDatetimeExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                                BusinessTypeFieldValueDatetime businessTypeFieldValueDatetime = businessTypeFieldValueDatetimeMapper.selectOneByExample(businessTypeFieldValueDatetimeExample);
                                if (businessTypeFieldValueDatetime != null) {
                                    map.put(field, businessTypeFieldValueDatetime.getValue());
                                    break;
                                } else {
                                    map.put(field, null);
                                    break;
                                }
                            }
                            if ("text".equals(businessTypeField.getFieldType())) {
                                //查询线索字段类型为Text的字段与字段所对应的值
                                BusinessTypeFieldValueTextExample businessTypeFieldValueTextExample = new BusinessTypeFieldValueTextExample();
                                businessTypeFieldValueTextExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                                BusinessTypeFieldValueText businessTypeFieldValueText = businessTypeFieldValueTextMapper.selectOneByExample(businessTypeFieldValueTextExample);
                                if (businessTypeFieldValueText != null) {
                                    map.put(field, businessTypeFieldValueText.getValue());
                                    break;
                                } else {
                                    map.put(field, null);
                                    break;
                                }
                            }
                        }
                    }
                }
                map.put("id", businessTypeValueIdentify.getId());
                map.put("createTime", businessTypeValueIdentify.getCreateTime());
                businessTypeValueIdentify.setData(map);
            }
        }
        pageInfo.setList(businessTypeValueIdentifyList);
        JSONObject json = new JSONObject();
        json.put("value", pageInfo);
        apiSendData.setData(json);
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        return apiSendData;
    }

    @Override
    public Map<String, Object> exportClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        List<BusinessTypeValueIdentify> businessTypeValueIdentifyList = new ArrayList<>();
        List<Map> resultMapList = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        Integer businessId = Integer.valueOf(jsonObject.get("businessId").toString());//类型id
        Integer companyId = Integer.valueOf(jsonObject.get("companyId").toString());//公司id
        Integer typeStatus = Integer.valueOf(jsonObject.get("typeStatus").toString()); //线索模块id
        Integer userId = Integer.valueOf(jsonObject.get("userId").toString());//当前登录用户 ,
        List<Map> searchMap = (List<Map>) jsonObject.get("data");//搜索
        String identifier = jsonObject.get("identifier").toString();//查询
        Map typeMap = new HashMap<>();
        typeMap.put("appId", "2");
        typeMap.put("userId", userId.toString());
        typeMap.put("identifier", identifier);
        typeMap.put("companyId", companyId.toString());
        String s = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + LIST_USER_BY_PERMISSIONS_URL, typeMap);
        List<Map> mapList1 = JsonUtils.jsonToList(s, Map.class);
        List<Integer> userIds = new ArrayList<>();
        BusinessTypeExample businessTypeExample = new BusinessTypeExample();
        businessTypeExample.createCriteria().andBusinessIdEqualTo(businessId).andTypeStatusEqualTo(typeStatus).andIsDelEqualTo(1).andIsEnabledEqualTo(1);
        List<BusinessType> businessTypeList = businessTypeMapper.selectByExample(businessTypeExample);
        BusinessType businessType = new BusinessType();
        if (businessTypeList.size() > 0) {
            businessType = businessTypeList.get(0);
        }
        //根据business_type_id查询所有线索字段 查询business_type_filed表
        //查询线索显示字段
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria().andBusinessTypeIdEqualTo(businessType.getId()).andPidNotEqualTo(0);
        List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
        //根据userId，business_type_id,business_id,company_id
        //查询当前显示字段 查询business_type_value_identify表
//        BusinessTypeFiledUserExample businessTypeFiledUserExample = new BusinessTypeFiledUserExample();
//        businessTypeFiledUserExample.createCriteria().andBusinessIdEqualTo(businessId).andUserIdEqualTo(userId).andBusinessTypeIdEqualTo(businessType.getId()).andCompanyIdEqualTo(companyId);
//        List<BusinessTypeFiledUser> businessTypeFiledUsers = businessTypeFiledUserMapper.selectByExample(businessTypeFiledUserExample);
        //判断是否为空，为空显示全部
        List<String> headList = new ArrayList<>();
        List<BusinessTypeField> businessTypeFieldList = new ArrayList<>();
//        if (businessTypeFiledUsers.size() > 0) {
//            for (BusinessTypeFiledUser businessTypeFiledUser : businessTypeFiledUsers) {
//                for (BusinessTypeField businessTypeField : businessTypeFields) {
//                    if (businessTypeFiledUser.getBusinessTypeFieldId().equals(businessTypeField.getId())) {
//                        String head = businessTypeField.getId() + ":" + businessTypeField.getFieldName();
//                        headList.add(head);
//                        businessTypeFieldList.add(businessTypeField);
//                    }
//                }
//            }
//        } else {
        for (BusinessTypeField businessTypeField : businessTypeFields) {
            if (businessTypeField.getIsEnabled().equals(1)) {
                String head = businessTypeField.getId() + ":" + businessTypeField.getFieldName();
                headList.add(head);
                businessTypeFieldList.add(businessTypeField);
            }
        }
//        }
        if (mapList1 != null && mapList1.size() > 0) {
            for (Map<String, Object> m : mapList1) {
                userIds.add(Integer.valueOf((m.get("id").toString())));
            }
            List<String> identifyIdList = new ArrayList<>();
            identifyIdList.add("flag");
            if (searchMap != null && searchMap.size() > 0) {
                boolean flag = false;
                for (Map<String, Object> map : searchMap) {
                    if (identifyIdList.size() > 0) {
                        List<String> ids = new ArrayList<>();
                        BusinessTypeField businessTypeField = businessTypeFieldMapper.selectByPrimaryKey(Integer.valueOf(map.get("fieldId").toString()));
                        if ("input".equals(businessTypeField.getFieldType())) {
                            //查询线索字段类型为varchar的字段与字段所对应的值
                            BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                            businessTypeFieldValueVarcharExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField.getId()).andValueLike(map.get("value").toString());
                            List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                            if (businessTypeFieldValueVarchars.size() > 0) {
                                if (identifyIdList.size() > 0 && !"flag".equals(identifyIdList.get(0))) {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        for (String id : identifyIdList) {
                                            if (b.getIdentifyId().equals(id)) {
                                                ids.add(id);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        if (!flag) {
                                            ids.add(b.getIdentifyId());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if ("select".equals(businessTypeField.getFieldType())) {
                            //查询线索字段类型为varchar的字段与字段所对应的值
                            BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                            businessTypeFieldValueVarcharExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField.getId()).andValueEqualTo(map.get("value").toString());
                            List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                            if (businessTypeFieldValueVarchars.size() > 0) {
                                if (identifyIdList.size() > 0 && !"flag".equals(identifyIdList.get(0))) {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        for (String id : identifyIdList) {
                                            if (b.getIdentifyId().equals(id)) {
                                                ids.add(id);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    for (BusinessTypeFieldValueVarchar b : businessTypeFieldValueVarchars) {
                                        if (!flag) {
                                            ids.add(b.getIdentifyId());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        //查询线索字段类型为dataTime的字段与字段所对应的值
                        if ("date".equals(businessTypeField.getFieldType())) {
                            String time = map.get("value").toString();
                            String[] timeArr = time.split("-");
                            BusinessTypeFieldValueDatetimeExample businessTypeFieldValueDatetimeExample = new BusinessTypeFieldValueDatetimeExample();
                            businessTypeFieldValueDatetimeExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTypeField.getId()).andValueBetween(DateUtil.string2Date(timeArr[0]), DateUtil.string2Date(timeArr[1]));
                            List<BusinessTypeFieldValueDatetime> businessTypeFieldValueDatetimes = businessTypeFieldValueDatetimeMapper.selectByExample(businessTypeFieldValueDatetimeExample);
                            if (businessTypeFieldValueDatetimes.size() > 0) {
                                if (identifyIdList.size() > 0 && !"flag".equals(identifyIdList.get(0))) {
                                    for (BusinessTypeFieldValueDatetime b : businessTypeFieldValueDatetimes) {
                                        for (String id : identifyIdList) {
                                            if (b.getIdentifyId().equals(id)) {
                                                ids.add(id);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    for (BusinessTypeFieldValueDatetime b : businessTypeFieldValueDatetimes) {
                                        if (!flag) {
                                            ids.add(b.getIdentifyId());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        identifyIdList.clear();
                        flag = true;
                        if (ids.size() > 0) {
                            identifyIdList.addAll(ids);
                        }
                    }
                }
                if (identifyIdList.size() > 0) {
                    BusinessTypeValueIdentifyExample businessTypeValueIdentifyExample = new BusinessTypeValueIdentifyExample();
                    businessTypeValueIdentifyExample.createCriteria().andIdIn(identifyIdList).andUserIdIn(userIds).andBusinessTypeIdEqualTo(businessType.getId()).andIsDelEqualTo(1);
                    businessTypeValueIdentifyList = businessTypeValueIdentifyMapper.selectByExample(businessTypeValueIdentifyExample);
                } else {
                    businessTypeValueIdentifyList = null;
                }
            } else {
                BusinessTypeValueIdentifyExample businessTypeValueIdentifyExample = new BusinessTypeValueIdentifyExample();
                businessTypeValueIdentifyExample.createCriteria().andUserIdIn(userIds).andBusinessTypeIdEqualTo(businessType.getId()).andIsDelEqualTo(1);
                businessTypeValueIdentifyList = businessTypeValueIdentifyMapper.selectByExample(businessTypeValueIdentifyExample);
            }
        }
        if (businessTypeValueIdentifyList.size() > 0) {
            for (BusinessTypeValueIdentify businessTypeValueIdentify : businessTypeValueIdentifyList) {
                Map<String, String> map = new HashMap<>();
                for (BusinessTypeField businessTypeField : businessTypeFieldList) {
                    if ("input".equals(businessTypeField.getFieldType())) {
                        //查询线索字段类型为varchar的字段与字段所对应的值
                        BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                        businessTypeFieldValueVarcharExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = businessTypeFieldValueVarcharMapper.selectOneByExample(businessTypeFieldValueVarcharExample);
                        if (businessTypeFieldValueVarchar != null) {
                            map.put(businessTypeField.getId().toString(), businessTypeFieldValueVarchar.getValue());
                            continue;
                        } else {
                            map.put(businessTypeField.getId().toString(), "");
                            continue;
                        }
                    }
                    if ("select".equals(businessTypeField.getFieldType())) {
                        //查询线索字段类型为varchar的字段与字段所对应的值
                        BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                        businessTypeFieldValueVarcharExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = businessTypeFieldValueVarcharMapper.selectOneByExample(businessTypeFieldValueVarcharExample);
                        if (businessTypeFieldValueVarchar != null) {
                            BusinessTypeFieldValueChooseExample businessTypeFieldValueChooseExample = new BusinessTypeFieldValueChooseExample();
                            businessTypeFieldValueChooseExample.createCriteria().andValueEqualTo(businessTypeFieldValueVarchar.getValue()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                            List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses = businessTypeFieldValueChooseMapper.selectByExample(businessTypeFieldValueChooseExample);
                            if (!businessTypeFieldValueChooses.isEmpty()) {
                                map.put(businessTypeField.getId().toString(), businessTypeFieldValueChooses.get(0).getName());
                                continue;
                            } else {
                                map.put(businessTypeField.getId().toString(), "");
                                continue;
                            }
                        }
                    }
                    //查询线索字段类型为dataTime的字段与字段所对应的值
                    if ("date".equals(businessTypeField.getFieldType())) {
                        BusinessTypeFieldValueDatetimeExample businessTypeFieldValueDatetimeExample = new BusinessTypeFieldValueDatetimeExample();
                        businessTypeFieldValueDatetimeExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                        BusinessTypeFieldValueDatetime businessTypeFieldValueDatetime = businessTypeFieldValueDatetimeMapper.selectOneByExample(businessTypeFieldValueDatetimeExample);
                        if (businessTypeFieldValueDatetime != null) {
                            map.put(businessTypeField.getId().toString(), String.valueOf(businessTypeFieldValueDatetime.getValue()));
                            continue;
                        } else {
                            map.put(businessTypeField.getId().toString(), "");
                            continue;
                        }
                    }
                    if ("text".equals(businessTypeField.getFieldType())) {
                        //查询线索字段类型为Text的字段与字段所对应的值
                        BusinessTypeFieldValueTextExample businessTypeFieldValueTextExample = new BusinessTypeFieldValueTextExample();
                        businessTypeFieldValueTextExample.createCriteria().andIdentifyIdEqualTo(businessTypeValueIdentify.getId()).andBusinessTypeFieldIdEqualTo(businessTypeField.getId());
                        BusinessTypeFieldValueText businessTypeFieldValueText = businessTypeFieldValueTextMapper.selectOneByExample(businessTypeFieldValueTextExample);
                        if (businessTypeFieldValueText != null) {
                            map.put(businessTypeField.getId().toString(), businessTypeFieldValueText.getValue());
                            continue;
                        } else {
                            map.put(businessTypeField.getId().toString(), "");
                            continue;
                        }
                    }
                }
                resultMapList.add(map);
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("resultMapList", resultMapList);
        result.put("headList", headList);
        return result;
    }

    @Override
    public Map<String, Object> exportClueExcel(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        Integer businessId = Integer.valueOf(jsonObject.get("businessId").toString());//类型id
        Integer companyId = Integer.valueOf(jsonObject.get("companyId").toString());//公司id
        Integer typeStatus = Integer.valueOf(jsonObject.get("typeStatus").toString()); //线索模块id
        Integer userId = Integer.valueOf(jsonObject.get("userId").toString());//当前登录用户 ,
        BusinessTypeExample businessTypeExample = new BusinessTypeExample();
        businessTypeExample.createCriteria().andBusinessIdEqualTo(businessId).andTypeStatusEqualTo(typeStatus).andIsDelEqualTo(1).andIsEnabledEqualTo(1);
        List<BusinessType> businessTypeList = businessTypeMapper.selectByExample(businessTypeExample);
        BusinessType businessType = new BusinessType();
        if (businessTypeList.size() > 0) {
            businessType = businessTypeList.get(0);
        }
        //根据business_type_id查询所有线索字段 查询business_type_filed表
        //查询线索显示字段
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria().andBusinessTypeIdEqualTo(businessType.getId()).andPidNotEqualTo(0);
        List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
        //根据userId，business_type_id,business_id,company_id
        //查询当前显示字段 查询business_type_value_identify表
//        BusinessTypeFiledUserExample businessTypeFiledUserExample = new BusinessTypeFiledUserExample();
//        businessTypeFiledUserExample.createCriteria().andBusinessIdEqualTo(businessId).andUserIdEqualTo(userId).andBusinessTypeIdEqualTo(businessType.getId()).andCompanyIdEqualTo(companyId);
//        List<BusinessTypeFiledUser> businessTypeFiledUsers = businessTypeFiledUserMapper.selectByExample(businessTypeFiledUserExample);
        //判断是否为空，为空显示全部
        List<String> headList = new ArrayList<>();
        List<BusinessTypeField> businessTypeFieldList = new ArrayList<>();
//        if (businessTypeFiledUsers.size() > 0) {
//            for (BusinessTypeFiledUser businessTypeFiledUser : businessTypeFiledUsers) {
//                for (BusinessTypeField businessTypeField : businessTypeFields) {
//                    if (businessTypeFiledUser.getBusinessTypeFieldId().equals(businessTypeField.getId())) {
//                        String head = businessTypeField.getId() + ":" + businessTypeField.getFieldName();
//                        headList.add(head);
//                    }
//                }
//            }
//        } else {
        for (BusinessTypeField businessTypeField : businessTypeFields) {
            if (businessTypeField.getIsEnabled().equals(1)) {
                String head;
                if (businessTypeField.getIsRequired().equals(1)) {
                    head = businessTypeField.getId() + ":" +businessTypeField.getFieldName()+"（必填）";
                } else {
                    head = businessTypeField.getId() + ":" + businessTypeField.getFieldName();
                }
                headList.add(head);
            }
        }
//        }
        Map<String, Object> result = new HashMap<>();
        result.put("headList", headList);
        return result;
    }

    @Override
    public ApiSendData insertClueUserShowField(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        List<Integer> showFieldList = (List<Integer>) jsonObject.get("showField");//搜索
        Integer businessId = Integer.valueOf(jsonObject.get("businessId").toString());//类型id
        Integer businessTypeId = Integer.valueOf(jsonObject.get("businessTypeId").toString());//类型id
        Integer companyId = Integer.valueOf(jsonObject.get("companyId").toString());//公司id
//        Integer typeStatus = Integer.valueOf(jsonObject.get("typeStatus").toString()); //线索模块id
        Integer userId = Integer.valueOf(jsonObject.get("userId").toString());//当前登录用户 ,
        List<String> showIds = (List<String>) jsonObject.get("showId");
        boolean flag = false;
        BusinessTypeFiledUserExample businessTypeFiledUserExample = new BusinessTypeFiledUserExample();
        businessTypeFiledUserExample.createCriteria().andUserIdEqualTo(userId).andBusinessIdEqualTo(businessId).andBusinessTypeIdEqualTo(businessTypeId).andCompanyIdEqualTo(companyId);
        businessTypeFiledUserMapper.deleteByExample(businessTypeFiledUserExample);
        if (showFieldList != null && showFieldList.size() > 0) {
            for (Integer i : showFieldList) {
                BusinessTypeFiledUser businessTypeFiledUser = new BusinessTypeFiledUser();
                businessTypeFiledUser.setBusinessId(businessId);
                businessTypeFiledUser.setCompanyId(companyId);
                businessTypeFiledUser.setUserId(userId);
                businessTypeFiledUser.setBusinessTypeFieldId(i);
                businessTypeFiledUser.setBusinessTypeId(businessTypeId);
                flag = businessTypeFiledUserMapper.insertSelective(businessTypeFiledUser) > 0;
            }
        }
        if (showIds != null && showIds.size() > 0) {
            for (String i : showIds) {
                BusinessTypeFiledUser businessTypeFiledUser = new BusinessTypeFiledUser();
                businessTypeFiledUser.setBusinessId(businessId);
                businessTypeFiledUser.setCompanyId(companyId);
                businessTypeFiledUser.setUserId(userId);
                businessTypeFiledUser.setShowId(i);
                businessTypeFiledUser.setBusinessTypeId(businessTypeId);
                flag = businessTypeFiledUserMapper.insertSelective(businessTypeFiledUser) > 0;
            }
        }
        JSONObject json = new JSONObject();
        json.put("flag", flag);
        apiSendData.setData(json);
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        return apiSendData;
    }

    /**
     * 线索详情：
     * 通过pid,businessTypeId来加载线索字段
     * 查询指定clueId的线索值
     *
     * @return
     */
    @Override
    public ApiSendData getClueDetail(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        Integer businessTypeId = Integer.parseInt(jsonObject.get("businessTypeId").toString());
        Integer pid = Integer.parseInt(jsonObject.get("pid").toString());
        String clueId = (String) jsonObject.get("clueId");
        Map<String, Object> map = new HashMap<>();
        map.put("businessTypeId", businessTypeId);
        map.put("pid", pid);
        List<BusinessTypeField> list = businessTypeFieldMapper.getDetailClueByBusinessTypeIdAndPid(map);
        if (list != null) {
            for (BusinessTypeField btf : list) {
                if ("div".equals(btf.getFieldType())) {
                    List<BusinessTypeField> businessTypeFieldList = btf.getBusinessTypeFieldList();
                    if (businessTypeFieldList != null) {
                        for (BusinessTypeField businessTF : businessTypeFieldList) {
                            if ("input".equals(businessTF.getFieldType())) {
                                BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                                businessTypeFieldValueVarcharExample.createCriteria().andIdentifyIdEqualTo(clueId).
                                        andBusinessTypeFieldIdEqualTo(businessTF.getId()).andBusinessTypeIdEqualTo(businessTypeId);
                                List<BusinessTypeFieldValueVarchar> btfvv = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                                if (btfvv != null && btfvv.size() > 0) {
                                    String value = btfvv.get(0).getValue();
                                    businessTF.setValue((value == null) ? "" : value);
                                }
                            }
                            if ("date".equals(businessTF.getFieldType())) {
                                BusinessTypeFieldValueDatetimeExample businessTypeFieldValueDatetimeExample = new BusinessTypeFieldValueDatetimeExample();
                                businessTypeFieldValueDatetimeExample.createCriteria().andIdentifyIdEqualTo(clueId).
                                        andBusinessTypeFieldIdEqualTo(businessTF.getId()).andBusinessTypeIdEqualTo(businessTypeId);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                List<BusinessTypeFieldValueDatetime> btfvd = businessTypeFieldValueDatetimeMapper.selectByExample(businessTypeFieldValueDatetimeExample);
                                if (btfvd != null && btfvd.size() > 0) {
                                    String dt = format.format(btfvd.get(0).getValue());
                                    businessTF.setValue(dt == null ? "" : dt);
                                }
                            }
                            if ("text".equals(businessTF.getFieldType())) {
                                BusinessTypeFieldValueTextExample businessTypeFieldValueTextExample = new BusinessTypeFieldValueTextExample();
                                businessTypeFieldValueTextExample.createCriteria().andIdentifyIdEqualTo(clueId).
                                        andBusinessTypeFieldIdEqualTo(businessTF.getId()).andBusinessTypeIdEqualTo(businessTypeId);
                                List<BusinessTypeFieldValueText> btfvt = businessTypeFieldValueTextMapper.selectByExample(businessTypeFieldValueTextExample);
                                if (btfvt != null && btfvt.size() > 0) {
                                    if (btfvt.get(0) != null) {
                                        BusinessTypeFieldValueText text = businessTypeFieldValueTextMapper.selectOneByExample(businessTypeFieldValueTextExample);
                                        businessTF.setValue(text.getValue());
                                    }
                                }
                            }
                            if ("select".equals(businessTF.getFieldType())) {
                                BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                                BusinessTypeFieldValueChooseExample businessTypeFieldValueChooseExample = new BusinessTypeFieldValueChooseExample();
                                businessTypeFieldValueVarcharExample.createCriteria().andIdentifyIdEqualTo(clueId).
                                        andBusinessTypeFieldIdEqualTo(businessTF.getId()).andBusinessTypeIdEqualTo(businessTypeId);
                                List<BusinessTypeFieldValueVarchar> btfvv = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                                if (btfvv != null && btfvv.size() > 0) {
                                    if (btfvv.get(0).getValue() != null) {
                                        businessTypeFieldValueChooseExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTF.getId())
                                                .andValueEqualTo(btfvv.get(0).getValue());
                                        if(!(businessTypeFieldValueChooseMapper.selectByExample(businessTypeFieldValueChooseExample).isEmpty())){
                                            businessTF.setValue(businessTypeFieldValueChooseMapper.selectByExample(businessTypeFieldValueChooseExample).get(0).getName());
                                        }else {
                                            businessTF.setValue("");
                                        }

                                    }
                                }
                            }
                            if ("checkbox".equals(businessTF.getFieldType())) {
                                BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                                BusinessTypeFieldValueChooseExample businessTypeFieldValueChooseExample = new BusinessTypeFieldValueChooseExample();
                                businessTypeFieldValueVarcharExample.createCriteria().andIdentifyIdEqualTo(clueId).
                                        andBusinessTypeFieldIdEqualTo(businessTF.getId()).andBusinessTypeIdEqualTo(businessTypeId);
                                List<BusinessTypeFieldValueVarchar> btfvv = businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                                if (btfvv != null && btfvv.size() > 0) {
                                    String[] split = btfvv.get(0).toString().split(",");
                                    List<String> l = new ArrayList<>();
                                    for (String st : split) {
                                        l.add(st);
                                    }
                                    businessTypeFieldValueChooseExample.createCriteria().andBusinessTypeFieldIdEqualTo(businessTF.getId())
                                            .andValueIn(l);
                                    List<BusinessTypeFieldValueChoose> bbb = businessTypeFieldValueChooseMapper.selectByExample(businessTypeFieldValueChooseExample);
                                    if(bbb.isEmpty()){
                                        StringBuffer sb = new StringBuffer();
                                        for (BusinessTypeFieldValueChoose b : bbb) {
                                            sb.append("," + b.getName());
                                        }
                                        businessTF.setValue(sb);
                                    }else {
                                        businessTF.setValue("");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        apiSendData.setData(list);
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        return apiSendData;
    }

    /**
     * 新建线索:显示线索字段
     * 获取线索下面的所有字段
     *
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    @Override
    public ApiSendData createClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        Integer businessId = Integer.parseInt(jsonObject.get("businessId").toString());
        BusinessTypeExample example = new BusinessTypeExample();
        example.createCriteria().andBusinessIdEqualTo(businessId).andNameEqualTo("线索");
        List<BusinessType> businessTypes = businessTypeMapper.selectByExample(example);
        Integer businessTypeId = businessTypes.get(0).getId();
        Integer pid = 0;
        //获取线索下的字段
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("businessTypeId", businessTypeId);
        fieldMap.put("pid", pid);
        //获取线索字段
        List<BusinessTypeField> list = businessTypeFieldMapper.getDetailClueByBusinessTypeIdAndPid(fieldMap);
        if (list != null && list.size() > 0) {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
            apiSendData.setData(list);
        } else {
            apiSendData.setCode(ApiReturnCode.NO_DATA.getCode());
            apiSendData.setMessage(ApiReturnCode.NO_DATA.getMessage());
        }
        return apiSendData;
    }

    /**
     * 保存线索:
     *
     * @param
     * @param apiAcceptData
     * @return
     */
    @Override
    public ApiSendData saveClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        Integer businessId = Integer.parseInt(jsonObject.get("businessId").toString());
        BusinessTypeExample ex = new BusinessTypeExample();
        ex.createCriteria().andBusinessIdEqualTo(businessId).andNameEqualTo("线索");
        List<BusinessType> businessTypes = businessTypeMapper.selectByExample(ex);
        Integer businessTypeId = businessTypes.get(0).getId();
        Integer userId = Integer.parseInt(jsonObject.get("userId").toString());
        String clueId = String.valueOf(UUID.randomUUID());
        //插入线索唯一关联表，后续查询时用
        BusinessTypeValueIdentify identify = new BusinessTypeValueIdentify();
        Date dt = new Date();
        identify.setIsDel(1);
        identify.setCreateTime(dt);
        identify.setCreateUserId(userId);
        identify.setId(clueId);
        identify.setUserId(userId);
        identify.setBusinessTypeId(businessTypeId);
        businessTypeValueIdentifyMapper.insert(identify);
        //接受封装页的面参数:List<Map>
        List<Map> listMap = (List<Map>) jsonObject.get("listMap");
        try {
            Integer fieldId = 0;
            String value = null;
            String name = null;
            Date followNextTime = new Date();
            for (Map lt : listMap) {
                fieldId = Integer.parseInt((lt.get("fieldId")).toString());
                BusinessTypeField businessTypeField = businessTypeFieldMapper.selectByPrimaryKey(fieldId);
                String fieldType = businessTypeField.getFieldType();
                String fieldAlias = businessTypeField.getFieldAlias();
                value = (String) lt.get("value");
                if (value != null && value.toString().length() > 0) {
                    //根据字段类型进行制定字段表进行值的插入
                    if ("input".equals(fieldType)) {
                        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = new BusinessTypeFieldValueVarchar();
                        businessTypeFieldValueVarchar.setBusinessTypeFieldId(fieldId);
                        businessTypeFieldValueVarchar.setBusinessTypeId(businessTypeId);
                        businessTypeFieldValueVarchar.setIdentifyId(clueId);
                        businessTypeFieldValueVarchar.setValue((String) value);
                        businessTypeFieldValueVarcharMapper.insert(businessTypeFieldValueVarchar);
                        if ("contactName".equals(fieldAlias)) {
                            name = value;
                        }
                    }
                    if ("select".equals(fieldType)) {
                        BusinessTypeFieldValueChooseExample example = new BusinessTypeFieldValueChooseExample();
                        example.createCriteria().andNameEqualTo((String) value);
                        List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses = businessTypeFieldValueChooseMapper.selectByExample(example);
                        String val = businessTypeFieldValueChooses.get(0).getValue();
                        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = new BusinessTypeFieldValueVarchar();
                        businessTypeFieldValueVarchar.setBusinessTypeFieldId(fieldId);
                        businessTypeFieldValueVarchar.setBusinessTypeId(businessTypeId);
                        businessTypeFieldValueVarchar.setIdentifyId(clueId);
                        businessTypeFieldValueVarchar.setValue((String) val);
                        businessTypeFieldValueVarcharMapper.insert(businessTypeFieldValueVarchar);
                    }
                    if ("checkbox".equals(fieldType)) {
                        String[] split = value.split(",");
                        List<String> list = new ArrayList<>();
                        for (String st : split) list.add(st);
                        BusinessTypeFieldValueChooseExample example = new BusinessTypeFieldValueChooseExample();
                        example.createCriteria().andNameIn(list);
                        List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses = businessTypeFieldValueChooseMapper.selectByExample(example);
                        //String val = businessTypeFieldValueChooses.get(0).getValue();
                        StringBuffer sb = new StringBuffer();
                        for (BusinessTypeFieldValueChoose btfc : businessTypeFieldValueChooses) {
                            String val = btfc.getValue();
                            sb.append("," + val);
                        }
                        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = new BusinessTypeFieldValueVarchar();
                        businessTypeFieldValueVarchar.setBusinessTypeFieldId(fieldId);
                        businessTypeFieldValueVarchar.setBusinessTypeId(businessTypeId);
                        businessTypeFieldValueVarchar.setIdentifyId(clueId);
                        businessTypeFieldValueVarchar.setValue((sb.toString()));
                        businessTypeFieldValueVarcharMapper.insert(businessTypeFieldValueVarchar);
                    }
                    if ("date".equals(fieldType)) {
                        BusinessTypeFieldValueDatetime businessTypeFieldValueDatetime = new BusinessTypeFieldValueDatetime();
                        businessTypeFieldValueDatetime.setBusinessTypeFieldId(fieldId);
                        businessTypeFieldValueDatetime.setBusinessTypeId(businessTypeId);
                        businessTypeFieldValueDatetime.setIdentifyId(clueId);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = sdf.parse(value);
                        followNextTime = date;
                        businessTypeFieldValueDatetime.setValue(date);
                        businessTypeFieldValueDatetimeMapper.insert(businessTypeFieldValueDatetime);
                    }
                    if ("text".equals(fieldType)) {
                        BusinessTypeFieldValueText businessTypeFieldValueText = new BusinessTypeFieldValueText();
                        businessTypeFieldValueText.setBusinessTypeFieldId(fieldId);
                        businessTypeFieldValueText.setBusinessTypeId(businessTypeId);
                        businessTypeFieldValueText.setIdentifyId(clueId);
                        businessTypeFieldValueText.setValue((String) value);
                        businessTypeFieldValueTextMapper.insert(businessTypeFieldValueText);
                    }
                }
            }
            if (followNextTime != null) {
                insertSmsZ.insertSms1(name, followNextTime, businessId, clueId, null, businessTypeId);
            }
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage("保存成功");
            apiSendData.setData(new JSONObject());
        } catch (Exception e) {
            apiSendData.setMessage("保存失败");
            apiSendData.setData(new JSONObject());
            e.printStackTrace();
        }
        return apiSendData;
    }

    @Override
    public ApiSendData updateEditClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        Integer userId = (Integer) jsonObject.get("userId");
        String clueId = (String) jsonObject.get("clueId");
        Integer businessTypeId = (Integer) jsonObject.get("businessTypeId");
        //更新唯一标识表
        BusinessTypeValueIdentify identify = new BusinessTypeValueIdentify();

        identify.setUpdateTime(new Date());
        identify.setId(clueId);
        identify.setUpdateUserId(userId);
        businessTypeValueIdentifyMapper.updateByPrimaryKeySelective(identify);
        //接受封装页的面参数:List<Map>
        List<Map> listMap = (List<Map>) jsonObject.get("listMap");
        try {
            Integer fieldId = 0;
            String value = null;
            for (Map lt : listMap) {
                if (lt.get("fieldId") instanceof Integer) {
                    fieldId = (Integer) lt.get("fieldId");
                } else {
                    fieldId = Integer.parseInt((String) lt.get("fieldId"));
                }
                BusinessTypeField businessTypeField = businessTypeFieldMapper.selectByPrimaryKey(fieldId);
                String fieldType = businessTypeField.getFieldType();

                value = (String) lt.get("value");
                //根据字段类型进行制定字段表进行值的插入
                if (value != null && value.toString().length() > 0) {
                    if ("input".equals(fieldType)) {
                        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = new BusinessTypeFieldValueVarchar();
                        businessTypeFieldValueVarchar.setValue(value);
                        BusinessTypeFieldValueVarcharExample example = new BusinessTypeFieldValueVarcharExample();
                        BusinessTypeFieldValueVarcharExample.Criteria criteria = example.createCriteria();
                        criteria.andIdentifyIdEqualTo(clueId);
                        criteria.andBusinessTypeFieldIdEqualTo(fieldId);
                        int i = businessTypeFieldValueVarcharMapper.updateByExampleSelective(businessTypeFieldValueVarchar, example);
                        if (i == 0) {
                            BusinessTypeFieldValueVarchar qqq = new BusinessTypeFieldValueVarchar();
                            qqq.setBusinessTypeFieldId(fieldId);
                            qqq.setBusinessTypeId(businessTypeId);
                            qqq.setIdentifyId(clueId);
                            qqq.setValue(value);
                            businessTypeFieldValueVarcharMapper.insertSelective(qqq);
                        }
                    }
                    if ("select".equals(fieldType)) {
                        BusinessTypeFieldValueChooseExample example = new BusinessTypeFieldValueChooseExample();
                        example.createCriteria().andNameEqualTo(value);
                        List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses = businessTypeFieldValueChooseMapper.selectByExample(example);
                        String val = businessTypeFieldValueChooses.get(0).getValue();
                        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = new BusinessTypeFieldValueVarchar();
                        businessTypeFieldValueVarchar.setValue(val);
                        BusinessTypeFieldValueVarcharExample example1 = new BusinessTypeFieldValueVarcharExample();
                        BusinessTypeFieldValueVarcharExample.Criteria criteria = example1.createCriteria();
                        criteria.andIdentifyIdEqualTo(clueId);
                        criteria.andBusinessTypeFieldIdEqualTo(fieldId);
                        int i = businessTypeFieldValueVarcharMapper.updateByExampleSelective(businessTypeFieldValueVarchar, example1);
                        if (i == 0) {
                            BusinessTypeFieldValueChooseExample examplez = new BusinessTypeFieldValueChooseExample();
                            examplez.createCriteria().andNameEqualTo(value);
                            List<BusinessTypeFieldValueChoose> qqq = businessTypeFieldValueChooseMapper.selectByExample(example);
                            String valq = qqq.get(0).getValue();
                            BusinessTypeFieldValueVarchar qqqq = new BusinessTypeFieldValueVarchar();
                            qqqq.setBusinessTypeFieldId(fieldId);
                            qqqq.setBusinessTypeId(businessTypeId);
                            qqqq.setIdentifyId(clueId);
                            qqqq.setValue(valq);
                            businessTypeFieldValueVarcharMapper.insertSelective(qqqq);
                        }
                    }
                    if ("checkbox".equals(fieldType)) {
                        String[] split = value.split(",");
                        List<String> list = new ArrayList<>();
                        for (String st : split) list.add(st);
                        BusinessTypeFieldValueChooseExample example = new BusinessTypeFieldValueChooseExample();
                        example.createCriteria().andNameIn(list);
                        List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses = businessTypeFieldValueChooseMapper.selectByExample(example);
                        StringBuffer sb = new StringBuffer();
                        for (BusinessTypeFieldValueChoose btfc : businessTypeFieldValueChooses) {
                            String val = btfc.getValue();
                            sb.append("," + val);
                        }
                        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = new BusinessTypeFieldValueVarchar();
                        businessTypeFieldValueVarchar.setValue((sb.toString()));
                        BusinessTypeFieldValueVarcharExample example1 = new BusinessTypeFieldValueVarcharExample();
                        BusinessTypeFieldValueVarcharExample.Criteria criteria = example1.createCriteria();
                        criteria.andIdentifyIdEqualTo(clueId);
                        criteria.andBusinessTypeFieldIdEqualTo(fieldId);
                        int i = businessTypeFieldValueVarcharMapper.updateByExampleSelective(businessTypeFieldValueVarchar, example1);
                        if (i == 0) {
                            String[] split11 = value.split(",");
                            List<String> list11 = new ArrayList<>();
                            for (String st : split11) list11.add(st);
                            BusinessTypeFieldValueChooseExample example11 = new BusinessTypeFieldValueChooseExample();
                            example11.createCriteria().andNameIn(list11);
                            List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses11 = businessTypeFieldValueChooseMapper.selectByExample(example11);
                            StringBuffer sb11 = new StringBuffer();
                            for (BusinessTypeFieldValueChoose btfc11 : businessTypeFieldValueChooses11) {
                                String val11 = btfc11.getValue();
                                sb11.append("," + val11);
                            }
                            BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar11 = new BusinessTypeFieldValueVarchar();
                            businessTypeFieldValueVarchar11.setBusinessTypeFieldId(fieldId);
                            businessTypeFieldValueVarchar11.setBusinessTypeId(businessTypeId);
                            businessTypeFieldValueVarchar11.setIdentifyId(clueId);
                            businessTypeFieldValueVarchar11.setValue((sb11.toString()));
                            businessTypeFieldValueVarcharMapper.insert(businessTypeFieldValueVarchar11);
                        }
                    }
                    if ("date".equals(fieldType)) {
                        BusinessTypeFieldValueDatetime businessTypeFieldValueDatetime = new BusinessTypeFieldValueDatetime();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = sdf.parse(value);
                        businessTypeFieldValueDatetime.setValue(date);
                        BusinessTypeFieldValueDatetimeExample example = new BusinessTypeFieldValueDatetimeExample();
                        BusinessTypeFieldValueDatetimeExample.Criteria criteria = example.createCriteria();
                        criteria.andIdentifyIdEqualTo(clueId);
                        criteria.andBusinessTypeFieldIdEqualTo(fieldId);
                        int i = businessTypeFieldValueDatetimeMapper.updateByExampleSelective(businessTypeFieldValueDatetime, example);
                        if (i == 0) {
                            BusinessTypeFieldValueDatetime qqq = new BusinessTypeFieldValueDatetime();
                            qqq.setBusinessTypeFieldId(fieldId);
                            qqq.setBusinessTypeId(businessTypeId);
                            qqq.setIdentifyId(clueId);
                            SimpleDateFormat sdfq = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date dateq = sdfq.parse(value);
                            qqq.setValue(dateq);
                            businessTypeFieldValueDatetimeMapper.insertSelective(qqq);
                        }
                    }
                    if ("text".equals(fieldType)) {
                        BusinessTypeFieldValueText businessTypeFieldValueText = new BusinessTypeFieldValueText();
                        businessTypeFieldValueText.setValue(value);
                        BusinessTypeFieldValueTextExample example = new BusinessTypeFieldValueTextExample();
                        BusinessTypeFieldValueTextExample.Criteria criteria = example.createCriteria();
                        criteria.andIdentifyIdEqualTo(clueId);
                        criteria.andBusinessTypeFieldIdEqualTo(fieldId);
                        int i = businessTypeFieldValueTextMapper.updateByExampleSelective(businessTypeFieldValueText, example);
                        if (i == 0) {
                            BusinessTypeFieldValueText qqq = new BusinessTypeFieldValueText();
                            qqq.setBusinessTypeFieldId(fieldId);
                            qqq.setBusinessTypeId(businessTypeId);
                            qqq.setIdentifyId(clueId);
                            qqq.setValue(value);
                            businessTypeFieldValueTextMapper.insertSelective(qqq);
                        }
                    }
                }
            }
            apiSendData.setData(new JSONObject());
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage("编辑成功");
        } catch (Exception e) {
            apiSendData.setData(new JSONObject());
            apiSendData.setMessage("编辑失败");
            e.printStackTrace();
        }
        return apiSendData;
    }


    /**
     * 转让线索
     *
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    @Override
    public ApiSendData transferClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        List<String> clueList = (List) data.get("clueList");
        Integer userId = Integer.parseInt(data.get("userId").toString());
        boolean flag = false;
        if (clueList != null && clueList.size() > 0) {
            BusinessTypeValueIdentifyExample businessTypeValueIdentifyExample = new BusinessTypeValueIdentifyExample();
            businessTypeValueIdentifyExample.createCriteria().andIdIn(clueList);
            List<BusinessTypeValueIdentify> businessTypeValueIdentifies = businessTypeValueIdentifyMapper.selectByExample(businessTypeValueIdentifyExample);
            try {
                if (businessTypeValueIdentifies != null && businessTypeValueIdentifies.size() > 0) {
                    for (BusinessTypeValueIdentify btvi : businessTypeValueIdentifies) {
                        btvi.setUserId(userId);
                        businessTypeValueIdentifyMapper.updateByPrimaryKeySelective(btvi);
                    }
                }
                flag = true;
            } catch (Exception e) {
                apiSendData.setCode(ApiReturnCode.ERROR.getCode());
                apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
                apiAcceptData.setData(new JSONObject());
                e.printStackTrace();
            }
        }
        if (flag) {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
            apiAcceptData.setData(new JSONObject());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiAcceptData.setData(new JSONObject());
        }
        return apiSendData;
    }

    /**
     * 短信发送
     *
     * @param apiSendData
     * @param
     * @return
     */
    @Override
    public ApiSendData sendMsg(ApiSendData apiSendData, Integer userId, List<String> clueIds, Integer contentId,
                               String content, String signature) {
        //插入发送表记录
        SendMessage sendMessage = new SendMessage();
        sendMessage.setSendContentTem(Integer.valueOf(contentId));
        sendMessage.setSendTime(new Date());
        sendMessage.setSendUserid(userId);
        sendMessage.setStatus(1);
        sendMessageMapper.insert(sendMessage);
        int id = sendMessage.getId();
        //插入接收表记录
        boolean flag = false;
        for (String clueId : clueIds) {
            BusinessTypeValueIdentify businessTypeValueIdentify = businessTypeValueIdentifyMapper.selectByPrimaryKey(clueId);
            if (businessTypeValueIdentify != null) {
                Integer businessTypeId = businessTypeValueIdentify.getBusinessTypeId();
                BusinessTypeFieldExample example1 = new BusinessTypeFieldExample();
                example1.createCriteria().andBusinessTypeIdEqualTo(businessTypeId).andFieldAliasEqualTo("phone");
                List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(example1);
                if (businessTypeFields != null && businessTypeFields.size() > 0) {
                    Integer id1 = businessTypeFields.get(0).getId();
                    BusinessTypeFieldValueVarcharExample example = new BusinessTypeFieldValueVarcharExample();
                    example.createCriteria().andIdentifyIdEqualTo(clueId).andBusinessTypeFieldIdEqualTo(id1);
                    List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarchars = businessTypeFieldValueVarcharMapper.
                            selectByExample(example);
                    if (businessTypeFieldValueVarchars != null && businessTypeFieldValueVarchars.size() > 0) {
                        String phone = businessTypeFieldValueVarchars.get(0).getValue();
                        if (phone != null) {
                            SendReceive sendReceive = new SendReceive();
                            String smsReturn = null;
                            try {
                                String smsCode = SmsUtil.SendSms(phone, "【" + signature + "】" + content);
                                smsReturn = smsCode;
                                sendReceive.setStatus(1);
                                sendReceive.setSendId(userId);
                                sendReceive.setSendId(id);
                                sendReceive.setClueId(clueId);
                                sendReceive.setMessageType(0);
                                sendReceive.setMessageReturn(smsCode);
                                sendReceiveMapper.insert(sendReceive);
                                flag = true;

                            } catch (Exception e) {
                                sendReceive.setStatus(2);
                                sendReceive.setSendId(userId);
                                sendReceive.setSendId(id);
                                sendReceive.setClueId(clueId);
                                sendReceive.setMessageReturn(smsReturn);
                                sendReceiveMapper.insert(sendReceive);
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        if (flag) {
            apiSendData.setData(new JSONObject());
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        } else {
            apiSendData.setData(new JSONObject());
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData showMessageTemplate(ApiSendData apiSendData, Integer companyId) {
        MessageTemplateExample example = new MessageTemplateExample();
        example.createCriteria().andCompanyIdEqualTo(companyId);
        List<MessageTemplate> messageTemplates = messageTemplateMapper.selectByExample(example);
        if (messageTemplates != null && messageTemplates.size() > 0) {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setData(messageTemplates);
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData selectBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取企业id
        Integer companyId = Integer.valueOf(data.get("companyId").toString());
        //获取所有组织id
        List<Integer> organizationList = (List<Integer>) data.get("organizationList");
        //全公司
        organizationList.add(0);
        //获取类别状态
        Integer typeStatus = Integer.valueOf(data.get("typeStatus").toString());

        //获取所有的业务
        CompanyOrganizationBusinessExample companyOrganizationBusinessExample = new CompanyOrganizationBusinessExample();
        companyOrganizationBusinessExample.createCriteria()
                .andCompanyIdEqualTo(companyId)
                .andOrganizationIdIn(organizationList)
                .andIsDelEqualTo(1);
        List<CompanyOrganizationBusiness> companyOrganizationBusinessList =
                companyOrganizationBusinessMapper.selectByExample(companyOrganizationBusinessExample);
        List<Integer> businessIdList = new ArrayList<>();
        for (CompanyOrganizationBusiness companyOrganizationBusiness : companyOrganizationBusinessList) {
            businessIdList.add(companyOrganizationBusiness.getBusinessId());
        }
        //获取业务信息
        if (!businessIdList.isEmpty()) {
            BusinessExample businessExample = new BusinessExample();
            businessExample.createCriteria()
                    .andIsDelEqualTo(1)
                    .andIdIn(businessIdList)
                    .andIsEnabledEqualTo(1);
            List<Business> businessList = businessMapper.selectByExample(businessExample);
            List<Business> reBusinessList = new ArrayList<>();
            for (Business business : businessList) {
                BusinessTypeExample businessTypeExample = new BusinessTypeExample();
                businessTypeExample.createCriteria().andBusinessIdEqualTo(business.getId())
                        .andTypeStatusEqualTo(typeStatus)
                        .andIsEnabledEqualTo(1);
                List<BusinessType> businessTypeList = businessTypeMapper.selectByExample(businessTypeExample);
                if (businessTypeList.size() > 0) {
                    business.setBusinessTypeId(businessTypeList.get(0).getId());
                    String[] fields = SEARCH_CLUE_FIELD.split(",");
                    List<Map> mapList = new ArrayList<>();
                    for (String field : fields) {
                        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
                        businessTypeFieldExample.createCriteria().andFieldAliasEqualTo(field).andBusinessTypeIdEqualTo(businessTypeList.get(0).getId()).andIsEnabledEqualTo(1).andIsDelEqualTo(1);
                        List<BusinessTypeField> businessTypeFieldList = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
                        Map<String, Object> map = new HashMap<>();
                        map.put("fieldId", businessTypeFieldList.get(0).getId());
                        map.put("fieldName", businessTypeFieldList.get(0).getFieldName());
                        mapList.add(map);
                    }
                    business.setMapList(mapList);
                    reBusinessList.add(business);
                }
            }
            apiSendData.setData(reBusinessList);
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        } else {
            apiSendData.setData(new JSONObject());
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData changeFollowStatus(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取业务类型id
        Integer businessTypeId = Integer.valueOf(data.get("businessTypeId").toString());
        //获取跟进记录的id
        Integer followStatusId = Integer.valueOf(data.get("followStatusId").toString());
        String clueId = (String) data.get("clueId");
        //获取跟进记录的值
        String value = data.get("value").toString();
        BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = new BusinessTypeFieldValueVarchar();
        businessTypeFieldValueVarchar.setBusinessTypeId(businessTypeId);
        businessTypeFieldValueVarchar.setBusinessTypeFieldId(followStatusId);
        businessTypeFieldValueVarchar.setValue(value);
        businessTypeFieldValueVarchar.setIdentifyId(clueId);
        //更新跟进记录数据
        BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
        businessTypeFieldValueVarcharExample.createCriteria()
                .andBusinessTypeFieldIdEqualTo(followStatusId)
                .andBusinessTypeIdEqualTo(businessTypeId).andIdentifyIdEqualTo(clueId);
        List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarcharList = businessTypeFieldValueVarcharMapper
                .selectByExample(businessTypeFieldValueVarcharExample);
        int num = 0;
        if (!businessTypeFieldValueVarcharList.isEmpty()) {
            num = businessTypeFieldValueVarcharMapper.updateByExampleSelective(businessTypeFieldValueVarchar
                    , businessTypeFieldValueVarcharExample);
        } else {
            num = businessTypeFieldValueVarcharMapper.insertSelective(businessTypeFieldValueVarchar);
        }

        if (num > 0) {
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
            apiSendData.setData(new JSONObject());
        } else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
        }
        return apiSendData;
    }

    @Override
    public ApiSendData delClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取所有组织id
        List<String> clueIdList = (List<String>) data.get("clueIdList");
        if(clueIdList != null && !clueIdList.isEmpty()){
            int num = businessTypeValueIdentifyMapper.delByIds(clueIdList);
            if (num > 0) {
                apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
                apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
                apiSendData.setData(new JSONObject());
            } else {
                apiSendData.setCode(ApiReturnCode.ERROR.getCode());
                apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
                apiSendData.setData(new JSONObject());
            }
        }else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage("未选择删除的线索");
            apiSendData.setData(new JSONObject());
        }

        return apiSendData;
    }

    @Override
    public ApiSendData getSmsTem(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取企业id
        Integer companyId = (Integer) data.get("companyId");
        MessageTemplateExample example = new MessageTemplateExample();
        example.createCriteria().andCompanyIdEqualTo(companyId).andStatusEqualTo(1).andIsDelEqualTo(1);
        List<MessageTemplate> messageTemplates = messageTemplateMapper.selectByExample(example);
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        apiSendData.setData(messageTemplates);
        return apiSendData;
    }

    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public ApiSendData callClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        String toPhone = (String) data.get("toPhone");
        String fromPhone = (String) data.get("fromPhone");
        String callerPhone = (String) data.get("showcaller");
        String showcaller = getFixedPhone(callerPhone.replace("-", ""));
       /* log.info("----------toPhone----------" + toPhone);
        log.info("----------fromPhone----------" + fromPhone);
        log.info("----------showcaller----------" + showcaller);*/
        //拨打电话
        Map<String, Object> map = new HashMap<>();
        map.put("showcaller", showcaller);
        map.put("from", fromPhone);
        map.put("to", toPhone);
        /*log.info("----------CALL_URL----------" + CALL_URL);*/
        String reStr = HttpclientUtils.doPostByJson(CALL_URL, map);
        JSONObject reData = JSONObject.parseObject(reStr);
        String statuscode = (String) reData.get("statuscode");
        String msge = (String) reData.get("msge");
        apiSendData.setCode(Integer.valueOf(statuscode));
        apiSendData.setMessage(msge);
        apiSendData.setData(new JSONObject());
        return apiSendData;
    }

    //用于获取固定电话中的区号
    private final static String REGEX_ZIPCODE = "^(010|02\\d|0[3-9]\\d{2})\\d{6,8}$";

    /**
     * 获取固定号码号码中的区号
     *
     * @param strNumber
     * @return
     */
    private static String getZipFromTel(String strNumber) {
        Matcher matcher = Pattern.compile(REGEX_ZIPCODE).matcher(strNumber);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return strNumber;
        }
    }

    /**
     * 获取固话
     */
    private static String getFixedPhone(String number) {
        if (number != null && number.length() > 0) {
            if (isTel(number)) {
                //获取区号
                Matcher matcher = Pattern.compile(REGEX_ZIPCODE).matcher(number);
                String zipCode = getZipFromTel(number);
                if (!TextUtils.isEmpty(zipCode) && matcher.find()) {
                    String fixedphone = number.replace(zipCode, "");
                    fixedphone = fixedphone.replace("-", "");
                    return fixedphone;
                }
            } else {
                return number;
            }
        }
        return number;
    }

    /**
     * 正则：电话号码
     */
    private static final String REGEX_TEL = "^($$[0]{1}\\d{2,3}-)|([(]?[（]?([0]{1}\\d{2,3})?[)]?[）]?[-]?)\\d{7,8}$";

    /**
     * 验证电话号码
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    private static boolean isTel(final CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(REGEX_TEL, input);
    }

    @Override
    public ApiSendData getFollowRecordOrPointStore(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //业务类型id
        Integer businessTypeId = (Integer) data.get("businessTypeId");
        //线索id
        String clueId = (String) data.get("clueId");
        //别名
        String fieldAlias = (String) data.get("fieldAlias");
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria()
                .andBusinessTypeIdEqualTo(businessTypeId)
                .andFieldAliasEqualTo(fieldAlias)
                .andIsEnabledEqualTo(1)
                .andIsDelEqualTo(1);
        //获取业务类型字段
        List<BusinessTypeField> businessTypeFieldList = businessTypeFieldMapper
                .selectByExample(businessTypeFieldExample);
        if (!businessTypeFieldList.isEmpty()) {
            if ("select".equals(businessTypeFieldList.get(0).getFieldType()) ||
                    "checkbox".equals(businessTypeFieldList.get(0).getFieldType())) {
                //获取对应的选项数据
                BusinessTypeFieldValueChooseExample businessTypeFieldValueChooseExample =
                        new BusinessTypeFieldValueChooseExample();
                businessTypeFieldValueChooseExample.createCriteria()
                        .andBusinessTypeFieldIdEqualTo(businessTypeFieldList.get(0).getId())
                        .andIsEnabledEqualTo(1)
                        .andIsDelEqualTo(1);
                //获取下拉框选项的值
                List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooseList =
                        businessTypeFieldValueChooseMapper.selectByExample(businessTypeFieldValueChooseExample);
                //获取该选项对应的值
                BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                businessTypeFieldValueVarcharExample.createCriteria()
                        .andBusinessTypeIdEqualTo(businessTypeId)
                        .andBusinessTypeFieldIdEqualTo(businessTypeFieldList.get(0).getId())
                        .andIdentifyIdEqualTo(clueId);
                List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarcharList =
                        businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);
                Map<String, Object> map = new HashMap<>();
                if (!businessTypeFieldValueVarcharList.isEmpty()) {
                    map.put("value", businessTypeFieldValueVarcharList.get(0).getValue());
                } else {
                    map.put("value", "");
                }
                map.put("businessTypeFieldValueChooseList", businessTypeFieldValueChooseList);
                apiSendData.setData(map);
            } else {
                apiSendData.setData(new HashMap<>());
            }
        } else {
            apiSendData.setData(new HashMap<>());
        }
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        return apiSendData;
    }

    @Override
    public ApiSendData getClueDetailApi(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //业务类型id
        Integer businessTypeId = Integer.parseInt(jsonObject.get("businessTypeId").toString());
        //别名列表
        List<String> fieldAliasList = (List<String>) jsonObject.get("fieldAliasList");
        //线索id
        String clueId = (String) jsonObject.get("clueId");
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria().andBusinessTypeIdEqualTo(businessTypeId)
                .andFieldAliasIn(fieldAliasList).andIsDelEqualTo(1).andIsEnabledEqualTo(1);
        //根据别名获取业务类型
        List<BusinessTypeField> businessTypeFieldList = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
        //获取业务类型对应的值
        for (BusinessTypeField businessTypeField : businessTypeFieldList) {
            if ("input".equals(businessTypeField.getFieldType())) {
                BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
                businessTypeFieldValueVarcharExample.createCriteria()
                        .andIdentifyIdEqualTo(clueId)
                        .andBusinessTypeFieldIdEqualTo(businessTypeField.getId())
                        .andBusinessTypeIdEqualTo(businessTypeId);
                List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarcharList =
                        businessTypeFieldValueVarcharMapper.selectByExample(businessTypeFieldValueVarcharExample);

                if (!businessTypeFieldValueVarcharList.isEmpty()) {
                    businessTypeField.setValue((businessTypeFieldValueVarcharList.get(0).getValue() == null) ? "" :
                            businessTypeFieldValueVarcharList.get(0).getValue());
                }

            }
            if ("date".equals(businessTypeField.getFieldType())) {
                BusinessTypeFieldValueDatetimeExample businessTypeFieldValueDatetimeExample = new BusinessTypeFieldValueDatetimeExample();
                businessTypeFieldValueDatetimeExample.createCriteria().andIdentifyIdEqualTo(clueId).
                        andBusinessTypeFieldIdEqualTo(businessTypeField.getId()).andBusinessTypeIdEqualTo(businessTypeId);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<BusinessTypeFieldValueDatetime> businessTypeFieldValueDatetimeList =
                        businessTypeFieldValueDatetimeMapper.selectByExample(businessTypeFieldValueDatetimeExample);
                if (!businessTypeFieldValueDatetimeList.isEmpty()) {
                    if (businessTypeFieldValueDatetimeList.get(0).getValue() != null) {
                        businessTypeField.setValue(format.format(businessTypeFieldValueDatetimeList.get(0).getValue()));
                    } else {
                        businessTypeField.setValue("");
                    }
                }
            }
            if ("text".equals(businessTypeField.getFieldType())) {
                BusinessTypeFieldValueTextExample businessTypeFieldValueTextExample = new BusinessTypeFieldValueTextExample();
                businessTypeFieldValueTextExample.createCriteria()
                        .andIdentifyIdEqualTo(clueId)
                        .andBusinessTypeFieldIdEqualTo(businessTypeField.getId())
                        .andBusinessTypeIdEqualTo(businessTypeId);
                List<BusinessTypeFieldValueText> businessTypeFieldValueTextList =
                        businessTypeFieldValueTextMapper.selectByExample(businessTypeFieldValueTextExample);
                if (!businessTypeFieldValueTextList.isEmpty()) {
                    if (businessTypeFieldValueTextList.get(0) != null) {
                        businessTypeField.setValue(businessTypeFieldValueTextList.get(0).getValue());
                    } else {
                        businessTypeField.setValue("");
                    }
                }
            }
            if ("select".equals(businessTypeField.getFieldType()) ||
                    "checkbox".equals(businessTypeField.getFieldType())) {
                BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample =
                        new BusinessTypeFieldValueVarcharExample();
                BusinessTypeFieldValueChooseExample businessTypeFieldValueChooseExample =
                        new BusinessTypeFieldValueChooseExample();
                businessTypeFieldValueVarcharExample.createCriteria()
                        .andIdentifyIdEqualTo(clueId)
                        .andBusinessTypeFieldIdEqualTo(businessTypeField.getId())
                        .andBusinessTypeIdEqualTo(businessTypeId);
                List<BusinessTypeFieldValueVarchar> businessTypeFieldValueVarcharList =
                        businessTypeFieldValueVarcharMapper
                                .selectByExample(businessTypeFieldValueVarcharExample);
                if (!businessTypeFieldValueVarcharList.isEmpty()) {
                    if (businessTypeFieldValueVarcharList.get(0).getValue() != null) {
                        businessTypeFieldValueChooseExample.createCriteria()
                                .andBusinessTypeFieldIdEqualTo(businessTypeField.getId())
                                .andValueEqualTo(businessTypeFieldValueVarcharList.get(0).getValue());
                        businessTypeField.setValue(businessTypeFieldValueChooseMapper
                                .selectByExample(businessTypeFieldValueChooseExample).get(0).getName());
                    } else {
                        businessTypeField.setValue("");
                    }
                }
            }
        }
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        apiSendData.setData(businessTypeFieldList);
        return apiSendData;
    }

    @Override
    public ApiSendData getClueSystemInfo(ApiSendData apiSendData, ApiAcceptData apiAcceptData) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        //获取线索id
        String clueId = (String) jsonObject.get("clueId");
        //获取线索系统信息
        BusinessTypeValueIdentifyExample businessTypeValueIdentifyExample = new BusinessTypeValueIdentifyExample();
        businessTypeValueIdentifyExample.createCriteria()
                .andIdEqualTo(clueId)
                .andIsDelEqualTo(1);
        List<BusinessTypeValueIdentify> businessTypeValueIdentifyList = businessTypeValueIdentifyMapper
                .selectByExample(businessTypeValueIdentifyExample);
        if (!businessTypeValueIdentifyList.isEmpty()) {
            BusinessTypeValueIdentify businessTypeValueIdentify = businessTypeValueIdentifyList.get(0);
            if (businessTypeValueIdentify.getCreateUserId() != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", businessTypeValueIdentify.getCreateUserId());
                String s1 = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + GET_USER_BY_USERID, map);
                Map map1 = JsonUtils.jsonToPojo(s1, Map.class);
                String name = (String) map1.get("name");
                businessTypeValueIdentify.setCreateName(name);
            }
            if (businessTypeValueIdentify.getUpdateUserId() != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", businessTypeValueIdentify.getUpdateUserId());
                String s1 = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + GET_USER_BY_USERID, map);
                Map map1 = JsonUtils.jsonToPojo(s1, Map.class);
                String name = (String) map1.get("name");
                businessTypeValueIdentify.setUpdateName(name);
            }
            if (businessTypeValueIdentify.getUserId() != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", businessTypeValueIdentify.getUserId());
                String s1 = HttpclientUtils.doPostByJson(UNITE_ACCOUNT_BASE_URL + GET_USER_BY_USERID, map);
                Map map1 = JsonUtils.jsonToPojo(s1, Map.class);
                String name = (String) map1.get("name");
                businessTypeValueIdentify.setPrincipalName(name);
            }
        }
        apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
        apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
        apiSendData.setData(businessTypeValueIdentifyList);
        return apiSendData;
    }

    @Override
    public String  validateData(Integer businessTypeId,String headerName,String value) {
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria().andBusinessTypeIdEqualTo(businessTypeId).andFieldNameEqualTo(headerName);
        List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
        BusinessTypeField businessTypeField = businessTypeFields.get(0);
        String fieldType = businessTypeField.getFieldType();
        String fieldAlias = businessTypeField.getFieldAlias();
        Integer bId = businessTypeField.getId();
        BusinessTypeFieldValueChooseExample businessTypeFieldValueChooseExample = new BusinessTypeFieldValueChooseExample();
        businessTypeFieldValueChooseExample.createCriteria().andBusinessTypeFieldIdEqualTo(bId);
        List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses = businessTypeFieldValueChooseMapper.selectByExample(businessTypeFieldValueChooseExample);
        List<String> chooseNameList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        String st= null;
        if(businessTypeFieldValueChooses!=null&&businessTypeFieldValueChooses.size()>0){
            sb.append("[");
            for (BusinessTypeFieldValueChoose btf:businessTypeFieldValueChooses) {
                chooseNameList.add(btf.getName());
                sb.append(btf.getName()+"/");
            }
            st=sb.toString();
            st=st.substring(0,sb.lastIndexOf("/"));
            st+="]";
        }
        if(chooseNameList!=null&&chooseNameList.size()>0){
            if(!chooseNameList.contains(value)){
                return  "填写内容无效,请填写"+st;
            }
        }
        else{
            if("input".equals(fieldType)){
                if("phone".equals(fieldAlias)&&!Validate.isMobile(value)){
                    return "手机号格式不正确";
                }
                if("fixedTel".equals(fieldAlias)&&!Validate.isPhone(value)){
                    return  "电话格式不正确";
                }
                if("email".equals(fieldAlias)&& !Validate.isEmail(value)){
                    return "邮箱格式不正确";
                }
            }
            if("date".equals(fieldType)){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date = simpleDateFormat.parse(value);
                    return null;
                } catch (ParseException e) {
                    e.printStackTrace();
                    return "日期格式不正确,请按照(1999-12-12 00:00:00)样式填写";
                }
            }
        }
        return  null;
    }

    @Override
    public List<BusinessType> selectByBusinessId(Integer businessId) {
        BusinessTypeExample businessTypeExample = new BusinessTypeExample();
        businessTypeExample.createCriteria().andBusinessIdEqualTo(businessId).
                andTypeStatusEqualTo(1);
        return businessTypeMapper.selectByExample(businessTypeExample);
    }

    @Override
    public List<BusinessTypeField> selectByBusinessTypeIdAndTableHeader(Integer businessTypeId,String tableHeader) {
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria().andBusinessTypeIdEqualTo(businessTypeId).
                andFieldNameEqualTo(tableHeader);
        return businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
    }

    @Override
    public CompanyOrganizationBusiness selectByCompanyId(int i) {
        CompanyOrganizationBusinessExample companyOrganizationBusinessExample = new CompanyOrganizationBusinessExample();
        companyOrganizationBusinessExample.createCriteria().andCompanyIdEqualTo(i);
        List<CompanyOrganizationBusiness> companyOrganizationBusinesses = companyOrganizationBusinessMapper.selectByExample(companyOrganizationBusinessExample);
        return  companyOrganizationBusinesses.get(0);
    }

    @Override
    public void batchSaveClues(Integer userId,Integer businessTypeId,Integer businessId,List<List> list,String checkBox) {
        //定义给一个集合用于存储电话，用于查重使用
        Map<String,String> phoneMap = new HashMap<>();
        for(List<Map<String,Object>> lst:list){
            String clueId = String.valueOf(UUID.randomUUID());
            //插入线索唯一关联表，后续查询时用
            BusinessTypeValueIdentify identify = new BusinessTypeValueIdentify();
            Date dt = new Date();
            identify.setIsDel(1);
            identify.setCreateTime(dt);
            identify.setCreateUserId(userId);
            identify.setId(clueId);
            identify.setUserId(userId);
            identify.setBusinessTypeId(businessTypeId);
            businessTypeValueIdentifyMapper.insert(identify);
            //接受封装页的面参数:List<Map>
            try {
                Integer fieldId;
                String value;
                String name = "";
                Date followNextTime = null;
                OUT:
                for (Map<String,Object> lt : lst) {
                    fieldId = Integer.parseInt(lt.get("fieldId").toString());
                    BusinessTypeField businessTypeField = businessTypeFieldMapper.selectByPrimaryKey(fieldId);
                    String fieldType = businessTypeField.getFieldType();
                    String fieldAlias = businessTypeField.getFieldAlias();
                    value = (String) lt.get("value");
                    if (value != null && value.toString().length() > 0) {
                        //根据字段类型进行制定字段表进行值的插入
                        if ("input".equals(fieldType)) {
                            if("contactName".equals(fieldAlias)){
                                name=value;
                            }
                            if("phone".equals(fieldAlias)){
                                Set<String> strings = phoneMap.keySet();
                                if(strings!=null&&strings.contains(value)){
                                    if("1".equals(checkBox)){
                                        delClueByClueId(phoneMap.get(value));
                                    }
                                    if("2".equals(checkBox)){
                                        delClueByClueId(clueId);
                                        break  OUT;
                                    }

                                }
                            }
                            BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = new BusinessTypeFieldValueVarchar();
                            businessTypeFieldValueVarchar.setBusinessTypeFieldId(fieldId);
                            businessTypeFieldValueVarchar.setBusinessTypeId(businessTypeId);
                            businessTypeFieldValueVarchar.setIdentifyId(clueId);
                            businessTypeFieldValueVarchar.setValue( value);
                            businessTypeFieldValueVarcharMapper.insert(businessTypeFieldValueVarchar);
                            phoneMap.put(value,clueId);

                        }
                        if ("select".equals(fieldType)) {
                            BusinessTypeFieldValueChooseExample example = new BusinessTypeFieldValueChooseExample();
                            example.createCriteria().andNameEqualTo( value);
                            List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses = businessTypeFieldValueChooseMapper.selectByExample(example);
                            String val = businessTypeFieldValueChooses.get(0).getValue();
                            BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = new BusinessTypeFieldValueVarchar();
                            businessTypeFieldValueVarchar.setBusinessTypeFieldId(fieldId);
                            businessTypeFieldValueVarchar.setBusinessTypeId(businessTypeId);
                            businessTypeFieldValueVarchar.setIdentifyId(clueId);
                            businessTypeFieldValueVarchar.setValue(val);
                            businessTypeFieldValueVarcharMapper.insert(businessTypeFieldValueVarchar);
                        }
                        if("checkbox".equals(fieldType)){
                            String[] split = value.split(",");
                            List<String> ll = new ArrayList<>();
                            for(String st:split) ll.add(st);
                            BusinessTypeFieldValueChooseExample example = new BusinessTypeFieldValueChooseExample();
                            example.createCriteria().andNameIn(ll);
                            List<BusinessTypeFieldValueChoose> businessTypeFieldValueChooses = businessTypeFieldValueChooseMapper.selectByExample(example);
                            //String val = businessTypeFieldValueChooses.get(0).getValue();
                            StringBuffer sb = new StringBuffer();
                            for(BusinessTypeFieldValueChoose btfc:businessTypeFieldValueChooses){
                                String val =btfc.getValue();
                                sb.append(","+val);
                            }
                            BusinessTypeFieldValueVarchar businessTypeFieldValueVarchar = new BusinessTypeFieldValueVarchar();
                            businessTypeFieldValueVarchar.setBusinessTypeFieldId(fieldId);
                            businessTypeFieldValueVarchar.setBusinessTypeId(businessTypeId);
                            businessTypeFieldValueVarchar.setIdentifyId(clueId);
                            businessTypeFieldValueVarchar.setValue((sb.toString()));
                            businessTypeFieldValueVarcharMapper.insert(businessTypeFieldValueVarchar);
                        }
                        if ("date".equals(fieldType)) {
                            BusinessTypeFieldValueDatetime businessTypeFieldValueDatetime = new BusinessTypeFieldValueDatetime();
                            businessTypeFieldValueDatetime.setBusinessTypeFieldId(fieldId);
                            businessTypeFieldValueDatetime.setBusinessTypeId(businessTypeId);
                            businessTypeFieldValueDatetime.setIdentifyId(clueId);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = sdf.parse(value);
                            followNextTime = date;
                            businessTypeFieldValueDatetime.setValue(date);
                            businessTypeFieldValueDatetimeMapper.insert(businessTypeFieldValueDatetime);
                        }
                        if ("text".equals(fieldType)) {
                            BusinessTypeFieldValueText businessTypeFieldValueText = new BusinessTypeFieldValueText();
                            businessTypeFieldValueText.setBusinessTypeFieldId(fieldId);
                            businessTypeFieldValueText.setBusinessTypeId(businessTypeId);
                            businessTypeFieldValueText.setIdentifyId(clueId);
                            businessTypeFieldValueText.setValue((String) value);
                            businessTypeFieldValueTextMapper.insert(businessTypeFieldValueText);
                        }
                    }
                }
                if(followNextTime!=null){insertSmsZ.insertSms1(name, followNextTime,businessId, clueId, null, businessTypeId);}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        phoneMap.clear();
    }

    public void delClueByClueId(String clueId){
        BusinessTypeFieldValueVarcharExample businessTypeFieldValueVarcharExample = new BusinessTypeFieldValueVarcharExample();
        businessTypeFieldValueVarcharExample.createCriteria().andIdentifyIdEqualTo(clueId);
        businessTypeFieldValueVarcharMapper.deleteByExample(businessTypeFieldValueVarcharExample);
        BusinessTypeFieldValueTextExample businessTypeFieldValueTextExample = new BusinessTypeFieldValueTextExample();
        businessTypeFieldValueTextExample.createCriteria().andIdentifyIdEqualTo(clueId);
        businessTypeFieldValueTextMapper.deleteByExample(businessTypeFieldValueTextExample);
        BusinessTypeFieldValueDatetimeExample businessTypeFieldValueDatetimeExample = new BusinessTypeFieldValueDatetimeExample();
        businessTypeFieldValueDatetimeMapper.deleteByExample(businessTypeFieldValueDatetimeExample);
        businessTypeValueIdentifyMapper.deleteByPrimaryKey(clueId);
    }
    @Override
    public ApiSendData checkBusinessStatus(ApiSendData apiSendData,ApiAcceptData apiAcceptData) {
        JSONObject data = JSON.parseObject(JSON.toJSONString(apiAcceptData.getData()));
        Integer businessTypeId = Integer.parseInt(data.get("businessTypeId").toString());
        Integer businessId = Integer.parseInt(data.get("businessId").toString());
        BusinessTypeExample businessTypeExample = new BusinessTypeExample();
        businessTypeExample.createCriteria().andIdEqualTo(businessTypeId).andBusinessIdEqualTo(businessId);
        List<BusinessType> businessTypes = businessTypeMapper.selectByExample(businessTypeExample);
        BusinessType businessType = businessTypes.get(0);
        Integer isEnabled = businessType.getIsEnabled();
        if(isEnabled==1){
            String showName = businessType.getShowName();
            apiSendData.setCode(ApiReturnCode.SUCCESS.getCode());
            apiSendData.setMessage(ApiReturnCode.SUCCESS.getMessage());
            apiSendData.setData(showName);
        }
        else {
            apiSendData.setCode(ApiReturnCode.ERROR.getCode());
            apiSendData.setMessage(ApiReturnCode.ERROR.getMessage());
            apiSendData.setData(new JSONObject());
        }
        return apiSendData;
    }
    @Override
    public MessageTemplate getMessageTemplate(Integer id){
        MessageTemplate messageTemplate = messageTemplateMapper.selectByPrimaryKey(id);
        return messageTemplate;
    }

    @Override
    public boolean judgeIsRequired(Integer businessTypeId, String s) {
        BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
        businessTypeFieldExample.createCriteria().andBusinessTypeIdEqualTo(businessTypeId).andFieldNameEqualTo(s);
        List<BusinessTypeField> businessTypeFields = businessTypeFieldMapper.selectByExample(businessTypeFieldExample);
        BusinessTypeField businessTypeField = businessTypeFields.get(0);
        Integer isRequired = businessTypeField.getIsRequired();
        if(isRequired==1){
            return true;
        }
        return  false;
    }
    @Override
    public String[] getFieldIdByHeader(String[] tableHeader,Integer businessTypeId) {
        String[] temArray = new String[tableHeader.length];
        for(int i=0;i<tableHeader.length;i++){
            BusinessTypeFieldExample businessTypeFieldExample = new BusinessTypeFieldExample();
            businessTypeFieldExample.createCriteria().andFieldNameEqualTo(tableHeader[i]).andBusinessTypeIdEqualTo(businessTypeId);
            temArray[i]=businessTypeFieldMapper.selectByExample(businessTypeFieldExample).get(0).getId().toString();
        }
        return temArray;
    }

    @Override
    public Integer getBusinessTypeId(Integer businessId) {
        BusinessTypeExample businessTypeExample = new BusinessTypeExample();
        businessTypeExample.createCriteria().andBusinessIdEqualTo(businessId).andTypeStatusEqualTo(1);
        List<BusinessType> businessTypes = businessTypeMapper.selectByExample(businessTypeExample);
        return businessTypes.get(0).getId();
    }
}
