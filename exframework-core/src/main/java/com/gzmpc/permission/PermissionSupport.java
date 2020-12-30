package com.gzmpc.permission;

import java.util.Map;

import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.sys.Permission;

public interface PermissionSupport {

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
