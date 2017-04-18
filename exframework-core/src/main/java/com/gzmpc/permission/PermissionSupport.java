package com.gzmpc.permission;

import com.gzmpc.metadata.sys.Account;

public interface PermissionSupport {
	public final String ALLPASSKEY = "all";

	public boolean hasRight(Account account,String moduleId);
	
}
