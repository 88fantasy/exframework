package org.exframework.support.rest.annotation;

import org.exframework.support.common.entity.CheckerResult;
import org.exframework.support.rest.exception.ApiException;
import org.exframework.support.rest.validator.FieldCheckerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.Function;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({PARAMETER, FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = {FieldCheckerValidator.class})
/**
 * @author rwe
 * @version 创建时间：2021年5月21日 下午4:42:56
 *  请求字段检查器
 */

public @interface RequestFieldChecker {

    /**
     * 断言判断实现类
     *
     * @return
     */
    Class<? extends Function<Object, CheckerResult>> value();

    /**
     * 抛出错误类型
     * (用于全局错误拦截)
     *
     * @return
     */
    Class<? extends RuntimeException> exception() default ApiException.class;

    String message() default "校验不通过";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
