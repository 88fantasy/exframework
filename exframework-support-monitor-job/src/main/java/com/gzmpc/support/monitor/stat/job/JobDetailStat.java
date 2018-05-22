package com.gzmpc.support.monitor.stat.job;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.time.FastDateFormat;
import org.quartz.JobDetail;

/**
* @author rwe
* @version 创建时间：2018年5月19日 下午12:18:50
* 任务衡量标准类
*/

public class JobDetailStat {
	
	private final JobDetail detail;
	
	private static final FastDateFormat DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	
	private Date lastStartDate;
	
	private Date lastEndDate;
	
	private String lastMsg = "";
	
	private final AtomicLong executeCount = new AtomicLong(0);
	
	private final AtomicLong errorTotal = new AtomicLong(0);
	
	private volatile int errorCount = 0;
	
	private Date startDate;
	
	private volatile double totalRuntime = 0;
	private volatile long minRuntime = 0;
	private volatile long maxRuntime = 0;

	public JobDetailStat(JobDetail detail) {
		this.detail = detail;
	}
	
	
	public JobDetail getDetail() {
		return detail;
	}

	public void setLastStartDate(Date lastStartDate) {
		this.lastStartDate = lastStartDate;
	}


	public void setLastEndDate(Date lastEndDate) {
		this.lastEndDate = lastEndDate;
	}


	public void setLastMsg(String lastMsg) {
		this.lastMsg = lastMsg;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setRuntime(long runtime) {
		totalRuntime += runtime;
		if( runtime < minRuntime || minRuntime == 0 ) {
			minRuntime = runtime;
		}
		if ( runtime > maxRuntime || maxRuntime == 0 ) {
			maxRuntime = runtime;
		}
	}

	public void executeCountIncrement() {
		executeCount.incrementAndGet();
	}
	
	public void errorCountIncrement() {
		errorCount++;
	}
	
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	
	public void errorTotalIncrement() {
		errorTotal.incrementAndGet();
	}
	

	public Map<String, Object> getStatData() {
		ConcurrentHashMap<String,Object> data = new ConcurrentHashMap<String,Object>();
		
		data.put("totalRuntime", totalRuntime);
		data.put("minRuntime", minRuntime);
		data.put("maxRuntime", maxRuntime);
		data.put("executeCount", executeCount.longValue());
		data.put("errorTotal", errorTotal.longValue());
		data.put("errorCount", errorCount);
		data.put("executing", startDate != null);
		data.put("lastStartDate", lastStartDate != null? DATETIME_FORMAT.format(lastStartDate) : "");
		data.put("lastEndDate", lastEndDate != null? DATETIME_FORMAT.format(lastEndDate) : "");
		data.put("startDate", startDate != null ? DATETIME_FORMAT.format(startDate) : "");
		data.put("lastMsg", lastMsg);
		
		return data;
	}

	public void reset() {
		
	}

}
