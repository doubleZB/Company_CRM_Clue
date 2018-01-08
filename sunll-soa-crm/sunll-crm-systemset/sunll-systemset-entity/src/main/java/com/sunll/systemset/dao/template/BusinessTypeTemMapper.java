package com.sunll.systemset.dao.template;

import com.sunll.systemset.entity.template.BusinessTypeTem;
import com.sunll.systemset.entity.template.BusinessTypeTemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTypeTemMapper {
    long countByExample(BusinessTypeTemExample example);

    int deleteByExample(BusinessTypeTemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeTem record);

    int insertSelective(BusinessTypeTem record);

    List<BusinessTypeTem> selectByExample(BusinessTypeTemExample example);

    BusinessTypeTem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeTem record, @Param("example") BusinessTypeTemExample example);

    int updateByExample(@Param("record") BusinessTypeTem record, @Param("example") BusinessTypeTemExample example);

    int updateByPrimaryKeySelective(BusinessTypeTem record);

    int updateByPrimaryKey(BusinessTypeTem record);
}