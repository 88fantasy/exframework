package com.gzmpc.portal.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.lang.Nullable;

import com.gzmpc.portal.metadata.FilterCondition;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.metadata.di.DataItem.DataItemValueTypeEnum;
import com.gzmpc.support.common.entity.Page;
import com.gzmpc.support.common.entity.PageModel;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface DataItemDao extends MetaDao<DataItem> {

	Map<String,List<DataItem>> allExtends();
	
	@Nullable
	DataItem findExtend(String objectCode, String code);
	
	@Nullable
	Collection<DataItem> findExtendByObjectCode(String objectCode);
	
	PageModel<DataItem> query(Collection<FilterCondition> params, Page page);
	
	List<DataItem> list(Collection<FilterCondition> params);
	
	default DataItemValueTypeEnum defaultValueType(Object value) {
		if( value != null) {
			Class<?> c = value.getClass();
			if(c.isArray()) {
				return DataItemValueTypeEnum.STRING;
			}
			switch (c.getName()) {
				case "java.lang.Integer":
				case "java.lang.Long":
					return DataItemValueTypeEnum.LONG;
				case "java.lang.Double":
					return DataItemValueTypeEnum.BIGDECIMAL;
				case "java.lang.Boolean":
					return DataItemValueTypeEnum.BOOLEAN;
				case "java.util.List":
				case "java.util.Collection":
					return DataItemValueTypeEnum.STRING;
				case "java.util.Date":
					return DataItemValueTypeEnum.DATETIME;
				default:
					return DataItemValueTypeEnum.STRING;
			}
		}
		else {
			return DataItemValueTypeEnum.STRING;
		}
	}
}
