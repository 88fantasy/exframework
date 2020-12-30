package com.gzmpc.metadata.sys;


import java.util.List;
import java.util.Map;

import com.gzmpc.metadata.Meta;

/**
 * 角色
 * 
 * @author rwe
 *
 */
public class Role extends Meta {

	private static final long serialVersionUID = -7626696033258068195L;

	/**
	 * 父角色
	 */
	private String  parentKey;
	
	private List<Role> children;
	/**
	 * 
	 */
	private Map<String,Permission> permissionMap;

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public List<Role> getChildren() {
		return children;
	}

	public void setChildren(List<Role> children) {
		this.children = children;
	}

	public Map<String, Permission> getPermissionMap() {
		return permissionMap;
	}

	public void setPermissionMap(Map<String, Permission> permissionMap) {
		this.permissionMap = permissionMap;
	}

	
}