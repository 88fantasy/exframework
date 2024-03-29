package org.exframework.portal.metadata.sys;

import org.exframework.portal.metadata.Meta;

import java.io.Serializable;
import java.util.List;

/**
 * 权限实体
 * 
 * @author rwe
 *
 */
public class Permission extends Meta implements Serializable {

	private static final long serialVersionUID = -7275087103665173246L;

	
	private String permissionEntry;
	
	/**
	 * 父权限 Id
	 */
	private String parentPermissionKey;
	
	/**
	 * 排序
	 */
	private Long permissionOrder;

	
	/**
	 * 生效
	 */
	private boolean valid;
	
	/**
	 * 不显示
	 */
	private boolean notDisplay;
	

	private List<Permission> children;


	public String getPermissionEntry() {
		return permissionEntry;
	}


	public void setPermissionEntry(String permissionEntry) {
		this.permissionEntry = permissionEntry;
	}


	public String getParentPermissionKey() {
		return parentPermissionKey;
	}


	public void setParentPermissionKey(String parentPermissionKey) {
		this.parentPermissionKey = parentPermissionKey;
	}


	public Long getPermissionOrder() {
		return permissionOrder;
	}


	public void setPermissionOrder(Long permissionOrder) {
		this.permissionOrder = permissionOrder;
	}

	public boolean isValid() {
		return valid;
	}


	public void setValid(boolean valid) {
		this.valid = valid;
	}


	public boolean isNotDisplay() {
		return notDisplay;
	}


	public void setNotDisplay(boolean notDisplay) {
		this.notDisplay = notDisplay;
	}


	public List<Permission> getChildren() {
		return children;
	}


	public void setChildren(List<Permission> children) {
		this.children = children;
	}
	
	

}