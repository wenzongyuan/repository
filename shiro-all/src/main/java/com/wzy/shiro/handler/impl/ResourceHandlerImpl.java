package com.wzy.shiro.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzy.shiro.dao.entity.Resource;
import com.wzy.shiro.dao.entity.ResourceExample;
import com.wzy.shiro.dao.mapper.ResourceMapper;
import com.wzy.shiro.exception.ShiroException;
import com.wzy.shiro.handler.IResourceHandler;
@Service
public class ResourceHandlerImpl implements IResourceHandler{
	
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public Resource addResource(Resource resource) throws ShiroException {
		resourceMapper.insertSelective(resource);
		return resource;
	}

	@Override
	public Resource upddateResource(Resource resource) throws ShiroException {
		if(resource.getId()==null||"".equals(resource.getId().toString())){
			throw new ShiroException("²Ù×÷Ê§°Ü£¡");
		}
		resourceMapper.updateByPrimaryKeySelective(resource);
		return resource;
	}

	@Override
	public void deleteResourceById(Long resourceId) throws ShiroException {
		resourceMapper.deleteByPrimaryKey(resourceId);
	}

	@Override
	public Resource findResourceById(Long resourceId) throws ShiroException {
		Resource resource=resourceMapper.selectByPrimaryKey(resourceId);
		return resource;
	}

	@Override
	public List<Resource> findAllResource() throws ShiroException {
		ResourceExample example =new ResourceExample();
		return resourceMapper.selectByExample(example);
	}

}
