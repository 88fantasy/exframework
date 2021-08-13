package org.exframework.support.rest.validator;

import org.exframework.support.common.util.SpringContextUtils;
import org.exframework.support.common.util.StrUtils;
import org.exframework.support.rest.annotation.RequestFieldChecker;
import org.exframework.support.rest.exception.ServerException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.function.Function;

/**
 * 请求字段检查验证器
 *
 * @author rwe
 * @date 2021/7/16 15:05
 **/
public class RequestFieldCheckerValidator implements ConstraintValidator<RequestFieldChecker, Object> {

    private Class<? extends Function<Object, String>> function;

    private Class<? extends RuntimeException> exception;

    @Override
    public void initialize(RequestFieldChecker constraintAnnotation) {
        function = constraintAnnotation.value();
        exception = constraintAnnotation.exception();
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
        }
        return true;
    }
}
