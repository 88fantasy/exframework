package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.exframework.spring.boot.autoconfigure.chinaunicom.rest.json.serialize.EnumSerializer;

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
@JsonSerialize(using = EnumSerializer.class)
public @interface EnumValue {
}
