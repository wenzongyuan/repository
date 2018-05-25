package com.wzy.shiro.handler.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzy.shiro.dao.entity.User;
import com.wzy.shiro.dao.entity.UserExample;
import com.wzy.shiro.dao.mapper.UserMapper;
import com.wzy.shiro.exception.ShiroException;
import com.wzy.shiro.handler.IUserHandler;
import com.wzy.shiro.util.StringUtils;
/**
 * 
 * @author Wen  用户dao层实现类
 *
 */
@Service
public class UserHandlerImpl implements IUserHandler{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User addUser(User user) throws ShiroException {
		userMapper.insertSelective(user);
		return user;
	}

	@Override
	public User updateUser(User user) throws ShiroException {
		if(user.getId()==null || "".equals(user.getId())){
			 throw new ShiroException("操作失败！");
		}
		userMapper.updateByPrimaryKeySelective(user);
		return user;
	}

	@Override
	public void deleteUserById(Long userId) throws ShiroException {
		userMapper.deleteByPrimaryKey(userId);	
	}

	@Override
	public User findUserById(Long userId) throws ShiroException {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public List<User> findAllUser() throws ShiroException {
		UserExample example=new UserExample();
		List<User> list=userMapper.selectByExample(example);
		if(list==null ||list.size()==0){
			return new ArrayList<User>();
		}
		return list;
	}

	@Override
	public User findUserByName(String name)  {
		UserExample example=new UserExample();
		UserExample.Criteria criteria=example.createCriteria();
		if(StringUtils.isNotEmpty(name)){
			criteria.andUsernameEqualTo(name);
		}
		List<User> list=null;
		try {
			list = userMapper.selectByExample(example);
		} catch (Exception e) {
           throw new RuntimeException(e.getMessage());			
		}
		if(list==null ||list.size()==0){
			return null;
		}
		return list.get(0);
	}
}
