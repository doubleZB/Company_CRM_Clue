package com.sunll.systemset.dao.template;

import com.sunll.systemset.entity.template.BusinessTypeFieldValueChooseTem;
import com.sunll.systemset.entity.template.BusinessTypeFieldValueChooseTemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessTypeFieldValueChooseTemMapper {
    long countByExample(BusinessTypeFieldValueChooseTemExample example);

    int deleteByExample(BusinessTypeFieldValueChooseTemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFieldValueChooseTem record);

    int insertSelective(BusinessTypeFieldValueChooseTem record);

    List<BusinessTypeFieldValueChooseTem> selectByExample(BusinessTypeFieldValueChooseTemExample example);

    BusinessTypeFieldValueChooseTem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFieldValueChooseTem record, @Param("example") BusinessTypeFieldValueChooseTemExample example);

    int updateByExample(@Param("record") BusinessTypeFieldValueChooseTem record, @Param("example") BusinessTypeFieldValueChooseTemExample example);

    int updateByPrimaryKeySelective(BusinessTypeFieldValueChooseTem record);

    int updateByPrimaryKey(BusinessTypeFieldValueChooseTem record);
}