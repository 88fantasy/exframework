package org.exframework.portal.metadata.entity;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Scope("prototype")
/**
 * 字典
 * @author rwe
 * @since Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
public @interface EntityClass {

}
