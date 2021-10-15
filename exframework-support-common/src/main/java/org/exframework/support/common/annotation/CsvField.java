package org.exframework.support.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.Function;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * CSV字段注解
 *
 * @author rwe
 * @version 创建时间：2021年5月21日 下午4:42:56
 */

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE})
public @interface CsvField {

    /**
     * the name for header
     *
     * @return name
     */
    String value();

    /**
     * thr order
     *
     * @return order
     */
    int order();

    Class<? extends Function<Object, String>> translator() default ToStringFunction.class;


    class ToStringFunction implements Function<Object, String> {

        @Override
        public String apply(Object o) {
            if (o == null) {
                return "";
            }
            return o.toString();
        }
    }
}
