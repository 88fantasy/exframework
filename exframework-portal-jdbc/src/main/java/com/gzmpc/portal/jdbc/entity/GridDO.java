package com.gzmpc.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.portal.metadata.grid.Grid;

/**
 * 表格实体类
 * Author: rwe
 * Date: Dec 30, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName("sys_grid")
public class GridDO extends Grid {


	private static final long serialVersionUID = -5339578558986848710L;

	@TableId
	private String code;
	
	@TableField
	private String name;
	
	@TableField
	private String description;
	
	@TableField
	private String moduleKey;
	
	@TableField
	private String dataSource;
	
	@TableField
	private String dataIndex;
	
	@TableField
	private String queryType;
	
	@TableField
	private String autoQuery;
	
	@TableField
	private String pagesize;
	
	@TableField
	private String querymoduleid;
	
	@TableField
	private String downloadmoduleid;
}
