package com.shiqi.oos.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.shiqi.oos.entity.SqMenu;
import com.shiqi.oos.entity.SqRole;
import com.shiqi.oos.entity.SqUser;

/**
 * @ClassName IRoleService
 * @Description 
 * @Author 修罗
 * @Date 2018年2月22日 下午4:05:06
 */
public interface IRoleService {

	/**
	 * 查询角色(带分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	List<SqRole> findEasyUI(int page, int rows);

	/**
	 * 根据角色名查询角色(带分页)
	 * @param roleName
	 * @param page
	 * @param rows
	 * @return
	 */
	List<SqRole> findRoles(String roleName, int page, int rows);

	/**
	 * 根据id批量删除角色
	 * @param roles
	 */
	void deleteRolesByIds(String roles) throws Exception;

	/**
	 * 添加角色
	 * @param roleId
	 * @param roleName
	 * @param zIndex
	 * @return
	 */
	boolean saveOrUpdateRole(String roleId, String roleName, Integer zIndex);

	/**
	 * 根据id查询角色
	 * @param roleId
	 * @return
	 */
	SqRole findById(String roleId);

	/**
	 * 根据角色id查询模块
	 * @param roleId
	 * @return
	 */
	List<SqMenu> findMenuByRoleId(String roleId);

	/**
	 * 修改角色模块
	 * @param roleId
	 * @param menuIds
	 */
	void updateRoleMenus(String roleId, String menuIds) throws Exception;

	/**
	 * 修改角色用户
	 * @param roleId
	 * @param userIds
	 */
	void updateRoleUsers(String roleId, String userIds) throws Exception;

	/**
	 * 根据角色id查询用户
	 * @param roleId
	 * @return
	 */
	List<SqUser> findUsersByRoleId(String roleId);

	/**
	 * 查询全部
	 * @return
	 */
	List<SqRole> findAll();

}

