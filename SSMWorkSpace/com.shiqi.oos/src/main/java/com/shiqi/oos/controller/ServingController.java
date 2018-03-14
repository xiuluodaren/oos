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
import com.shiqi.oos.service.IServingService;
import com.shiqi.oos.utils.AjaxReturn;
import com.shiqi.oos.utils.EasyUIResult;

/**
 * 上菜controller
 * @ClassName ServingController
 * @Description 
 * @Author 修罗
 * @Date 2018年3月14日 下午1:30:02
 */
@Controller
@RequestMapping("/serving")
public class ServingController extends BaseController {

	@Autowired
	private IServingService servingService;
	
	/**
	 * 查询含有待上菜状态的购物车
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/findEasyUI")
	@ResponseBody
	public Map<String, Object> findEasyUI(int page,int rows) {
		List<SqShoppingcar> list = servingService.findEasyUI(page,rows);
		
		int total = list.size();
		
		int startIndex = rows * (page - 1);
		int endIndex = startIndex + rows;
		
		endIndex = endIndex > total ? total : endIndex;
		List<SqShoppingcar> resultList = list.subList(startIndex, endIndex);
		
		return EasyUIResult.result(resultList,total);
	}
	
	/**
	 * 根据购物车详情id上菜
	 * @param id
	 * @return
	 */
	@RequestMapping("/servingByCarDetailId")
	@ResponseBody
	public Map<String, String> servingByCarDetailId(String id) {
		boolean success = servingService.servingByCarDetailId(id);
		
		if (success) {
			return AjaxReturn.ok("上菜成功");
		}else {
			return AjaxReturn.failed("失败失败");
		}
		
	}
	
}

