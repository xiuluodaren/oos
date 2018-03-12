package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqMenu;
import com.shiqi.oos.entity.SqMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqMenuMapper {
    int countByExample(SqMenuExample example);

    int deleteByExample(SqMenuExample example);

    int deleteByPrimaryKey(String id);

    int insert(SqMenu record);

    int insertSelective(SqMenu record);

    List<SqMenu> selectByExample(SqMenuExample example);

    SqMenu selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SqMenu record, @Param("example") SqMenuExample example);

    int updateByExample(@Param("record") SqMenu record, @Param("example") SqMenuExample example);

    int updateByPrimaryKeySelective(SqMenu record);

    int updateByPrimaryKey(SqMenu record);
    
//    List<SqMenu> queryMenuTreeList();
//    
//    List<SqMenu> selectMenuChildrenById(String id);
    
    List<SqMenu> selectByRoleId(String roleId);

	List<SqMenu> findMenuByUserId(String userId);

}