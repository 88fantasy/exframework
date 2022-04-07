package org.exframework.support.rest.annotation;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
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
 * 地址类型
 *
 * @author rwe
 * @date 2022/4/7 10:24
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = AddressValue.AddressValueValidator.class)
@JacksonAnnotationsInside
@JsonSerialize(using = AddressValue.AddressDesensitizationSerializer.class)
public @interface AddressValue {

    String message() default "";

    boolean notNull() default false;

    int sensitiveSize() default AddressDesensitizationSerializer.DEFAULT_SENSITIVE_SIZE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class AddressValueValidator implements ConstraintValidator<AddressValue, String> {

        private String message;

        private boolean notNull;

        @Override
        public void initialize(AddressValue constraintAnnotation) {
            message = constraintAnnotation.message();
            notNull = constraintAnnotation.notNull();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (value != null) {
                if (!Validator.isCitizenId(value)) {
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

    class AddressDesensitizationSerializer extends JsonSerializer<String> implements ContextualSerializer {

        static final int DEFAULT_SENSITIVE_SIZE = 6;

        private int sensitiveSize;

        public AddressDesensitizationSerializer setSensitiveSize(int sensitiveSize) {
            this.sensitiveSize = sensitiveSize;
            return this;
        }

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (StringUtils.hasText(value) && value.length() > sensitiveSize) {
                value = DesensitizedUtil.address(value, sensitiveSize);
            }
            gen.writeObject(value);
        }

        @Override
        public JsonSerializer<String> createContextual(SerializerProvider prov, BeanProperty property) {
            AddressValue value = property.getAnnotation(AddressValue.class);
            if (value == null) {
                value = property.getContextAnnotation(AddressValue.class);
            }
            if (value != null) { // 如果能得到注解，就将注解的 value 传入 ImageURLSerialize
                return new AddressDesensitizationSerializer().setSensitiveSize(value.sensitiveSize());
            } else {
                return new AddressDesensitizationSerializer();
            }
        }
    }
}
