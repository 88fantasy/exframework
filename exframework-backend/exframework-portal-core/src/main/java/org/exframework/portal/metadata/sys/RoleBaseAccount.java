package org.exframework.portal.metadata.sys;

import java.util.Collection;

/**
 *
 * @author rwe Date: Dec 28, 2020
 *
 * Copyright @ 2020
 * 
 */
public class RoleBaseAccount extends Account {

	private static final long serialVersionUID = 4155267853939278551L;
	
	/**
	 * 拥有的角色
	 */
	private Collection<Role> roles;


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public Role getRoleByKey(String key) {
		if (roles == null) {
			return null;
		}
		for (Role r : roles) {
			if (r.getCode().equals(key)) {
				return r;
			}
		}
		return null;
	}

}
