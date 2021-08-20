package org.exframework.portal.jdbc.entity.security;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.exframework.support.doc.annotation.TableDoc;
import org.exframework.support.doc.annotation.TableFieldDoc;

/**
 * 角色权限
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("角色权限")
@TableName("sys_role_permission")
public class RolePermissionDO {

	/**
	 * 主键
	 */
	@TableFieldDoc("主键")
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	
	/**
	 * 角色编码
	 */
	@TableFieldDoc("角色编码")
	@TableField
	private String role;
	
	/**
	 * 权限编码
	 */
	@TableFieldDoc("权限编码")
	@TableField
	private String permission;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	
	
}
