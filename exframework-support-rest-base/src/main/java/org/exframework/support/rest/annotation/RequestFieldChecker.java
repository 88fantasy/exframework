package org.exframework.support.rest.annotation;

import org.exframework.support.common.util.SpringContextUtils;
import org.exframework.support.common.util.StrUtils;
import org.springframework.beans.BeansException;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
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
    Class<? extends Function<Object, RuntimeException>> value();

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

        private Class<? extends Function<Object, RuntimeException>> function;

        private String notNullMessage;

        @Override
        public void initialize(RequestFieldChecker constraintAnnotation) {
            function = constraintAnnotation.value();
            notNullMessage = constraintAnnotation.notNullMessage();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            String message = null;
            if (value != null) {
                Function<Object, RuntimeException> f = null;
                try {
                    f = SpringContextUtils.getBeanByClass(function);
                } catch (BeansException e) {
                    try {
                        f = function.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e2) {
                        message = e2.getMessage();
                    }
                }
                RuntimeException exception = f.apply(value);
                if (exception != null) {
                    throw exception;
                }
            } else if (StrUtils.hasLength(notNullMessage)) {
                message = notNullMessage;
            }

            if (StrUtils.hasText(message)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
                return false;
            }
            return true;
        }
    }
}
