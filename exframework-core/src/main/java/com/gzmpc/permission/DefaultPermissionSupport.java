package com.gzmpc.permission;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.sys.Module;

@Service
public class DefaultPermissionSupport implements PermissionSupport {

	@Override
	public boolean hasRight(Account account, String moduleId) {
		if(ALLPASSKEY.equals(moduleId)){
			return true;
		}
		else {
			Map<String, Module> modules = account.getModules();
			return modules.containsKey(moduleId);
		}
	}

}
