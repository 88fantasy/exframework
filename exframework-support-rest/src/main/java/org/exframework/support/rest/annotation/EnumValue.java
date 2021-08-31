package org.exframework.support.rest.annotation;

import cn.hutool.core.util.ArrayUtil;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import org.exframework.support.common.annotation.Dictionary;
import org.exframework.support.common.util.StrUtils;
import org.exframework.support.rest.enums.ResultCode;
import org.exframework.support.rest.exception.ApiException;
import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.io.IOException;
import java.lang.annotation.*;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 枚举类型
 *
 * @author rwe
 * @date 2021/8/20 10:15
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = EnumValue.EnumValueValidator.class)
@JacksonAnnotationsInside
@JsonDeserialize(using = EnumValue.EnumValueDeserializer.class)
//@JsonSerialize(using = EnumValue.EnumSerializer.class)
public @interface EnumValue {

    String message() default "";

    String[] anyOf() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class EnumValueValidator implements ConstraintValidator<EnumValue, Enum<?>> {

        private String message;

        private String[] anyOf;

        @Override
        public void initialize(EnumValue constraintAnnotation) {
            message = constraintAnnotation.message();
            anyOf = constraintAnnotation.anyOf();
        }

        @Override
        public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
            if (value != null) {
                if (ArrayUtil.isEmpty(anyOf)) {
                    anyOf = Arrays.stream(value.getClass().getEnumConstants()).map(e -> e.name()).collect(Collectors.toList()).toArray(new String[0]);
                }
                if (Stream.of(anyOf).noneMatch(any -> value.name().equals(any))) {
                    String anyMessage = String.join("或", anyOf);
                    context.disableDefaultConstraintViolation();
                    Class<?> clazz = value.getClass();
                    if (StrUtils.hasText(message)) {
                        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
                    } else if (clazz.isAnnotationPresent(Dictionary.class)) {
                        Dictionary dictionary = clazz.getAnnotation(Dictionary.class);
                        String dictCode = dictionary.value();
                        if (!StringUtils.hasText(dictCode)) {
                            String simpleName = clazz.getSimpleName();
                            dictCode = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
                        }
                        context.buildConstraintViolationWithTemplate(MessageFormat.format("字典项[{0}]不满足{1}", dictCode, anyMessage)).addConstraintViolation();
                    } else {
                        context.buildConstraintViolationWithTemplate(MessageFormat.format("字典项[{0}]不满足{1}", clazz.getName(), anyMessage)).addConstraintViolation();
                    }
                    return false;
                }
            }
            return true;
        }
    }

    class EnumValueDeserializer extends JsonDeserializer<Enum<?>> implements ContextualDeserializer {

        private Class<?> enumType;

        public EnumValueDeserializer setEnumType(Class<?> enumType) {
            this.enumType = enumType;
            return this;
        }

        @Override
        public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (Objects.isNull(enumType) || !enumType.isEnum()) {
                return null;
            }
            String text = p.getText();
            if (!StrUtils.hasText(text)) {
                return null;
            }
            Enum<?>[] enumConstants = (Enum<?>[]) enumType.getEnumConstants();
            Optional<Enum<?>> enumItem = Stream.of(enumConstants).filter(e -> text.equals(e.name())).findFirst();
            if (!enumItem.isPresent()) {
                String names = String.join("或", Stream.of(enumConstants).map(e -> e.name()).collect(Collectors.toList()));
                throw new ApiException(ResultCode.NOT_ACCEPTABLE.getCode(), MessageFormat.format("枚举项[{0}]不满足{1}", p.getCurrentName(), names), text);
            }
            return enumItem.get();
        }

        @Override
        public JsonDeserializer<Enum<?>> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
            Class<?> rawCls = ctxt.getContextualType().getRawClass();
            EnumValueDeserializer converter = new EnumValueDeserializer();
            converter.setEnumType(rawCls);
            return converter;
        }
    }
}
