package com.gzmpc.metadata;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

import com.gzmpc.metadata.enums.FilterConditionDataType;
import com.gzmpc.metadata.enums.FilterConditionOper;
import com.gzmpc.support.common.entity.Page;

public class FilterCondition {

	/**
	 * 字段
	 */
	private String key;

	/**
	 * 操作符
	 */
	private FilterConditionOper oper;

	/**
	 * 条件值
	 */
	private Object filterValue;

	/**
	 * 数据类型
	 */
	private FilterConditionDataType filterDataType;

	public FilterCondition(String key, FilterConditionOper oper, Object filterValue) {
		super();
		this.key = key;
		this.oper = oper;
		this.filterValue = filterValue;
		this.filterDataType = defaultType(filterValue);
	}

	public FilterCondition(String key, FilterConditionOper oper, Object filterValue,
			FilterConditionDataType filterDataType) {
		super();
		this.key = key;
		this.oper = oper;
		this.filterValue = filterValue;
		this.filterDataType = filterDataType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public FilterConditionOper getOper() {
		return oper;
	}

	public void setOper(FilterConditionOper oper) {
		this.oper = oper;
	}

	public Object getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(Object filterValue) {
		this.filterValue = filterValue;
	}

	public FilterConditionDataType getFilterDataType() {
		return filterDataType;
	}

	public void setFilterDataType(FilterConditionDataType filterDataType) {
		this.filterDataType = filterDataType;
	}

	private FilterConditionDataType defaultType(Object value) {
		if( value != null) {
			Class<?> c = value.getClass();
			if(c.isArray()) {
				return FilterConditionDataType.LIST;
			}
			switch (c.getName()) {
				case "java.lang.Integer":
				case "java.lang.Long":
				case "java.lang.Double":
					return FilterConditionDataType.NUMBER;
				case "java.lang.Boolean":
					return FilterConditionDataType.BOOLEAN;
				case "java.util.List":
				case "java.util.Collection":
					return FilterConditionDataType.BOOLEAN;
				case "java.util.Date":
					return FilterConditionDataType.DATETIME;
				default:
					return FilterConditionDataType.STRING;
			}
		}
		else {
			return FilterConditionDataType.STRING;
		}
	}

	public static Collection<FilterCondition> fromDTO(@Nullable Object editable) {
		return fromDTO(editable, Collections.emptyList());
	}

	public static Collection<FilterCondition> fromDTO(@Nullable Object editable, Collection<String> ignoreList) {
		List<FilterCondition> fcList = new ArrayList<FilterCondition>();
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(editable.getClass());
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(editable.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null) {
						ResolvableType sourceResolvableType = ResolvableType.forMethodReturnType(readMethod);
						ResolvableType targetResolvableType = ResolvableType.forMethodParameter(writeMethod, 0);
						if (targetResolvableType.isAssignableFrom(sourceResolvableType)) {
							try {
								if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
									readMethod.setAccessible(true);
								}
								Object value = readMethod.invoke(editable);
								if(value == null || value.getClass().isAssignableFrom(Page.class)) {
									continue;
								}
								FilterCondition fc = new FilterCondition(targetPd.getName(),
										FilterConditionOper.MATCHING, value);
								fcList.add(fc);
							} catch (Throwable ex) {
								throw new FatalBeanException(
										"Could not copy property '" + targetPd.getName() + "' from source to target",
										ex);
							}
						}
					}
				}
			}
		}
		return fcList;
	}
}