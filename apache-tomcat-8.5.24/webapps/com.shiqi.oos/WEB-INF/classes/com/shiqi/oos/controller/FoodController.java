package com.shiqi.oos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiqi.oos.controller.base.BaseController;
import com.shiqi.oos.entity.SqShoppingcar;
import com.shiqi.oos.entity.SqShoppingcardetail;
import com.shiqi.oos.service.IFoodService;
import com.shiqi.oos.utils.AjaxReturn;
import com.shiqi.oos.utils.EasyUIResult;

/**
 * 配餐controller
 * @ClassName FondController
 * @Description 
 * @Author 修罗
 * @Date 2018年3月14日 下午1:30:02
 */
@Controller
@RequestMapping("/food")
public class FoodController extends BaseController {

	@Autowired
	private IFoodService foodService;
	
	/**
	 * 查询含有未准备状态的购物车
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/findEasyUI")
	@ResponseBody
	public Map<String, Object> findEasyUI(int page,int rows) {
		List<SqShoppingcar> list = foodService.findEasyUI(page,rows);
		
		int total = list.size();
		
		int startIndex = rows * (page - 1);
		int endIndex = startIndex + rows;
		
		endIndex = endIndex > total ? total : endIndex;
		List<SqShoppingcar> resultList = list.subList(startIndex, endIndex);
		
		return EasyUIResult.result(resultList,total);
	}
	
	/**
	 * 根据购物车详情id配餐
	 * @param id
	 * @return
	 */
	@RequestMapping("/foodByCarDetailId")
	@ResponseBody
	public Map<String, String> foodByCarDetailId(String id) {
		boolean success = foodService.foodByCarDetailId(id);
		
		if (success) {
			return AjaxReturn.ok("配餐成功");
		}else {
			return AjaxReturn.failed("配餐失败");
		}
		
	}
	
}

