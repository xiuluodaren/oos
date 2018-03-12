package com.shiqi.oos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shiqi.oos.entity.SqDishes;
import com.shiqi.oos.entity.SqDishesExample;
import com.shiqi.oos.entity.SqDishesExample.Criteria;
import com.shiqi.oos.mapper.SqDishesMapper;
import com.shiqi.oos.mapper.SqDishestypeMapper;
import com.shiqi.oos.service.IDishesService;

/**
 * 菜品service
 * @ClassName DishesServiceImpl
 * @Description 
 * @Author 修罗
 * @Date 2018年1月31日 下午9:00:09
 */
@Service
public class DishesServiceImpl implements IDishesService {

	@Autowired
	private SqDishesMapper dishesMapper;
	
	@Autowired
	private SqDishestypeMapper dishestypeMapper;
	
	/**
	 * 根据分类id查询菜品
	 * @param typeId
	 * @return
	 */
	@Override
	public List<SqDishes> selectByTypeId(String typeId) {
		SqDishesExample example = new SqDishesExample();
		Criteria criteria = example.createCriteria();
		criteria.andTypeidEqualTo(typeId);
		List<SqDishes> list = dishesMapper.selectByExample(example);
		return list;
	}

	/**
	 * 查询全部菜品(分页)
	 * @param page
	 * @param rows
	 * @param name 
	 * @param dishesType 
	 * @return
	 */
	public List<SqDishes> findEasyUI(int page, int rows, String dishesType, String name)
	{
		SqDishesExample example = new SqDishesExample();
		
		Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(dishesType)) {
			criteria.andTypeidEqualTo(dishesType);
		}
		
		if (StringUtils.isNotBlank(name)) {
			criteria.andNameLike("%" + name + "%");
		}
		
		PageHelper.startPage(page, rows);
		List<SqDishes> list = dishesMapper.selectByExample(example);
		
		if (list != null) {
			for (SqDishes sqDishes : list) {
				sqDishes.setDishestype(dishestypeMapper.selectByPrimaryKey(sqDishes.getTypeid()));
			}
		}
		
		return list;
	}

	/**
	 * 根据id批量删除
	 * @param ids
	 * @return
	 */
	public boolean delteByIds(String ids)
	{
		String[] idArray = ids.split(",");
		
		for (String id : idArray) {
			if (dishesMapper.deleteByPrimaryKey(id) <= 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
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
	@SuppressWarnings("finally")
	public boolean addDishes(String id, String isvip, String name, String originalPrice, String picUrl, String price,
			String dishesType)
	{
		boolean success = true;
		
		try {
			SqDishes dishes = new SqDishes();
			if (StringUtils.isNotBlank(id)) {
				dishes.setId(id);
			}
			
			if (StringUtils.isNotBlank(isvip)) {
				dishes.setIsvip(isvip);
			}
			
			if (StringUtils.isNotBlank(name)) {
				dishes.setName(name);
			}
			
			if (StringUtils.isNotBlank(originalPrice)) {
				dishes.setOriginalprice(originalPrice);
			}
			
			if (StringUtils.isNotBlank(picUrl)) {
				dishes.setPic(picUrl);
			}
			
			if (StringUtils.isNotBlank(price)) {
				dishes.setPrice(price);
			}
			
			if (StringUtils.isNotBlank(dishesType)) {
				dishes.setTypeid(dishesType);
			}
			
			if (dishesMapper.insert(dishes) > 0)
			{
				success = true;
			}
			
		} catch (Exception e) {
			success = false;
			throw e;
		}finally {
			return success;
		}
		
	}
	
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
	@SuppressWarnings("finally")
	public boolean editDisheById(String id, String isvip, String name, String originalprice, String picUrl, String price,
			String dishestype)
	{
		boolean success = true;
		
		try {
			if (StringUtils.isBlank(id)) {
				return false;
			}
			
			SqDishes dishes = dishesMapper.selectByPrimaryKey(id);
			
			if (StringUtils.isNotBlank(isvip)) {
				dishes.setIsvip(isvip);
			}
			
			if (StringUtils.isNotBlank(name)) {
				dishes.setName(name);
			}
			
			if (StringUtils.isNotBlank(originalprice)) {
				dishes.setOriginalprice(originalprice);
			}
			
			if (StringUtils.isNotBlank(picUrl)) {
				dishes.setPic(picUrl);
			}
			
			if (StringUtils.isNotBlank(price)) {
				dishes.setPrice(price);
			}
			
			if (StringUtils.isNotBlank(dishestype)) {
				dishes.setTypeid(dishestype);
			}
			
			if (dishesMapper.updateByPrimaryKey(dishes)> 0)
			{
				success = true;
			}
			
		} catch (Exception e) {
			success = false;
			throw e;
		}finally {
			return success;
		}
		
	}
	
}
