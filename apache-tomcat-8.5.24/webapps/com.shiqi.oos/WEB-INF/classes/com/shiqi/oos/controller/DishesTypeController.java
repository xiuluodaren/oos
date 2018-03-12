package com.shiqi.oos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.shiqi.oos.controller.base.BaseController;
import com.shiqi.oos.entity.SqDishes;
import com.shiqi.oos.entity.SqDishestype;
import com.shiqi.oos.service.IDishesTypeService;
import com.shiqi.oos.utils.AjaxReturn;
import com.shiqi.oos.utils.EasyUIResult;

/**
 * 菜品分类controller
 * @ClassName DishesTypeController
 * @Description 
 * @Author 修罗
 * @Date 2018年1月31日 下午5:40:07
 */
@Controller
@RequestMapping("/dishesType")
public class DishesTypeController extends BaseController {

	@Autowired
	private IDishesTypeService dishesTypeService;
	
	/**
	 * 获取首页展示的菜品分类
	 * @return
	 */
	@RequestMapping("/GetShowHome")
	@ResponseBody
	public List<SqDishestype> GetShowHome() {
		return dishesTypeService.selectShowHome();
	}
	
	/**
	 * 查询全部菜品类型(分页)
	 * @param page
	 * @param rows
	 * @param dishestypename
	 * @param isshowonhome
	 * @return
	 */
	@RequestMapping("/findEasyUI")
	@ResponseBody
	public Map<String, Object> findEasyUI(int page,int rows,String dishestypename,String isshowonhome) {
		Page<SqDishestype> list = (Page<SqDishestype>)dishesTypeService.findEasyUI(page,rows,dishestypename,isshowonhome);
		return EasyUIResult.result(list,list.getTotal());
	}
	
	/**
	 * 添加菜品类型
	 * @param id
	 * @param isshowonhome
	 * @param name
	 * @param zindex
	 * @return
	 */
	@RequestMapping(value = "/addDishesType",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addDishesType(String id, String isshowonhome, String name, String zindex)
	{
		if (dishesTypeService.addDishesType(id,isshowonhome,name,zindex)) {
			return AjaxReturn.ok("添加成功");
		}else {
			return AjaxReturn.failed("添加失败");
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
	@RequestMapping(value = "/updateDishesType",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateDishesType(String id, String isshowonhome, String name, String zindex)
	{
		if (dishesTypeService.updateDishesType(id,isshowonhome,name,zindex)) {
			return AjaxReturn.ok("修改成功");
		}else {
			return AjaxReturn.failed("修改失败");
		}
	}
	
	/**
	 * 根据id删除菜品类型
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public Map<String, String> deleteByIds(String ids) throws Exception {

		boolean success = true;
		try {
			dishesTypeService.delteByIds(ids);
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
	
}
