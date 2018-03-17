package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqOrder;
import com.shiqi.oos.entity.SqOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqOrderMapper {
    int countByExample(SqOrderExample example);

    int deleteByExample(SqOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(SqOrder record);

    int insertSelective(SqOrder record);

    List<SqOrder> selectByExample(SqOrderExample example);

    SqOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SqOrder record, @Param("example") SqOrderExample example);

    int updateByExample(@Param("record") SqOrder record, @Param("example") SqOrderExample example);

    int updateByPrimaryKeySelective(SqOrder record);

    int updateByPrimaryKey(SqOrder record);
}