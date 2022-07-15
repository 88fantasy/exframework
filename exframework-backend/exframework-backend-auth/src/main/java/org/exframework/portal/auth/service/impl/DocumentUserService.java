package org.exframework.portal.auth.service.impl;

import org.exframework.portal.auth.config.AuthProperties;
import org.exframework.portal.auth.entity.SysUser;
import org.exframework.portal.auth.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author rwe
 * @date 2022/7/11 16:16
 **/
public class DocumentUserService implements UserService {

    private final List<AuthProperties.DocumentAccount> accounts;

    public DocumentUserService(List<AuthProperties.DocumentAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean checkUser(SysUser user, String password) {
        return user.getPassword().equals(password);
    }

    @Override
    public List<String> permissions(String userId) {
        Optional<AuthProperties.DocumentAccount> account = getAccount(userId);
        if (!account.isPresent()) {
            return Collections.emptyList();
        }
        return account.get().getRoles();
    }

    @Override
    public SysUser loadAccount(String userId) {
        Optional<AuthProperties.DocumentAccount> optional = getAccount(userId);
        if (!optional.isPresent()) {
            return null;
        }
        AuthProperties.DocumentAccount account = optional.get();
        return new SysUser()
                .setUserId(account.getUser())
                .setPassword(account.getPassword())
                .setName(account.getName())
                .setPermissions(account.getRoles())
                .setEnabled(true)
//                .setSalt()
                ;
    }


    private Optional<AuthProperties.DocumentAccount> getAccount(String userId) {
        return accounts.stream().filter(a -> a.getUser().equals(userId)).findFirst();
    }
}
