package com.wzy.shiro.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzy.shiro.dao.entity.Organization;
import com.wzy.shiro.dao.entity.OrganizationExample;
import com.wzy.shiro.dao.mapper.OrganizationMapper;
import com.wzy.shiro.exception.ShiroException;
import com.wzy.shiro.handler.IOrganizationHandler;
@Service
public class OrganizationHandlerImpl implements IOrganizationHandler{
	
	@Autowired
	private OrganizationMapper organizationMapper;

	@Override
	public Organization addOrganization(Organization organization) throws ShiroException {
		organizationMapper.insertSelective(organization);
		return organization;
	}

	@Override
	public Organization updateOrganization(Organization organization) throws ShiroException {
		if(organization.getId()==null||"".equals(organization.getId().toString())){
			throw new ShiroException("²Ù×÷Ê§°Ü£¡");
		}
		organizationMapper.updateByPrimaryKeySelective(organization);
		return organization;
	}

	@Override
	public void deleteOrganization(Long organizationId) throws ShiroException {
		organizationMapper.deleteByPrimaryKey(organizationId);	
	}

	@Override
	public Organization findOrganizationById(Long organizationId) throws ShiroException {
		return organizationMapper.selectByPrimaryKey(organizationId);
	}

	@Override
	public List<Organization> findAllOrganization() throws ShiroException {
		OrganizationExample example =new OrganizationExample();
		return organizationMapper.selectByExample(example);
	}

}
