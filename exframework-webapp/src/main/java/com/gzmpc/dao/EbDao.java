package com.gzmpc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public interface EbDao {

	public DataSource getDataSource();

	public Connection getConnection() throws SQLException;
	
	public Connection getUnautocommitConnection() throws SQLException;
	
	public JdbcTemplate getTemplate();

}
