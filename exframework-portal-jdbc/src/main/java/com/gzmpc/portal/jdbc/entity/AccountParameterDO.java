package com.gzmpc.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.portal.metadata.sys.AccountParameter;
import com.gzmpc.support.doc.annotation.TableDoc;
import com.gzmpc.support.doc.annotation.TableFieldDoc;

/**
 * 帐号参数
 * Author: rwe
 * Date: Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("帐号参数")
@TableName("sys_accountparam")
public class AccountParameterDO extends AccountParameter {

	private static final long serialVersionUID = 1988325593021911178L;
	/**
	 * 主键
	 */
	@TableFieldDoc("主键")
	@TableId( type = IdType.ASSIGN_ID)
	private Long id;
	
	/**
	 * 代码
	 */
	@TableFieldDoc("代码")
	@TableField
	private String code;
	
	/**
	 * 名称
	 */
	@TableFieldDoc("名称")
	@TableField
	private String name;
	
	/**
	 * 描述
	 */
	@TableFieldDoc("描述")
	@TableField
	private String description;
	
	/**
	 * 帐号
	 */
	@TableFieldDoc("帐号")
	@TableField
	private String account;
	
	/**
	 * 值
	 */
	@TableFieldDoc("值")
	@TableField
	private String value;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
