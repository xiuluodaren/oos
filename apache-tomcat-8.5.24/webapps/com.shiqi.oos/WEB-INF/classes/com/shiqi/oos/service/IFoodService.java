package com.shiqi.oos.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.shiqi.oos.entity.SqShoppingcar;

/**
 * @ClassName IFoodService
 * @Description 
 * @Author 修罗
 * @Date 2018年3月14日 下午1:32:42
 */
public interface IFoodService {

	/**
	 * 查询含有未准备状态的购物车
	 * @param page
	 * @param rows
	 * @return
	 */
	List<SqShoppingcar> findEasyUI(int page, int rows);

	/**
	 * 根据购物车详情id配餐
	 * @param id
	 * @return
	 */
	boolean foodByCarDetailId(String id);

}

