package com.gzmpc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public abstract class DbDao {
	
	public abstract DataSource getDataSource();
	
	public abstract void setDataSource(DataSource dataSource);
	
	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}
