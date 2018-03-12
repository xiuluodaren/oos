package com.shiqi.oos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.shiqi.oos.controller.base.BaseController;
import com.shiqi.oos.entity.SqDishes;
import com.shiqi.oos.entity.SqDishestype;
import com.shiqi.oos.service.IDishesService;
import com.shiqi.oos.service.IDishesTypeService;
import com.shiqi.oos.utils.AjaxReturn;
import com.shiqi.oos.utils.EasyUIResult;

/**
 * 
 * @ClassName DishesController
 * @Description 
 * @Author 修罗
 * @Date 2018年1月31日 下午8:51:37
 */
@Controller
@RequestMapping("/dishes")
public class DishesController extends BaseController {

	@Autowired
	private IDishesService dishesService;
	
	@Autowired
	private IDishesTypeService dishesTypeService;
	
	/**
	 * 根据类型id获取菜品
	 * @param typeId
	 * @param model
	 * @return
	 */
	@RequestMapping("/{typeId}")
	public String selectByTypeId(@PathVariable String typeId,Model model) {
		List<SqDishes> list = dishesService.selectByTypeId(typeId);
		model.addAttribute("dishesList", list);
		return "/baseFunction/Dishes";
	}
	
	/**
	 * 查询全部餐桌(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/findEasyUI")
	@ResponseBody
	public Map<String, Object> findEasyUI(int page,int rows,String dishesType,String name) {
		Page<SqDishes> list = (Page<SqDishes>)dishesService.findEasyUI(page,rows,dishesType,name);
		return EasyUIResult.result(list,list.getTotal());
	}
	
	/**
	 * 获取页面
	 * @return
	 */
	@RequestMapping("/getUI")
	public String getUI(Model model) {
		
		List<SqDishestype> list = dishesTypeService.findAll();
		
		model.addAttribute("list",list);
		
		return "admin/dishesAdmin";
	}
	
	/**
	 * 根据id删除菜品
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public Map<String, String> deleteByIds(String ids) throws Exception {

		boolean success = true;
		try {
			dishesService.delteByIds(ids);
		} catch (Exception e) {
			success = false;
			throw e;
		}finally {
			if (success) {
				return AjaxReturn.ok("删除成功");
			}else {
				return AjaxReturn.failed("删除失败");
			}
		}
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
	@RequestMapping("/addDishes")
	@ResponseBody
	public Map<String, String> addDishes(String id,String isvip,String name,String originalprice,String picUrl,String price,String dishestype) {
		if (dishesService.addDishes(id,isvip,name,originalprice,picUrl,price,dishestype))
		{
			return AjaxReturn.ok("添加成功");
		}else {
			return AjaxReturn.failed("添加失败");
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
	@RequestMapping("/editDisheById")
	@ResponseBody
	public Map<String, String> editDisheById(String id,String isvip,String name,String originalprice,String pic,String price,String dishestype) {
		if (dishesService.editDisheById(id,isvip,name,originalprice,pic,price,dishestype))
		{
			return AjaxReturn.ok("修改成功");
		}else {
			return AjaxReturn.failed("修改失败");
		}
	}
	
}
