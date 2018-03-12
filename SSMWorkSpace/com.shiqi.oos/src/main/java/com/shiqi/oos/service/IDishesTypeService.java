package com.shiqi.oos.service;

import java.util.List;
import com.shiqi.oos.entity.SqDishestype;

/**
 * @ClassName IDishesTypeService
 * @Description 
 * @Author 修罗
 * @Date 2018年1月31日 下午5:41:49
 */
public interface IDishesTypeService {

	/**
	 * 查询首页展示的菜品分类
	 * @return
	 */
	List<SqDishestype> selectShowHome();

	/**
	 * 查询全部菜品类型
	 * @return
	 */
	List<SqDishestype> findAll();

	/**
	 * 查询全部菜品类型(分页)
	 * @param page
	 * @param rows
	 * @param isshowonhome 
	 * @param dishestypename 
	 * @return
	 */
	List<SqDishestype> findEasyUI(int page, int rows, String dishestypename, String isshowonhome);

	/**
	 * 添加菜品类型
	 * @param id
	 * @param isshowonhome
	 * @param name
	 * @param zindex
	 * @return
	 */
	boolean addDishesType(String id, String isshowonhome, String name, String zindex);

	/**
	 * 修改菜品类型
	 * @param id
	 * @param isshowonhome
	 * @param name
	 * @param zindex
	 * @return
	 */
	boolean updateDishesType(String id, String isshowonhome, String name, String zindex);

	/**
	 * 根据id批量删除菜品类型
	 * @param ids
	 */
	boolean delteByIds(String ids);

}
