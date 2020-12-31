package com.gzmpc.core.entity;

import org.springframework.beans.BeanUtils;

/**
 *
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.metadata.sys.Permission;

@TableName("sys_permission")
public class PermissionDO implements AutoMapper<Permission>{

	private static final long serialVersionUID = 5821041616688028717L;

	@TableId
	private String code;

	@TableField
	private String name;

	@TableField
	private String comment;

	@TableField
	private String permissionEntry;

	@TableField
	private String parentPermissionKey;

	@TableField
	private Long permissionOrder;

	@TableField
	private Long permissionType;

	@TableField
	private boolean valid;

	@TableField
	private boolean notDisplay;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

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

	public Long getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(Long permissionType) {
		this.permissionType = permissionType;
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
}
