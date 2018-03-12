package com.shiqi.oos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.dialect.mysql.ast.MysqlForeignKey.Match;
import com.shiqi.oos.controller.base.BaseController;
import com.shiqi.oos.entity.SqShoppingcar;
import com.shiqi.oos.entity.SqShoppingcardetail;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.service.IDishesService;
import com.shiqi.oos.service.IShoppingCarService;
import com.shiqi.oos.utils.AjaxReturn;
import com.shiqi.oos.utils.EasyUIResult;

/**
 * 购物车controller
 * @ClassName ShoppingCarController
 * @Description 
 * @Author 修罗
 * @Date 2018年2月2日 上午11:21:21
 */
@Controller
@RequestMapping("/shoppingCar")
public class ShoppingCarController extends BaseController {

	@Autowired
	private IShoppingCarService shoppingCarService;

	/**
	 * 查询当前用户的购物车详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/shopCarDetail")
	@ResponseBody
	public Map<String, Object> selectShopCarDetail(HttpServletRequest request,int page,int rows) {
		SqUser user = (SqUser) request.getSession().getAttribute("user");
		
		SqShoppingcar car = shoppingCarService.selectDetailByUserId(user.getId());
		
		int total = car.getShoppingcardetails().size();
		int startIndex = rows * (page - 1);
		int endIndex = startIndex + rows;
		
		endIndex = endIndex > total ? total : endIndex;
		
		List<SqShoppingcardetail> resultList = new ArrayList<SqShoppingcardetail>();
		List<SqShoppingcardetail> list = car.getShoppingcardetails();
		
		for (int i = startIndex;i < endIndex;i++) {
			resultList.add(list.get(i));
		}
		
		return EasyUIResult.result(resultList,total);
	}
	
	/**
	 * 添加至购物车
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/addToShopCar",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addToShopCar(String shopId,HttpServletRequest request) {
		
		SqUser user = (SqUser) request.getSession().getAttribute("user");
		
		if (user != null) {
			boolean result = shoppingCarService.addToShopCar(shopId,user.getId());
			
			if (result) {
				return AjaxReturn.ok("添加成功");
			}else {
				return AjaxReturn.failed("添加失败");
			}
			
		}else {
			return AjaxReturn.failed("请先登录");
		}
		
	}
	
	/**
	 * 从购物车移除
	 * @param detailId
	 * @return
	 */
	@RequestMapping("/removeDetail")
	@ResponseBody
	public Map<String, String> removeDetail(String detailId) {
		boolean success = shoppingCarService.removeDetail(detailId);
		
		if (success) {
			return AjaxReturn.ok("移除成功");
		}else {
			return AjaxReturn.failed("移除失败");
		}
		
	}
	
	/**
	 * 确定点餐
	 * @param zhuohao
	 * @return
	 */
	@RequestMapping("/confirmOrder")
	@ResponseBody
	public Map<String, String> confirmOrder(String zhuohao,HttpServletRequest request) {
		SqUser user = (SqUser) request.getSession().getAttribute("user");
		
		boolean success = shoppingCarService.confirmOrder(zhuohao,user.getId());
		
		if (success) {
			return AjaxReturn.ok("点餐成功，菜品正在准备中。。。");
		}else {
			return AjaxReturn.failed("点餐失败，请重试");
		}
		
	}

	/**
	 * 获取页面
	 * @return
	 */
	@RequestMapping("/getUI")
	public String getHTMLUI(HttpServletRequest request,Model model) {
		
		SqUser user = (SqUser) request.getSession().getAttribute("user");
		
		SqShoppingcar car = shoppingCarService.selectDetailByUserId(user.getId());
		
		if (car == null) {
			return "/baseFunction/carnull";
		}
		
		model.addAttribute("zhuohao",car.getDiningtableid());
		
		return "/baseFunction/ShopCar";
	}
	
}
