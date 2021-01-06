package com.gzmpc.core.entity;

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
import com.gzmpc.core.entity.mapper.AutoMapper;
import com.gzmpc.metadata.sys.Permission;

@TableName( value = "sys_permission", excludeProperty = "children")
public class PermissionDO extends Permission implements AutoMapper<Permission> {

	private static final long serialVersionUID = 5821041616688028717L;

	@TableId
	private String code;

	@TableField
	private String name;

	@TableField
	private String description;

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
}
