package com.gzmpc.support.monitor.stat.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
* @author rwe
* @version 创建时间：2018年5月13日 下午4:17:18
* uri 衡量数据收集类
*/

public class RestUriStat {
	
	 private final String  uri;

	/**
	 * uri请求数
	 **/
	private final AtomicLong requestCount = new AtomicLong();
	
	/**
	 * uri成功处理数
	 **/
	private final AtomicLong successCount = new AtomicLong();
	
	/**
	 * uri正在执行中数
	 **/
	private final AtomicInteger runningCount = new AtomicInteger();
	
	/**
	 * uri最大并发数
	 */
	private final AtomicInteger concurrentMax = new AtomicInteger();
	
	/**
	 * uri最长执行时间
	 **/
	private long maxRuntime = 0 ;
	
	/**
	 * uri历史执行时间
	 **/
	private final AtomicLong totalRuntime = new AtomicLong();
	
	/**
	 * uri最短执行时间
	 **/
	private long minRuntime = 0 ;
	
	public RestUriStat(String uri){
        this.uri = uri;
    }
	
	public void requestCountIncrease() {
		requestCount.getAndIncrement();
	}
	
	public void successCountIncrease() {
		successCount.getAndIncrement();
	}
	
	public void runningCountIncrease() {
		runningCount.getAndIncrement();
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
	
	public void afterInvoke(Throwable e, long runtime) {
		runningCount.decrementAndGet();
		totalRuntime.addAndGet(runtime);
		if(e == null) {
			successCount.incrementAndGet();
		}
		
		if( runtime > maxRuntime || maxRuntime == 0) {
			maxRuntime = runtime;
		}
		if( runtime < minRuntime || minRuntime == 0) {
			minRuntime = runtime;
		}
	}
	
	public Map<String, Object> getStatData() {
		ConcurrentHashMap<String,Object> data = new ConcurrentHashMap<String,Object>();
		
		data.put("uri", uri);
		data.put("totalRuntime", totalRuntime);
		data.put("minRuntime", minRuntime);
		data.put("maxRuntime", maxRuntime);
		data.put("avgRuntime", totalRuntime.get() / requestCount.get());
		data.put("requestCount", requestCount.longValue());
		data.put("successCount", successCount.longValue());
		data.put("runningCount", runningCount.longValue());
		data.put("concurrentMax", concurrentMax.longValue());
		
		
		
		return data;
	}
}
