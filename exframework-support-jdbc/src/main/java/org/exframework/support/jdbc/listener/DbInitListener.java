package org.exframework.support.jdbc.listener;

import java.sql.Connection;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import org.exframework.support.jdbc.annotation.DbInit;
import org.exframework.support.jdbc.dao.DbDao;


@Repository
public class DbInitListener implements ApplicationListener<ApplicationReadyEvent> {

	private Log log = LogFactory.getLog(DbInitListener.class.getName());

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		ApplicationContext ac = event.getApplicationContext();
		
		log.info("开始自动加载[DbInit]注解");
		Map<String, Object> dbdaos = ac.getBeansWithAnnotation(DbInit.class);
		for(String name : dbdaos.keySet()) {
			Object o = dbdaos.get(name);
			if(o instanceof DbDao){	//判断是否实现了接口
				log.info("加载数据库连接["+o.getClass().getName()+"]开始");
				Connection con = null;
				DbDao dao = (DbDao)o;
				try{
					con = dao.getConnection();
					con.setAutoCommit(false);
					con.commit();
					log.info("加载数据库连接["+o.getClass().getName()+"]结束");
				} catch (Exception e) {
					log.error("加载数据库连接["+o.getClass().getName()+"]失败:"+e.getMessage(),e);
				} finally {
					DbUtils.closeQuietly(con);
				}
			}
		}
		log.info("自动加载[DbInit]注解结束");
	}
	
}
