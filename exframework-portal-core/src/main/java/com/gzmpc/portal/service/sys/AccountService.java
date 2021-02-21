package com.gzmpc.portal.service.sys;

import java.util.Date;
import java.util.Map;

import com.gzmpc.portal.dao.AccountDao;
import com.gzmpc.portal.exception.NotAuthorizedException;
import com.gzmpc.portal.exception.NotFoundException;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.portal.metadata.sys.Permission;
import com.gzmpc.portal.metadata.sys.Account.AccountStatusTypeEnum;
import com.gzmpc.portal.pub.Security;

public interface AccountService {

	String getGlobalAccountId();

	AccountDao getAccountDao();

	/**
	 * 获取当前登录帐号
	 * 
	 * @return
	 */
	default Account getAccount() throws NotAuthorizedException {
		throw new NotAuthorizedException();
	}

	default Account getAccount(String accountId) {
		Account account = getAccountDao().getAccount(accountId);
		Map<String, Permission> ms = accountPermissionMap(account);
		account.setPermissions(ms);

//		List<Module> funcs = new ArrayList<Module>();
//		Collection<Permission> permission = ms.values();
//		Iterator<Permission> it = permission.iterator();
//		while(it.hasNext()){
//			Permission p = it.next();
//			if(p.getPermissionType() == Permission.MODULETYPE_FUNCTION) {
//				Module func = metaData.findFuncsByCode(p.getKey());
//				if( func != null ) {
//					funcs.add(func);
//				}
//			}
//		}
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
	 * @param context
	 *            上下文环境
	 * @param account
	 *            账号对象
	 * @param pswdInput
	 *            密码
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
		if (account.getAccountStatus() != AccountStatusTypeEnum.VALID) {
			return false;
		}
		return true;
	}

	/**
	 * 获取账号名称
	 * 
	 * @param accountId 账号id
	 * @return String 账号名称
	 */
	default String getAccountName(String accountid) throws NotFoundException {
		Account account = getAccount(accountid);
		String accountname  = account.getAccountName();
		return accountname;
	}
	
	/**
	 * 改变密码
	 * 
	 * @param accountid
	 * @param oldPassword
	 * @param password
	 * @throws java.lang.Exception
	 */
	 default boolean changePassword(String accountId, String oldPassword, String password) {
		Account account = getAccount(accountId);
		return getAccountDao().changePassword(account, oldPassword, password);
	}
}
