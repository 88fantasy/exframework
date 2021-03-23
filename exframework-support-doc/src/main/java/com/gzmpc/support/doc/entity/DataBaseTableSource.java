package com.gzmpc.support.doc.entity;

import java.lang.annotation.Annotation;
import java.util.Collection;

import com.gzmpc.support.doc.annotation.TableDoc;
import com.gzmpc.support.doc.annotation.TableFieldDoc;

/**
 * 数据库类型源
 * Author: rwe
 * Date: 2021年3月10日
 *
 * Copyright @ 2021 
 * 
 */
public interface DataBaseTableSource {
	
	final String DEFAUTL_DESCRIPTION = "描述";
	final String DEFAUTL_VALUE = " ";
	

	Collection<DataBaseTable> getTables();
	
	default String getTableDescription(Class<?> tableClass) {
		TableDoc doc = tableClass.getAnnotation(TableDoc.class);
		if(doc != null) {
			return doc.value();
		}
		else {
			return DEFAUTL_DESCRIPTION;
		}
	}
	
	default TableFieldDoc getFieldDescription(java.lang.reflect.Field field) {
		TableFieldDoc dtl = field.getAnnotation(TableFieldDoc.class);
		if(dtl != null) {
			return new TableFieldDoc() {
				@Override
				public Class<? extends Annotation> annotationType() {
					return TableFieldDoc.class;
				}

				@Override
				public String value() {
					return DEFAUTL_DESCRIPTION;
				}

				@Override
				public String defaultValue() {
					return DEFAUTL_VALUE;
				}
				
			};
		}
		else {
			return dtl;
		}
	}
	
	
}