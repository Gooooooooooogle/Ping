package com.ping.service;

import com.ping.domain.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

	public void createRole(Role role);
	
	public void updateRole(Role role);
	
	public void deleteRole(Role role);
	
	public Role findRoleByRoleId(String roleId);
	
	public Set<String> findRolesByRoleIds(String roleIdStr);
	
	public Set<String> findPermissionsByRoleIds(String roleIdStr);
	
	public List<Role> findAll();
	
}
