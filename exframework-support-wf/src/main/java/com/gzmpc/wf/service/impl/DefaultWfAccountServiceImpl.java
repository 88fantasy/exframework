package com.gzmpc.wf.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.wf.service.WfAccountService;

@Service
public class DefaultWfAccountServiceImpl implements WfAccountService {
	private Log log = LogFactory.getLog(DefaultWfAccountServiceImpl.class.getName());
	

	@Autowired
	SystemDao systemDao;

	@Override
	public Account findAccount(String id) {
		Account account = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = systemDao.getUnautocommitConnection();
			pst = con.prepareStatement("select * from sys_account  where account_id = ?");
			pst.setString(1, id);
			pst.executeQuery();
			rs = pst.executeQuery();
			if(rs.next()){
				account = new Account();
				account.setAccountId(rs.getString("account_id"));
				account.setAccountName(rs.getString("account_name"));
			}
			rs.close();
			pst.close();
			con.commit();
		} catch (SQLException ex) {
			log.error(ex.getMessage(),ex);
			try {
				if ( con != null) {
					con.rollback();
				}
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(),ex1);
			}
		} finally {
			DbUtils.closeQuietly(con,pst,rs);
		}
		return account;
	}

	@Override
	public Iterator<Account> listAccountByRoleid(String roleid) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prestmt = null;
		List<Account> list = new ArrayList<Account>();
		try {
			con = systemDao.getUnautocommitConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select sys_account.account_id, sys_account.account_name,");
			sql.append(" sys_account.personid ");
			sql.append(" from   sys_account, sys_accountrole, sys_role");
			sql.append(" where  sys_role.role_id = sys_accountrole.role_id");
			sql.append("  and sys_accountrole.account_id = sys_account.account_id");
			sql.append("  and sys_role.role_id = ?");

			prestmt = con.prepareStatement(sql.toString());
			prestmt.setString(1, roleid);
			// 返回数据库查询结果集ResultSet
			rs = prestmt.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setAccountId(rs.getString("account_id"));
				account.setAccountName(rs.getString("account_name"));
				// 加入包中
				list.add(account);
			}
		} catch (SQLException sqle) {
			log.error(sqle.getMessage(), sqle);
			try{
				DbUtils.rollback(con);
			}catch (SQLException sqle2) {
				log.error(sqle2.getMessage(), sqle2);
			}
		} finally {
			DbUtils.closeQuietly(con, prestmt, rs);
		}
		return list.iterator();
	}

}
