package com.shiqi.oos.service;

import java.util.List;
import com.shiqi.oos.entity.SqDishes;

public interface IDishesService {

	/**
	 * 根据分类id查询菜品
	 * @param typeId
	 * @return
	 */
	List<SqDishes> selectByTypeId(String typeId);

	/**
	 * 查询全部菜品(分页)
	 * @param page
	 * @param rows
	 * @param name 
	 * @param dishesType 
	 * @return
	 */
	List<SqDishes> findEasyUI(int page, int rows, String dishesType, String name);

	/**
	 * 根据id批量删除
	 * @param ids
	 * @return
	 */
	boolean delteByIds(String ids);

	/**
	 * 添加菜品
	 * @param id
	 * @param isvip
	 * @param name
	 * @param originalPrice
	 * @param picUrl
	 * @param price
	 * @param dishesType
	 * @return
	 */
	boolean addDishes(String id, String isvip, String name, String originalPrice, String picUrl, String price,
			String dishesType);

	/**
	 * 修改菜品
	 * @param id
	 * @param isvip
	 * @param name
	 * @param originalPrice
	 * @param picUrl
	 * @param price
	 * @param dishesType
	 * @return
	 */
	boolean editDisheById(String id, String isvip, String name, String originalprice, String picUrl, String price,
			String dishestype);

}
