package org.exframework.portal.jdbc.entity.security;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.exframework.portal.metadata.sys.Role;
import org.exframework.support.doc.annotation.TableDoc;
import org.exframework.support.doc.annotation.TableFieldDoc;

/**
 * 角色
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("角色")
@TableName( value = "sys_role", excludeProperty = {"children", "permissionMap"})
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
	private String parentKey;
}
