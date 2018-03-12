package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqRoleMenuExample;
import com.shiqi.oos.entity.SqRoleMenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqRoleMenuMapper {
    int countByExample(SqRoleMenuExample example);

    int deleteByExample(SqRoleMenuExample example);

    int deleteByPrimaryKey(SqRoleMenuKey key);

    int insert(SqRoleMenuKey record);

    int insertSelective(SqRoleMenuKey record);

    List<SqRoleMenuKey> selectByExample(SqRoleMenuExample example);

    int updateByExampleSelective(@Param("record") SqRoleMenuKey record, @Param("example") SqRoleMenuExample example);

    int updateByExample(@Param("record") SqRoleMenuKey record, @Param("example") SqRoleMenuExample example);
}