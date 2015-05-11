package com.ping.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.ping.dao.RoleDao;
import com.ping.domain.Role;
import com.ping.service.ResourceService;
import com.ping.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	private ResourceService resourceService;
	
	@Override
	public void createRole(Role role) {
		roleDao.save(role);
	}
	
	@Override
	public void updateRole(Role role) {
		roleDao.update(role);
	}
	
	@Override
	public void deleteRole(Role role) {
		roleDao.delete(role);
	}
	
	@Override
	public Role findRoleByRoleId(String roleId) {
		return roleDao.get(roleId);
	}
	
	@Override
	public List<Role> findAll() {
		return roleDao.loadAll();
	}
	
	@Override
	public Set<String> findRolesByRoleIds(String roleIdStr) {
		Set<String> roles = new HashSet<String>();
		String[] roleIds = roleIdStr.split(",");
		
		Role role = null;
		for (String roleId : roleIds) {
			role = findRoleByRoleId(roleId);
			if (role != null) {
				roles.add(role.getRole());
			}
		}
		
		return roles;
	}
	
	@Override
	public Set<String> findPermissionsByRoleIds(String roleIdStr) {
		Set<String> resourceIds = new HashSet<String>();
		String[] roleIds = roleIdStr.split(",");
		
		Role role = null;
		for (String roleId : roleIds) {
			role = findRoleByRoleId(roleId);
			if (role != null) {
				resourceIds.add(role.getResourceIds());
			}
		}
		
		return resourceService.findPermissionsByResourceIds(resourceIds);
	}

	@Resource
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
}
