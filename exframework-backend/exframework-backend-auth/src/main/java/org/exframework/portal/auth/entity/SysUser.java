package org.exframework.portal.auth.entity;

import com.usthe.sureness.provider.SurenessAccount;

import java.util.List;

/**
 * 系统帐号
 *
 * @author rwe
 * @date 2022/7/11 15:54
 **/
public class SysUser implements SurenessAccount {

    private String userId;

    private String name;

    private String password;

    private String salt;

    private Boolean enabled;

    private List<String> permissions;

    public String getUserId() {
        return userId;
    }

    public SysUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public SysUser setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getAppId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public SysUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public SysUser setPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public List<String> getOwnRoles() {
        return permissions;
    }

    @Override
    public boolean isDisabledAccount() {
        return !enabled;
    }

    @Override
    public boolean isExcessiveAttempts() {
        return false;
    }

    public SysUser setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public SysUser setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
