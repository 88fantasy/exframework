package org.exframework.support.rest.annotation;

import cn.hutool.core.util.ArrayUtil;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import org.exframework.support.common.annotation.Dictionary;
import org.exframework.support.common.util.StrUtils;
import org.exframework.support.rest.enums.ResultCode;
import org.exframework.support.rest.exception.ApiException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.io.IOException;
import java.lang.annotation.*;
import java.text.MessageFormat;
import java.util.*;
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
@Constraint(validatedBy = EnumValueList.EnumValueListValidator.class)
@JacksonAnnotationsInside
@JsonDeserialize(using = EnumValueList.EnumValueListDeserializer.class)
@JsonSerialize(using = EnumValueList.EnumValueListSerializer.class)
public @interface EnumValueList {

    String message() default "";

    boolean notNull() default false;

    String[] anyOf() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class EnumValueListValidator implements ConstraintValidator<EnumValueList, List<?>> {

        private String message;

        private boolean notNull;

        private String[] anyOf;

        @Override
        public void initialize(EnumValueList constraintAnnotation) {
            message = constraintAnnotation.message();
            anyOf = constraintAnnotation.anyOf();
            notNull = constraintAnnotation.notNull();
        }

        @Override
        public boolean isValid(List<?> value, ConstraintValidatorContext context) {
            if (value != null && value.size() > 0) {
                context.disableDefaultConstraintViolation();
                Object first = value.get(0);
                if(!first.getClass().isEnum()) {
                    context.buildConstraintViolationWithTemplate("不是枚举类").addConstraintViolation();
                    return false;
                }
                Enum<?> eValue = (Enum<?>) value.get(0);
                if (ArrayUtil.isEmpty(anyOf)) {
                    anyOf = Arrays.stream(eValue.getClass().getEnumConstants()).map(e -> e.name()).collect(Collectors.toList()).toArray(new String[0]);
                }
                if (Stream.of(anyOf).noneMatch(any -> eValue.name().equals(any))) {
                    String anyMessage = String.join("或", anyOf);
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
            }else if(notNull){
                context.buildConstraintViolationWithTemplate("不能为空").addConstraintViolation();
            }
            return true;
        }
    }

    class EnumValueListDeserializer extends JsonDeserializer<List<?>> implements ContextualDeserializer {

        private Class<?> enumType;

        public EnumValueListDeserializer setEnumType(Class<?> enumType) {
            this.enumType = enumType;
            return this;
        }

        @Override
        public List<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (Objects.isNull(enumType) || !enumType.isEnum()) {
                return null;
            }
            String text = p.getText();
            if (!StrUtils.hasText(text)) {
                return null;
            }
            Enum<?>[] enumConstants = (Enum<?>[]) enumType.getEnumConstants();
            List<Enum<?>> list = new ArrayList<>();
            String[] strings = new ObjectMapper().readValue(text, String[].class);
            for(String s : strings) {
                Optional<Enum<?>> enumItem = Stream.of(enumConstants).filter(e -> s.equals(e.name())).findFirst();
                if (!enumItem.isPresent()) {
                    String names = String.join("或", Stream.of(enumConstants).map(e -> e.name()).collect(Collectors.toList()));
                    throw new ApiException(ResultCode.NOT_ACCEPTABLE.getCode(), MessageFormat.format("枚举项[{0}]不满足{1}", p.getCurrentName(), names), text);
                }
                Enum<?> enumValue = enumItem.get();
                list.add(enumValue);
            }
            return list;
        }

        @Override
        public JsonDeserializer<List<?>> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
            Class<?> rawCls = ctxt.getContextualType().getRawClass();
            EnumValueListDeserializer converter = new EnumValueListDeserializer();
            converter.setEnumType(rawCls);
            return converter;
        }
    }

    class EnumValueListSerializer extends JsonSerializer<List<?>> {

        @Override
        public void serialize(List<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if(!ObjectUtils.isEmpty(value)) {
                gen.writeObject(value);
            }
            else {
                gen.writeObject(Collections.emptyList());
            }
        }
    }
}
