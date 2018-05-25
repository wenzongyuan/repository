package com.wzy.shiro.handler;

import java.util.List;

import com.wzy.shiro.dao.entity.Resource;
import com.wzy.shiro.exception.ShiroException;

public interface IResourceHandler {

	
	public Resource addResource(Resource resource)throws ShiroException;
	
	public Resource upddateResource(Resource resource)throws ShiroException;
	
	public void deleteResourceById(Long resourceId)throws ShiroException;
	
	public Resource findResourceById(Long resourceId)throws ShiroException;
	
	public List<Resource> findAllResource()throws ShiroException;
}
