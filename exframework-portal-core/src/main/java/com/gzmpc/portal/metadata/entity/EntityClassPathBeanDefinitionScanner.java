package com.gzmpc.portal.metadata.entity;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 *
 * Author: rwe
 * Date: Jan 9, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class EntityClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

	public EntityClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		super(registry,false);
    	this.addIncludeFilter(new AnnotationTypeFilter(EntityClass.class));
	}

	@Override
	protected void postProcessBeanDefinition(AbstractBeanDefinition beanDefinition, String beanName) {
		super.postProcessBeanDefinition(beanDefinition, beanName);
	}

}
