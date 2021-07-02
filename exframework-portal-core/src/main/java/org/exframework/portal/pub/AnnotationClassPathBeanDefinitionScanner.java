package org.exframework.portal.pub;

import java.lang.annotation.Annotation;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

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
