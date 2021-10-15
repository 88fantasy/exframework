package org.exframework.portal.auth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE })
/**
* @author rwe
* @version 创建时间：2021年5月21日 下午4:42:56
* 设置当前用户名称
*/

public @interface AutoCurrentAccountName {

}
