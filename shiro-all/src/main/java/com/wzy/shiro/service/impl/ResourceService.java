package com.wzy.shiro.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzy.shiro.dao.entity.Resource;
import com.wzy.shiro.exception.ShiroException;
import com.wzy.shiro.handler.IResourceHandler;
import com.wzy.shiro.handler.impl.ResourceType;
import com.wzy.shiro.service.IResourceService;
import com.wzy.shiro.util.StringUtils;
@Service
public class ResourceService implements IResourceService {

	@Autowired
	private IResourceHandler resourceHandler;
	
	
	@Override
	public Resource addResource(Resource resource) throws ShiroException {
		return resourceHandler.addResource(resource);
	}

	@Override
	public Resource upddateResource(Resource resource) throws ShiroException {
		return resourceHandler.upddateResource(resource);
	}

	@Override
	public void deleteResourceById(Long resourceId) throws ShiroException {
		resourceHandler.deleteResourceById(resourceId);
	}

	@Override
	public Resource findResourceById(Long resourceId) throws ShiroException {
		return resourceHandler.findResourceById(resourceId);
	}

	@Override
	public List<Resource> findAllResource() throws ShiroException {
		return resourceHandler.findAllResource();
	}

	@Override
	public Set<String> findPermissionsByResourceIds(Set<Long> resourceIds) throws ShiroException {
		Set<String> set=new HashSet<String>();
		for(Long resourceId:resourceIds){
			Resource resource=resourceHandler.findResourceById(resourceId);  
			set.add(resource.getPermission());
		}
		return set;
	}
    //��ȡ��ԴΪ��ȡ�˵�����Դ
	@Override
	public List<Resource> findMenus(Set<String> permissions) throws ShiroException {
		List<Resource> allResource=this.findAllResource();
		List<Resource> menuResource=new ArrayList<Resource>();
		for(Resource resource:allResource){
			if(resource.getParentId()==0){ 
				//�����Դ�ĸ���IDΪ�վͽ�������ѭ������ʼ��һ��ѭ��
				continue;
			}
			if(resource.getType()!=ResourceType.MENU.getInfo()){
				//�����Դ���Ͳ��ǲ˵��Ľ�������ѭ������ʼ��һ��ѭ��
			   continue;	
			}
			if(!hasPermission(permissions,resource)){
				//�����Դ��Ȩ�޲��ڲ�����������ģ���������ѭ������ʼ��һ��ѭ��
				continue;
			}
			menuResource.add(resource);
		}
		return menuResource;
	}

	private boolean hasPermission(Set<String> permissions, Resource resource) {
		if(StringUtils.isEmpty(resource.getPermission())){
			return true;
		}
		for(String permission:permissions){
			//��Ȩ���ַ���תΪȨ�޶���
			WildcardPermission wp=new WildcardPermission(permission);
			//��ȡ��Դ��Ȩ���ַ�����תΪȨ�޶���
			WildcardPermission Respermissionwp=new WildcardPermission(resource.getPermission());
			//����WildcardPermission��imples�����ж�Ȩ���Ƿ�ƥ�䡣
			if(wp.implies(Respermissionwp)||Respermissionwp.implies(wp)){
				return true;
			}
		}
		return false;
	}

}
