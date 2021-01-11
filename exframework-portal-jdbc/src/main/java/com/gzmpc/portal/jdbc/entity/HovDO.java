package com.gzmpc.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.portal.metadata.hov.Hov;

/**
 *
 * Author: rwe
 * Date: Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName( value = "sys_hov", excludeProperty = {"queryParamItems", "columns"})
public class HovDO extends Hov {

	private static final long serialVersionUID = 7765402758315833737L;

	@TableId
	private String code;
	
	@TableField
	private String name;
	
	@TableField
	private String description;
	
	@TableField
	private String returnKey;
}
