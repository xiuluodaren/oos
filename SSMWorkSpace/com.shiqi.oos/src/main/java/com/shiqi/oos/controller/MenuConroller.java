package com.shiqi.oos.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.shiqi.oos.controller.base.BaseController;
import com.shiqi.oos.entity.SqMenu;
import com.shiqi.oos.entity.SqRole;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.service.IMenuService;
import com.shiqi.oos.utils.AjaxReturn;
import com.shiqi.oos.utils.EasyUIResult;

@Controller
@RequestMapping("/menu")
public class MenuConroller extends BaseController {

	@Autowired
	private IMenuService menuService;
	
	/**
	 * 根据父菜单id查询子菜单
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/menuListByParentId")
	@ResponseBody
	public List<SqMenu> menuListByParentId(String parentId) {
		
		if (StringUtils.isNotBlank(parentId)) {
			List<SqMenu> list = menuService.menuListByParentId(parentId);
			
			//过滤掉整棵树都没有权限的菜单
			Subject subject = SecurityUtils.getSubject();
			SqUser user = (SqUser)subject.getPrincipal();
			List<SqMenu> listTemp = menuService.findMenuByUserId(user.getId());
			
			list = menuService.filterMenu(listTemp, list);
			return list;
		}else {
			return menuService.selectTop();
		}
		
	}
	
	/**
	 * 查询全部菜单(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/findEasyUI")
	@ResponseBody
	public Map<String, Object> findEasyUI(int page,int rows) {
		Page<SqMenu> list = (Page<SqMenu>)menuService.findEasyUI(page,rows);
		return EasyUIResult.result(list,list.getTotal());
	}
	
	/**
	 * 查询全部菜单,返回树
	 * @return
	 */
	@RequestMapping("/findTree")
	@ResponseBody
	public List<SqMenu> findTree() {
		SqMenu rootMenu = new SqMenu();
		rootMenu.setMenuname("所有模块");
		rootMenu.setState("open");
		rootMenu.setChildren(menuService.selectTop());
		
		List<SqMenu> list = new ArrayList<SqMenu>();
		list.add(rootMenu);
		
		return list;
	}
	
	/**
	 * 根据菜单名和父类id查询菜单
	 * @param page
	 * @param rows
	 * @param parentId
	 * @param menuname
	 * @return
	 */
	@RequestMapping("/findByMenuname")
	@ResponseBody
	public Map<String, Object> findByMenuname(int page, int rows,String parentId,String menuname) {
		try {
			menuname = new String(menuname.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<SqMenu> list = menuService.findByMenuname(page,rows,parentId,menuname);
		
		return EasyUIResult.result(list, list.size());
	}
	
	/**
	 * 根据id查找菜单
	 * @param menuId
	 * @return
	 */
	@RequestMapping("/findById")
	@ResponseBody
	public SqMenu findById(String menuId) {
		return menuService.findById(menuId);
	}
	
	/**
	 * 保存修改
	 * @param id
	 * @param menuName
	 * @param parentId
	 * @param url
	 * @param zindex
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, String> saveOrInsert(String id,String menuName,String parentId,String url,Integer zindex) {
		boolean success = menuService.saveOrInsert(id,menuName,parentId,url,zindex);
		
		if (success) {
			return AjaxReturn.ok("保存成功");
		}else {
			return AjaxReturn.failed("保存失败");
		}
		
	}

	/**
	 * 根据id批量删除菜单
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/deleteMenusByIds")
	@ResponseBody
	public Map<String, String> deleteMenusByIds(String ids) throws Exception {
		boolean success = true;
		try {
			menuService.deleteMenusByIds(ids.split(","));
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
	
	/**
	 * 根据菜单id查询角色
	 */
	@RequestMapping("/findRolesByMenuId")
	@ResponseBody
	public List<SqRole> findRolesByMenuId(String menuId)
	{
		return menuService.findRolesByMenuId(menuId);
	}
	
	/**
	 * 修改菜单角色
	 * @param menuIds
	 * @param roleIds
	 * @return
	 */
	@RequestMapping("/updateMenuRoles")
	@ResponseBody
	public Map<String, String> updateMenuRoles(String menuId,String roleIds)
	{
		if (menuService.findById(menuId) == null)
		{
			return AjaxReturn.failed("模块不存在");
		}
		
		boolean success = true;
		try {
			menuService.updateMenuRoles(menuId,roleIds);
		} catch (Exception e) {
			success = false;
			throw e;
		}finally {
			if (success) {
				return AjaxReturn.ok("修改成功");
			}else {
				return AjaxReturn.failed("修改失败");
			}
		}
	}
	
}
