package com.sunll.systemset.api.template;

import com.sunll.systemset.entity.template.BusinessBusinesstypeTem;
import com.sunll.systemset.entity.template.BusinessBusinesstypeTemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BusinessBusinesstypeTemService {

    long countByExample(BusinessBusinesstypeTemExample example);

    int deleteByExample(BusinessBusinesstypeTemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BusinessBusinesstypeTem record);

    int insertSelective(BusinessBusinesstypeTem record);

    List<BusinessBusinesstypeTem> selectByExample(BusinessBusinesstypeTemExample example);

    BusinessBusinesstypeTem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BusinessBusinesstypeTem record, @Param("example") BusinessBusinesstypeTemExample example);

    int updateByExample(@Param("record") BusinessBusinesstypeTem record, @Param("example") BusinessBusinesstypeTemExample example);

    int updateByPrimaryKeySelective(BusinessBusinesstypeTem record);

    int updateByPrimaryKey(BusinessBusinesstypeTem record);

}
