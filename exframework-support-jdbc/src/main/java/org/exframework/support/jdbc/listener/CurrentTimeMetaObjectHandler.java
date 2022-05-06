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
import java.util.function.Function;

/**
 * @author rwe
 * @since 2021年5月21日
 * <p>
 * Copyright @ 2021
 */
public class CurrentTimeMetaObjectHandler implements AnnotationSetterMetaObjectHandler<AutoCurrentTime> {


    @Override
    public Object getObject(Object row) {
        return new Date();
    }
}
