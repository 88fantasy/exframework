package org.exframework.support.monitor.stat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* @author rwe
* @version 创建时间：2018年5月19日 下午12:50:50
* 类说明
*/

@Service
public class StatManager {

	@Autowired
	ObjectMapper objectMapper;

	private final ConcurrentHashMap<String,Stat> stats = new ConcurrentHashMap<String,Stat>();
	
	public Stat register(String key,Stat stat) {
		return stats.putIfAbsent(key, stat);
	}
	
	public String getJSON() {
		Map<String,Object> row = new ConcurrentHashMap<>();
		for(String key : stats.keySet()) {
			Stat stat = stats.get(key);
			row.put(key, stat.getStatData());
		}
		try {
			return objectMapper.writeValueAsString(row);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
