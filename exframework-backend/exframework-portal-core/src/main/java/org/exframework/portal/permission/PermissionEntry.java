package org.exframework.portal.permission;

import org.exframework.portal.metadata.sys.Account;
import org.exframework.portal.metadata.sys.Permission;

import java.util.Map;

/**
 * 权限实体接口
 * @author rwe
 * @since 2021年6月30日
 *
 * Copyright @ 2021 
 * 
 */
public interface PermissionEntry {
	
	String ALLPASSKEY = "all";
	
	default boolean hasRight(Account account,String moduleId) {
		if(ALLPASSKEY.equals(moduleId)){
			return true;
		}
		else {
			Map<String, Permission> modules = account.getPermissions();
			return modules.containsKey(moduleId);
		}
	}
	
	
	
}
