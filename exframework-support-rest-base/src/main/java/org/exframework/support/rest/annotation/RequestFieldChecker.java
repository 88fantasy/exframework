package org.exframework.support.rest.annotation;

import org.exframework.support.common.util.SpringContextUtils;
import org.exframework.support.common.util.StrUtils;
import org.exframework.support.rest.exception.ServerException;

import javax.validation.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.Function;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({PARAMETER, FIELD, ANNOTATION_TYPE})
@Constraint(validatedBy = {RequestFieldChecker.RequestFieldCheckerValidator.class})
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
    Class<? extends Function<Object, String>> value();

    /**
     * 抛出错误类型
     * (用于全局错误拦截)
     *
     * @return
     */
    Class<? extends RuntimeException> exception() default ConstraintDeclarationException.class;

    String message() default "校验不通过";

    /**
     * 当字段值为空时抛出错误信息
     * 不设置时忽略 null
     *
     * @return
     * @since 0.8.3
     */
    String notNullMessage() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class RequestFieldCheckerValidator implements ConstraintValidator<RequestFieldChecker, Object> {

        private Class<? extends Function<Object, String>> function;

        private Class<? extends RuntimeException> exception;

        private String notNullMessage;

        @Override
        public void initialize(RequestFieldChecker constraintAnnotation) {
            function = constraintAnnotation.value();
            exception = constraintAnnotation.exception();
            notNullMessage = constraintAnnotation.notNullMessage();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (value != null) {
                Function<Object, String> f = SpringContextUtils.getBeanByClass(function);
                String message = f.apply(value);
                if (StrUtils.hasLength(message)) {
                    RuntimeException runtimeException;
                    try {
                        runtimeException = exception.getDeclaredConstructor(String.class).newInstance(message);
                    } catch (Exception e) {
                        throw new ServerException(message);
                    }
                    throw runtimeException;
                }
            } else if(StrUtils.hasLength(notNullMessage)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(notNullMessage).addConstraintViolation();
            }
            return true;
        }
    }
}
