package com.sunll.clue.dao.business;

import com.sunll.clue.entity.business.BusinessTypeField;
import com.sunll.clue.entity.business.BusinessTypeFieldExample;

import java.util.List;
import java.util.Map;

public interface BusinessTypeFieldMapper {

    List<BusinessTypeField> selectByExample(BusinessTypeFieldExample example);

    BusinessTypeField selectByPrimaryKey(Integer id);

    List<BusinessTypeField> getDetailClueByBusinessTypeIdAndPid(Map<String, Object> map);
}