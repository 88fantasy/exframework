package com.gzmpc.service.sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
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
	public String getAccoutParameter(Account account, String key) {
		return systemParameterDao.getAccoutParameter(account, key);
	}

	public boolean putAccountKey(Account account, String key, String name, String value, String comment) {
		return systemParameterDao.putAccountKey(account, key, name, value, comment);
	}

	public boolean removeAccountKey(Account account, String key) {
		return systemParameterDao.removeAccountKey(account, key);
	}

	

	public boolean putDefaultKey(String key, String name, String value, String comment) {
		Account account = accountService.getGlobalAccount();
		return systemParameterDao.putAccountKey(account, key, name, value, comment);
	}

	public boolean removeDefaultKey(String key) {
		Account account = accountService.getGlobalAccount();
		return systemParameterDao.removeAccountKey(account, key);
	}

}
