package org.exframework.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.exframework.portal.metadata.queryparam.QueryParam;

/**
 *
 * Author: rwe
 * Date: Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName( value = "sys_queryparam", excludeProperty = "queryParamItems")
public class QueryParamDO extends QueryParam {

	private static final long serialVersionUID = -7715558276022717832L;

	@TableId
	private String code;
	
	@TableField
	private String name;
	
	@TableField
	private String description;
	
	@TableField
	private String moduleKey;
}
