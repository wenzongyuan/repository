package com.wzy.shiro.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzy.shiro.dao.entity.Role;
import com.wzy.shiro.dao.entity.RoleExample;
import com.wzy.shiro.dao.mapper.RoleMapper;
import com.wzy.shiro.exception.ShiroException;
import com.wzy.shiro.handler.IRoleHandler;
@Service
public class RoleHandlerImpl implements IRoleHandler{
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role addRole(Role role) throws ShiroException {
		roleMapper.insertSelective(role);
		return role;
	}

	@Override
	public Role updateRole(Role role) throws ShiroException {
		if(role.getId()==null||"".equals(role.getId().toString())){
			throw new ShiroException("²Ù×÷Ê§°Ü£¡");
		}
		roleMapper.updateByPrimaryKeySelective(role);
		return role;
	}

	@Override
	public void deleteRoleById(Long roleId) throws ShiroException {
		roleMapper.deleteByPrimaryKey(roleId);	
	}

	@Override
	public Role fingRoleById(Long roleId) throws ShiroException {
		Role role=roleMapper.selectByPrimaryKey(roleId);
		return role;
	}

	@Override
	public List<Role> findAnnRole() throws ShiroException {
		RoleExample example = new RoleExample();
	  List<Role> list=roleMapper.selectByExample(null);
		return list;
	}

}
