package com.gzmpc.metadata.di;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.gzmpc.metadata.di.dt.DispType;
import com.gzmpc.util.SpringContextUtils;

import org.springframework.stereotype.Component;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

@Component
public class DispTypeAdapter {
	public Map<String, String> dataClassMap;
	public String DEF_DATACLASSTYPE = "edit";// 默认是编辑框

	DispTypeAdapter() {
		dataClassMap = new ConcurrentHashMap<String, String>();
		dataClassMap.put("edit", "DTEdit");
		dataClassMap.put("list", "DTList");// key里配置好
		dataClassMap.put("password", "DTPassword");
		dataClassMap.put("querylist", "DTQueryList");
		dataClassMap.put("checkbox", "DTCheckbox");
		dataClassMap.put("sqllist", "DTSqlList");
		dataClassMap.put("checkquerylist", "DTCheckQueryList");
		dataClassMap.put("checksqllist", "DTCheckSqlList");
	}

	public DispType retDispType(String type) {
		String beanid = dataClassMap.get(type);
		DispType instance = SpringContextUtils.getBeanById(beanid, DispType.class);
		return instance;
	}

	
}
