package org.exframework.portal.auth.service.impl;

import com.usthe.sureness.provider.SurenessAccountProvider;
import org.exframework.portal.auth.entity.SysUser;
import org.exframework.portal.auth.service.UserService;
import org.exframework.support.common.util.StrUtils;

import java.util.List;
import java.util.Set;

/**
 * @author rwe
 * @date 2022/7/11 16:16
 **/
public class DefaultUserService implements UserService, SurenessAccountProvider {

    private final String user;

    private final String password;


    private final SysUser defaultUser;

    public DefaultUserService(String user, String password, String name, List<String> permissions) {
        this.user = user;
        this.password = password;
        this.defaultUser = new SysUser()
                .setUserId(user)
                .setPassword(password)
                .setName(StrUtils.hasText(name) ? name : "系统管理员")
                .setEnabled(true)
                .setPermissions(permissions)
//                .setSalt()
        ;
    }

    @Override
    public boolean checkUser(SysUser user, String password) {
        return this.password.equals(password);
    }

    @Override
    public List<String> permissions(String userId) {
        return defaultUser.getPermissions();
    }

    @Override
    public SysUser loadAccount(String userId) {
        return defaultUser;
    }

    @Override
    public String getIssuer() {
        return "default";
    }
}
