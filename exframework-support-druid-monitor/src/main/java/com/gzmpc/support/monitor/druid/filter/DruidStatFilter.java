package com.gzmpc.support.monitor.druid.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @author rwe
 * @version 创建时间：2018年5月9日 下午3:22:56
 */

@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {
		/** 忽略资源 */
		@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*,/static/*"),
		@WebInitParam(name = "profileEnable", value = "true"),
		/** 监控当前COOKIE的用户 */
		@WebInitParam(name = "principalCookieName", value = "USER_COOKIE"),
		/** 监控当前SESSION的用户 */
		@WebInitParam(name = "principalSessionName", value = "USER_SESSION"),
		/** 监控单个url访问数据库情况 */
		@WebInitParam(name = "profileEnable", value = "true") })
public class DruidStatFilter extends WebStatFilter {

}
