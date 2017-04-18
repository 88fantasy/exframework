package com.gzmpc.metadata.queryparam;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.gzmpc.metadata.queryparamitem.QueryParamBase;
import com.gzmpc.util.SpringContextUtils;

/**
 * 查询适配器
 * 
 */

@Component
public class QueryParamAdapter {
	public Map<String, String> queryparamClassMap;

	QueryParamAdapter() {
		queryparamClassMap = new ConcurrentHashMap<String, String>();
		queryparamClassMap.put("s", "QPIString");
		queryparamClassMap.put("su", "QPIStringUpper");
		queryparamClassMap.put("sl", "QPIStringLower");
		queryparamClassMap.put("tree", "QPITree");// 树形
		queryparamClassMap.put("n", "QPINumber");
		queryparamClassMap.put("h", "QPIHov");
		queryparamClassMap.put("dt", "QPIDate");
		queryparamClassMap.put("ddl", "QPIDdl");
		queryparamClassMap.put("sddl", "QPISqlDdl");
	}


	public QueryParamBase retBase(QueryParamItem qpi) {
		String beanid = queryparamClassMap.get(qpi.getQpitype());
		QueryParamBase instance = SpringContextUtils.getBeanById(beanid, QueryParamBase.class);
		return instance;
	}
}
