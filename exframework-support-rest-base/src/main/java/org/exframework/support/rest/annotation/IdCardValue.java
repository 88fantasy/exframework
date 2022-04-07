package org.exframework.support.rest.annotation;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
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
 * 身份证类型
 *
 * @author rwe
 * @date 2022/4/7 10:24
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = IdCardValue.IdCardValueValidator.class)
@JacksonAnnotationsInside
@JsonSerialize(using = IdCardValue.IdCardDesensitizationSerializer.class)
public @interface IdCardValue {

    /**
     * 提示信息
     * @return
     */
    String message() default "";

    /**
     * 非空
     * @return
     */
    boolean notNull() default false;

    /**
     * 脱敏前显位数
     * @return
     */
    int front() default IdCardDesensitizationSerializer.DEFAULT_FRONT_INDEX;

    /**
     * 脱敏后显位数
     * @return
     */
    int end() default IdCardDesensitizationSerializer.DEFAULT_END_INDEX;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class IdCardValueValidator implements ConstraintValidator<IdCardValue, String> {

        private String message;

        private boolean notNull;

        @Override
        public void initialize(IdCardValue constraintAnnotation) {
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

    class IdCardDesensitizationSerializer extends JsonSerializer<String> implements ContextualSerializer {

        static final int DEFAULT_FRONT_INDEX = 2;

        static final int DEFAULT_END_INDEX = 2;

        private int front;

        private int end;

        public IdCardDesensitizationSerializer setFront(int front) {
            this.front = front;
            return this;
        }

        public IdCardDesensitizationSerializer setEnd(int end) {
            this.end = end;
            return this;
        }

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (StringUtils.hasText(value) && Validator.isCitizenId(value)) {
                value = DesensitizedUtil.idCardNum(value, front, end);
            }
            gen.writeObject(value);
        }

        @Override
        public JsonSerializer<String> createContextual(SerializerProvider prov, BeanProperty property) {
            IdCardValue value = property.getAnnotation(IdCardValue.class);
            if (value == null) {
                value = property.getContextAnnotation(IdCardValue.class);
            }
            if (value != null) { // 如果能得到注解，就将注解的 value 传入 ImageURLSerialize
                return new IdCardDesensitizationSerializer().setFront(value.front()).setEnd(value.end());
            } else {
                return new IdCardDesensitizationSerializer();
            }
        }
    }
}
