package com.wzy.shiro.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wzy.shiro.annotion.CurrentUser;
import com.wzy.shiro.dao.entity.Resource;
import com.wzy.shiro.dao.entity.User;
import com.wzy.shiro.exception.ShiroException;
import com.wzy.shiro.service.IResourceService;
import com.wzy.shiro.service.IUserService;
import com.wzy.shiro.service.impl.ResourceService;

/**
 * ��¼controller�������ĵ�¼��shiroFilter�н��У�
 * @author Wen
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IResourceService  resourceService;
	
	@RequestMapping(value="/login")
	public String showLoginForm(HttpServletRequest req,Model model){
		String exceptionClassName=(String) req.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String error=null;
		if(UnknownAccountException.class.getSimpleName().equals(exceptionClassName)){
			error="用户名或密码错误！";
		}else if(IncorrectCredentialsException.class.getSimpleName().equals(exceptionClassName)){
			error="用户名或密码错误！";
		}else if(exceptionClassName!=null){
			error="系统繁忙！";
		}
		model.addAttribute("error", error);
		return "login";
		
	}
	
	
	
	
	@RequestMapping(value="/addUser")
	public String add(HttpServletRequest req,Model model) throws ShiroException{
	    User user=new User();
	    user.setUsername("tang");
	    user.setPassword("123");
	    User existUser= userService.addUser(user);
		return "login";
		
	}
	
	

	@RequestMapping("/")
	public String index(@CurrentUser User loginUser, Model model) throws ShiroException {
	Set<String> permissions = userService.findPermissions(loginUser.getUsername());
	List<Resource> menus = resourceService.findMenus(permissions);
	   model.addAttribute("menus", menus);
	   return "index";
	  }
	}
	
	
