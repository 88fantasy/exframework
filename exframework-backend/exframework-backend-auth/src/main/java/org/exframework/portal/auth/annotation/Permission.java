package org.exframework.portal.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ METHOD, ANNOTATION_TYPE })
/**
* @author rwe
* @version 创建时间：2021年5月21日 下午4:42:56
* 需要登录后才能使用
*/

public @interface Permission {

    /**
     * 权限值
     * @return key of permission
     */
    String value() default "";
}
