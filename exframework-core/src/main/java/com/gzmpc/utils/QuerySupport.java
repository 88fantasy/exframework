package com.gzmpc.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.gzmpc.dao.DbDao;
import com.gzmpc.util.SpringContextUtils;

@Repository
public class QuerySupport {
	
	@Value("${system.dao.default:systemDao}")
	private String defaultDbName;
	
	public Connection retConnection() throws SQLException {
		return SpringContextUtils.getBeanById(defaultDbName, DbDao.class).getConnection();
	}
	
	public Connection retConnection(String dbname) throws SQLException {
		if( dbname == null) {
			dbname = defaultDbName;
		}
		return SpringContextUtils.getBeanById(dbname, DbDao.class).getConnection();
	}
}
