package org.exframework.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.exframework.portal.metadata.module.ModuleBase;

/**
 * 功能实体类
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName( value = "sys_module", excludeProperty = {"dataItems", "permissions", "hovs"})
public class ModuleDO extends ModuleBase {

	private static final long serialVersionUID = 5740415660479029153L;
	
	@TableId
	private String code;
	
	@TableField
	private String name;
	
	@TableField
	private String description;
	
	@TableField
	private boolean valid;
	
	@TableField
	private long star;
}
