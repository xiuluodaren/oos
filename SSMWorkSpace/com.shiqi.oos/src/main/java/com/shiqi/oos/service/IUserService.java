package com.shiqi.oos.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.shiqi.oos.entity.SqRole;
import com.shiqi.oos.entity.SqUser;

/**
 * 用户service接口
 * @ClassName IUserService
 * @Description 
 * @Author 修罗
 * @Date 2018年2月3日 下午5:25:38
 */
public interface IUserService {

	/**
	 * 根据用户名密码查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	SqUser selectUser(String username, String password);

	/**
	 * 修改密码
	 * @param id
	 * @param txtNewPass
	 * @return
	 */
	boolean updatePassword(String id, String txtNewPass);

	/**
	 * 查询用户(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	List<SqUser> findEasyUI(int page, int rows);

	/**
	 * 查询用户(分页)
	 * @param id
	 * @param username
	 * @param isvip
	 * @param page
	 * @param rows
	 * @return
	 */
	List<SqUser> search(String id, String username, String isvip, int page, int rows);

	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	boolean delteByIds(String ids);

	/**
	 * 重置密码
	 * @param userId
	 * @return
	 */
	boolean resetPassword(String userId);

	/**
	 * 添加用户
	 * @param username
	 * @param isvip
	 * @return
	 */
	boolean addUser(String username, String isvip);

	/**
	 * 查询全部用户
	 * @return
	 */
	List<SqUser> findAll();

	/**
	 * 根据用户id查询角色
	 * @param userId
	 * @return
	 */
	List<SqRole> findRolesByUserId(String userId);

	/**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 */
	SqUser findById(String userId);

	/**
	 * 修改用户角色
	 * @param roleIds
	 * @param userId
	 */
	void updateUserRoles(String roleIds, String userId) throws Exception;

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	SqUser findUserByUsername(String username);

}
