package com.gzmpc.portal.metadata;

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

import com.gzmpc.portal.metadata.dict.Dictionary;
import com.gzmpc.portal.metadata.dict.DictionaryEnum;
import com.gzmpc.portal.metadata.dict.DictionaryEnumClass;
import com.gzmpc.portal.metadata.entity.EntityClass;
import com.gzmpc.support.common.entity.Page;

@EntityClass
public class FilterCondition implements DictionaryEnumClass {

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

	FilterCondition() {
		
	}
	
	public FilterCondition(String key, Object filterValue) {
		this(key, defaultOper(filterValue), filterValue);
	}
	
	public FilterCondition(String key, FilterConditionOper oper, Object filterValue) {
		this(key, oper, filterValue, defaultType(filterValue));
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
	
	public static FilterConditionOper defaultOper(Object value) {
		if( value != null) {
			Class<?> c = value.getClass();
			if(c.isArray()) {
				return FilterConditionOper.IN;
			}
			switch (c.getName()) {
				case "java.lang.String":
					return FilterConditionOper.MATCHING;
				case "java.util.List":
				case "java.util.Collection":
					return FilterConditionOper.IN;
				default:
					return FilterConditionOper.EQUAL;
			}
		}
		else {
			return FilterConditionOper.EQUAL;
		}
	}

	public static FilterConditionDataType defaultType(Object value) {
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
					return FilterConditionDataType.LIST;
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
								FilterCondition fc = new FilterCondition(targetPd.getName(), value);
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
	
	@Dictionary( value = "FilterConditionDataType", name = "条件数据类型")
	public enum FilterConditionDataType implements DictionaryEnum {

		STRING("字符串"), 
		LIST("数组"), 
		NUMBER("数字"),
		BOOLEAN("布尔"), 
		JSON("JSON"), 
		DATE("日期"),
		DATETIME("日期时间")
		;

		private String label;

		private FilterConditionDataType(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	@Dictionary(value = "FilterConditionOper", name = "条件操作符")
	public enum FilterConditionOper implements DictionaryEnum {

		EQUAL("等于"), GREATER("大于"), LESS("小于"), BETWEEN("介于"),
		GREATER_EQUAL("大于等于"), LESS_EQUAL("小于等于"), IN("包含"),
		MATCHING("匹配"), NOT_EQUAL("不等于"), ISNULL("为空"),
		IS_NOT_NULL("不为空"), STR("自定义");


		private String label;

		private FilterConditionOper(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends DictionaryEnum>[] enums() {
		return new Class[] {FilterConditionDataType.class, FilterConditionOper.class };
	}

}