package com.shiqi.oos.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shiqi.oos.entity.SqDiningtable;
import com.shiqi.oos.entity.SqDiningtableExample;
import com.shiqi.oos.entity.SqRole;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.entity.SqUserExample;
import com.shiqi.oos.entity.SqUserRoleExample;
import com.shiqi.oos.entity.SqUserRoleKey;
import com.shiqi.oos.entity.SqUserExample.Criteria;
import com.shiqi.oos.mapper.SqRoleMapper;
import com.shiqi.oos.mapper.SqUserMapper;
import com.shiqi.oos.mapper.SqUserRoleMapper;
import com.shiqi.oos.service.IUserService;
import com.shiqi.oos.utils.IDUtils;
import com.shiqi.oos.utils.MD5Utils;

/**
 * 用户service
 * @ClassName UserServiceImpl
 * @Description 
 * @Author 修罗
 * @Date 2018年2月3日 下午5:26:33
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private SqUserMapper userMapper;
	
	@Autowired
	private SqRoleMapper roleMapper;
	
	@Autowired
	private SqUserRoleMapper userRoleMapper;
	
	@Value("${START_PWD}")
	private String start_pwd;
	
	/**
	 * 根据用户名密码查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public SqUser selectUser(String username, String password) {
		
		SqUserExample example = new SqUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		List<SqUser> list =  userMapper.selectByExample(example);
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	/**
	 * 修改密码
	 */
	@Override
	public boolean updatePassword(String id, String txtNewPass) {
		
		SqUser user = userMapper.selectByPrimaryKey(id);
		user.setPassword(MD5Utils.md5(txtNewPass));
		if (userMapper.updateByPrimaryKey(user) > 0)
		{
			return true;
		}else {
			return false;
		}
		
	}

	/**
	 * 查询全部(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public List<SqUser> findEasyUI(int page, int rows) {
		
		SqUserExample example = new SqUserExample();
		
		PageHelper.startPage(page, rows);
		List<SqUser> list = userMapper.selectByExample(example );
		
		return list;
	}
	
	/**
	 * 查询用户(分页)
	 * @param id
	 * @param username
	 * @param isvip
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SqUser> search(String id, String username, String isvip, int page, int rows){
		
		SqUserExample example = new SqUserExample();
		
		Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNoneBlank(id)) {
			criteria.andIdEqualTo(id);
		}
		
		if (StringUtils.isNoneBlank(username)) {
			criteria.andUsernameLike("%" + username + "%");
		}
		
		if (StringUtils.isNoneBlank(isvip)) {
			criteria.andIsvipEqualTo(isvip);
		}
		
		PageHelper.startPage(page, rows);
		List<SqUser> list = userMapper.selectByExample(example );
		
		return list;
	}

	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	public boolean delteByIds(String ids)
	{
		String[] idArray = ids.split(",");
		
		for (String id : idArray) {
			if (userMapper.deleteByPrimaryKey(id) <= 0)
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 重置密码
	 * @param userId
	 * @return
	 */
	@Override
	public boolean resetPassword(String userId) {
		
		SqUser user = userMapper.selectByPrimaryKey(userId);
		
		if (user != null) {
			user.setPassword(MD5Utils.md5(start_pwd));
			userMapper.updateByPrimaryKey(user);
			return true;
		}
		
		return false;
	}

	/**
	 * 添加用户
	 * @param username
	 * @param isvip
	 * @return
	 */
	@Override
	public boolean addUser(String username, String isvip) {
		
		SqUser user = new SqUser();
		user.setId(String.valueOf(IDUtils.genItemId()));
		user.setUsername(username);
		user.setPassword(MD5Utils.md5(start_pwd));
		user.setIsvip(isvip);
		
		if (userMapper.insert(user) > 0)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * 查询全部用户
	 * @return
	 */
	public List<SqUser> findAll()
	{
		SqUserExample example = new SqUserExample();
		return userMapper.selectByExample(example);
	}
	
	/**
	 * 根据用户id查询角色
	 * @param userId
	 * @return
	 */
	public List<SqRole> findRolesByUserId(String userId)
	{
		return roleMapper.selectRoleByUserId(userId);
	}
	
	/**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 */
	public SqUser findById(String userId)
	{
		return userMapper.selectByPrimaryKey(userId);
	}
	
	/**
	 * 修改用户角色
	 * @param roleIds
	 * @param userId
	 */
	public void updateUserRoles(String roleIds, String userId) throws Exception
	{
		String[] ids = roleIds.split(",");
		
		try {
			SqUserRoleExample example = new SqUserRoleExample();
			com.shiqi.oos.entity.SqUserRoleExample.Criteria criteria = example.createCriteria();
			criteria.andUseridEqualTo(userId);
			userRoleMapper.deleteByExample(example);
			
			for (String id : ids) {
				if (StringUtils.isNotBlank(id)) {
					SqUserRoleKey key = new SqUserRoleKey();
					key.setRoleid(id);
					key.setUserid(userId);
					userRoleMapper.insert(key);
				}
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	public SqUser findUserByUsername(String username)
	{
		SqUserExample example = new SqUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<SqUser> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
}
