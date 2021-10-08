package org.exframework.portal.service.sys;


import org.exframework.portal.dao.PortalCoreAccountDao;
import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.sys.*;
import org.exframework.support.common.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 账号服务
 * 
 * @author rwe
 *
 */
@Service
public class PortalCoreRoleBaseAccountServiceImpl implements PortalCoreAccountService {

	private Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	SystemConst systemConst;

	@Autowired
	PortalCoreAccountDao portalCoreAccountDao;

	@Autowired
	PortalCorePermissionService portalCorePermissionService;

	@Autowired
	PortalCoreRoleService portalCoreRoleService;

	// 由于用户名较少更改,故增加缓冲减少查询数据库
	private ConcurrentHashMap<String, String> accountNames = new ConcurrentHashMap<String, String>();

	/**
	 * 判断账号是否拥有访问该模块的权限
	 *
	 * @param account  账号对象
	 * @param key 模块ID
	 * @return boolean 返回是否有权限
	 */
	public boolean hasPermission(Account account, String key) {
		if (account == null) {
			return false;
		}
		if (account.getAccount().equals(systemConst.ACCOUNT_ADMIN)) {
			return true;
		}
		if (account.getPermissions().containsKey(key)) {
			return true;
		}
		return false;
	}

	/**
	 * 根据账号名称获取账号对象
	 * 
	 * @param accountId 账号名称
	 * @return Account 账号对象
	 */

	@Override
	public Account getAccount(String accountId) {
		Account account = portalCoreAccountDao.getAccount(accountId);
		Map<String, Permission> ms = accountPermissionMap(account);
		account.setPermissions(ms);
		return account;
	}
	
	private RoleBaseAccount getRoleBaseAccount(Account account) {
		RoleBaseAccount rbAccount = new RoleBaseAccount();
		BeanUtils.copyProperties(account, rbAccount);
		Collection<Role> roles = accountRoleList(rbAccount);
		rbAccount.setRoles(roles);
		List<Map<String, Permission>> permissionMapList = roles.stream().map(Role::getPermissionMap).collect(Collectors.toList());
		Map<String, Permission> ms = portalCorePermissionService.sum(permissionMapList);
		rbAccount.setPermissions(ms);

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
		return rbAccount;
	}

	@Override
	public String getAccountName(String accountid) throws NotFoundException {
		return getAccountName(accountid, false);
	}

	/**
	 * 获取账号名称
	 * 
	 * @param accountId 账号id
	 * @return String 账号名称
	 */
	public String getAccountName(String accountId, boolean force) throws NotFoundException {
		if (!force && accountNames.contains(accountId)) {
			return accountNames.get(accountId);
		}
		String accountname = getAccount(accountId).getAccountName();
		MapUtil.putIfNotNull(accountNames, accountId, accountname);
		return accountname;
	}

	/**
	 * 取帐号对应的所有模块
	 * 
	 * @param account 账号名称
	 * @return 该账号对应的所有角色
	 */
	private Collection<Role> accountRoleList(RoleBaseAccount account) {
		// 全局帐号具有所有模块权限
		if (systemConst.ACCOUNT_ADMIN.equals(account.getAccount())) {
			return portalCoreRoleService.getAllRoles().values();
		} else {
			return portalCoreRoleService.findByAccount(account);
		}
	}

	@Override
	public Map<String, Permission> accountPermissionMap(Account account) {
		Map<String, Permission> allPermissions = portalCorePermissionService.getAllPermissions();
		// 全局帐号具有所有模块权限
		if (systemConst.ACCOUNT_ADMIN.equals(account.getAccount())) {
			return allPermissions;
		} else {
			return getRoleBaseAccount(account).getPermissions();
		}
	}

	@Override
	public String getGlobalAccountId() {
		return systemConst.ACCOUNT_ADMIN;
	}

	@Override
	public PortalCoreAccountDao getAccountDao() {
		return portalCoreAccountDao;
	}

}