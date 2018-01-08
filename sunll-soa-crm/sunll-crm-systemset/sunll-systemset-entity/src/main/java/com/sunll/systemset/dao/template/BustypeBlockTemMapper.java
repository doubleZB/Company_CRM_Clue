package com.sunll.systemset.dao.template;

import com.sunll.systemset.entity.template.BustypeBlockTem;
import com.sunll.systemset.entity.template.BustypeBlockTemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BustypeBlockTemMapper {
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