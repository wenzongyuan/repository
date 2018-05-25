package com.wzy.shiro.service;

import java.util.List;
import java.util.Set;

import com.wzy.shiro.dao.entity.User;
import com.wzy.shiro.exception.ShiroException;

public interface IUserService {

	public User addUser(User user) throws ShiroException;

	public User updateUser(User user) throws ShiroException;

	public void deleteUserById(Long userId) throws ShiroException;

	public User findUserById(Long userId) throws ShiroException;

	public List<User> findAllUser() throws ShiroException;

	public User findUserByName(String name);

	/**
	 * �ù��û�����ȡ���û������н�ɫ
	 * 
	 * @param username
	 * @return
	 * @throws ShiroException
	 */
	public Set<String> findRoles(String username) throws ShiroException;

	/**
	 * ͨ���û�����ȡ���û�������Ȩ���ַ���
	 * 
	 * @param username
	 * @return
	 * @throws ShiroException
	 */
	public Set<String> findPermissions(String username) throws ShiroException;;

}
