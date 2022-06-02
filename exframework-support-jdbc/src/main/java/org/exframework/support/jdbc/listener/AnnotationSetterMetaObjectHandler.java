package org.exframework.support.jdbc.listener;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;


/**
 * 自动注解填充处理器
 *
 * @param <A> 适配注解
 * @author rwe
 */
public interface AnnotationSetterMetaObjectHandler<A extends Annotation> extends MetaObjectHandler {

    /**
     * 插入
     *
     * @param metaObject 元对象
     */
    default void insertFill(MetaObject metaObject) {
        fill(metaObject, Arrays.asList(FieldFill.INSERT, FieldFill.INSERT_UPDATE));
    }

    /**
     * 更新
     *
     * @param metaObject 元对象
     */
    default void updateFill(MetaObject metaObject) {
        fill(metaObject, Arrays.asList(FieldFill.UPDATE, FieldFill.INSERT_UPDATE));
    }

    /**
     * 填充
     *
     * @param metaObject 元数据
     * @param fieldFills 填充策略
     */
    default void fill(MetaObject metaObject, Collection<FieldFill> fieldFills) {
        Class<A> clazz = (Class<A>) ReflectionKit.getSuperClassGenericType(this.getClass(), AnnotationSetterMetaObjectHandler.class, 0);
        Object o = metaObject.getOriginalObject();
        ReflectionUtils.doWithFields(o.getClass(), field -> {
            TableField tableField = field.getAnnotation(TableField.class);
            if (fieldFills.contains(tableField.fill())) {
                Object value = getObject(o);
                if (passIfNull(o)) {
                    setFieldValByName(field.getName(), value, metaObject);
                }
            }
        }, field -> field.isAnnotationPresent(TableField.class) && field.isAnnotationPresent(clazz));
    }

    /**
     * 获取字段值
     *
     * @param row 行记录
     * @return 值
     */
    Object getObject(Object row);

    /**
     * 遇到空值时是否跳过
     *
     * @param row 行记录
     * @return
     */
    default boolean passIfNull(Object row) {
        return true;
    }
}
