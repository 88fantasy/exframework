package com.gzmpc.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.dao.AccountParameterDao;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.sys.SystemConst;

/**
 * 系统参数类，提供获得用户参数的方法
 * 
 * @author rwe
 *
 *
 */
@Service
public class SystemParameterService {

//	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SystemConst systemConst;

	@Autowired
	AccountParameterDao systemParameterDao;
	
	@Autowired
	AccountService accountService;
	
	/**
	 *
	 * @param accountid
	 *            帐号id
	 * @param key
	 *            参数的关键字
	 * @return 与key对应的在帐号参数中定义的值， 如未定义，则返回帐号的第一个帐号中的第一个角色的角色参数， 如若也未定义，则返回默认值
	 *
	 */
	public String getAccoutParameter(String accountId, String key) {
		Account account = accountService.getAccount(accountId);
		return systemParameterDao.getAccoutParameter(account, key);
	}

	public boolean putAccountKey(String accountId, String key, String name, String value, String description) {
		Account account = accountService.getAccount(accountId);
		return systemParameterDao.putAccountKey(account, key, name, value, description);
	}

	public boolean removeAccountKey(String accountId, String key) {
		Account account = accountService.getAccount(accountId);
		return systemParameterDao.removeAccountKey(account, key);
	}

	

	public boolean putDefaultKey(String key, String name, String value, String description) {
		Account account = accountService.getGlobalAccount();
		return systemParameterDao.putAccountKey(account, key, name, value, description);
	}

	public boolean removeDefaultKey(String key) {
		Account account = accountService.getGlobalAccount();
		return systemParameterDao.removeAccountKey(account, key);
	}
}
