package com.shiqi.oos.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shiqi.oos.entity.SqMenu;
import com.shiqi.oos.entity.SqMenuExample;
import com.shiqi.oos.entity.SqRole;
import com.shiqi.oos.entity.SqRoleMenuExample;
import com.shiqi.oos.entity.SqRoleMenuKey;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.entity.SqUserExample;
import com.shiqi.oos.entity.SqMenuExample.Criteria;
import com.shiqi.oos.mapper.SqMenuMapper;
import com.shiqi.oos.mapper.SqRoleMapper;
import com.shiqi.oos.mapper.SqRoleMenuMapper;
import com.shiqi.oos.service.IMenuService;
import com.shiqi.oos.shiro.MyShiroFilterFactoryBean;
import com.shiqi.oos.utils.IDUtils;

/**
 * 菜单service实现
 * @ClassName MenuServiceImpl
 * @Description 
 * @Author 修罗
 * @Date 2018年1月28日 下午8:24:35
 */
@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private SqMenuMapper menuMapper;
	
	@Autowired
	private SqRoleMapper roleMapper;
	
	@Autowired
	private SqRoleMenuMapper roleMenuMapper;
	
	@Autowired
	private MyShiroFilterFactoryBean chain;
	
	/**
	 * 根据parentId查询菜单
	 * @param parentId
	 * @return
	 */
	@Override
	public List<SqMenu> menuListByParentId(String parentId) {
		
		SqMenuExample example = new SqMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(parentId);
		
		List<SqMenu> list = menuMapper.selectByExample(example);

		if (list == null || list.size() == 0) {

		}else {
			
			for (SqMenu sqMenu : list) {
				List<SqMenu> childrenList = menuListByParentId(sqMenu.getId());
				sqMenu.setChildren(childrenList);
				
				if (childrenList == null || childrenList.size() == 0) {
					sqMenu.setState(null);
				}
				
			}
			
		}
		
		//按zindex升序排列
		list.sort(Comparator.naturalOrder());
		return list;
	}

	/**
	 * 查询顶层菜单
	 * @return
	 */
	@Override
	public List<SqMenu> selectTop() {
		// TODO Auto-generated method stub
		SqMenuExample example = new SqMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentidIsNull();
		
		List<SqMenu> list = menuMapper.selectByExample(example);
		
		for (SqMenu sqMenu : list) {
			List<SqMenu> childrenList = menuListByParentId(sqMenu.getId());
			sqMenu.setChildren(childrenList);
		}
		
		return list;
	}

	/**
	 * 查询全部(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public List<SqMenu> findEasyUI(int page, int rows) {
		
		SqMenuExample example = new SqMenuExample();
		
		PageHelper.startPage(page, rows);
		List<SqMenu> list = menuMapper.selectByExample(example );
		
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
	@Override
	public List<SqMenu> findByMenuname(int page, int rows,String parentId, String menuname) {
		SqMenuExample example = new SqMenuExample();
		Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNoneBlank(parentId)) {
			criteria.andParentidEqualTo(parentId);
		}else {
			criteria.andParentidIsNull();
		}
		criteria.andMenunameLike("%" + menuname + "%");
		
		PageHelper.startPage(page, rows);
		List<SqMenu> list = menuMapper.selectByExample(example );
		
		return list;
	}
	
	/**
	 * 根据id查询菜单
	 * @param menuId
	 * @return
	 */
	@Override
	public SqMenu findById(String menuId)
	{
		return menuMapper.selectByPrimaryKey(menuId);
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
	@Override
	public boolean saveOrInsert(String id, String menuName, String parentId, String url, Integer zindex)
	{
		zindex = zindex == null ? 0 : zindex;
		
		SqMenu menu = findById(id);
		
		if (StringUtils.isNoneBlank(parentId) && findById(parentId) == null) {
			return false;
		}
		
		if (menu == null) {
			menu = new SqMenu();
			menu.setId(String.valueOf(IDUtils.genItemId()));
			menu.setMenuname(menuName);
			menu.setParentid(StringUtils.isNoneBlank(parentId) ? parentId : null);
			menu.setUrl(url);
			menu.setZindex(zindex);
			
			if (menuMapper.insert(menu) > 0)
			{
				return true;
			}else {
				return false;
			}
			
		}else {
			menu.setMenuname(menuName);
			menu.setParentid(StringUtils.isNoneBlank(parentId) ? parentId : null);
			menu.setUrl(url);
			menu.setZindex(zindex);
			
			if (menuMapper.updateByPrimaryKey(menu) > 0)
			{
				return true;
			}else {
				return false;
			}
		}
		
	}

	/**
	 * 根据id批量删除
	 * @param ids
	 */
	public void deleteMenusByIds(String[] ids) throws Exception
	{
		try {
			for (String id : ids) {
				menuMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 根据菜单id查询角色
	 * @param menuId
	 * @return
	 */
	public List<SqRole> findRolesByMenuId(String menuId)
	{
		List<SqRole> list = roleMapper.selectRolesByMenuId(menuId);
		return list;
	}
	
	/**
	 * 修改模块角色
	 * @param menuId
	 * @param roleIds
	 */
	public void updateMenuRoles(String menuId, String roleIds) throws Exception
	{
		String[] ids = roleIds.split(",");
		
		try {
			SqRoleMenuExample example = new SqRoleMenuExample();
			com.shiqi.oos.entity.SqRoleMenuExample.Criteria criteria = example.createCriteria();
			criteria.andMenuidEqualTo(menuId);
			roleMenuMapper.deleteByExample(example);
			
			for (String id : ids) {
				if (StringUtils.isNotBlank(id)) {
					SqRoleMenuKey key = new SqRoleMenuKey();
					key.setRoleid(id);
					key.setMenuid(menuId);
					roleMenuMapper.insert(key);
				}
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<SqMenu> findAll()
	{
		SqMenuExample example = new SqMenuExample();
		return menuMapper.selectByExample(example);
	}
	
	/**
	 * 根据用户id查询模块
	 * @param id
	 * @return
	 */
	public List<SqMenu> findMenuByUserId(String userId)
	{
		return menuMapper.findMenuByUserId(userId);
	}
	
	/**
	 * 刷新权限
	 */
	public void refreshFunction()
	{
		chain.setFilterChainDefinitions(null);
	}
	
	/**
	 * 过滤掉整棵树都没有权限的菜单
	 * @param menuList
	 * @param allList
	 * @return
	 */
	public List<SqMenu> filterMenu(List<SqMenu> menuList,List<SqMenu> allList)
	{
		List<SqMenu> retList = new ArrayList<SqMenu>();
		for (SqMenu sqMenu : allList) {
			if (contains(menuList, sqMenu)) {
				retList.add(sqMenu);
			}else {
				sqMenu.setChildren( filterMenu(menuList, sqMenu.getChildren()));
				if (sqMenu.getChildren() != null && sqMenu.getChildren().size() > 0) {
					retList.add(sqMenu);
				}
			}
		}
		
		return retList;
	}
	
	/**
	 * 判断是否包含
	 * @param list
	 * @param menu
	 * @return
	 */
	public boolean contains(List<SqMenu> list,SqMenu menu) {
		for (SqMenu sqMenu : list) {
			if (sqMenu.getId().equals(menu.getId())) {
				return true;
			}
		}
		
		return false;
	}
	
}
