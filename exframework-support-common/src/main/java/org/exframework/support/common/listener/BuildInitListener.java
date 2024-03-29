package org.exframework.support.common.listener;

import org.exframework.support.common.build.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

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
