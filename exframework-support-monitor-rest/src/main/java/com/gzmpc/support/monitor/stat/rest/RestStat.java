package com.gzmpc.support.monitor.stat.rest;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.gzmpc.support.monitor.job.annotation.Monitor;
import com.gzmpc.support.monitor.stat.Stat;

/**
 * @author rwe
 * @version 创建时间：2018年5月10日 上午11:49:21 
 * rest 衡量数据收集类
 */

@Monitor
public class RestStat extends Stat {

	private final AtomicLong requestCount = new AtomicLong();
	private final AtomicInteger runningCount = new AtomicInteger();
	private final AtomicInteger concurrentMax = new AtomicInteger();
	
	private ConcurrentHashMap<String,RestUriStat> uristats = new ConcurrentHashMap<String,RestUriStat>();

	public long getRequestCount() {
		return requestCount.longValue();
	}

	public void requestCountIncrease() {
		requestCount.getAndIncrement();
	}
	
	public void beforeInvoke() {
		requestCount.incrementAndGet();
		int running = runningCount.incrementAndGet();

        for (;;) {
            int max = concurrentMax.get();
            if (running > max) {
                if (concurrentMax.compareAndSet(max, running)) {
                    break;
                }
            } else {
                break;
            }
        }
	}
	
	public void afterInvoke(Throwable e,long runtime) {
		runningCount.decrementAndGet();
	}
	
	public RestUriStat register(String uri, RestUriStat uristat) {
		RestUriStat r = uristats.putIfAbsent(uri, uristat);
		return r==null?uristat:r;
	}

	@Override
	public Map<String, Object> getStatData() {
		Map<String, Object> data = new LinkedHashMap<String,Object>();
		
		data.put("requestCount", requestCount.get());
		data.put("runningCount", runningCount.get());
		data.put("concurrentMax", concurrentMax.get());
		
		if(uristats.size() > 0) {
			Map<String,Object> uris = new ConcurrentHashMap<String,Object>();
			for(String key : uristats.keySet()) {
				RestUriStat uristat = uristats.get(key);
				Map<String, Object> uridata = uristat.getStatData();
				uris.put(key, uridata);
			}
			data.put("uris", uris);
		}
		
		return data;
	}
	
	@Override
	public void reset() {
		requestCount.set(0);
	}
}
