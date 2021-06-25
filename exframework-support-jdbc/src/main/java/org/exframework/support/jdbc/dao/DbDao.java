package org.exframework.support.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public interface DbDao {
	
	DataSource getDataSource();
	
	void setDataSource(DataSource dataSource);
	
	default Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
	
	default Connection getUnautocommitConnection() throws SQLException {
		Connection con = getDataSource().getConnection();
		con.setAutoCommit(false);
		return con;
	}
	
	default JdbcTemplate getTemplate() {
		return new JdbcTemplate(getDataSource());
	}
}
