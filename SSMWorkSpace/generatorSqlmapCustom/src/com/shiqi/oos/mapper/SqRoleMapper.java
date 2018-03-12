package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqRole;
import com.shiqi.oos.entity.SqRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqRoleMapper {
    int countByExample(SqRoleExample example);

    int deleteByExample(SqRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(SqRole record);

    int insertSelective(SqRole record);

    List<SqRole> selectByExample(SqRoleExample example);

    SqRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SqRole record, @Param("example") SqRoleExample example);

    int updateByExample(@Param("record") SqRole record, @Param("example") SqRoleExample example);

    int updateByPrimaryKeySelective(SqRole record);

    int updateByPrimaryKey(SqRole record);
}