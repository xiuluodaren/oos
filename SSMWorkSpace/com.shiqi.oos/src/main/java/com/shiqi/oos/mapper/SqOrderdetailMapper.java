package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqOrderdetail;
import com.shiqi.oos.entity.SqOrderdetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqOrderdetailMapper {
    int countByExample(SqOrderdetailExample example);

    int deleteByExample(SqOrderdetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(SqOrderdetail record);

    int insertSelective(SqOrderdetail record);

    List<SqOrderdetail> selectByExample(SqOrderdetailExample example);

    SqOrderdetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SqOrderdetail record, @Param("example") SqOrderdetailExample example);

    int updateByExample(@Param("record") SqOrderdetail record, @Param("example") SqOrderdetailExample example);

    int updateByPrimaryKeySelective(SqOrderdetail record);

    int updateByPrimaryKey(SqOrderdetail record);
}