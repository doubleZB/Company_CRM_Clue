package com.sunll.systemset.api.template;

import com.sunll.systemset.entity.template.BusinessTem;
import com.sunll.systemset.entity.template.BusinessTemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTemService {
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
