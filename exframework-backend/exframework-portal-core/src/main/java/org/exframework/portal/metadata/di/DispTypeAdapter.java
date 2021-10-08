package org.exframework.portal.metadata.di;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.exframework.portal.metadata.di.DataItem.DataItemDisplayTypeEnum;
import org.exframework.portal.metadata.di.dt.DispType;
import org.exframework.support.common.util.SpringContextUtils;

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
	public Map<DataItemDisplayTypeEnum, String> dataClassMap;
	public DataItemDisplayTypeEnum DEF_DATACLASSTYPE = DataItemDisplayTypeEnum.INPUT;

	DispTypeAdapter() {
		dataClassMap = new ConcurrentHashMap<DataItemDisplayTypeEnum, String>();
		dataClassMap.put(DataItemDisplayTypeEnum.INPUT, "DTEdit");
		dataClassMap.put(DataItemDisplayTypeEnum.DICTIONARY, "DTList");
		dataClassMap.put(DataItemDisplayTypeEnum.PASSWORD, "DTPassword");
		dataClassMap.put(DataItemDisplayTypeEnum.CHECKBOX, "DTCheckbox");
//		dataClassMap.put("sqllist", "DTSqlList");
//		dataClassMap.put("querylist", "DTQueryList");
	}

	public DispType retDispType(DataItemDisplayTypeEnum type) {
		String beanid = dataClassMap.get(type);
		DispType instance = SpringContextUtils.getBeanById(beanid, DispType.class);
		return instance;
	}

	
}
