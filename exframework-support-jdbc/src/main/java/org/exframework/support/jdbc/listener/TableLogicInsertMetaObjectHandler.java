package org.exframework.support.jdbc.listener;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.ReflectionUtils;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 逻辑删除增强, 自动插入值
 * @author rwe
 * @since 2021年5月21日
 *
 * Copyright @ 2021 
 * 
 */
public class TableLogicInsertMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		Object o = metaObject.getOriginalObject();
		ReflectionUtils.doWithFields(o.getClass(), field -> {
			Class clazz = field.getType();
			if(Integer.class.isAssignableFrom(clazz)) {
				setFieldValByName(field.getName(), 0,metaObject);
			}
			else if(Long.class.isAssignableFrom(clazz)) {
				setFieldValByName(field.getName(), 0L,metaObject);
			}
			else if(Boolean.class.isAssignableFrom(clazz)) {
				setFieldValByName(field.getName(), false,metaObject);
			}
			else if(String.class.isAssignableFrom(clazz)) {
				setFieldValByName(field.getName(), "0",metaObject);
			}
			else if(LocalDateTime.class.isAssignableFrom(clazz)) {
				setFieldValByName(field.getName(), LocalDateTime.now(),metaObject);
			}
			else if(Date.class.isAssignableFrom(clazz)) {
				setFieldValByName(field.getName(), new Date(),metaObject);
			}
		}, field -> field.isAnnotationPresent(TableLogic.class));
	}

	@Override
	public void updateFill(MetaObject metaObject) {

	}
	
}
