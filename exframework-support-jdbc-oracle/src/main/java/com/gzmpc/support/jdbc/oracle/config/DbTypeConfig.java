package com.gzmpc.support.jdbc.oracle.config;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import oracle.jdbc.rowset.OracleCachedRowSet;

/**
* @author rwe
* @version 创建时间：Apr 5, 2020 3:27:26 PM
* 类说明
*/

@Configuration
public class DbTypeConfig {

	@Bean
	public CachedRowSet oracleDbType() throws SQLException {
		return new OracleCachedRowSet();
	}
}
