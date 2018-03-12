package com.shiqi.oos.realm;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiqi.oos.entity.SqMenu;
import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.service.IMenuService;
import com.shiqi.oos.service.IUserService;

public class OOSRealm extends AuthorizingRealm{
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMenuService menuService;
	
	//认证方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("自定义的realm中认证方法执行了。。。。");
		UsernamePasswordToken passwordToken = (UsernamePasswordToken)token;
		//获得页面输入的用户名
		String username = passwordToken.getUsername();
		//根据用户名查询数据库中的密码
		SqUser user = userService.findUserByUsername(username);
		if(user == null){
			//页面输入的用户名不存在
			return null;
		}
		//简单认证信息对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		//框架负责比对数据库中的密码和页面输入的密码是否一致
		return info;
	}

	//授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo  info = new SimpleAuthorizationInfo();
		
		//获取用户
		SqUser user = (SqUser)principals.getPrimaryPrincipal();
//		User user = (User)SecurityUtils.getSubject().getPrincipal();
		List<SqMenu> list = null;
		if (user.getUsername().equals("admin")) {
			list = menuService.findAll();
		}else{
			list = menuService.findMenuByUserId(user.getId());
		}
		
		if (list != null && list.size() > 0) {
			for (SqMenu menu : list) {
				if (StringUtils.isNotBlank(menu.getUrl())) {
					info.addStringPermission(menu.getUrl()); 
				}
			}
		}
		
		return info;
	}
}
