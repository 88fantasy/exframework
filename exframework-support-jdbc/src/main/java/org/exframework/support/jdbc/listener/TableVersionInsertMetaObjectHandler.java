package org.exframework.support.jdbc.listener;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.ReflectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 逻辑版本增强, 自动插入值
 * @author rwe
 * @since 2021年5月21日
 * <p>
 * Copyright @ 2021
 */
public class TableVersionInsertMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object o = metaObject.getOriginalObject();
        ReflectionUtils.doWithFields(o.getClass(), field -> {
            Class clazz = field.getType();
            if (Integer.class.isAssignableFrom(clazz) || int.class.isAssignableFrom(clazz)) {
                setFieldValByName(field.getName(), 0, metaObject);
            } else if (Long.class.isAssignableFrom(clazz) || long.class.isAssignableFrom(clazz)) {
                setFieldValByName(field.getName(), 0L, metaObject);
            } else if (LocalDateTime.class.isAssignableFrom(clazz)) {
                setFieldValByName(field.getName(), LocalDateTime.now(), metaObject);
            } else if (Date.class.isAssignableFrom(clazz)) {
                setFieldValByName(field.getName(), new Date(), metaObject);
            } else if (Timestamp.class.isAssignableFrom(clazz)) {
                setFieldValByName(field.getName(), new Timestamp(System.currentTimeMillis()), metaObject);
            }
        }, field -> field.isAnnotationPresent(Version.class));
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }

}
