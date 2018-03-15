package com.shiqi.oos.controller;
/**
 * 收银
 * @ClassName CashierController
 * @Description 
 * @Author 修罗
 * @Date 2018年3月14日 下午6:33:15
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.shiqi.oos.entity.SqShoppingcar;
import com.shiqi.oos.service.IShoppingCarService;
import com.shiqi.oos.utils.EasyUIResult;

@Controller
@RequestMapping("/cashier")
public class CashierController {

	@Autowired
	private IShoppingCarService carService;
	
	/**
	 * 查询全部购物车
	 * @param page
	 * @param rows
	 * @param diningTableId
	 * @param username
	 * @return
	 */
	@RequestMapping("/findEasyUI")
	@ResponseBody
	public Map<String, Object> findEasyUI(int page,int rows,String diningTableId,String username) {
		List<SqShoppingcar> list = carService.findEasyUI(diningTableId,username);
		
		int total = list.size();
		int startIndex = rows * (page - 1);
		int endIndex = startIndex + rows;
		
		endIndex = endIndex > total ? total : endIndex;
		
		return EasyUIResult.result(list.subList(startIndex, endIndex), total);
	}
	
	/**
	 * 
	 * @param shopCarId 购物车id
	 * @param totalprice 总价
	 * @param isdiscount 是否折扣
	 * @param discount 折扣钱数
	 * @param receivable 应收钱数
	 * @param realPrice 实收钱数
	 * @param change 找零
	 * @return
	 */
	@RequestMapping("/confirmCashier")
	@ResponseBody
	public Map<String, String> confirmCashier(String shopCarId, String totalprice, String isdiscount, String discount, String receivable, String realPrice, String change) {
		
	}
	
}

