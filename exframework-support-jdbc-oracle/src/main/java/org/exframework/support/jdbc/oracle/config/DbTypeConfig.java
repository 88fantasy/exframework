package org.exframework.support.jdbc.oracle.config;

import oracle.jdbc.rowset.OracleCachedRowSet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

/**
* @author rwe
* @version 创建时间：Apr 5, 2020 3:27:26 PM
* 类说明
*/

@Configuration
public class DbTypeConfig {

	@Bean
	@ConditionalOnMissingBean(name = "oracleDbType")
	public CachedRowSet oracleDbType() throws SQLException {
		return new OracleCachedRowSet();
	}
}
