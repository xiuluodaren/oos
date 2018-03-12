package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqDiningtable;
import com.shiqi.oos.entity.SqDiningtableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqDiningtableMapper {
    int countByExample(SqDiningtableExample example);

    int deleteByExample(SqDiningtableExample example);

    int deleteByPrimaryKey(String id);

    int insert(SqDiningtable record);

    int insertSelective(SqDiningtable record);

    List<SqDiningtable> selectByExample(SqDiningtableExample example);

    SqDiningtable selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SqDiningtable record, @Param("example") SqDiningtableExample example);

    int updateByExample(@Param("record") SqDiningtable record, @Param("example") SqDiningtableExample example);

    int updateByPrimaryKeySelective(SqDiningtable record);

    int updateByPrimaryKey(SqDiningtable record);
}