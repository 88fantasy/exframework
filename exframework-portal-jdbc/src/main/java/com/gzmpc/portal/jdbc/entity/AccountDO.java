package com.gzmpc.portal.jdbc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.support.doc.annotation.TableDoc;
import com.gzmpc.support.doc.annotation.TableFieldDoc;

/**
 * 帐号实体类
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("帐号")
@TableName( value = "sys_account", excludeProperty = {"permissions", "modules"})
public class AccountDO extends Account {

	private static final long serialVersionUID = -3573124333224304627L;

	/**
	 * 登陆账号ID
	 */
	@TableFieldDoc("登陆账号ID")
	@TableId
	private String account;

	/**
	 * 密码
	 */
	@TableFieldDoc("密码")
	@TableField
	private String password;

	/**
	 * 帐号名称
	 */
	@TableFieldDoc("帐号名称")
	@TableField
	private String accountName;

	/**
	 * 最近登录日期
	 */
	@TableFieldDoc("最近登录日期")
	@TableField
	@ColumnType(value = MySqlTypeConstant.DATETIME)
	private Date lastLoginDate;

	/**
	 * 最近登录 IP
	 */
	@TableFieldDoc("最近登录 IP")
	@TableField
	private String lastLoginIp;

	/**
	 * 最近登录地区
	 */
	@TableFieldDoc("最近登录地区")
	@TableField
	private String lastLoginArea;

	/**
	 * 帐号状态
	 */
	@TableFieldDoc("帐号状态")
	@TableField
	@EnumValue
	@ColumnType(value = MySqlTypeConstant.VARCHAR)
	private AccountStatusTypeEnum accountStatus;

	/**
	 * 截止日期
	 */
	@TableFieldDoc("截止日期")
	@TableField
	private Date accountInvalidDate;
}
