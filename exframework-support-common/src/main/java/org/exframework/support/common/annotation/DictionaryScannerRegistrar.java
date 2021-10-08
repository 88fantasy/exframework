package org.exframework.support.common.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rwe
 * @since Jan 9, 2021
 * <p>
 * Copyright @ 2021
 */
public class DictionaryScannerRegistrar implements ImportBeanDefinitionRegistrar {

    private static Logger log = LoggerFactory.getLogger(DictionaryScannerRegistrar.class.getName());


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(DictionaryScan.class.getName()));
        List<String> basePackages = new ArrayList<>();
        basePackages.addAll(Arrays.stream(mapperScanAttrs.getStringArray("value")).filter(StringUtils::hasText).collect(Collectors.toList()));

        basePackages.addAll(Arrays.stream(mapperScanAttrs.getStringArray("basePackages")).filter(StringUtils::hasText)
                .collect(Collectors.toList()));

        if (basePackages.size() == 0) {
            basePackages.add(((StandardAnnotationMetadata) importingClassMetadata).getIntrospectedClass().getPackage().getName());
        }
        DictionaryAnnotationClassPathBeanDefinitionScanner scanner = new DictionaryAnnotationClassPathBeanDefinitionScanner(registry, Dictionary.class);
        scanner.scan(StringUtils.toStringArray(basePackages));
    }


    class DictionaryAnnotationClassPathBeanDefinitionScanner extends AnnotationClassPathBeanDefinitionScanner {

        public <T extends Annotation> DictionaryAnnotationClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, Class<T> clazz) {
            super(registry, clazz);
        }

        @Override
        protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
            try {
                Class clazz = Class.forName(beanDefinition.getBeanClassName());
                if (clazz.isEnum()) {
                    Dictionary.DictionaryCache.bind(beanDefinition.getBeanClassName(), clazz);
                }
            } catch (ClassNotFoundException e) {
                log.error("初始化字典错误:" + e.getMessage(), e);
            }
            return false;
        }
    }
}
