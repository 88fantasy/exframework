package com.gzmpc.service.impl;

import java.sql.*;

import java.util.Date;
import java.util.Iterator;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.MetaData;
import com.gzmpc.metadata.func.Func;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.sys.Module;
import com.gzmpc.metadata.sys.Role;
import com.gzmpc.metadata.sys.SystemConst;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import org.apache.commons.dbutils.DbUtils;
import com.gzmpc.pub.Security;
import com.gzmpc.service.AccountService;
import com.gzmpc.sys.ModuleService;
import com.gzmpc.util.MapUtil;

/**
 * 
 * <p>
 * Title: 账号服务
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
 * @author rwe
 * @version 1.0
 */

@Service
public class AccountServiceImpl implements AccountService {
	
	private Log log = LogFactory.getLog(AccountServiceImpl.class.getName());
	
	public int ACCOUNT_INVALID = 0;
	public int ACCOUNT_VALID = 1;
	public int ACCOUNT_NOTASS = -1;
	
	@Autowired
	SystemDao systemDao;
	
	@Autowired
	SystemConst systemConst;
	
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	MetaData metaData;
	
	//由于用户名较少更改,故增加缓冲减少查询数据库
	private ConcurrentHashMap<String,String> accountNames = new ConcurrentHashMap<String,String>();

	/**
	 * 判断账号是否拥有访问该模块的权限
	 * 
	 * @param context
	 *            上下文环境
	 * @param account
	 *            账号对象
	 * @param moduleId
	 *            模块ID
	 * @return boolean 返回是否有权限
	 */

	public  boolean hasModule(Account account, String moduleId) {
		if (account == null) {
			return false;
		}
		if (account.getAccountId().equals(systemConst.ACCOUNT_ADMIN)) {
			return true;
		}
		if (account.getModules().containsKey(moduleId)) {
			return true;
		}
		return false;
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

	public  boolean isValid(Account account, String password) {
		boolean result = true;
		if (account == null) {
			return false;
		}

		String accountid = account.getAccountId();
		if (accountid.contains("@"))
			accountid = accountid.substring(0, accountid.indexOf("@"));
		String logpwd = Security.encode(accountid, password);
		if (!account.getAccountPassword().equals(logpwd)) {
			result = false;
		}
		
		Date dtNow = new Date();
		if (dtNow.compareTo(account.getAccountInvalidDate()) > 0) {
			result = false;
		}

		// 账号失效
		if (account.getAccountStatus().intValue() == ACCOUNT_INVALID) {
			result = false;
		}
		return result;
	}

	/**
	 * 初始化该账号对应的所有模块
	 * 
	 * @param context
	 *            上下文环境
	 * @param account
	 *            账号对象
	 */
	public synchronized  void initAccountModules(Account account) {
		if (account == null) {
			return;
		}
		Connection con = null;
		try {
			con = systemDao.getUnautocommitConnection();
			con.setAutoCommit(false);
			Map<String,Module> modules = accountModulesMap(con, account.getAccountId());
			account.setModules(modules);
			con.commit();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
		} finally {
			DbUtils.closeQuietly(con);
		}

	}

	/**
	 * 根据账号名称获取账号对象
	 * 
	 * @param accountId
	 *            账号名称
	 * @return Account 账号对象
	 */

	public  Account getAccount(String accountid) {
		Connection con = null;
		Account account = null;
		try {
			con = systemDao.getUnautocommitConnection();
			con.setAutoCommit(false);
			account = accountExist(con, accountid);
			if (account == null) {
				return null;
			}
			Map<String,Module> ms = accountModulesMap(con, accountid);
			account.setModules(ms);
			
			List<Func> funcs = new ArrayList<Func>();
			Collection<Module> modules = ms.values();
			Iterator<Module> it = modules.iterator();
			while(it.hasNext()){
				Module m = it.next();
				if(m.getModuleType()==Module.MODULETYPE_FUNCTION) {
					Func func = metaData.findFuncsByCode(m.getModuleId());
					if( func != null ) {
						funcs.add(func);
					}
				}
			}
			account.setFuncs(funcs);
			
			con.commit();
			return account;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
			return account;
		} finally {
			DbUtils.closeQuietly(con);
		}

	}
	
	/**
	 * 获取账号名称
	 * 
	 * @param accountId
	 *            账号id
	 * @return String 账号名称
	 */

	public  String getAccountName(String accountid, boolean force) throws NotFoundException {
		String accountname = null;
		if ( !force && accountNames.contains(accountid) ) {
			return accountNames.get(accountid);
		}
		Connection con = null;
		try {
			con = systemDao.getUnautocommitConnection();
			con.setAutoCommit(false);
			Account account = accountExist(con, accountid);
			if (account != null) {
				accountname  = account.getAccountName();
			}
			con.commit();
		} catch (SQLException ex) {
			log.error(ex.getMessage(), ex);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
		} finally {
			DbUtils.closeQuietly(con);
		}
		MapUtil.putIfNotNull(accountNames, accountid, accountname);
		return accountname;
	}

	/**
	 * 根据账号名称，看一下是否存在该账号
	 * 
	 * @param con
	 *            数据库连接
	 * @param accountid
	 *            账号名称
	 * @return 如果存在，则返回对象，如果不存在，则返回null
	 */
	public  Account accountExist(Connection con, String accountid) {
		StringBuffer sb = new StringBuffer();
		String sql = null;
		sb.append("select t.* from sys_account t where t.account_id = ?");
		PreparedStatement pstm = null;
		ResultSet rs = null;
		sql = sb.toString();
		Account account = new Account();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, accountid);

			
			rs = pstm.executeQuery(); // .execute();//.executeQuery(sb.toString());
			if (rs.next()) {
				account.setAccountId(accountid);
				account.setAccountInvalidDate(rs.getDate("account_invalid_date"));
				account.setAccountName(rs.getString("account_name"));
				account.setAccountPassword(rs.getString("account_password"));
				account.setAccountStatus(new Long(rs.getLong("account_status")));
				account.setAccountName(rs.getString("account_name"));
				account.setAccounttype(new Long(rs.getLong("account_type")));
				account.setRoles(accountRoleList(con, accountid));
				return account;
			} else {
				return null;
			}
		} catch (SQLException ex) {
			log.error(ex.getMessage(), ex);
			return null;
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
	}

