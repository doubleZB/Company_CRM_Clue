package com.sunll.systemset.dao.template;

import com.sunll.systemset.entity.template.BusinessTem;
import com.sunll.systemset.entity.template.BusinessTemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTemMapper {
    long countByExample(BusinessTemExample example);

    int deleteByExample(BusinessTemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTem record);

    int insertSelective(BusinessTem record);

    List<BusinessTem> selectByExample(BusinessTemExample example);

    BusinessTem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTem record, @Param("example") BusinessTemExample example);

    int updateByExample(@Param("record") BusinessTem record, @Param("example") BusinessTemExample example);

    int updateByPrimaryKeySelective(BusinessTem record);

    int updateByPrimaryKey(BusinessTem record);
}