package com.wzy.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzy.shiro.dao.entity.Organization;
import com.wzy.shiro.exception.ShiroException;
import com.wzy.shiro.handler.IOrganizationHandler;
import com.wzy.shiro.service.IOrganizationService;
@Service
public class OrganizationService implements IOrganizationService {

	@Autowired
	private IOrganizationHandler organizationHandler;
	
	@Override
	public Organization addOrganization(Organization organization) throws ShiroException {
		return organizationHandler.addOrganization(organization);
	}

	@Override
	public Organization updateOrganization(Organization organization) throws ShiroException {
		return organizationHandler.updateOrganization(organization);
	}

	@Override
	public void deleteOrganization(Long organizationId) throws ShiroException {
		organizationHandler.deleteOrganization(organizationId);
	}

	@Override
	public Organization findOrganizationById(Long organizationId) throws ShiroException {
		return organizationHandler.findOrganizationById(organizationId);
	}

	@Override
	public List<Organization> findAllOrganization() throws ShiroException {
		return organizationHandler.findAllOrganization();
	}

}
