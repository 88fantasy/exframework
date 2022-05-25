package org.exframework.support.jdbc.annotation;

import org.exframework.support.rest.exception.ApiException;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.Function;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE })
/**
* @author rwe
* @version 创建时间：2021年5月21日 下午4:42:56
*  记录检查器
*/

public @interface EntityFieldChecker {

    /**
     * 断言判断实现类
     * @return
     */
    Class<? extends Function<Object, String>> value();

}
