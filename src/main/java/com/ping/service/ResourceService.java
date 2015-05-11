package com.ping.service;

import com.ping.domain.Resource;

import java.util.List;
import java.util.Set;

public interface ResourceService {
	
	public Resource findResourceByResourceId(String resourceId);
	
	public Set<String> findPermissionsByResourceIds(Set<String> resourceIds);
	
	public List<Resource> findMenusByPermission(Set<String> permissions);
	
	public List<Resource> findAll();

}
