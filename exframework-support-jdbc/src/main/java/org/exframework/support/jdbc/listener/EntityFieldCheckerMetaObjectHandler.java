package org.exframework.support.jdbc.listener;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.exframework.support.common.util.SpringContextUtils;
import org.exframework.support.common.util.StrUtils;
import org.exframework.support.jdbc.annotation.EntityFieldChecker;
import org.exframework.support.rest.exception.ServerException;
import org.springframework.util.ReflectionUtils;

import java.util.function.Function;

/**
 * @author rwe
 * @version 创建时间：2021年5月21日 下午4:49:01
 * 字段检查器
 */

public class EntityFieldCheckerMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        fill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fill(metaObject);
    }

    private void fill(MetaObject metaObject) {
        Object o = metaObject.getOriginalObject();
        if (o != null) {
            Class<?> clazz = o.getClass();
            ReflectionUtils.doWithFields(clazz, field -> {
                        EntityFieldChecker checker = field.getAnnotation(EntityFieldChecker.class);
                        Class<? extends Function<Object, String>> f = checker.value();
                        Class<? extends RuntimeException> exception = checker.exception();
                        Function<Object, String> function = SpringContextUtils.getBeanByClass(f);
                        Object value = getFieldValByName(field.getName(), metaObject);
                        String message = function.apply(value);
                        if (StrUtils.hasLength(message)) {
                            RuntimeException runtimeException;
                            try {
                                runtimeException = exception.getDeclaredConstructor(String.class).newInstance(message);
                            } catch (Exception e) {
                                throw new ServerException(message);
                            }
                            throw runtimeException;
                        }
                    }
                    , field -> field.isAnnotationPresent(EntityFieldChecker.class));
        }
    }
}
