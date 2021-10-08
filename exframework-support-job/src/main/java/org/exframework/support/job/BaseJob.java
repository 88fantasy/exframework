package org.exframework.support.job;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public abstract class BaseJob extends QuartzJobBean {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		FastDateFormat dateTimeFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
		Date start = new Date();
		String desc = context.getJobDetail().getDescription();
		log.info("任务["+desc+"]开始执行"+dateTimeFormat.format(start));
		 
		try {
			executeJob(context);
			
			Date end = new Date();
			log.info("任务["+desc+"]执行完成"+dateTimeFormat.format(end)+",耗时"+((end.getTime()-start.getTime())/1000));
		}
		catch( Exception e) {
			Date end = new Date();
			log.error("任务["+desc+"]执行出现错误["+e.getMessage()+"]"+dateTimeFormat.format(end)+",耗时"+((end.getTime()-start.getTime())/1000),e);
		}
				
	}

	public abstract void executeJob(JobExecutionContext context);
	
}
