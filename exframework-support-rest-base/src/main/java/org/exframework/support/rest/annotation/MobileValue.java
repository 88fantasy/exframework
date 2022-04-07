package org.exframework.support.rest.annotation;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.exframework.support.common.lang.Validator;
import org.exframework.support.common.util.StrUtils;
import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.io.IOException;
import java.lang.annotation.*;

/**
 * 手机类型
 *
 * @author rwe
 * @date 2022/4/7 10:24
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = MobileValue.MobileValueValidator.class)
@JacksonAnnotationsInside
@JsonSerialize(using = MobileValue.MobileDesensitizationSerializer.class)
public @interface MobileValue {

    String message() default "";

    boolean notNull() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class MobileValueValidator implements ConstraintValidator<MobileValue, String> {

        private String message;

        private boolean notNull;

        @Override
        public void initialize(MobileValue constraintAnnotation) {
            message = constraintAnnotation.message();
            notNull = constraintAnnotation.notNull();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value != null) {
                if (!Validator.isMobile(value)) {
                    if (StrUtils.hasText(message)) {
                        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
                    }
                    return false;
                }
            } else if (notNull) {
                context.buildConstraintViolationWithTemplate("不能为空").addConstraintViolation();
            }
            return true;
        }
    }

    class MobileDesensitizationSerializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (StringUtils.hasText(value) && Validator.isMobile(value)) {
                value = DesensitizedUtil.mobilePhone(value);
            }
            gen.writeObject(value);
        }
    }
}
