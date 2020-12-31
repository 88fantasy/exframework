package com.gzmpc.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.metadata.sys.Role;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName("sys_role")
public class RoleDO extends Role {

	private static final long serialVersionUID = 939184434239094313L;

	@TableId
	private String code;
	
	@TableField
	private String name;
	
	@TableField
	private String comment;
	
	@TableField
	private String  parentKey;
}