package org.exframework.support.jdbc.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.exframework.support.doc.annotation.DataBaseGen;
import org.exframework.support.doc.annotation.TableFieldDoc;
import org.exframework.support.doc.entity.DataBaseTable;
import org.exframework.support.doc.entity.DataBaseTableField;
import org.exframework.support.doc.entity.DataBaseTableSource;

/**
 *
 * Author: rwe
 * Date: 2021年3月10日
 *
 * Copyright @ 2021 
 * 
 */
@DataBaseGen
public class JdbcDataBaseSource implements DataBaseTableSource {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Collection<DataBaseTable> getTables() {
		List<DataBaseTable> tables = new ArrayList<DataBaseTable>();
		Map<String, Object> entities = applicationContext.getBeansWithAnnotation(TableName.class);
		for (Entry<String, Object> entry : entities.entrySet()) {
			Object o = entry.getValue();
			Class<?> c = o.getClass();
			TableName document = c.getAnnotation(TableName.class);
			if(document != null) {
				String name = document.value();
				List<DataBaseTableField> fields = new ArrayList<DataBaseTableField>();
				ReflectionUtils.doWithFields(o.getClass(), new ReflectionUtils.FieldCallback() {
					@Override
					public void doWith(java.lang.reflect.Field field) throws IllegalArgumentException, IllegalAccessException {
						ReflectionUtils.makeAccessible(field);
						boolean empty = field.isAnnotationPresent(TableId.class) || field.isAnnotationPresent(NotNull.class)
								|| field.isAnnotationPresent(NotEmpty.class);
						TableFieldDoc doc = getFieldDescription(field);
						DataBaseTableField f = new DataBaseTableField(field.getName(), doc.value(), field.getType(), !empty, doc.defaultValue());
						fields.add(f);
					}
				}, new ReflectionUtils.FieldFilter() {
					@Override
					public boolean matches(Field field) {
						return field.isAnnotationPresent(TableId.class) || field.isAnnotationPresent(TableField.class);
					}
				} );
				DataBaseTable table = new DataBaseTable(name, getTableDescription(c), fields);
				tables.add(table);
			}
		}
		return tables;
	}

}
