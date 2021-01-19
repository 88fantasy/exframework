package com.gzmpc.portal.jdbc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.portal.metadata.sys.Logger;

/**
 * 日志表
 * Author: rwe Date: Jan 19, 2021
 *
 * Copyright @ 2021
 * 
 */
@TableName(value = "sys_log")
public class LogDO extends Logger {

	@TableId( type = IdType.ASSIGN_ID)
	private Long loggerId;

	@TableField
	private String moduleCode;

	@TableField
	private String content;

	@TableField
	private String sourceId;

	@TableField
	private String param;

	@TableField
	private String account;

	@TableField
	private String accountName;

	@TableField
	private Date credate;
}
