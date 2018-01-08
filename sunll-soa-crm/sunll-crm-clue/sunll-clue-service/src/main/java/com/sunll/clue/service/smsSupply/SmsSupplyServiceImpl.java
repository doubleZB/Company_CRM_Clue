package com.sunll.clue.service.smsSupply;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunll.clue.api.smsSupply.SmsSupplyService;
import com.sunll.clue.dao.business.BusinessTypeMapper;
import com.sunll.clue.dao.business.BusinessTypeValueIdentifyMapper;
import com.sunll.clue.entity.business.BusinessType;
import com.sunll.clue.entity.business.BusinessTypeExample;
import com.sunll.clue.entity.business.BusinessTypeValueIdentify;
import com.sunll.clue.entity.business.BusinessTypeValueIdentifyExample;
import com.sunll.common.util.DataEnumerate;
import com.sunll.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/12/7.
 */
@Service
public class SmsSupplyServiceImpl implements SmsSupplyService {
    @Autowired
    BusinessTypeMapper businessTypeMapper;
    @Autowired
    BusinessTypeValueIdentifyMapper businessTypeValueIdentifyMapper;
    /**
     * 根据具体业务id获取负责人id
     * @param specificId 具体业务id
     * @return userId
     */
    @Override
    public Integer selectUserIdBySpecificId(String specificId) {
        BusinessTypeValueIdentifyExample example = new BusinessTypeValueIdentifyExample();
        BusinessTypeValueIdentifyExample.Criteria criteria1 = example.createCriteria();
        criteria1.andIdEqualTo(specificId.toString());
        criteria1.andIsDelEqualTo(DataEnumerate.INT_NUM_ONE);//未删除
        List<BusinessTypeValueIdentify> btvi = businessTypeValueIdentifyMapper.selectByExample(example);
        BusinessTypeValueIdentify valueIdentity = btvi.get(0);
        Integer userId = valueIdentity.getUserId();
        return userId;
    }

    @Override
    public List<BusinessTypeValueIdentify> selectSpecificIdListByUserId(Integer userId) {
        BusinessTypeValueIdentifyExample example = new BusinessTypeValueIdentifyExample();
        BusinessTypeValueIdentifyExample.Criteria criteria1 = example.createCriteria();
        criteria1.andIsDelEqualTo(DataEnumerate.INT_NUM_ONE);//未删除
        criteria1.andUserIdEqualTo(userId);
        List<BusinessTypeValueIdentify> btvi = businessTypeValueIdentifyMapper.selectByExample(example);
        return btvi;
    }

    @Override
    public String selectbusinessNameBybusinessId(Integer businessId) {
        //查询业务名称
        BusinessTypeExample businessTypeExample = new BusinessTypeExample();
        BusinessTypeExample.Criteria criteria = businessTypeExample.createCriteria();
        criteria.andBusinessIdEqualTo(businessId);
        List<BusinessType> types = businessTypeMapper.selectByExample(businessTypeExample);
        BusinessType businessType = types.get(0);
        return businessType.getName();
    }

    /**
     * 校验唯一标识是否存在
     * @param identifyId
     * @return
     */
    @Override
    public Result checkIdentify(String identifyId) {
        BusinessTypeValueIdentifyExample example = new BusinessTypeValueIdentifyExample();
        BusinessTypeValueIdentifyExample.Criteria criteria1 = example.createCriteria();
        criteria1.andIsDelEqualTo(DataEnumerate.INT_NUM_ONE);//未删除
        criteria1.andIdEqualTo(identifyId);
        List<BusinessTypeValueIdentify> btvi = businessTypeValueIdentifyMapper.selectByExample(example);
        if(btvi.size() == 0){
            return Result.build(500,"线索已删除",null);
        }else {
            return Result.build(200,"线索存在",null);
        }
    }
}
