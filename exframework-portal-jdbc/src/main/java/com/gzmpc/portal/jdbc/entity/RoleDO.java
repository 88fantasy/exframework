package com.gzmpc.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.portal.metadata.sys.Role;
import com.gzmpc.support.doc.annotation.TableDoc;
import com.gzmpc.support.doc.annotation.TableFieldDoc;

/**
 * 角色
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("角色")
@TableName( value = "sys_role", excludeProperty = "children")
public class RoleDO extends Role {

	private static final long serialVersionUID = 939184434239094313L;

	/**
	 * 角色编码
	 */
	@TableFieldDoc("角色编码")
	@TableId( type = IdType.INPUT)
	private String code;
	
	/**
	 * 角色名称
	 */
	@TableFieldDoc("角色名称")
	@TableField
	private String name;
	
	/**
	 * 角色描述
	 */
	@TableFieldDoc("角色描述")
	@TableField
	private String description;
	
	/**
	 * 父角色
	 */
	@TableFieldDoc("父角色")
	@TableField
	private String  parentKey;
}
