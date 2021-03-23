package com.gzmpc.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gzmpc.support.doc.annotation.TableDoc;
import com.gzmpc.support.doc.annotation.TableFieldDoc;

/**
 * 帐号角色关系
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("帐号角色关系")
@TableName("sys_account_role")
public class AccountRoleDO {

	/**
	 * 主键
	 */
	@TableFieldDoc("主键")
	@TableId( type = IdType.ASSIGN_ID)
	private String id;
	
	/**
	 * 帐号
	 */
	@TableFieldDoc("帐号id")
	@TableField
	private String account;
	
	/**
	 * 角色
	 */
	@TableFieldDoc("角色code")
	@TableField
	private String role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
