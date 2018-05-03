package com.gzmpc.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gzmpc.dao.DbDao;
import com.gzmpc.dao.EbDao;
import com.gzmpc.stereotype.DbInit;

@Repository("ebDao")
@DbInit
public class EbDaoImpl extends DbDao implements EbDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Resource(name = "ebSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public Connection getUnautocommitConnection() throws SQLException {
		Connection con = this.getConnection();
		con.setAutoCommit(false);
		return con;
	}

	@Override
	public JdbcTemplate getTemplate() {
		return new JdbcTemplate(dataSource);
	}

}
