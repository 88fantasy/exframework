package com.gzmpc.support.monitor.stat;

import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;
import org.json.JSONObject;
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
		JSONArray array = new JSONArray();
		for(String key : stats.keySet()) {
			Stat stat = stats.get(key);
			JSONObject row = new JSONObject();
			row.put(key, stat.getStatData());
			array.put(row);
		}
		return array.toString();
	}
}
