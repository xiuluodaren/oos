package com.shiqi.oos.mapper;

import com.shiqi.oos.entity.SqShoppingcardetail;
import com.shiqi.oos.entity.SqShoppingcardetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SqShoppingcardetailMapper {
    int countByExample(SqShoppingcardetailExample example);

    int deleteByExample(SqShoppingcardetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(SqShoppingcardetail record);

    int insertSelective(SqShoppingcardetail record);

    List<SqShoppingcardetail> selectByExample(SqShoppingcardetailExample example);

    SqShoppingcardetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SqShoppingcardetail record, @Param("example") SqShoppingcardetailExample example);

    int updateByExample(@Param("record") SqShoppingcardetail record, @Param("example") SqShoppingcardetailExample example);

    int updateByPrimaryKeySelective(SqShoppingcardetail record);

    int updateByPrimaryKey(SqShoppingcardetail record);
    
    List<SqShoppingcardetail> selectNotFoodByCarId(String carId,String status);
    
}