package com.gzmpc.metadata;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gzmpc.dao.SystemDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gzmpc.utils.QuerySupport;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.dbutils.DbUtils;
import java.sql.*;

@Service
public class DBTableColumnPool {

	@Autowired
	SystemDao systemDao;
	
	@Autowired
	QuerySupport querySupport;
	
	private Log log = LogFactory.getLog(DBTableColumnPool.class.getName());

	public Map<TableCon, Object[]> tableMap = new ConcurrentHashMap<TableCon, Object[]>(); // 缓存数字字典

	private class TableCon {
		String tablename;
		String concode;
		
		public TableCon (String tablename,String concode) {
			this.tablename = tablename;
			this.concode = concode;
		}

		@Override
		public int hashCode() {
			String str = tablename + "@"+concode;
			return str.hashCode();
		}
	}
	
	public Object[] retTableColumn(String tableName, String primaryKey) {
		Connection con = null;
		try {
			con = systemDao.getUnautocommitConnection();
			return retTableColumn(con,tableName,primaryKey);
		} catch (SQLException e) {
			return null;
		} finally {
			DbUtils.closeQuietly(con);
		}
	}

	public Object[] retTableColumn(Connection con, String tableName, String primaryKey) throws SQLException {
		Object[] value = null;
		DatabaseMetaData dmd;
		String code = null;
		try {
			dmd = con.getMetaData();
			code = dmd.getURL() + dmd.getUserName();
		} catch (SQLException e) {
			log.error(e);
		}
		TableCon tc = new TableCon(tableName,code);
		if (!tableMap.containsKey(tc)) {
			synchronized (tableMap) {
				value = initColumns(con, tableName, primaryKey);
				tableMap.put(tc, value);
			}
		}
		return (Object[]) tableMap.get(tc);
	}

	public Object[] retTableColumn(String tableName, String primaryKey, String dbname) {
		Connection con = null;
		try {
			con = querySupport.retConnection(dbname);
			return retTableColumn(con,tableName,primaryKey);
		} catch (SQLException e) {
			return null;
		} finally {
			DbUtils.closeQuietly(con);
		}
	}

	public Object[] initColumns(Connection con, String tableName, String primaryKey) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Object[] values = new Object[2];
		try {
			StringBuffer loadsql = new StringBuffer();
			loadsql.append(" select * from ");
			loadsql.append(tableName);
			loadsql.append(" where ");
			loadsql.append(primaryKey);
			loadsql.append(" = ? ");

			stmt = con.prepareStatement(loadsql.toString());
			stmt.setString(1, "-1");
			rs = stmt.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			int[] columnTypes = new int[columnCount];
			List<String> columnNames = new ArrayList<String>();

			for (int i = 0; i < columnCount; i++) {
				columnNames.add(md.getColumnLabel((i + 1)).toLowerCase());
				columnTypes[i] = md.getColumnType(i + 1);
			}
			values[0] = columnNames;
			values[1] = columnTypes;
			return values;
		} catch (SQLException e) {
			log.error("init error, could not init columns type", e);
			throw new SQLException("初始化表信息的时候出错" + e.getMessage(),e);
		} finally {
			DbUtils.closeQuietly(null, stmt, rs);
		}
	}
}


