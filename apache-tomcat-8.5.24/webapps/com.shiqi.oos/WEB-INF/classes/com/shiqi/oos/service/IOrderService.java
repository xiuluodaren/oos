package com.shiqi.oos.service;

import java.util.List;

import com.shiqi.oos.entity.SqOrder;

/**
 * @ClassName IOrderService
 * @Description 
 * @Author 修罗
 * @Date 2018年3月16日 上午10:09:17
 */
public interface IOrderService {

	/**
	 * 确认订单
	 * @param shopCarId 购物车id
	 * @param totalprice 总价
	 * @param isdiscount 是否折扣
	 * @param discount 折扣钱数
	 * @param receivable 应收钱数
	 * @param realPrice 实收钱数
	 * @param change 找零
	 * @return
	 */
	boolean createOrder(String shopCarId, String totalprice, String isdiscount, String discount, String receivable,
			String realPrice, String change);


	/**
	 * 查询订单(分页)
	 * @param page
	 * @param rows
	 * @param orderId
	 * @param username
	 * @param operator
	 * @param createtimestart
	 * @param createtimeend
	 * @return
	 */
	List<SqOrder> findOrderEasyUI(int page, int rows, String orderId, String username, String operator, String createtimestart, String createtimeend);

}

