package com.sunll.systemset.api.template;

import com.sunll.systemset.entity.template.FieldblockFieldTem;
import com.sunll.systemset.entity.template.FieldblockFieldTemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator
 * on 2017/11/30
 */
public interface FieldblockFieldTemService {
    long countByExample(FieldblockFieldTemExample example);

    int deleteByExample(FieldblockFieldTemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FieldblockFieldTem record);

    int insertSelective(FieldblockFieldTem record);

    List<FieldblockFieldTem> selectByExample(FieldblockFieldTemExample example);

    FieldblockFieldTem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FieldblockFieldTem record, @Param("example") FieldblockFieldTemExample example);

    int updateByExample(@Param("record") FieldblockFieldTem record, @Param("example") FieldblockFieldTemExample example);

    int updateByPrimaryKeySelective(FieldblockFieldTem record);

    int updateByPrimaryKey(FieldblockFieldTem record);
}
