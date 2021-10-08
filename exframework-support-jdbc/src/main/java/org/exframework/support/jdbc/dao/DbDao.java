package org.exframework.support.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

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
