package com.wzy.shiro.service;

import java.util.List;
import java.util.Set;

import com.wzy.shiro.dao.entity.Role;
import com.wzy.shiro.exception.ShiroException;

public interface IRoleService {

	 public Role addRole(Role role) throws ShiroException;
	  
	  public Role updateRole(Role role)throws ShiroException;
	  
	  public void deleteRoleById(Long roleId)throws ShiroException;
	  
	  public Role fingRoleById(Long roleId) throws ShiroException;
	  
	  public List<Role> findAnnRole()throws ShiroException;
	  /**
	   * ͨ����ɫID���ϻ�ȡ��ɫ����
	   * @param roleIds
	   * @return
	   * @throws ShiroException
	   */
	  public Set<String> findRolesName(Long ...roleIds)throws ShiroException;
	  /**
	   * ��ȡ��ɫ���϶�Ӧ��Ȩ���ַ�������
	   * @param roleIds
	   * @return
	   * @throws ShiroException
	   */
	  public Set<String> finPermissionsByRoleIds(Long...roleIds)throws ShiroException;
}
