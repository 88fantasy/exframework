package com.gzmpc.sys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.dao.SystemDao;
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

	public String COLUMN_PARAMKEY = "PARAMKEY";

	private String COLUMN_PARAM_VALUE = "PARAMVALUE";


	private Log log = LogFactory.getLog(SystemParameterService.class.getName());

	@Autowired
	SystemDao systemDao;
	
	@Autowired
	SystemConst systemConst;

	/**
	 *
	 * @param accountid
	 *            帐号id
	 * @param key
	 *            参数的关键字
	 * @return 与key对应的在帐号参数中定义的值， 如未定义，则返回帐号的第一个帐号中的第一个角色的角色参数， 如若也未定义，则返回默认值
	 *
	 */
	public String getAccoutParameter(String accountid, String key) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String value = null;
		try {
			con = systemDao.getUnautocommitConnection();
			stmt = con.prepareStatement(" select paramvalue from sys_accountparam where accountid=? and paramkey=? ");
			stmt.setString(1, accountid);
			stmt.setString(2, key);
			rs = stmt.executeQuery();
			if (rs.next()) {
				value = rs.getString(COLUMN_PARAM_VALUE);
			}
			rs.close();
			stmt.close();
			con.commit();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
		} finally {
			DbUtils.closeQuietly(con, stmt, rs);
		}
		return value;
	}

	public void putAccountKey(String accountid, String key, String value) {
		Connection con = null;
		try {
			con = systemDao.getUnautocommitConnection();
			int success = updateAccountParam(con, accountid, key, value);
			if( success < 1) {
				saveAccountParam(con, accountid, key, value);
			}
			con.commit();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
		} finally {
			DbUtils.closeQuietly(con);
		}

	}

	public void removeAccountKey(String accountid, String key) {
		deleteAccountParam(accountid, key);
	}

	

	public void putDefaultKey(String key, String value) {
		Connection con = null;
		try {
			con = systemDao.getUnautocommitConnection();
			int success = updateAccountParam(con, systemConst.ACCOUNT_ADMIN,key, value);
			if( success < 1 ) {
				saveAccountParam(con, systemConst.ACCOUNT_ADMIN, key, value);
			}
			con.commit();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}

			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(con, null, null);
		}
	}

	public void removeDefaultKey(String key) {
		deleteAccountParam(systemConst.ACCOUNT_ADMIN, key);
	}

	/**
	 * @param accountid
	 * @param key
	 * @param value
	 */
	private void saveAccountParam(Connection con, String accountid, String key, String value) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "insert into sys_accountparam (paramid,accountid,paramkey,paramvalue) "
				+ "values(?,?,?,?)";
		try {
			pst = con.prepareStatement("select max(paramid) from sys_accountparam ");
			rs = pst.executeQuery();
			rs.next();
			long id = rs.getLong(1);
			rs.close();
			pst.close();
			
			pst = con.prepareStatement(sql);
			pst.setLong(1, id);
			pst.setString(2, accountid);
			pst.setString(3, key);
			pst.setString(4, value);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			log.error(e);
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(null, pst, rs);
		}
	}

	/**
	 * @param accountid
	 * @param key
	 * @param value
	 */
	private int updateAccountParam(Connection con, String accountid, String key, String value) {
		int result = -1;
		PreparedStatement stmt = null;
		String sql = "update sys_accountparam set paramvalue=? where accountid=? and paramkey=?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, value);
			stmt.setString(2, accountid);
			stmt.setString(3, key);
			result = stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			log.error(e);
		} finally {
			DbUtils.closeQuietly(stmt);
		}
		return result;
	}

	/**
	 * @param accountid
	 * @param key
	 */
	private void deleteAccountParam(String accountid, String key) {
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "delete sys_accountparam where accountid=? and paramkey=?";
		try {
			con = systemDao.getUnautocommitConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, accountid);
			stmt.setString(2, key);
			stmt.executeUpdate();
			stmt.close();
			con.commit();
		} catch (Exception e) {
			log.error(e);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}

			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(con, stmt, null);
		}
	}

}
