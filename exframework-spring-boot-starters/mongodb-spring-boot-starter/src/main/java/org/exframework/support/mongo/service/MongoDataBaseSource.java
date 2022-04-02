package org.exframework.support.mongo.service;

import org.exframework.support.doc.annotation.DataBaseGen;
import org.exframework.support.doc.annotation.TableFieldDoc;
import org.exframework.support.doc.entity.DataBaseTable;
import org.exframework.support.doc.entity.DataBaseTableField;
import org.exframework.support.doc.entity.DataBaseTableSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.ReflectionUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author rwe
 * @since 2021年3月10日
 *
 * Copyright @ 2021 
 * 
 */
@DataBaseGen
public class MongoDataBaseSource implements DataBaseTableSource {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Collection<DataBaseTable> getTables() {
		List<DataBaseTable> tables = new ArrayList<DataBaseTable>();
		Map<String, Object> entities = applicationContext.getBeansWithAnnotation(Document.class);
		for (Entry<String, Object> entry : entities.entrySet()) {
			Object o = entry.getValue();
			Class<?> c = o.getClass();
			Document document = c.getAnnotation(Document.class);
			String name = document.collection();
			List<DataBaseTableField> fields = new ArrayList<DataBaseTableField>();
			ReflectionUtils.doWithFields(o.getClass(), new ReflectionUtils.FieldCallback() {
				@Override
				public void doWith(java.lang.reflect.Field field) throws IllegalArgumentException, IllegalAccessException {
					ReflectionUtils.makeAccessible(field);
					boolean empty = field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(NotNull.class)
							|| field.isAnnotationPresent(NotEmpty.class);
					TableFieldDoc doc = getFieldDescription(field);
					DataBaseTableField f = new DataBaseTableField(field.getName(), doc.value(), field.getType(), !empty, doc.defaultValue());
					fields.add(f);
				}
			}, new ReflectionUtils.FieldFilter() {
				@Override
				public boolean matches(java.lang.reflect.Field field) {
					return field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(Field.class);
				}
			});
			DataBaseTable table = new DataBaseTable(name, getTableDescription(c), fields);
			tables.add(table);
		}
		return tables;
	}

}
