package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqRole;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.entity.SqUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqUserMapper {
    int countByExample(SqUserExample example);

    int deleteByExample(SqUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SqUser record);

    int insertSelective(SqUser record);

    List<SqUser> selectByExample(SqUserExample example);

    SqUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SqUser record, @Param("example") SqUserExample example);

    int updateByExample(@Param("record") SqUser record, @Param("example") SqUserExample example);

    int updateByPrimaryKeySelective(SqUser record);

    int updateByPrimaryKey(SqUser record);
    
    List<SqUser> selectByRoleId(String roleId);
    
}