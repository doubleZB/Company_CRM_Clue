package com.sunll.systemset.dao.template;

import com.sunll.systemset.entity.template.FieldblockFieldTem;
import com.sunll.systemset.entity.template.FieldblockFieldTemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FieldblockFieldTemMapper {
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