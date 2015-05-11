package com.ping.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ping.dao.ResourceDao;
import com.ping.domain.Resource;
import com.ping.service.ResourceService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public Resource findResourceByResourceId(String resourceId) {
		return resourceDao.get(resourceId);
	}
	
	@Override
	public List<Resource> findAll() {
		return resourceDao.loadAll();
	}
	
	@Override
	public Set<String> findPermissionsByResourceIds(Set<String> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for (String resourceId : resourceIds) {
            Resource resource = findResourceByResourceId(resourceId);
            if (resource != null && resource.getPermission() != null) {
            	permissions.add(resource.getPermission());
            }
        }
        
        return permissions;
    }
	
	@Override
	public List<Resource> findMenusByPermission(Set<String> permissions) {
		List<Resource> resources = findAll();
		List<Resource> menus = new ArrayList<Resource>();
		
		for (Resource resource : resources) {
			if (resource.getResourceType() != Resource.ResourceType.menu) {
				continue;
			}
			if (!hasPermission(permissions, resource)) {
				continue;
			}
			menus.add(resource);
		}
		return menus;
	}

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if (resource.getPermission() == null) {
            return true;
        }
        
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        
        return false;
    }

}
