package com.wzy.shiro.handler;

import java.util.List;

import com.wzy.shiro.dao.entity.Role;
import com.wzy.shiro.exception.ShiroException;

public interface IRoleHandler {
	
	
  public Role addRole(Role role) throws ShiroException;
  
  public Role updateRole(Role role)throws ShiroException;
  
  public void deleteRoleById(Long roleId)throws ShiroException;
  
  public Role fingRoleById(Long roleId) throws ShiroException;
  
  public List<Role> findAnnRole()throws ShiroException;
	
}
