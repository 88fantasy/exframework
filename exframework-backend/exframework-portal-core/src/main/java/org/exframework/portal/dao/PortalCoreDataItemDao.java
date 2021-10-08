package org.exframework.portal.dao;

import org.exframework.portal.enums.DataItemValueType;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface PortalCoreDataItemDao extends PortalCoreMetaDao<DataItem> {

	Map<String,List<DataItem>> allExtends();
	
	@Nullable
	DataItem findExtend(String objectCode, String code);
	
	@Nullable
	Collection<DataItem> findExtendByObjectCode(String objectCode);
	
	PageModel<DataItem> query(Collection<FilterCondition> params, Page page);
	
	PageModel<DataItem> query(Collection<FilterCondition> params, Page page, Collection<String> sorts);
	
	List<DataItem> list(Collection<FilterCondition> params);
	
	default DataItemValueType defaultValueType(Object value) {
		if( value != null) {
			Class<?> c = value.getClass();
			if(c.isArray()) {
				return DataItemValueType.STRING;
			}
			switch (c.getName()) {
				case "java.lang.Integer":
				case "java.lang.Long":
					return DataItemValueType.LONG;
				case "java.lang.Double":
					return DataItemValueType.BIGDECIMAL;
				case "java.lang.Boolean":
					return DataItemValueType.BOOLEAN;
				case "java.util.List":
				case "java.util.Collection":
					return DataItemValueType.STRING;
				case "java.util.Date":
					return DataItemValueType.DATETIME;
				default:
					return DataItemValueType.STRING;
			}
		}
		else {
			return DataItemValueType.STRING;
		}
	}
}
