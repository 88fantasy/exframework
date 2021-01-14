package com.gzmpc.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.gzmpc.portal.metadata.grid.Column;
import com.gzmpc.portal.metadata.hov.Hov;
import com.gzmpc.portal.metadata.hov.HovQueryParams;

/**
 *
 * Author: rwe
 * Date: Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName( value = "sys_hov", autoResultMap = true )
public class HovDO extends Hov {

	private static final long serialVersionUID = 7765402758315833737L;

	@TableId
	private String code;
	
	@TableField
	private String name;
	
	@TableField
	private String description;
	
	@TableField(typeHandler = JacksonTypeHandler.class)
	@ColumnType(value = MySqlTypeConstant.VARCHAR, length = 2000)
	private HovQueryParams[] queryParams;
	
	@TableField(typeHandler = JacksonTypeHandler.class)
	@ColumnType(value = MySqlTypeConstant.VARCHAR, length = 2000)
	private Column[] columns;
	
	@TableField
	private String dataClass;
	
	@TableField
	private String returnKey;
}
