package com.shiqi.oos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shiqi.oos.entity.SqMenu;
import com.shiqi.oos.entity.SqRole;
import com.shiqi.oos.entity.SqRoleExample;
import com.shiqi.oos.entity.SqRoleExample.Criteria;
import com.shiqi.oos.entity.SqRoleMenuExample;
import com.shiqi.oos.entity.SqRoleMenuKey;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.entity.SqUserRoleExample;
import com.shiqi.oos.entity.SqUserRoleKey;
import com.shiqi.oos.mapper.SqMenuMapper;
import com.shiqi.oos.mapper.SqRoleMapper;
import com.shiqi.oos.mapper.SqRoleMenuMapper;
import com.shiqi.oos.mapper.SqUserMapper;
import com.shiqi.oos.mapper.SqUserRoleMapper;
import com.shiqi.oos.service.IRoleService;

/**
 * @ClassName RoleServiceImpl
 * @Description 
 * @Author 修罗
 * @Date 2018年2月22日 下午4:06:02
 */
@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private SqRoleMapper roleMapper;
	
	@Autowired
	private SqMenuMapper menuMapper;
	
	@Autowired
	private SqUserMapper userMapper;
	
	@Autowired
	private SqRoleMenuMapper roleMenuMapper;
	
	@Autowired
	private SqUserRoleMapper userRoleMapper;
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<SqRole> findAll()
	{
		SqRoleExample example = new SqRoleExample();
		return roleMapper.selectByExample(example);
	}
	
	/**
	 * 查询角色(带分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SqRole> findEasyUI(int page, int rows)
	{
		SqRoleExample example = new SqRoleExample();
		example.setOrderByClause("zIndex ASC");
		PageHelper.startPage(page, rows);
		return roleMapper.selectByExample(example);
	}
	
	/**
	 * 根据角色名查询角色(带分页)
	 * @param roleName
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SqRole> findRoles(String roleName, int page, int rows)
	{
		SqRoleExample example = new SqRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andRolenameLike("%" + roleName + "%");
		example.setOrderByClause("zIndex ASC");
		
		PageHelper.startPage(page, rows);
		return roleMapper.selectByExample(example);
	}
	
	/**
	 * 根据id批量删除角色
	 * @param roles
	 */
	public void deleteRolesByIds(String roles) throws Exception
	{
		String[] ids = roles.split(",");
		
		try {
			for (String id : ids) {
				roleMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * 添加角色
	 * @param roleId
	 * @param roleName
	 * @param zIndex
	 * @return
	 */
	public boolean saveOrUpdateRole(String roleId, String roleName, Integer zIndex)
	{
		if (StringUtils.isBlank(roleId)) return false;
		
		SqRole role = roleMapper.selectByPrimaryKey(roleId);
		
		if (role == null) {
			role = new SqRole();
			role.setId(roleId);
			role.setRolename(roleName);
			role.setZindex(zIndex);
			
			if (roleMapper.insert(role) > 0)
			{
				return true;
			}else {
				return false;
			}
		}else {
			role.setRolename(roleName);
			role.setZindex(zIndex);
			
			if (roleMapper.updateByPrimaryKey(role) > 0)
			{
				return true;
			}else {
				return false;
			}
		}
		
	}
	
	/**
	 * 根据id查询角色
	 * @param roleId
	 * @return
	 */
	public SqRole findById(String roleId)
	{
		SqRole sqRole = roleMapper.selectByPrimaryKey(roleId);
		return sqRole;
	}
	
	/**
	 * 根据角色id查询模块
	 * @param roleId
	 * @return
	 */
	public List<SqMenu> findMenuByRoleId(String roleId)
	{
		List<SqMenu> list = menuMapper.selectByRoleId(roleId);
		return list;
	}

	/**
	 * 修改角色模块
	 * @param roleId
	 * @param menuIds
	 */
	public void updateRoleMenus(String roleId, String menuIds) throws Exception
	{
		String[] ids = menuIds.split(",");
		
		try {
			SqRoleMenuExample example = new SqRoleMenuExample();
			com.shiqi.oos.entity.SqRoleMenuExample.Criteria criteria = example.createCriteria();
			criteria.andRoleidEqualTo(roleId);
			roleMenuMapper.deleteByExample(example);
			
			for (String id : ids) {
				if (StringUtils.isNotBlank(id)) {
					SqRoleMenuKey key = new SqRoleMenuKey();
					key.setRoleid(roleId);
					key.setMenuid(id);
					roleMenuMapper.insert(key);
				}
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}

	/**
	 * 修改角色用户
	 * @param roleId
	 * @param userIds
	 */
	public void updateRoleUsers(String roleId, String userIds) throws Exception
	{
		String[] ids = userIds.split(",");
		
		try {
			SqUserRoleExample example = new SqUserRoleExample();
			com.shiqi.oos.entity.SqUserRoleExample.Criteria criteria = example.createCriteria();
			criteria.andRoleidEqualTo(roleId);
			userRoleMapper.deleteByExample(example);
			
			for (String id : ids) {
				if (StringUtils.isNotBlank(id)) {
					SqUserRoleKey key = new SqUserRoleKey();
					key.setRoleid(roleId);
					key.setUserid(id);
					userRoleMapper.insert(key);
				}
			}
			
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据角色id查询用户
	 * @param roleId
	 * @return
	 */
	public List<SqUser> findUsersByRoleId(String roleId)
	{
		List<SqUser> list = userMapper.selectByRoleId(roleId);
		return list;
	}
	
}

