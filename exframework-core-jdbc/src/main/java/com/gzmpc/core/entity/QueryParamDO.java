package com.gzmpc.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.metadata.queryparam.QueryParam;

/**
 *
 * Author: rwe
 * Date: Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName("sys_queryparam")
public class QueryParamDO extends QueryParam {

	private static final long serialVersionUID = -7715558276022717832L;

	@TableId
	private String code;
	
	@TableField
	private String name;
	
	@TableField
	private String comment;
	
	@TableField
	private String moduleKey;
}
