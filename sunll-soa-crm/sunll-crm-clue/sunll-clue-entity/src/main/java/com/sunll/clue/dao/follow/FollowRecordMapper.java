package com.sunll.clue.dao.follow;

import com.sunll.clue.entity.follow.FollowRecord;
import com.sunll.clue.entity.follow.FollowRecordExample;
import com.sunll.clue.entity.message.Sms;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FollowRecordMapper {
    long countByExample(FollowRecordExample example);

    int deleteByExample(FollowRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FollowRecord record);

    int insertSelective(FollowRecord record);

    List<FollowRecord> selectByExample(FollowRecordExample example);

    FollowRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FollowRecord record, @Param("example") FollowRecordExample example);

    int updateByExample(@Param("record") FollowRecord record, @Param("example") FollowRecordExample example);

    int updateByPrimaryKeySelective(FollowRecord record);

    int updateByPrimaryKey(FollowRecord record);

    List<String> selectCreateTimeGroupBy(String followSourceId);

    List<FollowRecord> selectFollowRecordList(Map<String,Object> map);

    int insertSmsSelective (Sms sms);


}