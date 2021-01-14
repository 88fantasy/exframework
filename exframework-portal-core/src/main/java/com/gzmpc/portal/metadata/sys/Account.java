package com.gzmpc.portal.metadata.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gzmpc.portal.metadata.di.DataItemEntity;
import com.gzmpc.portal.metadata.di.DataItem.DataItemDisplayTypeEnum;
import com.gzmpc.portal.metadata.di.DataItem.DataItemValueTypeEnum;
import com.gzmpc.portal.metadata.dict.Dictionary;
import com.gzmpc.portal.metadata.dict.DictionaryEnum;
import com.gzmpc.portal.metadata.dict.DictionaryEnumClass;
import com.gzmpc.portal.metadata.entity.EntityClass;
import com.gzmpc.portal.metadata.module.Module;


/**
 * 帐号实体类
 * 
 * @author rwe
 *
 */
@EntityClass
public class Account implements Serializable, DictionaryEnumClass {

	private static final long serialVersionUID = -1150456135277097507L;

	/**
	 * 登陆账号ID
	 */
	@DataItemEntity(value = "accountId", name = "帐号")
	private String accountId;

	/**
	 * 密码
	 */
	@DataItemEntity(value = "password", name = "密码")
	private String password;

	/**
	 * 帐号密码
	 */
	@DataItemEntity(value = "accountName", name = "帐号")
	private String accountName;

	/**
	 * 最近登录日期
	 */
//	@DataItemField(value = "accountId", name = "帐号")
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
	@DataItemEntity(value = "accountStatus", name = "帐号状态", type = DataItemDisplayTypeEnum.DICTIONARY, displayKey="AccountStatusType", valueType = DataItemValueTypeEnum.STRING)
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

	@Override
	public Class<? extends DictionaryEnum<?>>[] getEnums() {
		return new Class[] {AccountStatusTypeEnum.class};
	}

	@Dictionary( value = "AccountStatus", name = "帐号状态")
	public enum AccountStatusTypeEnum implements DictionaryEnum<String> {

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

		public String getKey() {
			return key;
		}

		public String getName() {
			return name;
		}

		@Override
		public String getValue() {
			return this.key;
		}

	}
	
}