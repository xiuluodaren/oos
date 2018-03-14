package com.shiqi.oos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiqi.oos.entity.SqShoppingcar;
import com.shiqi.oos.entity.SqShoppingcarExample;
import com.shiqi.oos.entity.SqShoppingcardetail;
import com.shiqi.oos.mapper.SqShoppingcarMapper;
import com.shiqi.oos.mapper.SqShoppingcardetailMapper;
import com.shiqi.oos.service.IFoodService;

/**
 * @ClassName FoodService
 * @Description 
 * @Author 修罗
 * @Date 2018年3月14日 下午1:33:06
 */
@Service
public class FoodServiceImpl implements IFoodService {

	@Autowired
	private SqShoppingcarMapper carMapper;
	
	@Autowired
	private SqShoppingcardetailMapper detailMapper;
	
	/**
	 * 查询含有未准备状态的购物车
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SqShoppingcar> findEasyUI(int page, int rows)
	{
		SqShoppingcarExample example = new SqShoppingcarExample();
		List<SqShoppingcar> list = carMapper.selectByExample(example);
		
		for (int i = 0;i < list.size();i++) {
			SqShoppingcar car = list.get(i);
			
			List<SqShoppingcardetail> detailList = detailMapper.selectNotFoodByCarId(car.getId(),"0");
			if (detailList == null || detailList.size() == 0) {
				list.remove(car);
				i--;
			}else {
				car.setShoppingcardetails(detailList);
			}
		}
		
		return list;
		
	}
	
	/**
	 * 根据购物车id配餐
	 * @param id
	 * @return
	 */
	public boolean foodByCarDetailId(String id)
	{
		SqShoppingcardetail detail = detailMapper.selectByPrimaryKey(id);
		
		if (detail == null) {
			return false;
		}
		
		//0未准备 1配菜中 2待上菜 3已上菜
		detail.setStatus("2");
		
		if (detailMapper.updateByPrimaryKey(detail) > 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
}

