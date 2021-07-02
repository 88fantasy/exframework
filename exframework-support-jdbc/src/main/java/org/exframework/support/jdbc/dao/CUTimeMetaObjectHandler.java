package org.exframework.support.jdbc.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.ReflectionUtils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.exframework.support.jdbc.annotation.AutoCreateTime;
import org.exframework.support.jdbc.annotation.AutoUpdateTime;

/**
 *
 * @author rwe
 * @since 2021年5月21日
 *
 * Copyright @ 2021 
 * 
 */
public class CUTimeMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		setDate(metaObject, AutoCreateTime.class);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		setDate(metaObject, AutoUpdateTime.class);
	}

	private void setDate(MetaObject metaObject, Class<? extends Annotation> clazz) {
		Object o = metaObject.getOriginalObject();
		ReflectionUtils.doWithFields(o.getClass(), new ReflectionUtils.FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
//				ReflectionUtils.makeAccessible(field);
				// 如果字段添加了我们自定义注解
				setFieldValByName(field.getName(),new Date(),metaObject);
			}
		}, new ReflectionUtils.FieldFilter() {
			@Override
			public boolean matches(Field field) {
				return field.isAnnotationPresent(clazz);
			}
		});
	}
	
}
