package com.sunll.systemset.api.template;

import com.sunll.systemset.entity.template.BustypeBlockTem;
import com.sunll.systemset.entity.template.BustypeBlockTemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface BustypeBlockTemService {
    long countByExample(BustypeBlockTemExample example);

    int deleteByExample(BustypeBlockTemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BustypeBlockTem record);

    int insertSelective(BustypeBlockTem record);

    List<BustypeBlockTem> selectByExample(BustypeBlockTemExample example);

    BustypeBlockTem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BustypeBlockTem record, @Param("example") BustypeBlockTemExample example);

    int updateByExample(@Param("record") BustypeBlockTem record, @Param("example") BustypeBlockTemExample example);

    int updateByPrimaryKeySelective(BustypeBlockTem record);

    int updateByPrimaryKey(BustypeBlockTem record);
}
