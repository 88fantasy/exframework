package com.gzmpc.config;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @author rwe
* @version 创建时间：Apr 5, 2020 2:03:21 PM
* 类说明
*/

@Configuration
public class DbTypeConfig {

	@SuppressWarnings("restriction")
	@Bean
	@ConditionalOnMissingBean(name = "mysqlDbType")
	public CachedRowSet mysqlDbType() throws SQLException {
		return new com.sun.rowset.CachedRowSetImpl();
	}
}
