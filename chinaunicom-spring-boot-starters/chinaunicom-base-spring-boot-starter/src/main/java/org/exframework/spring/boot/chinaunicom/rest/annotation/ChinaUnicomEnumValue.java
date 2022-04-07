package org.exframework.spring.boot.chinaunicom.rest.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.exframework.support.common.enums.DictionaryEnum;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 响应枚举转换
 *
 * @author rwe
 * @date 2021/7/22 15:07
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@JacksonAnnotationsInside
@JsonSerialize(using = ChinaUnicomEnumValue.ChinaUnicomEnumSerializer.class)
public @interface ChinaUnicomEnumValue {


    class ChinaUnicomEnumSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                Class<?> clazz = value.getClass();
                if (clazz.isEnum() && DictionaryEnum.class.isAssignableFrom(clazz)) {
                    DictionaryEnum dictionaryEnum = (DictionaryEnum) value;
                    gen.writeObject(value);
                    gen.writeObjectField(gen.getOutputContext().getCurrentName() + "Name", dictionaryEnum.getLabel());
                }
            }
        }
    }
}
