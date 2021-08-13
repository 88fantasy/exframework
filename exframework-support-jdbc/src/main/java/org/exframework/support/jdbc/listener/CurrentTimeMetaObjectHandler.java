package org.exframework.support.jdbc.listener;

import java.lang.annotation.Annotation;
import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.ReflectionUtils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.exframework.support.jdbc.annotation.AutoCurrentTime;

/**
 *
 * @author rwe
 * @since 2021年5月21日
 *
 * Copyright @ 2021 
 * 
 */
public class CurrentTimeMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		setDate(metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		setDate(metaObject);
	}

	private void setDate(MetaObject metaObject) {
		Object o = metaObject.getOriginalObject();
		ReflectionUtils.doWithFields(o.getClass(), field -> {
			setFieldValByName(field.getName(),new Date(),metaObject);
		}, field -> field.isAnnotationPresent(AutoCurrentTime.class));
	}
	
}
