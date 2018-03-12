package com.shiqi.oos.service.impl;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shiqi.oos.entity.SqDishestype;
import com.shiqi.oos.entity.SqDishestypeExample;
import com.shiqi.oos.entity.SqDishestypeExample.Criteria;
import com.shiqi.oos.mapper.SqDishestypeMapper;
import com.shiqi.oos.service.IDishesTypeService;

/**
 * 菜品分类service
 * @ClassName DishesTypeService
 * @Description 
 * @Author 修罗
 * @Date 2018年1月31日 下午5:42:15
 */
@Service
public class DishesTypeServiceImpl implements IDishesTypeService {

	@Autowired
	private SqDishestypeMapper dishestypeMapper;
	
	/**
	 * 查询首页展示的菜品分类
	 * @return
	 */
	@Override
	public List<SqDishestype> selectShowHome() {
		
		SqDishestypeExample example = new SqDishestypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsshowonhomeEqualTo("1");
		List<SqDishestype> list = dishestypeMapper.selectByExample(example );
		
		//按zindex升序排列
		list.sort(Comparator.naturalOrder());
		return list;
	}

	/**
	 * 查询全部菜品类型
	 * @return
	 */
	public List<SqDishestype> findAll()
	{
		SqDishestypeExample example = new SqDishestypeExample();
		List<SqDishestype> list = dishestypeMapper.selectByExample(example);
		
		//按zindex升序排列
		list.sort(Comparator.naturalOrder());
		
		return list;
	}
	
	/**
	 * 查询全部菜品类型(分页)
	 * @param page
	 * @param rows
	 * @param isshowonhome 
	 * @param dishestypename 
	 * @return
	 */
	public List<SqDishestype> findEasyUI(int page, int rows, String dishestypename, String isshowonhome)
	{
		SqDishestypeExample example = new SqDishestypeExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(dishestypename)) {
			criteria.andNameLike("%" + dishestypename + "%");
		}
		
		if (StringUtils.isNotBlank(isshowonhome)) {
			criteria.andIsshowonhomeEqualTo(isshowonhome);
		}
		
		PageHelper.startPage(page, rows);
		List<SqDishestype> list = dishestypeMapper.selectByExample(example);
		
		return list;
	}
	
	/**
	 * 添加菜品类型
	 * @param id
	 * @param isshowonhome
	 * @param name
	 * @param zindex
	 * @return
	 */
	public boolean addDishesType(String id, String isshowonhome, String name, String zindex)
	{
		SqDishestype type = new SqDishestype();
		
		if (StringUtils.isNotBlank(id)) {
			type.setId(id);
		}else {
			return false;
		}
		
		if (StringUtils.isNotBlank(isshowonhome)) {
			type.setIsshowonhome(isshowonhome);
		}
		
		if (StringUtils.isNotBlank(name)) {
			type.setName(name);
		}
		
		if (StringUtils.isNotBlank(zindex)) {
			type.setZindex(Integer.valueOf(zindex));
		}
		
		if (dishestypeMapper.insert(type) > 0)
		{
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * 修改菜品类型
	 * @param id
	 * @param isshowonhome
	 * @param name
	 * @param zindex
	 * @return
	 */
	public boolean updateDishesType(String id, String isshowonhome, String name, String zindex)
	{
		SqDishestype dishestype = dishestypeMapper.selectByPrimaryKey(id);
	
		if (dishestype == null) {
			return false;
		}
		
		if (StringUtils.isNotBlank(isshowonhome)) {
			dishestype.setIsshowonhome(isshowonhome);
		}
		
		if (StringUtils.isNotBlank(name)) {
			dishestype.setName(name);
		}
		
		if (StringUtils.isNotBlank(zindex)) {
			dishestype.setZindex(Integer.valueOf(zindex));
		}
		
		if (dishestypeMapper.updateByPrimaryKey(dishestype) > 0)
		{
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * 根据id批量删除菜品类型
	 * @param ids
	 */
	public boolean delteByIds(String ids)
	{
		String[] idArray = ids.split(",");
		
		for (String id : idArray) {
			if (dishestypeMapper.deleteByPrimaryKey(id) <= 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
}
