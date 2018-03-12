package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqDishestype;
import com.shiqi.oos.entity.SqDishestypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqDishestypeMapper {
    int countByExample(SqDishestypeExample example);

    int deleteByExample(SqDishestypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(SqDishestype record);

    int insertSelective(SqDishestype record);

    List<SqDishestype> selectByExample(SqDishestypeExample example);

    SqDishestype selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SqDishestype record, @Param("example") SqDishestypeExample example);

    int updateByExample(@Param("record") SqDishestype record, @Param("example") SqDishestypeExample example);

    int updateByPrimaryKeySelective(SqDishestype record);

    int updateByPrimaryKey(SqDishestype record);
}