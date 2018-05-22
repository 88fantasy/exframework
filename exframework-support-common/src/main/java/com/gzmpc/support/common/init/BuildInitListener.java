package com.gzmpc.support.common.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.gzmpc.support.common.build.BuildService;

@Repository
public class BuildInitListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	BuildService build;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
	           //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
			ApplicationContext ac = event.getApplicationContext();
			
			build.reload(ac);
			
	      }
	}
	
}
