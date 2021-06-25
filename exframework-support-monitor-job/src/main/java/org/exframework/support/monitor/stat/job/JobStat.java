package org.exframework.support.monitor.stat.job;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.quartz.JobKey;

import org.exframework.support.monitor.job.annotation.Monitor;
import org.exframework.support.monitor.stat.Stat;

/**
* @author rwe
* @version 创建时间：2018年5月19日 下午12:16:12
* 任务衡量标准类
*/

@Monitor
public class JobStat extends Stat {
	
	private ConcurrentHashMap<JobKey,JobDetailStat> detailStatMap = new ConcurrentHashMap<JobKey,JobDetailStat>();

	public JobDetailStat register(JobKey key,JobDetailStat detail) {
		JobDetailStat d = detailStatMap.putIfAbsent(key, detail);
		return d==null?detail:d;
	}

	@Override
	public Map<String, Object> getStatData() {
		
		double executeCount = 0;
		double errorCount = 0;
		long executingCount = 0;
		for(JobDetailStat detailStat : detailStatMap.values()) {
			Map<String, Object> data = detailStat.getStatData();
			executeCount += (long) data.get("executeCount");
			errorCount += (int) data.get("errorCount");
			if( (long) data.get("executing") > 0) {
				executingCount ++;
			}
		}
		
		ConcurrentHashMap<String,Object> data = new ConcurrentHashMap<String,Object>();
		data.put("executeCount", executeCount);
		data.put("errorCount", errorCount);
		data.put("executingCount", executingCount);
		
		ConcurrentHashMap<String,JobDetailStat> detailStats = new ConcurrentHashMap<String,JobDetailStat>();
		for( JobKey key : detailStatMap.keySet()) {
			JobDetailStat stat = detailStatMap.get(key);
			detailStats.put(key.getName(), stat);
		}
		data.put("detailStats", detailStats);
		
		return data;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
