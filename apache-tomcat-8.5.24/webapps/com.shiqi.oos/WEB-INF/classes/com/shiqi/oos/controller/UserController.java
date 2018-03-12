package com.shiqi.oos.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.shiqi.oos.entity.SqDiningtable;
import com.shiqi.oos.entity.SqRole;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.service.IUserService;
import com.shiqi.oos.utils.AjaxReturn;
import com.shiqi.oos.utils.EasyUIResult;
import com.shiqi.oos.utils.MD5Utils;

/**
 * 用户controller
 * @ClassName UserController
 * @Description 
 * @Author 修罗
 * @Date 2018年2月4日 下午4:10:35
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	/**
	 * 修改密码
	 * @param request
	 * @param txtOldPass
	 * @param txtNewPass
	 * @param txtRePass
	 * @return
	 */
	@RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatePassword(HttpServletRequest request,String txtOldPass,String txtNewPass,String txtRePass) {
		
		if (!txtNewPass.equals(txtRePass)) {
			return AjaxReturn.failed("新密码与确认密码不匹配");
		}
		
		SqUser user = (SqUser) request.getSession().getAttribute("user");
		if (user != null) {
			if (user.getPassword().equals(MD5Utils.md5(txtOldPass))) {

				if (userService.updatePassword(user.getId(),txtNewPass))
				{
					request.getSession().removeAttribute("user");
					return AjaxReturn.ok("修改成功");
				}else {
					return AjaxReturn.failed("修改失败");
				}
			}else {
				return AjaxReturn.failed("原密码不正确");
			}
		}else {
			return AjaxReturn.failed("系统错误");
		}
		
	}
	
	/**
	 * 查询全部用户(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/findEasyUI")
	@ResponseBody
	public Map<String, Object> findEasyUI(int page,int rows) {
		Page<SqUser> list = (Page<SqUser>)userService.findEasyUI(page,rows);
		return EasyUIResult.result(list,list.getTotal());
	}
	
	/**
	 * 查询用户(分页)
	 * @param page
	 * @param rows
	 * @param id
	 * @param username
	 * @param isvip
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> search(String id,String username,String isvip,int page,int rows) {
		Page<SqUser> list = (Page<SqUser>)userService.search(id,username,isvip,page,rows);
		return EasyUIResult.result(list,list.getTotal());
	}
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public Map<String, String> deleteByIds(String ids) {
		boolean success = userService.delteByIds(ids);
		
		if (success) {
			return AjaxReturn.ok("删除成功");
		}else {
			return AjaxReturn.failed("删除失败");
		}
		
	}
	
	/**
	 * 重置密码
	 * @param userId
	 * @param password
	 * @return
	 */
	@RequestMapping("/resetPasswrod")
	@ResponseBody
	public Map<String, String> resetPassword(String userId,String password) {
		
		boolean success = userService.resetPassword(userId);
		
		if (success) {
			return AjaxReturn.ok("重置成功");
		}else {
			return AjaxReturn.ok("重置失败");
		}
		
	}
	
	/**
	 * 添加用户
	 * @param username
	 * @param isvip
	 * @return
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public Map<String, String> addUser(String username,String isvip)
	{
		boolean success = userService.addUser(username,isvip);
		
		if (success) {
			return AjaxReturn.ok("添加成功");
		}else {
			return AjaxReturn.failed("添加失败");
		}
		
	}
	
	/**
	 * 查询全部用户
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<SqUser> findAll()
	{
		return userService.findAll();
	}
	
	/**
	 * 根据用户id查询角色
	 * @param userId
	 * @return
	 */
	@RequestMapping("/findRolesByUserId")
	@ResponseBody
	public List<SqRole> findRolesByUserId(String userId){
		return userService.findRolesByUserId(userId);
	}
	
	/**
	 * 设置用户角色
	 * @param roleIds
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/updateUserRoles")
	@ResponseBody
	public Map<String, String> updateUserRoles(String roleIds,String userId) throws Exception
	{
		if (userService.findById(userId) == null)
		{
			return AjaxReturn.failed("用户不存在");
		}
		
		boolean success = true;
		try {
			userService.updateUserRoles(roleIds,userId);
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
