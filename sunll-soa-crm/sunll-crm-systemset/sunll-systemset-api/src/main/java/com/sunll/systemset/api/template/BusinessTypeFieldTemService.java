package com.sunll.systemset.api.template;

import com.sunll.systemset.entity.template.BusinessTypeFieldTem;
import com.sunll.systemset.entity.template.BusinessTypeFieldTemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeFieldTemService {
    long countByExample(BusinessTypeFieldTemExample example);

    int deleteByExample(BusinessTypeFieldTemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessTypeFieldTem record);

    int insertSelective(BusinessTypeFieldTem record);

    List<BusinessTypeFieldTem> selectByExample(BusinessTypeFieldTemExample example);

    BusinessTypeFieldTem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessTypeFieldTem record, @Param("example") BusinessTypeFieldTemExample example);

    int updateByExample(@Param("record") BusinessTypeFieldTem record, @Param("example") BusinessTypeFieldTemExample example);

    int updateByPrimaryKeySelective(BusinessTypeFieldTem record);

    int updateByPrimaryKey(BusinessTypeFieldTem record);
}
