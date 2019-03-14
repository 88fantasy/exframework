package com.gzmpc.support.monitor.stat;

import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
* @author rwe
* @version 创建时间：2018年5月19日 下午12:50:50
* 类说明
*/

@Service
public class StatManager {

	private final ConcurrentHashMap<String,Stat> stats = new ConcurrentHashMap<String,Stat>();
	
	public Stat register(String key,Stat stat) {
		return stats.putIfAbsent(key, stat);
	}
	
	public String getJSON() {
		JSONObject row = new JSONObject();
		for(String key : stats.keySet()) {
			Stat stat = stats.get(key);
			row.put(key, stat.getStatData());
		}
		return row.toString();
	}
}
