package com.gzmpc.support.monitor.druid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.logging.Log4j2Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

/**
* @author rwe
* @version 创建时间：Apr 5, 2020 1:25:18 PM
* druid监控面板加载设置
*/

@Configuration
public class DruidMonitorConfig {

	@Bean
	public Log4j2Filter logFilter () {
		Log4j2Filter logFilter = new Log4j2Filter();
	    logFilter.setStatementExecutableSqlLogEnable(true);
	    logFilter.setStatementLogEnabled(false);
	    return logFilter;
	}

	@Bean
	public StatFilter statFilter () {
	    StatFilter statFilter = new StatFilter();
	    statFilter.setSlowSqlMillis(5000);
	    statFilter.setLogSlowSql(true);
	    statFilter.setMergeSql(true);
	    return statFilter;
	}
	
	/**
	 * sql防火墙过滤器配置
	 * @param wallConfig
	 * @return
	 */
	@Bean
	public WallFilter wallFilter (WallConfig wallConfig) {
	    WallFilter wallFilter = new WallFilter();
	    wallFilter.setConfig(wallConfig);
	    wallFilter.setLogViolation(true);//对被认为是攻击的SQL进行LOG.error输出
	    wallFilter.setThrowException(false);//对被认为是攻击的SQL抛出SQLException
	    return wallFilter;
	}

	/**
	 * sql防火墙配置
	 * @return
	 */
	@Bean
	public WallConfig wallConfig () {
	    WallConfig wallConfig = new WallConfig();
	    wallConfig.setAlterTableAllow(false);
	    wallConfig.setCreateTableAllow(false);
	    wallConfig.setDeleteAllow(false);
	    wallConfig.setMergeAllow(false);
	    wallConfig.setDescribeAllow(false);
	    wallConfig.setShowAllow(false);
	    return wallConfig;
	}
}
