package org.exframework.portal.metadata.sys;

import org.exframework.portal.enums.AccountStatusType;
import org.exframework.portal.enums.DataItemDisplayType;
import org.exframework.portal.enums.DataItemValueType;
import org.exframework.portal.metadata.di.DataItemEntity;
import org.exframework.portal.metadata.entity.EntityClass;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


/**
 * 帐号实体类
 *
 * @author rwe
 */
@EntityClass
public class Account implements Serializable {

    private static final long serialVersionUID = -1150456135277097507L;

    /**
     * 登陆账号ID
     */
    @DataItemEntity(value = "account", name = "帐号")
    private String account;

    /**
     * 密码
     */
    @DataItemEntity(value = "password", name = "密码")
    private String password;

    /**
     * 帐号密码
     */
    @DataItemEntity(value = "accountName", name = "帐号名称")
    private String accountName;

    /**
     * 最近登录日期
     */
    @DataItemEntity(value = "lastLoginDate", name = "最近登录日期")
    private Date lastLoginDate;

    /**
     * 最近登录 IP
     */
    @DataItemEntity(value = "lastLoginIp", name = "最近登录IP")
    private String lastLoginIp;

    /**
     * 最近登录地区
     */
    @DataItemEntity(value = "lastLoginArea", name = "最近登录地区")
    private String lastLoginArea;

    /**
     * 帐号状态
     */
    @DataItemEntity(value = "accountStatus", name = "帐号状态", type = DataItemDisplayType.DICTIONARY, displayKey = "AccountStatusType", valueType = DataItemValueType.STRING)
    private AccountStatusType accountStatus;

    // 截止日期
    private Date accountInvalidDate;

    private Map<String, Permission> permissions;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public AccountStatusType getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatusType accountStatus) {
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


}