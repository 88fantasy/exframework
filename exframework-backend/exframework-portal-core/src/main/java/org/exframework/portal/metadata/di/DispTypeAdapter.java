package org.exframework.portal.metadata.di;

import org.exframework.portal.enums.DataItemDisplayType;
import org.exframework.portal.metadata.di.dt.DispType;
import org.exframework.support.common.util.SpringContextUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
	public Map<DataItemDisplayType, String> dataClassMap;
	public DataItemDisplayType DEF_DATACLASSTYPE = DataItemDisplayType.INPUT;

	DispTypeAdapter() {
		dataClassMap = new ConcurrentHashMap<DataItemDisplayType, String>();
		dataClassMap.put(DataItemDisplayType.INPUT, "DTEdit");
		dataClassMap.put(DataItemDisplayType.DICTIONARY, "DTList");
		dataClassMap.put(DataItemDisplayType.PASSWORD, "DTPassword");
		dataClassMap.put(DataItemDisplayType.CHECKBOX, "DTCheckbox");
//		dataClassMap.put("sqllist", "DTSqlList");
//		dataClassMap.put("querylist", "DTQueryList");
	}

	public DispType retDispType(DataItemDisplayType type) {
		String beanid = dataClassMap.get(type);
		DispType instance = SpringContextUtils.getBeanById(beanid, DispType.class);
		return instance;
	}

	
}
