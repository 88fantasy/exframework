package com.gzmpc.support.jdbc.service;

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
import com.gzmpc.support.doc.annotation.DataBaseGen;
import com.gzmpc.support.doc.entity.DataBaseTable;
import com.gzmpc.support.doc.entity.DataBaseTableField;
import com.gzmpc.support.doc.entity.DataBaseTableSource;

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
			String name = document.value();
			List<DataBaseTableField> fields = new ArrayList<DataBaseTableField>();
			ReflectionUtils.doWithFields(o.getClass(), new ReflectionUtils.FieldCallback() {
				@Override
				public void doWith(java.lang.reflect.Field field) throws IllegalArgumentException, IllegalAccessException {
					ReflectionUtils.makeAccessible(field);
					if (field.isAnnotationPresent(TableId.class) || field.isAnnotationPresent(TableField.class)) {
						boolean empty = field.isAnnotationPresent(TableId.class) || field.isAnnotationPresent(NotNull.class)
								|| field.isAnnotationPresent(NotEmpty.class);
						DataBaseTableField f = new DataBaseTableField(field.getName(), "描述", field.getType(), !empty);
						fields.add(f);
					}
				}
			});
			DataBaseTable table = new DataBaseTable(name, "", fields);
			tables.add(table);
		}
		return tables;
	}

}
