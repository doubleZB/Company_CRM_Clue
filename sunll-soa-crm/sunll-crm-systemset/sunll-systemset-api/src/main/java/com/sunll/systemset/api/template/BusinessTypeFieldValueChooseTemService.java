package com.sunll.systemset.api.template;

import com.sunll.systemset.entity.template.BusinessTypeFieldValueChooseTem;
import com.sunll.systemset.entity.template.BusinessTypeFieldValueChooseTemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeFieldValueChooseTemService {
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
