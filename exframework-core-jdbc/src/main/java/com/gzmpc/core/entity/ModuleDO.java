package com.gzmpc.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.metadata.module.ModuleEntity;

/**
 * 功能实体类
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName("sys_module")
public class ModuleDO extends ModuleEntity {

	private static final long serialVersionUID = 5740415660479029153L;
	
	@TableId
	private String code;
	
	@TableField
	private String name;
	
	@TableField
	private String comment;
	
	@TableField
	private boolean valid;
}