package com.wzy.shiro.handler;

import java.util.List;
import java.util.Set;

import com.wzy.shiro.dao.entity.User;
import com.wzy.shiro.exception.ShiroException;

public interface IUserHandler {
    
	public User addUser(User user) throws ShiroException;
	
	public User updateUser(User user)throws ShiroException;
	
	public void deleteUserById(Long userId)throws ShiroException;
	
	public User findUserById(Long userId)throws ShiroException;
	
	public List<User> findAllUser()throws ShiroException;
	
	public User findUserByName(String name);
	
	
}
