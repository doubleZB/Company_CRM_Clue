package com.sunll.systemset.service.template;

import com.sunll.systemset.api.template.BusinessTemService;
import com.sunll.systemset.entity.template.BusinessTem;
import com.sunll.systemset.entity.template.BusinessTemExample;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public class BusinessTemServiceImpl implements BusinessTemService {
    @Override
    public long countByExample(BusinessTemExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(BusinessTemExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(BusinessTem record) {
        return 0;
    }

    @Override
    public int insertSelective(BusinessTem record) {
        return 0;
    }

    @Override
    public List<BusinessTem> selectByExample(BusinessTemExample example) {
        return null;
    }

    @Override
    public BusinessTem selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(BusinessTem record, BusinessTemExample example) {
        return 0;
    }

    @Override
    public int updateByExample(BusinessTem record, BusinessTemExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(BusinessTem record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(BusinessTem record) {
        return 0;
    }
}
