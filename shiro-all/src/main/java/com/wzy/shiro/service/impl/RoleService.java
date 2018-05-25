package com.wzy.shiro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzy.shiro.dao.entity.Resource;
import com.wzy.shiro.dao.entity.Role;
import com.wzy.shiro.exception.ShiroException;
import com.wzy.shiro.handler.IResourceHandler;
import com.wzy.shiro.handler.IRoleHandler;
import com.wzy.shiro.service.IRoleService;
@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private IRoleHandler roleHandler;
	@Autowired
	private IResourceHandler resourceHandler;

	@Override
	public Role addRole(Role role) throws ShiroException {
		return roleHandler.addRole(role);
	}

	@Override
	public Role updateRole(Role role) throws ShiroException {
		return roleHandler.updateRole(role);
	}

	@Override
	public void deleteRoleById(Long roleId) throws ShiroException {
		roleHandler.deleteRoleById(roleId);
	}

	@Override
	public Role fingRoleById(Long roleId) throws ShiroException {
		return roleHandler.fingRoleById(roleId);
	}

	@Override
	public List<Role> findAnnRole() throws ShiroException {
		return roleHandler.findAnnRole();
	}

	@Override
	public Set<String> findRolesName(Long... roleIds) throws ShiroException {
		Set<String> set=new HashSet<String>();
		for(Long roleId:roleIds){
			Role role=roleHandler.fingRoleById(roleId);
			set.add(role.getRole());
		}
		return set;
	}

	@Override
	public Set<String> finPermissionsByRoleIds(Long... roleIds) throws ShiroException {
		Set<String> set=new HashSet<String>();
		for(Long roleId:roleIds){
			Role role=roleHandler.fingRoleById(roleId);
			String[] resourceIds=role.getResourceIds().split(",");
			for(String resourceId:resourceIds){
				Resource resource=resourceHandler.findResourceById(Long.valueOf(resourceId));
				set.add(resource.getPermission());
			}
		}
		return set;
	}

}
