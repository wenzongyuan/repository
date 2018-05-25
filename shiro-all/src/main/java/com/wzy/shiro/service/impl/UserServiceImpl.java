package com.wzy.shiro.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzy.shiro.dao.entity.Resource;
import com.wzy.shiro.dao.entity.Role;
import com.wzy.shiro.dao.entity.User;
import com.wzy.shiro.exception.ShiroException;
import com.wzy.shiro.handler.IResourceHandler;
import com.wzy.shiro.handler.IRoleHandler;
import com.wzy.shiro.handler.IUserHandler;
import com.wzy.shiro.service.IUserService;
/**
 * �û�serviceʵ����
 * @author Wen
 *
 */
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserHandler userDao;
	@Autowired
	private PasswordHelper passwordHelper;  //�����û����Σ��Լ���������ܲ�����Ϊ��������
	@Autowired
	private IRoleHandler roleHandler;
	@Autowired
	IResourceHandler resourcHandler;

	@Override
	public User addUser(User user) throws ShiroException {
		if(user.getUsername()==null||"".equals(user.getUsername())){
			  throw new ShiroException("�û�������Ϊ�գ�");
		}
		if(user.getPassword()==null||"".equals(user.getPassword())){
			  throw new ShiroException("���벻��Ϊ�գ�");
		}
		passwordHelper.encryptPassword(user);
		userDao.addUser(user);
		return user;
	}

	@Override
	public User updateUser(User user) throws ShiroException {
		if(user.getUsername()==null||"".equals(user.getUsername())){
			  throw new ShiroException("�û�������Ϊ�գ�");
		}
		if(user.getPassword()==null||"".equals(user.getPassword())){
			  throw new ShiroException("���벻��Ϊ�գ�");
		}
		//�ж��û����Ƿ��ظ�
		User findeduser=userDao.findUserByName(user.getUsername());
		if(findeduser.getId()==user.getId()){
			 throw new ShiroException("�û����ظ���");
		}
		userDao.updateUser(user);
		return user;
	}

	@Override
	public void deleteUserById(Long userId) throws ShiroException {
		User user=userDao.findUserById(userId);
		if(user==null){
			throw new ShiroException("�����쳣������ϵ����Ա��");	 
		}
		userDao.deleteUserById(userId);
	}

	@Override
	public User findUserById(Long userId) throws ShiroException {
		return userDao.findUserById(userId);
	}

	@Override
	public List<User> findAllUser() throws ShiroException {
		return userDao.findAllUser();
	}

	@Override
	public User findUserByName(String name)  {
		User user=userDao.findUserByName(name);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> findRoles(String username) throws ShiroException {
		User user = userDao.findUserByName(username);
		if(user==null){
		   return Collections.EMPTY_SET;	
		}
		String[] roleIds=user.getRoleIds().split(",");
		Set<String> set=new HashSet<String>();
		for(String str:roleIds){
			Role role=roleHandler.fingRoleById(Long.valueOf(str));
			set.add(role.getRole());
		}
		return set;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> findPermissions(String username) throws ShiroException {
		User user = userDao.findUserByName(username);
		if(user==null){
		   return Collections.EMPTY_SET;	
		}
		String[] roleIds=user.getRoleIds().split(",");
		Set<String> set=new HashSet<String>();
		for(String str:roleIds){
			Role role=roleHandler.fingRoleById(Long.valueOf(str));
			String[] resourceIds=role.getResourceIds().split(",");
			for(String resourceId:resourceIds){
				Resource resource=resourcHandler.findResourceById(Long.valueOf(resourceId));
				set.add(resource.getPermission());	
			}
			
		}
		return set;
	}

}
