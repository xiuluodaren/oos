package com.shiqi.oos.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shiqi.oos.entity.SqDiningtable;
import com.shiqi.oos.entity.SqDiningtableExample;
import com.shiqi.oos.entity.SqDiningtableExample.Criteria;
import com.shiqi.oos.entity.SqOrder;
import com.shiqi.oos.entity.SqOrderExample;
import com.shiqi.oos.entity.SqOrderdetail;
import com.shiqi.oos.entity.SqShoppingcar;
import com.shiqi.oos.entity.SqShoppingcardetail;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.entity.SqUserExample;
import com.shiqi.oos.mapper.SqDiningtableMapper;
import com.shiqi.oos.mapper.SqOrderMapper;
import com.shiqi.oos.mapper.SqOrderdetailMapper;
import com.shiqi.oos.mapper.SqShoppingcarMapper;
import com.shiqi.oos.mapper.SqShoppingcardetailMapper;
import com.shiqi.oos.mapper.SqUserMapper;
import com.shiqi.oos.service.IOrderService;
import com.shiqi.oos.utils.IDUtils;

/**
 * @ClassName OrderServiceImpl
 * @Description 
 * @Author 修罗
 * @Date 2018年3月16日 上午10:15:33
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private SqOrderMapper orderMapper;
	
	@Autowired
	private SqOrderdetailMapper orderDetailMapper;
	
	@Autowired
	private SqShoppingcarMapper carMapper;
	
	@Autowired
	private SqShoppingcardetailMapper carDetailMapper;
	
	@Autowired
	private SqDiningtableMapper diningtableMapper;
	
	@Autowired
	private SqUserMapper userMapper;
	
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
	public boolean createOrder(String shopCarId, String totalprice, String isdiscount, String discount, String receivable,
			String realPrice, String change)
	{
		if (StringUtils.isBlank(shopCarId) 
			||  StringUtils.isBlank(totalprice)
			||  StringUtils.isBlank(isdiscount)
			||  StringUtils.isBlank(receivable)
			||  StringUtils.isBlank(realPrice)
			||  StringUtils.isBlank(change)
			) {
			return false;
		}
		
		boolean success = true;
		
		try {
			//构造订单
			SqOrder order = new SqOrder();
			order.setId(String.valueOf(IDUtils.genItemId()));
			order.setCreatetime(new Date());
			order.setUpdatetime(new Date());
			order.setTotalprice(totalprice);
			order.setIsdiscount(isdiscount);
			
			if (StringUtils.isNotBlank(discount))
				order.setDiscount(discount);
			
			order.setReceivable(receivable);
			order.setRealprice(realPrice);
			order.setChangemoney(change);
			//获取当前登录对象
			SqUser user = (SqUser)SecurityUtils.getSubject().getPrincipal();
			
			order.setOperatorid(user.getId());
			
			//查询购物车全部信息
			SqShoppingcar car = carMapper.selectByShopCarId(shopCarId);
			
			//保存订单
			order.setShopnum(car.getShopnum());
			order.setUserid(car.getUserid());
			order.setUsername(car.getUser().getUsername());
			orderMapper.insert(order);
			
			for (SqShoppingcardetail carDetail : car.getShoppingcardetails()) {
				SqOrderdetail orderdetail = new SqOrderdetail();
				orderdetail.setId(String.valueOf(IDUtils.genItemId()));
				orderdetail.setOrderid(order.getId());
				orderdetail.setCreatetime(new Date());
				orderdetail.setUpdatetime(new Date());
				orderdetail.setShopid(carDetail.getShopid());
				orderdetail.setName(carDetail.getDishes().getName());
				orderdetail.setShopnum(carDetail.getShopnum());
				orderdetail.setIsvip(carDetail.getDishes().getIsvip());
				orderdetail.setOriginalprice(carDetail.getDishes().getOriginalprice());
				orderdetail.setPrice(carDetail.getDishes().getPrice());
				orderdetail.setXiaoji(String.valueOf(carDetail.getXiaoji()));
				
				//保存订单详情
				orderDetailMapper.insert(orderdetail);
				
				//删除购物车详情
				carDetailMapper.deleteByPrimaryKey(carDetail.getId());
				
			}

			//删除购物车
			carMapper.deleteByPrimaryKey(car.getId());
			
			SqDiningtableExample example = new SqDiningtableExample();
			Criteria criteria = example.createCriteria();
			criteria.andShopcaridEqualTo(car.getId());
			//将桌号状态置为空闲
			List<SqDiningtable> list = diningtableMapper.selectByExample(example);
			
			if (list != null && list.size() > 0) {
				SqDiningtable diningtable = list.get(0);
				diningtable.setStatus("0");
				diningtable.setShopcarid(null);
				diningtableMapper.updateByPrimaryKey(diningtable);
			}else {
				return false;
			}
			
		} catch (Exception e) {
			success = false;
			throw e;
		}
		
		return success;
		
	}
	
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
	@Override
	public List<SqOrder> findOrderEasyUI(int page, int rows, String orderId, String username, String operator, String createtimestart, String createtimeend)
	{
		SqOrderExample example = new SqOrderExample();
		example.setOrderByClause(" updatetime desc");
		
		com.shiqi.oos.entity.SqOrderExample.Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotBlank(orderId)) {
			criteria.andIdEqualTo(orderId);
		}
		
		if (StringUtils.isNotBlank(username)) {
			criteria.andUsernameLike("%" + username + "%");
		}
		
		if (StringUtils.isNotBlank(operator)) {
			
			SqUserExample example1 = new SqUserExample();
			example1.createCriteria().andUsernameEqualTo(operator);
			List<SqUser> userList = userMapper.selectByExample(example1);
			
			if (userList != null && userList.size() > 0) {
				criteria.andOperatoridEqualTo(userList.get(0).getId());
			}
			
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isBlank(createtimestart)) {
			createtimestart = "1970-01-01 00:00:00";
		}
		
		if (StringUtils.isBlank(createtimeend)) {
			createtimeend = format.format(new Date());
		}
		
		try {
			Date endDate =  format.parse(createtimeend);
//			Calendar c = Calendar.getInstance();  
//	        	c.setTime(endDate);  
//	        	c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天  
			criteria.andCreatetimeBetween(format.parse(createtimestart),endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PageHelper.startPage(page, rows);
		List<SqOrder> orderList = orderMapper.selectByExample(example);
		return orderList;
	}
	
}

