package com.gzmpc.init;

import java.sql.Connection;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.gzmpc.build.BuildService;
import com.gzmpc.dao.DbDao;
import com.gzmpc.stereotype.DbInit;

@Repository
public class ProjectInit implements ApplicationListener<ContextRefreshedEvent> {

	private Log log = LogFactory.getLog(ProjectInit.class.getName());
	
	@Autowired
	BuildService build;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
	           //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
			ApplicationContext ac = event.getApplicationContext();
			log.info("系统初始化.......");
			
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
			
			
			build.reload(ac);
			
			log.info("系统完成初始化.......");
	      }
	}
	
}
