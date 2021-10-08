package org.exframework.portal.permission;

import org.exframework.portal.metadata.Meta;
import org.exframework.portal.metadata.sys.Permission;

import java.util.List;

/**
 * 权限组
 * 
 * @author rwe
 * @since 2021年6月30日
 *
 * 
 */
public class PermissionResource extends Meta {

	private static final long serialVersionUID = -8164030726669013838L;
	
	List<Permission> permissions;

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
}
