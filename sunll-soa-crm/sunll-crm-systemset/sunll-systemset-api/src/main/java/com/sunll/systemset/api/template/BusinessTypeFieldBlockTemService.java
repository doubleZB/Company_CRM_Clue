package com.sunll.systemset.api.template;

import com.sunll.systemset.entity.template.BusinessTypeFieldBlockTem;
import com.sunll.systemset.entity.template.BusinessTypeFieldBlockTemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessTypeFieldBlockTemService {
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
}
