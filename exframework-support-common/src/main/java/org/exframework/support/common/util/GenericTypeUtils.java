package org.exframework.support.common.util;

import cn.hutool.core.util.TypeUtil;
import org.springframework.core.GenericTypeResolver;

/**
 * 泛型工具类
 *
 * @author rwe
 * @date 2022/5/5 16:35
 **/
public class GenericTypeUtils extends TypeUtil {

    public static Class<?> getSuperClassGenericType(final Class<?> clazz, final Class<?> genericIfc, final int index) {
        Class<?>[] typeArguments = GenericTypeResolver.resolveTypeArguments(clazz, genericIfc);
        return null == typeArguments ? null : typeArguments[index];
    }
}
