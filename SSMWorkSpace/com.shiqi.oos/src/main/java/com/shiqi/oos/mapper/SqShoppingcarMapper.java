package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqShoppingcar;
import com.shiqi.oos.entity.SqShoppingcarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqShoppingcarMapper {
    int countByExample(SqShoppingcarExample example);

    int deleteByExample(SqShoppingcarExample example);

    int deleteByPrimaryKey(String id);

    int insert(SqShoppingcar record);

    int insertSelective(SqShoppingcar record);

    List<SqShoppingcar> selectByExample(SqShoppingcarExample example);

    SqShoppingcar selectDetailByUserId(String id);
    
    SqShoppingcar selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SqShoppingcar record, @Param("example") SqShoppingcarExample example);

    int updateByExample(@Param("record") SqShoppingcar record, @Param("example") SqShoppingcarExample example);

    int updateByPrimaryKeySelective(SqShoppingcar record);

    int updateByPrimaryKey(SqShoppingcar record);
}