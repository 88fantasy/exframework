package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.json.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.exframework.support.common.enums.DictionaryEnum;

import java.io.IOException;

/**
 * 枚举串行转换
 *
 * @author rwe
 * @date 2021/7/22 15:12
 **/
public class EnumSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value != null) {
            Class clazz = value.getClass();
            if(clazz.isEnum() && DictionaryEnum.class.isAssignableFrom(clazz)) {
                DictionaryEnum dictionaryEnum = (DictionaryEnum) value;
                gen.writeObject(value);
                gen.writeObjectField(gen.getOutputContext().getCurrentName() + "Name", dictionaryEnum.getLabel());
            }
        }
    }
}
