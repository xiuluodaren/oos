package com.shiqi.oos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.shiqi.oos.entity.SqMenu;
import com.shiqi.oos.entity.SqRole;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.service.IRoleService;
import com.shiqi.oos.utils.AjaxReturn;
import com.shiqi.oos.utils.EasyUIResult;

/**
 * 角色controller
 * @ClassName RoleController
 * @Description 
 * @Author 修罗
 * @Date 2018年2月22日 下午2:56:33
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	/**
	 * 查询全部
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<SqRole> findAll()
	{
		return roleService.findAll();
	}
	
	/**
	 * 查询全部角色(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/findEasyUI")
	@ResponseBody
	public Map<String, Object> findEasyUI(int page,int rows) {
		Page<SqRole> list = (Page<SqRole>)roleService.findEasyUI(page,rows);
		return EasyUIResult.result(list,list.getTotal());
	}
	
	/**
	 * 查询全部角色(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/findRoles",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findRoles(String roleName,int page,int rows) {
		Page<SqRole> list = (Page<SqRole>)roleService.findRoles(roleName,page,rows);
		return EasyUIResult.result(list,list.getTotal());
	}
	
	/**
	 * 根据id批量删除角色
	 * @param roles
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/deleteRolesByIds")
	@ResponseBody
	public Map<String, String> deleteRolesByIds(String roleIds) throws Exception
	{
		boolean success = true;
		
		try {
			roleService.deleteRolesByIds(roleIds);
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
	 * 添加角色
	 * @param roleId
	 * @param roleName
	 * @param zIndex
	 * @return
	 */
	@RequestMapping("/saveOrUpdateRole")
	@ResponseBody
	public Map<String, String> saveOrUpdateRole(String roleId,String roleName,Integer zIndex) {
		boolean success = roleService.saveOrUpdateRole(roleId,roleName,zIndex);
		
		if (success) {
			return AjaxReturn.ok("操作成功");
		}else {
			return AjaxReturn.failed("操作失败");
		}
		
	}
	
	/**
	 * 根据id查找角色
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/findById")
	@ResponseBody
	public SqRole findById(String roleId)
	{
		return roleService.findById(roleId);
	}
	
	/**
	 * 根据角色id查询模块
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/findMenuByRoleId")
	@ResponseBody
	public List<SqMenu> findMenuByRoleId(String roleId) {
		return roleService.findMenuByRoleId(roleId);
	}
	
	/**
	 * 根据角色id查询用户
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/findUsersByRoleId")
	@ResponseBody
	public List<SqUser> findUsersByRoleId(String roleId) {
		return roleService.findUsersByRoleId(roleId);
	}
	
	/**
	 * 设置角色模块
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/updateRoleMenus")
	@ResponseBody
	public Map<String, String> updateRoleMenus(String roleId,String menuIds) throws Exception
	{
		if (roleService.findById(roleId) == null)
		{
			return AjaxReturn.failed("角色不存在");
		}
		
		boolean success = true;
		try {
			roleService.updateRoleMenus(roleId,menuIds);
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

	/**
	 * 设置角色用户
	 * @param roleId
	 * @param userIds
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/updateRoleUsers")
	@ResponseBody
	public Map<String, String> updateRoleUsers(String roleId,String userIds)
	{
		if (roleService.findById(roleId) == null)
		{
			return AjaxReturn.failed("角色不存在");
		}
		
		boolean success = true;
		try {
			roleService.updateRoleUsers(roleId,userIds);
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

