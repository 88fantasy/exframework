package com.gzmpc.support.monitor.job;

import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzmpc.support.job.BaseJob;
import com.gzmpc.support.monitor.stat.job.JobDetailStat;
import com.gzmpc.support.monitor.stat.job.JobStat;

/**
* @author rwe
* @version 创建时间：2018年5月19日 下午12:24:16
* 类说明
*/

@Component
public abstract class MonitorBaseJob extends BaseJob {

	private Logger log = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	JobStat jobStat;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		FastDateFormat dateTimeFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
		
		JobDetail detail = context.getJobDetail();
		String desc = detail.getDescription();
		String msg = "正常完成";
		Exception error = null;
		
		JobDetailStat detailStat = jobStat.register(detail.getKey(), new JobDetailStat(detail));
		Date start = new Date();
		detailStat.setStartDate(start);
		detailStat.executeCountIncrement();
		
		log.info("任务["+desc+"]开始执行"+dateTimeFormat.format(start));
		 
		try {
			executeJob(context);
		}
		catch( Exception e) {
			error = e;
			msg = e.getMessage();
			detailStat.errorCountIncrement();
			detailStat.errorTotalIncrement();
		} finally {
			Date end = new Date();
			detailStat.setLastStartDate(start);
			detailStat.setLastEndDate(end);
			long runtime = end.getTime() - start.getTime();
			detailStat.setRuntime(runtime);
			detailStat.setLastMsg(msg);
			detailStat.setStartDate(null);
			
			if(error == null) {
				detailStat.setErrorCount(0);
				log.info("任务["+desc+"]执行完成"+dateTimeFormat.format(end)+",耗时"+(runtime/1000));
			}
			else {
				log.error("任务["+desc+"]执行出现错误["+error.getMessage()+"]"+dateTimeFormat.format(end)+",耗时"+(runtime/1000),error);
			}
		}
				
	}
}
