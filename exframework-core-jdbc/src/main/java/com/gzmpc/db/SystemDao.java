package com.gzmpc.db;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.gzmpc.support.jdbc.dao.DbDao;
import com.gzmpc.support.jdbc.annotation.DbInit;

@Repository("systemDao")
@DbInit
public class SystemDao implements DbDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Resource(name = "systemSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


}
