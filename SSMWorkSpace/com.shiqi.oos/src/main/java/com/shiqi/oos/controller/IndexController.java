package com.shiqi.oos.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiqi.oos.controller.base.BaseController;
import com.shiqi.oos.entity.SqMenu;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.service.IMenuService;

/**
 * @ClassName IndexController
 * @Description 
 * @Author 修罗
 * @Date 2018年3月6日 下午1:27:54
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

	@Autowired
	private IMenuService menuService;
	
	/**
	 * 首页页面
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model)
	{
		List<SqMenu> topMenuList = menuService.selectTop();
		
		List<SqMenu> topList = new ArrayList<SqMenu>();
		Subject subject = SecurityUtils.getSubject();
		SqUser user = (SqUser)subject.getPrincipal();
		
		for (SqMenu topMenu : topMenuList) {
			List<SqMenu> list = menuService.menuListByParentId(topMenu.getId());
			
			//过滤掉整棵树都没有权限的菜单
			List<SqMenu> listTemp = menuService.findMenuByUserId(user.getId());
			list = menuService.filterMenu(listTemp, list);
			
			if (list != null && list.size() > 0) {
				topList.add(topMenu);
			}
			
		}
		
		model.addAttribute("topList", topList);
		model.addAttribute("user",user);
		
		return "index";
	}
	
}

