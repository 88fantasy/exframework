package org.exframework.portal.jdbc.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.exframework.portal.metadata.sys.Logger;
import org.exframework.support.doc.annotation.TableDoc;
import org.exframework.support.doc.annotation.TableFieldDoc;

import java.util.Date;

/**
 * 日志表
 * @author rwe Date: Jan 19, 2021
 *
 * Copyright @ 2021
 * 
 */
@TableDoc("日志")
@TableName(value = "sys_log")
public class LogDO extends Logger {

	/**
	 * 日志主键
	 */
	@TableFieldDoc("日志主键")
	@TableId( type = IdType.ASSIGN_ID)
	private Long loggerId;

	/**
	 * 模块编码
	 */
	@TableFieldDoc("模块编码")
	@TableField
	private String moduleCode;

	/**
	 * 日志内容
	 */
	@TableFieldDoc("日志内容")
	@TableField
	private String content;

	/**
	 * 来源单据
	 */
	@TableFieldDoc("来源单据")
	@TableField
	private String sourceId;

	/**
	 * 日志参数
	 */
	@TableFieldDoc("日志参数")
	@TableField
	private String param;

	/**
	 * 关联帐号
	 */
	@TableFieldDoc("关联帐号")
	@TableField
	private String account;

	/**
	 * 关联帐号名称
	 */
	@TableFieldDoc("关联帐号名称")
	@TableField
	private String accountName;

	/**
	 * 创建时间
	 */
	@TableFieldDoc("创建时间")
	@TableField
	private Date credate;
}
