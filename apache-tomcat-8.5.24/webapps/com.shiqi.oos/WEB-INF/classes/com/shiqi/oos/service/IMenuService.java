package com.shiqi.oos.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.shiqi.oos.entity.SqMenu;
import com.shiqi.oos.entity.SqRole;

/**
 * 菜单service接口
 * @ClassName IMenuService
 * @Description 
 * @Author 修罗
 * @Date 2018年1月28日 下午8:24:19
 */
public interface IMenuService {

	/**
	 * 根据parentId查询菜单
	 * @param parentId
	 * @return
	 */
	List<SqMenu> menuListByParentId(String parentId);

	/**
	 * 查询顶层菜单
	 * @return
	 */
	List<SqMenu> selectTop();

	/**
	 * 查询权限(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	List<SqMenu> findEasyUI(int page, int rows);

	/**
	 * 根据菜单名和父类id查询菜单
	 * @param page
	 * @param rows
	 * @param parentId
	 * @param menuname
	 * @return
	 */
	List<SqMenu> findByMenuname(int page, int rows,String parentId, String menuname);

	/**
	 * 根据id查询菜单
	 * @param menuId
	 * @return
	 */
	SqMenu findById(String menuId);

	/**
	 * 保存修改
	 * @param id
	 * @param menuName
	 * @param parentId
	 * @param url
	 * @param zindex
	 * @return
	 */
	boolean saveOrInsert(String id, String menuName, String parentId, String url, Integer zindex);

	/**
	 * 根据id批量删除
	 * @param ids
	 */
	void deleteMenusByIds(String[] ids) throws Exception;

	/**
	 * 根据菜单id查询角色
	 * @param menuId
	 * @return
	 */
	List<SqRole> findRolesByMenuId(String menuId);

	/**
	 * 修改模块角色
	 * @param menuId
	 * @param roleIds
	 */
	void updateMenuRoles(String menuId, String roleIds) throws Exception;

	/**
	 * 查询全部
	 * @return
	 */
	List<SqMenu> findAll();

	/**
	 * 根据用户id查询模块
	 * @param id
	 * @return
	 */
	List<SqMenu> findMenuByUserId(String userId);

	/**
	 * 刷新权限
	 */
	void refreshFunction();

	/**
	 * 过滤整棵树都没有权限的菜单
	 * @param list
	 * @param findCurrentMenuListByParentId
	 * @return
	 */
	List<SqMenu> filterMenu(List<SqMenu> list, List<SqMenu> findCurrentMenuListByParentId);

}
