package org.exframework.portal.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.lang.Nullable;

import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.di.DataItem.DataItemValueTypeEnum;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;

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
	
	PageModel<DataItem> query(Collection<FilterCondition> params, Page page, Collection<String> sorts);
	
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
