package com.sunll.systemset.dao.template;

import com.sunll.systemset.entity.template.BusinessTypeFieldBlockTem;
import com.sunll.systemset.entity.template.BusinessTypeFieldBlockTemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessTypeFieldBlockTemMapper {
    long countByExample(BusinessTypeFieldBlockTemExample example);

    int deleteByExample(BusinessTypeFieldBlockTemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFieldBlockTem record);

    int insertSelective(BusinessTypeFieldBlockTem record);

    List<BusinessTypeFieldBlockTem> selectByExample(BusinessTypeFieldBlockTemExample example);

    BusinessTypeFieldBlockTem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFieldBlockTem record, @Param("example") BusinessTypeFieldBlockTemExample example);

    int updateByExample(@Param("record") BusinessTypeFieldBlockTem record, @Param("example") BusinessTypeFieldBlockTemExample example);

    int updateByPrimaryKeySelective(BusinessTypeFieldBlockTem record);

    int updateByPrimaryKey(BusinessTypeFieldBlockTem record);

    //通过业务类型id获取所有的区块以及区块对应的字段信息
    List<BusinessTypeFieldBlockTem> selectBlockAndFieldByBusinessTypeId(Integer businessTypeId);
}