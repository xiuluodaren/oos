package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqDishes;
import com.shiqi.oos.entity.SqDishesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqDishesMapper {
    int countByExample(SqDishesExample example);

    int deleteByExample(SqDishesExample example);

    int deleteByPrimaryKey(String id);

    int insert(SqDishes record);

    int insertSelective(SqDishes record);

    List<SqDishes> selectByExample(SqDishesExample example);

    SqDishes selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SqDishes record, @Param("example") SqDishesExample example);

    int updateByExample(@Param("record") SqDishes record, @Param("example") SqDishesExample example);

    int updateByPrimaryKeySelective(SqDishes record);

    int updateByPrimaryKey(SqDishes record);
}