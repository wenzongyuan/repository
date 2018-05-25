package com.wzy.shiro.service;

import java.util.List;
import java.util.Set;

import com.wzy.shiro.dao.entity.Resource;
import com.wzy.shiro.exception.ShiroException;

public interface IResourceService {

	public Resource addResource(Resource resource)throws ShiroException;
	
	public Resource upddateResource(Resource resource)throws ShiroException;
	
	public void deleteResourceById(Long resourceId)throws ShiroException;
	
	public Resource findResourceById(Long resourceId)throws ShiroException;
	
	public List<Resource> findAllResource()throws ShiroException;
	/**
	 * 通过资源ID集合获取对应的权限字符串
	 * @param resourceIds
	 * @return
	 * @throws ShiroException
	 */
	public Set<String> findPermissionsByResourceIds(Set<Long> resourceIds)throws ShiroException;
	/**
	 * 通过权限字符串获取菜单列表
	 * @param permissions
	 * @return
	 * @throws ShiroException
	 */
	public List<Resource> findMenus(Set<String> permissions)throws ShiroException;
}
