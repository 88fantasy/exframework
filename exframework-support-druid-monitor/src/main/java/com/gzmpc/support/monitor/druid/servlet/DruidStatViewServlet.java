package com.gzmpc.support.monitor.druid.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * @author rwe
 * @version 创建时间：2018年5月9日 下午3:31:05 类说明
 */

@WebServlet(urlPatterns = "/druid/*", initParams = {
		/** 白名单，如果不配置或value为空，则允许所有 */
		@WebInitParam(name = "allow", value = ""),
		/** 黑名单，与白名单存在相同IP时，优先于白名单 */
		@WebInitParam(name = "deny", value = ""),
		/** 用户名 */
//		@WebInitParam(name = "loginUsername", value = "root"),
		/** 密码 */
//		@WebInitParam(name = "loginPassword", value = "sunshine"),
		/** 禁用HTML页面上的“Reset All”功能 */
		@WebInitParam(name = "resetEnable", value = "false") })
public class DruidStatViewServlet extends StatViewServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4937724149404344684L;
	

}
