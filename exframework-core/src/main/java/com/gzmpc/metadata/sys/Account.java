package com.gzmpc.metadata.sys;

import java.util.*;

import com.gzmpc.metadata.dict.DictionaryEnum;
import com.gzmpc.metadata.module.Module;

import java.io.*;

/**
 * 帐号实体类
 * 
 * @author rwe
 *
 */
public class Account implements Serializable {

	private static final long serialVersionUID = -1150456135277097507L;

	/**
	 * 登陆账号ID
	 */
	private String accountId;

	/**
	 * 密码
	 */
	private String password;

	// 帐号名称
	private String accountName;

	/**
	 * 最近登录日期
	 */
	private Date lastLoginDate;

	/**
	 * 最近登录 IP
	 */
	private String lastLoginIp;

	/**
	 * 最近登录地区
	 */
	private String lastLoginArea;

	/**
	 * 帐号状态
	 */
	private AccountStatusTypeEnum accountStatus;

	// 截止日期
	private Date accountInvalidDate;

	private Map<String, Permission> permissions;

	private List<Module> modules;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginArea() {
		return lastLoginArea;
	}

	public void setLastLoginArea(String lastLoginArea) {
		this.lastLoginArea = lastLoginArea;
	}

	public AccountStatusTypeEnum getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatusTypeEnum accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Date getAccountInvalidDate() {
		return accountInvalidDate;
	}

	public void setAccountInvalidDate(Date accountInvalidDate) {
		this.accountInvalidDate = accountInvalidDate;
	}

	public Map<String, Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public enum AccountStatusTypeEnum implements DictionaryEnum<AccountStatusTypeEnum> {

		/**
		 * 有效
		 */
		VALID("valid", "有效"),

		/**
		 * 失效
		 */
		INVALID("invalid", "失效"),

		/**
		 * 禁止
		 */
		FORBIDDEN("forbidden", "禁止")

		;

		private String key;

		private String name;

		private AccountStatusTypeEnum(String key, String name) {
			this.key = key;
			this.name = name;
		}

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public AccountStatusTypeEnum[] getValues() {
			return AccountStatusTypeEnum.values();
		}
	}
	
	
}