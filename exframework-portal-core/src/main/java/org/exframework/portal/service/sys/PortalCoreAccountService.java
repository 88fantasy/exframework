package org.exframework.portal.service.sys;

import java.util.Date;
import java.util.Map;

import org.exframework.portal.dao.PortalCoreAccountDao;
import org.exframework.portal.exception.NotAuthorizedException;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.sys.Account;
import org.exframework.portal.metadata.sys.Permission;
import org.exframework.portal.metadata.sys.Account.AccountStatusTypeEnum;
import org.exframework.portal.pub.Security;

/**
 * @author rwe
 */
public interface PortalCoreAccountService {

    String getGlobalAccountId();

    PortalCoreAccountDao getAccountDao();

    /**
     * 获取当前登录帐号
     */
    default Account getAccount() throws NotAuthorizedException {
        throw new NotAuthorizedException();
    }

    default Account getAccount(String accountId) {
        Account account = getAccountDao().getAccount(accountId);
        Map<String, Permission> ms = accountPermissionMap(account);
        account.setPermissions(ms);
        return account;
    }

    /**
     * 取帐号对应的所有权限
     *
     * @param account 账号
     * @return 该账号对应的所有权限
     */
    Map<String, Permission> accountPermissionMap(Account account);

    default Account getGlobalAccount() {
        return getAccount(getGlobalAccountId());
    }

    /**
     * 判断密码是否正确
     *
     * @param account  账号对象
     * @param password 密码
     * @return boolean 是否有权限
     */
    default boolean isValid(Account account, String password) {
        if (account == null) {
            return false;
        }

        String user = account.getAccount();
        String logpwd = Security.encode(user, password);
        if (!logpwd.equals(account.getPassword())) {
            return false;
        }

        Date dtNow = new Date();
        if (dtNow.compareTo(account.getAccountInvalidDate()) > 0) {
            return false;
        }

        // 账号失效
        return account.getAccountStatus() == AccountStatusTypeEnum.VALID;
    }

    /**
     * 获取账号名称
     *
     * @param accountId
     * @return String
     * @throws NotFoundException
     */
    default String getAccountName(String accountId) throws NotFoundException {
        Account account = getAccount(accountId);
        return account.getAccountName();
    }

    /**
     * 改变密码
     *
     * @param accountId
     * @param oldPassword
     * @param password
     * @return
     */
    default boolean changePassword(String accountId, String oldPassword, String password) {
        Account account = getAccount(accountId);
        return getAccountDao().changePassword(account, oldPassword, password);
    }
}
