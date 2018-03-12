package com.shiqi.oos.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Data;
import com.shiqi.oos.entity.SqDiningtable;
import com.shiqi.oos.entity.SqDishes;
import com.shiqi.oos.entity.SqShoppingcar;
import com.shiqi.oos.entity.SqShoppingcarExample;
import com.shiqi.oos.entity.SqShoppingcarExample.Criteria;
import com.shiqi.oos.entity.SqShoppingcardetail;
import com.shiqi.oos.entity.SqShoppingcardetailExample;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.mapper.SqDiningtableMapper;
import com.shiqi.oos.mapper.SqDishesMapper;
import com.shiqi.oos.mapper.SqShoppingcarMapper;
import com.shiqi.oos.mapper.SqShoppingcardetailMapper;
import com.shiqi.oos.mapper.SqUserMapper;
import com.shiqi.oos.service.IShoppingCarService;
import com.shiqi.oos.utils.IDUtils;

/**
 * 购物车serviceImpl
 * @ClassName IShoppingCarService
 * @Description 
 * @Author 修罗
 * @Date 2018年2月2日 上午11:27:32
 */
@Service
public class ShoppingCarServiceImpl implements IShoppingCarService {

	@Autowired
	private SqShoppingcarMapper shoppingCarMapper;
	
	@Autowired
	private SqShoppingcardetailMapper shoppingcardetailMapper;
	
	@Autowired
	private SqUserMapper userMapper;
	
	@Autowired
	private SqDiningtableMapper diningtableMapper;
	
	/**
	 * 根据用户id查询购物车详情
	 * @param userId
	 * @return
	 */
	@Override
	public SqShoppingcar selectDetailByUserId(String userId) {
		
		SqShoppingcar car = shoppingCarMapper.selectDetailByUserId(userId);
		
		SqUser user = userMapper.selectByPrimaryKey(userId);
		
		this.totalPrice(car, user.getIsvip().equals("1") ? true : false);
		return car;
	}

	/**
	 * 添加到购物车
	 * @param shopId
	 * @param userId
	 * @return
	 */
	 public boolean addToShopCar(String shopId,String userId) {
	
		 SqShoppingcar car = shoppingCarMapper.selectDetailByUserId(userId);
		 
		 //查找购物车
		 if (car != null) {
			 //购物车存在，直接添加
			for (SqShoppingcardetail detail : car.getShoppingcardetails()) {
				if (detail.getShopid().equals(shopId)) {
					
					detail.setUpdatetime(new Date());
					detail.setShopnum(detail.getShopnum() + 1);
					//detail.setStatus("0");
					if (shoppingcardetailMapper.updateByPrimaryKey(detail) > 0)
					{
						return true;
					}else {
						return false;
					}
				}
			}
			 
			//商品不存在
			 SqShoppingcardetail detail = new SqShoppingcardetail();
			 detail.setCreatetime(new Date());
			 detail.setUpdatetime(new Date());
			 detail.setId(Long.toString(IDUtils.genItemId()));
			 detail.setShopid(shopId);
			 detail.setShoppingcarid(car.getId());
			 detail.setShopnum(1);
			 detail.setStatus("0");
			 
			 if (car.getShoppingcardetails() == null) {
				car.setShoppingcardetails(new ArrayList<SqShoppingcardetail>());
			}
			 
			 car.getShoppingcardetails().add(detail);
			
			//保存商品
			 if (shoppingcardetailMapper.insert(detail) > 0)
			{
				return true;
			}else {
				return false;
			}
			
		}else {
			//购物车不存在，先创建购物车
			car = new SqShoppingcar();
			car.setCreatetime(new Date());
			car.setUpdatetime(new Date());
			car.setId(Long.toString(IDUtils.genItemId()));
			car.setUserid(userId);
			
			//创建商品
			 SqShoppingcardetail detail = new SqShoppingcardetail();
			 detail.setCreatetime(new Date());
			 detail.setUpdatetime(new Date());
			 detail.setId(Long.toString(IDUtils.genItemId()));
			 detail.setShopid(shopId);
			 detail.setShoppingcarid(car.getId());
			 detail.setShopnum(1);
			 detail.setStatus("0");
			 
			 List<SqShoppingcardetail> list = new ArrayList<SqShoppingcardetail>();
			 list.add(detail);
			 car.setShoppingcardetails(list);
				
			//保存购物车
			if (shoppingCarMapper.insert(car) > 0) {
				//保存商品
				if (shoppingcardetailMapper.insert(detail) > 0)
				{
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}

		}
		 
	}

	 /**
	  * 重新计算购物车总价，商品数量
	  * @param car
	  */
	 private void totalPrice(SqShoppingcar car,boolean isVIP)
	 {
		 if (car == null) return;
		 
		 Integer totalPrice = 0;
		 Integer count = 0;
		 
		for (SqShoppingcardetail detali : car.getShoppingcardetails()) {
			
			if (isVIP && detali.getDishes().getIsvip().equals("1")) {
				//只有用户是会员并且菜品打折时算特价
				detali.setXiaoji(Integer.valueOf(detali.getDishes().getPrice()) * detali.getShopnum());
				totalPrice += Integer.valueOf(detali.getDishes().getPrice()) * detali.getShopnum(); 
			}else {
				detali.setXiaoji(Integer.valueOf(detali.getDishes().getOriginalprice()) * detali.getShopnum());
				totalPrice += Integer.valueOf(detali.getDishes().getOriginalprice()) * detali.getShopnum(); 
			}
			
			count += detali.getShopnum();
			shoppingcardetailMapper.updateByPrimaryKey(detali);
		}
		 
		car.setTotalpirce(totalPrice);
		car.setShopnum(count);
		shoppingCarMapper.updateByPrimaryKey(car);
		
	 }

	 /**
	 * 从购物车移除
	 * @param detailId
	 * @return
	 */
	@Override
	public boolean removeDetail(String detailId) {
		
		//判断状态
		SqShoppingcardetail shoppingcardetail = shoppingcardetailMapper.selectByPrimaryKey(detailId);
		//0未准备 1配菜中 2待上菜 3已上菜
		if (shoppingcardetail.getStatus().equals("0")) {
			if (shoppingcardetailMapper.deleteByPrimaryKey(detailId) > 0) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 确定点餐
	 * @param zhuohao
	 * @param userId
	 * @return
	 */
	@Override
	public boolean confirmOrder(String zhuohao, String userId) {
		
		SqDiningtable diningtable = diningtableMapper.selectByPrimaryKey(zhuohao);
		if (diningtable == null) {
			return false;
		}
		
		SqShoppingcar car = shoppingCarMapper.selectDetailByUserId(userId);
		car.setDiningtableid(zhuohao);
		diningtable.setShopcarid(car.getId());
		diningtable.setStatus("1");

		shoppingCarMapper.updateByPrimaryKey(car);
		diningtableMapper.updateByPrimaryKey(diningtable);
		
		return true;
	}
	 
}
