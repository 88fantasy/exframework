package com.gzmpc.support.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JSONUtil {

	public Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if (object == null ) {
			return null;
		}
		
		Iterator<String> keysItr = object.keySet().iterator();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.size(); i++) {
			Object value = array.get(i);
			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object>[] toMapArray(JSONArray array) throws JSONException {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < array.size(); i++) {
			Object value = array.get(i);
			if (value instanceof JSONObject) {
				list.add(toMap((JSONObject) value));
			}
			else {
				throw new JSONException("数组内非JSONObject");
			}
			
		}
		return list.toArray(new ConcurrentHashMap[list.size()]);
	}
}
