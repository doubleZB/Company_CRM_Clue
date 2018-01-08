package com.sunll.systemset.api.template;

import com.sunll.systemset.entity.template.BusinessTypeTem;
import com.sunll.systemset.entity.template.BusinessTypeTemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeTemService {
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
