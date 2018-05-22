package com.gzmpc.support.monitor.listener;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.gzmpc.support.monitor.job.annotation.Monitor;
import com.gzmpc.support.monitor.stat.Stat;
import com.gzmpc.support.monitor.stat.StatManager;


@Repository
public class MonitorListener implements ApplicationListener<ContextRefreshedEvent> {

	private Log log = LogFactory.getLog(MonitorListener.class.getName());
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
	           //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
			ApplicationContext ac = event.getApplicationContext();
			
			log.info("开始自动加载[com.gzmpc.support.monitor.job.annotation.Monitor]注解");
			StatManager manager = ac.getBean(StatManager.class);
			Map<String, Object> stats = ac.getBeansWithAnnotation(Monitor.class);
			for(String name : stats.keySet()) {
				Object o = stats.get(name);
				if(o instanceof Stat){	//判断是否实现了接口
					Stat stat = (Stat)o;
					manager.register(stat.getStatKey(), stat);
					log.info("注册["+stat.getStatKey()+"]监控类");
				}
			}
			log.info("自动加载[com.gzmpc.support.monitor.job.annotation.Monitor]注解结束");
			
	      }
	}
	
}
