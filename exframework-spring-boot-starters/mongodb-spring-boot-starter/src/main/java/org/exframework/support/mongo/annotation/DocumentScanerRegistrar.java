package org.exframework.support.mongo.annotation;

import org.exframework.support.common.annotation.AnnotationClassPathBeanDefinitionScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author rwe Date: Jan 9, 2021
 *
 * Copyright @ 2021
 * 
 */
public class DocumentScanerRegistrar implements ImportBeanDefinitionRegistrar {

	private static Logger log = LoggerFactory.getLogger(DocumentScanerRegistrar.class.getName());

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes mapperScanAttrs = AnnotationAttributes
				.fromMap(importingClassMetadata.getAnnotationAttributes(DocumentEntityScan.class.getName()));
		List<String> basePackages = new ArrayList<>();
		basePackages.addAll(Arrays.stream(mapperScanAttrs.getStringArray("value")).filter(StringUtils::hasText)
				.collect(Collectors.toList()));

		basePackages.addAll(Arrays.stream(mapperScanAttrs.getStringArray("basePackages")).filter(StringUtils::hasText)
				.collect(Collectors.toList()));

		if (basePackages.size() > 0) {
			AnnotationClassPathBeanDefinitionScanner scanner = new AnnotationClassPathBeanDefinitionScanner(registry,
					Document.class);
			int count = scanner.scan(StringUtils.toStringArray(basePackages));
			String basePackagesLog = "";
			for (String pack : basePackages) {
				basePackagesLog += "\n " + pack;
			}
			log.info("扫描Document数据库实体注册数量" + count + ":" + basePackagesLog);
		}
	}

}
