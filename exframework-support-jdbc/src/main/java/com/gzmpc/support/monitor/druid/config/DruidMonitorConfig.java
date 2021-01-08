package com.gzmpc.support.monitor.druid.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
	
	private Log log = LogFactory.getLog(DruidMonitorConfig.class.getName());

	@Bean
	@ConditionalOnMissingBean(name = "logFilter")
	public Log4j2Filter logFilter () {
		log.info("加载druid 日志配置");
		Log4j2Filter logFilter = new Log4j2Filter();
	    logFilter.setStatementExecutableSqlLogEnable(true);
	    logFilter.setStatementLogEnabled(false);
	    return logFilter;
	}

	@Bean
	@ConditionalOnMissingBean(name = "statFilter")
	public StatFilter statFilter () {
		log.info("加载druid 慢SQL日志配置");
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
	@ConditionalOnMissingBean(name = "wallFilter")
	public WallFilter wallFilter (WallConfig wallConfig) {
		log.info("加载druid sql防火墙过滤器配置");
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
	@ConditionalOnMissingBean(name = "wallConfig")
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
