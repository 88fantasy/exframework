package org.exframework.support.common.annotation;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DictionaryScannerRegistrar.class)
/**
 * 绑定字典
 * @author rwe
 * @since Jan 2, 2021
 *
 * Copyright @ 2021 
 * 
 */
public @interface DictionaryScan {

	/**
	 * 别名 for basePackages
	 * @return
	 */
	String[] value() default {};

	/**
	 * 扫描包体
	 * @return
	 */
	String[] basePackages() default {};

}
