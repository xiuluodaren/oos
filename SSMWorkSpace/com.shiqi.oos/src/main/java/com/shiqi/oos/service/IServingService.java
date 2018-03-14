package com.shiqi.oos.service;

import java.util.List;

import com.shiqi.oos.entity.SqShoppingcar;


/**
 * @ClassName IServingService
 * @Description 
 * @Author 修罗
 * @Date 2018年3月14日 下午5:45:18
 */
public interface IServingService {

	/**
	 * 查询含有待上菜状态的购物车
	 * @param page
	 * @param rows
	 * @return
	 */
	List<SqShoppingcar> findEasyUI(int page, int rows);

	/**
	 * 根据购物车详情id上菜
	 * @param id
	 * @return
	 */
	boolean servingByCarDetailId(String id);

}

