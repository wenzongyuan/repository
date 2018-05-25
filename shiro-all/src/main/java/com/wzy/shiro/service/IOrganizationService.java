package com.wzy.shiro.service;

import java.util.List;

import com.wzy.shiro.dao.entity.Organization;
import com.wzy.shiro.exception.ShiroException;

public interface IOrganizationService {
	
	public Organization addOrganization(Organization organization)throws ShiroException;
	
	public Organization updateOrganization(Organization organization)throws ShiroException;
	
	public void deleteOrganization(Long organizationId)throws ShiroException;
	
	public Organization findOrganizationById(Long organizationId)throws ShiroException;
	
	public List<Organization> findAllOrganization()throws ShiroException;
}
