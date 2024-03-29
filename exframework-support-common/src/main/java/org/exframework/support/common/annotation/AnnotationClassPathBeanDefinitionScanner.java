package org.exframework.support.common.annotation;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 *
 * @author rwe
 * @since Jan 9, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class AnnotationClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

	public <T extends Annotation> AnnotationClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, Class<T> clazz) {
		super(registry,false);
    	this.addIncludeFilter(new AnnotationTypeFilter(clazz));
	}

	@Override
	protected void postProcessBeanDefinition(AbstractBeanDefinition beanDefinition, String beanName) {
		super.postProcessBeanDefinition(beanDefinition, beanName);
	}

}
