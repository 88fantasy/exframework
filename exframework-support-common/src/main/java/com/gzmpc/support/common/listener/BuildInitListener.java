package com.gzmpc.support.common.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import com.gzmpc.support.common.build.BuildService;

@Repository
public class BuildInitListener implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	BuildService build;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		ApplicationContext ac = event.getApplicationContext();
		build.reload(ac);
	}

}
