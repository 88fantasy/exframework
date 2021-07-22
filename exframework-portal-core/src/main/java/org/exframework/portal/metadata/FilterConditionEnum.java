package org.exframework.portal.metadata;

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

import org.exframework.portal.metadata.dict.Dictionary;
import org.exframework.portal.metadata.dict.DictionaryEnum;
import org.exframework.portal.metadata.dict.DictionaryEnumClass;
import org.exframework.portal.metadata.entity.EntityClass;
import org.exframework.support.common.entity.Page;

@EntityClass
public class FilterConditionEnum implements DictionaryEnumClass {

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

		FilterConditionDataType(String label) {
			this.label = label;
		}

		@Override
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