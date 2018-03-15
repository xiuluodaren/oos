package com.shiqi.oos.service;

import java.util.List;

import com.shiqi.oos.entity.SqShoppingcar;
import com.shiqi.oos.entity.SqShoppingcardetail;

/**
 * 购物车service
 * @ClassName IShoppingCarService
 * @Description 
 * @Author 修罗
 * @Date 2018年2月2日 上午11:27:32
 */
public interface IShoppingCarService {

	/**
	 * 根据用户id查询购物车
	 * @param userId
	 * @return
	 */
	SqShoppingcar selectDetailByUserId(String userId);

	/**
	 * 添加到购物车
	 * @param shopId
	 * @param userId
	 * @return
	 */
	boolean addToShopCar(String shopId,String userId);

	/**
	 * 从购物车移除
	 * @param detailId
	 * @return
	 */
	boolean removeDetail(String detailId);

	/**
	 * 确定点餐
	 * @param zhuohao
	 * @param userId
	 * @return
	 */
	boolean confirmOrder(String zhuohao, String id);

	/**
	 * 查询全部购物车
	 * @param diningTableId
	 * @param username
	 * @return
	 */
	List<SqShoppingcar> findEasyUI(String diningTableId, String username);
	
}
