package com.gzmpc.support.monitor.stat.job;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.quartz.JobKey;

import com.gzmpc.support.monitor.job.annotation.Monitor;
import com.gzmpc.support.monitor.stat.Stat;

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
			errorCount += (long) data.get("errorCount");
			if( (boolean) data.get("executing") ) {
				executingCount ++;
			}
		}
		
		ConcurrentHashMap<String,Object> data = new ConcurrentHashMap<String,Object>();
		data.put("executeCount", executeCount);
		data.put("errorCount", errorCount);
		data.put("executingCount", executingCount);
		data.put("detailStats", detailStatMap);
		
		return data;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
