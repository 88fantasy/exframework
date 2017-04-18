package com.gzmpc.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DbDao {

	public Connection getConnection() throws SQLException;
}
