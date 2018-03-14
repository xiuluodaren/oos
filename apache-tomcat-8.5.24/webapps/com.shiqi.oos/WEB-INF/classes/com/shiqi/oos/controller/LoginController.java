package com.shiqi.oos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiqi.oos.entity.SqUser;
import com.shiqi.oos.service.IMenuService;
import com.shiqi.oos.service.IUserService;
import com.shiqi.oos.utils.MD5Utils;

/**
 * 登陆controller
 * @ClassName LoginController
 * @Description 
 * @Author 修罗
 * @Date 2018年2月3日 下午5:20:07
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMenuService menuService;
	
	/**
	 * 登陆
	 * @param username
	 * @param password
	 * @param checkcode
	 * @return
	 */
	@RequestMapping("/login")
	public void login(String username,String password,String checkcode,HttpServletRequest request,HttpServletResponse response) {
		String code = (String) request.getSession().getAttribute("key");
		
		String baseURL = request.getContextPath();
		
		try {
			if (checkcode.equals(code)) {
				
				//使用shiro框架提供的方式进行认证操作
				Subject subject = SecurityUtils.getSubject();//获得当前用户对象,状态为“未认证”
				AuthenticationToken token = new UsernamePasswordToken(username,MD5Utils.md5(password));//创建用户名密码令牌对象
				try{
					subject.login(token);
				}catch(Exception e){
					e.printStackTrace();

					request.setAttribute("errorMsg", "用户名密码不匹配");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
					return;
				}
				SqUser user = (SqUser) subject.getPrincipal();
				
				//刷新权限
				menuService.refreshFunction();

				if (user == null) {
					request.setAttribute("errorMsg", "用户名密码不匹配");
					request.getRequestDispatcher(baseURL + "/login.jsp").forward(request, response);
				}else {
					request.getSession().setAttribute("user", user);
					response.sendRedirect(baseURL + "/index.jsp");
//					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("errorMsg", "验证码错误");
				request.getRequestDispatcher(baseURL + "/login.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 退出登陆
	 * @param request
	 * @param response
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getSession().removeAttribute("user");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
