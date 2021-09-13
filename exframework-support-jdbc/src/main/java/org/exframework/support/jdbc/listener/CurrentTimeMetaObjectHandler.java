package org.exframework.support.jdbc.listener;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.exframework.support.jdbc.annotation.AutoCurrentTime;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * @author rwe
 * @since 2021年5月21日
 * <p>
 * Copyright @ 2021
 */
public class CurrentTimeMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        setDate(metaObject, Arrays.asList(FieldFill.INSERT));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setDate(metaObject, Arrays.asList(FieldFill.UPDATE, FieldFill.INSERT_UPDATE));
    }

    private void setDate(MetaObject metaObject, Collection<FieldFill> fieldFills) {
        Object o = metaObject.getOriginalObject();
        ReflectionUtils.doWithFields(o.getClass(), field -> {
            TableField tableField = field.getAnnotation(TableField.class);
            if (fieldFills.contains(tableField.fill())) {
                setFieldValByName(field.getName(), new Date(), metaObject);
            }
        }, field -> field.isAnnotationPresent(TableField.class) && field.isAnnotationPresent(AutoCurrentTime.class));
    }

}
