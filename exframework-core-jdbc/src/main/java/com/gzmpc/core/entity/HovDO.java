package com.gzmpc.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.metadata.hov.Hov;

/**
 *
 * Author: rwe
 * Date: Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName("sys_hov")
public class HovDO extends Hov {

	private static final long serialVersionUID = 7765402758315833737L;

	@TableId
	private String code;
	
	@TableField
	private String name;
	
	@TableField
	private String comment;
	
	@TableField
	private String returnKey;
}
