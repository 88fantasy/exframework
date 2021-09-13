package org.exframework.support.common.annotation;

import java.lang.annotation.*;

/**
 * 枚举类型
 *
 * @author rwe
 * @date 2021/8/20 10:15
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface ConditionGreaterEqual {


}
