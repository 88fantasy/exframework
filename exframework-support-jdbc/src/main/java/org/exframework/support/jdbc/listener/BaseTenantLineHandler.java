package org.exframework.support.jdbc.listener;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import org.exframework.support.common.util.SpringContextUtils;
import org.exframework.support.common.util.StrUtils;
import org.exframework.support.jdbc.annotation.TenantIgnore;
import org.exframework.support.jdbc.entity.Tenantable;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

/**
 * 基础租户处理器
 *
 * @author rwe
 * @date 2022/5/17 18:45
 **/
public interface BaseTenantLineHandler extends TenantLineHandler {

    default boolean ignoreTable(String tableName) {
        // 通过反射检查 entity 中是否包含 tenant_id 字段
        String column = getTenantIdColumn();
        ApplicationContext ac = SpringContextUtils.getApplicationContext();
        Map<String, Object> tables = ac.getBeansWithAnnotation(TableName.class);
        Optional<Map.Entry<String, Object>> optional = tables.entrySet().stream().filter(entry -> {
            Class<?> clazz = entry.getValue().getClass();
            String key = entry.getKey();
            TableName table = clazz.getAnnotation(TableName.class);
            return (StrUtils.hasText(table.value()) && tableName.equalsIgnoreCase(table.value()))
                    || tableName.equalsIgnoreCase(StrUtils.humpToUnderline(key));
        }).findFirst();
        if (optional.isPresent()) {
            Class<?> clazz = optional.get().getValue().getClass();
            TenantIgnore ignore = ac.findAnnotationOnBean(optional.get().getKey(), TenantIgnore.class);
            if (ignore == null) {
                if (Tenantable.class.isAssignableFrom(clazz)) {
                    return false;
                }
                Map<String, Field> fields = ReflectionKit.getFieldMap(clazz);
                if (fields.containsKey(column) || fields.containsKey(StrUtils.toCamelCase(column))) {
                    return false;
                }
            }
        }
        return true;
    }
}
