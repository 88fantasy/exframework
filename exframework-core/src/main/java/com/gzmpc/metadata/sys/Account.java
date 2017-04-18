package com.gzmpc.metadata.sys;

import java.util.*;

import com.gzmpc.metadata.func.Func;

import java.io.*;

/**
 *对表 SYS_ACCOUNT：登陆账号 进行数据映射的bean
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1150456135277097507L;
	// 登陆账号ID
	private String accountId;
	private String password;
	private Date lastLoginDate;
	private String lastLoginIp;
	private String lastLoginArea;
	private Long accounttype;
	private Long accountStatus;

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountPassword(String accountPassword) {
		try {
			// this.internalAccountPassword = Encrypter.encode(accountPassword);
			// mod by clq 20090730 密码加密方式变更
			this.password = accountPassword;
		} catch (Exception ex) {
			this.password = null;
		}
	}

	public String getAccountPassword() {
		try {
			// return Encrypter.decode(internalAccountPassword);
			return password;
		} catch (Exception ex) {
			return null;
		}

	}

	// 状态
	public void setAccountStatus(Long accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Long getAccountStatus() {
		return accountStatus;
	}

	// 帐号名称
	private String accountName;

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountName() {
		return accountName;
	}

	// 截止日期
	private Date accountInvalidDate;

	public Date getAccountInvalidDate() {
		return new Date(accountInvalidDate.getTime());
	}

	public void setAccountInvalidDate(Date accountInvalidDate) {
		this.accountInvalidDate = new Date(accountInvalidDate.getTime());
	}

	// 拥有的角色

	/**
	 * @link dependency
	 * @stereotype bind
	 * @clientRole 0..n
	 * @supplierRole 0..n
	 */
	/* # Role lnkRole; */
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public Role getRoleById(String role_id){
		if(roles == null)
			return null;
		for(int i=0;i<roles.size();i++){
			Role r = (Role) roles.get(i);
			if(r.getRoleId().longValue() == new Long(role_id).longValue()){
				return r;
			}
		}
		return null;
	}

	private Map<String,Module> modules ;
	

	public Map<String,Module> getModules() {
		return modules;
	}

	public void setModules(Map<String,Module> modules) {
		this.modules = modules;
	}

	public String getLastLoginArea() {
		return lastLoginArea;
	}

	public void setLastLoginArea(String lastLoginArea) {
		this.lastLoginArea = lastLoginArea;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Long getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(Long accounttype) {
		this.accounttype = accounttype;
	}
	
	private List<Func> funcs;

	public List<Func> getFuncs() {
		return funcs;
	}

	public void setFuncs(List<Func> funcs) {
		this.funcs = funcs;
	}
	
}