	/**
	 * 取帐号对应的所有模块
	 * 
	 * @param con
	 *            数据库连接
	 * @param accountid
	 *            账号名称
	 * @return 该账号对应的所有角色
	 */
	private  List<Role> accountRoleList(Connection con, String accountid) {
		StringBuffer sb = new StringBuffer();
		String sql = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Role> list = new ArrayList<Role>();
		try {
			// supervisor 帐号具有所有模块权限
			if (accountid.equals(systemConst.ACCOUNT_ADMIN)) {
				sb.append("select t.role_id,t.role_name,t.role_desc from sys_role t  order by t.role_id ");
				sql = sb.toString();
				pstm = con.prepareStatement(sql);
			} else {
				// 注意要修改原有的权限管理后台，后台应该判断如果当前选中结点的是操作类型的，应该要保存到分类结点。
				// 如果为功能类型的，则同样也要保存到分类结点；
				sb.append("select distinct t.role_id,t.role_name,t.role_desc from sys_accountrole a,sys_role t");
				sb.append(" where a.role_id = t.role_id  and a.account_id = ?  order by t.role_id ");
				sql = sb.toString();
				pstm = con.prepareStatement(sql);
				pstm.setString(1, accountid);
			}

			log.info(sql);
			rs = pstm.executeQuery(); // .execute();//.executeQuery(sb.toString());
			while (rs.next()) {
				Role r = new Role();
				r.setRoleId(new Long(rs.getLong("role_id")));
				r.setRoleName(rs.getString("role_name"));
				r.setRoleDesc(rs.getString("role_desc"));
				list.add(r);
			}
			return list;
		} catch (SQLException ex) {
			log.error(ex.getMessage(), ex);
			return null;
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
	}

	/**
	 * 取帐号对应的所有模块
	 * 
	 * @param con
	 *            数据库连接
	 * @param accountid
	 *            账号名称
	 * @return 该账号对应的所有模块
	 */
	private  Map<String,Module> accountModulesMap(Connection con, String accountid) {
		StringBuffer sb = new StringBuffer();
		String sql = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Map<String,Module> map = new LinkedHashMap<String,Module>();
		Map<String,Module> allModules = moduleService.getAllModules();
		try {
			// supervisor 帐号具有所有模块权限
			if (accountid.equals(systemConst.ACCOUNT_ADMIN)) {
				sb.append("select t.module_id from sys_module t where t.module_valid=1 order by module_order ");
				sql = sb.toString();
				pstm = con.prepareStatement(sql);
			} else {
				// 注意要修改原有的权限管理后台，后台应该判断如果当前选中结点的是操作类型的，应该要保存到分类结点。
				// 如果为功能类型的，则同样也要保存到分类结点；
				sb.append("select t.module_id from sys_module t");
				sb.append(" where exists ( select 1 from sys_accountrole a,sys_rolemodule r where a.role_id = r.role_id and  r.module_id = t.module_id ");
				sb.append(" and t.module_valid = 1 and a.account_id = ? ) order by module_order ");
				sql = sb.toString();
				pstm = con.prepareStatement(sql);
				pstm.setString(1, accountid);
			}

			rs = pstm.executeQuery(); // .execute();//.executeQuery(sb.toString());
			while (rs.next()) {
				Module m = new Module();
				String mid = rs.getString("module_id");
				m = (Module) allModules.get(mid);
				if (m != null) {
					map.put(mid, m);
				}
			}
			return map;
		} catch (SQLException ex) {
			log.error(ex.getMessage(), ex);
			return null;
		} finally {
			DbUtils.closeQuietly(null, pstm, rs);
		}
	}

	/**
	 * 改变密码
	 * 
	 * @param accountid
	 * @param oldPassword
	 * @param password
	 * @throws java.lang.Exception
	 */
	 public void changePassword(String accountid, String oldPassword, String password) throws Exception {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String hhsl_find = "select account_password from sys_account where account_id = ? ";
		String hhsl_update = "update sys_account set account_password = ? where account_id = ? ";
		String accountid2 = accountid;
		try {
			con = systemDao.getUnautocommitConnection();
			con.setAutoCommit(false);
			pstm = con.prepareStatement(hhsl_find);
			pstm.setString(1, accountid);
			rs = pstm.executeQuery();
			if (rs.next()) {
				String psw = rs.getString("account_password");
				// psw = Encrypter.decode(psw);
				// mod by clq @ 20090730 修改密码加密方式
				if (accountid.contains("@"))
					accountid2 = accountid.substring(0, accountid.indexOf("@"));
				String oldpsw = Security.encode(accountid2, oldPassword);
				if (!psw.equals(oldpsw)) {
					throw new Exception("修改密码失败，原密码不匹配！");
				}
			} else {
				throw new Exception("修改密码失败，不存在此账号（" + accountid + "）");
			}
			pstm.close();
			// password = Encrypter.encode(password);
			password = Security.encode(accountid2, password);
			pstm = con.prepareStatement(hhsl_update);
			pstm.setString(1, password);
			pstm.setString(2, accountid);
			pstm.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			log.error(ex.getMessage(), ex);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
			throw ex;
		} finally {
			DbUtils.closeQuietly(con, pstm, rs);
		}
	}
}