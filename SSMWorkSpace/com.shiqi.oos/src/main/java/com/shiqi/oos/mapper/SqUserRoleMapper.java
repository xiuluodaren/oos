package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqUserRoleExample;
import com.shiqi.oos.entity.SqUserRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqUserRoleMapper {
    int countByExample(SqUserRoleExample example);

    int deleteByExample(SqUserRoleExample example);

    int deleteByPrimaryKey(SqUserRoleKey key);

    int insert(SqUserRoleKey record);

    int insertSelective(SqUserRoleKey record);

    List<SqUserRoleKey> selectByExample(SqUserRoleExample example);

    int updateByExampleSelective(@Param("record") SqUserRoleKey record, @Param("example") SqUserRoleExample example);

    int updateByExample(@Param("record") SqUserRoleKey record, @Param("example") SqUserRoleExample example);
}