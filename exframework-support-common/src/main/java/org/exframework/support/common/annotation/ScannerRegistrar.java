package org.exframework.support.common.annotation;

import org.exframework.support.common.util.GenericTypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
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
 * ImportBeanDefinitionRegistrar 增强
 *
 * @author rwe
 * @date 2022/5/5 16:07
 **/
public class ScannerRegistrar<S extends Annotation, A extends Annotation> implements ImportBeanDefinitionRegistrar {

    private static final Logger log = LoggerFactory.getLogger(ScannerRegistrar.class.getName());

    protected Class<S> scannerClass = getCurrentScanner();

    protected Class<A> annoClass = getCurrentAnnotation();

    protected Class<S> getCurrentScanner() {
        return (Class<S>) GenericTypeUtils.getSuperClassGenericType(this.getClass(), ScannerRegistrar.class, 0);
    }

    protected Class<A> getCurrentAnnotation() {
        return (Class<A>) GenericTypeUtils.getSuperClassGenericType(this.getClass(), ScannerRegistrar.class, 1);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(scannerClass.getName()));
        List<String> basePackages = new ArrayList<>();
        basePackages.addAll(Arrays.stream(mapperScanAttrs.getStringArray("value")).filter(StringUtils::hasText)
                .collect(Collectors.toList()));

        basePackages.addAll(Arrays.stream(mapperScanAttrs.getStringArray("basePackages")).filter(StringUtils::hasText)
                .collect(Collectors.toList()));
        if (basePackages.size() == 0) {
            basePackages.add(((StandardAnnotationMetadata) importingClassMetadata).getIntrospectedClass().getPackage().getName());
            //java 11
//            List<String> packages = Stream.of(((StandardAnnotationMetadata) importingClassMetadata).getIntrospectedClass().getClassLoader().getDefinedPackages()).map(Package::getName).collect(Collectors.toList());
//            basePackages.addAll(packages);
            //java 8
            Class<?> application = ((StandardAnnotationMetadata) importingClassMetadata).getIntrospectedClass();
            SpringBootApplication springBootApplication = application.getAnnotation(SpringBootApplication.class);
            if (springBootApplication != null) {
                basePackages.addAll(Arrays.asList(springBootApplication.scanBasePackages()));
            } else {
                ComponentScan componentScan = application.getAnnotation(ComponentScan.class);
                if (componentScan != null) {
                    basePackages.addAll(Arrays.asList(componentScan.basePackages()));
                }
            }
        }
        if (basePackages.size() > 0) {
            AnnotationClassPathBeanDefinitionScanner scanner = new AnnotationClassPathBeanDefinitionScanner(registry,
                    annoClass);
            int count = scanner.scan(StringUtils.toStringArray(basePackages));
            String basePackagesLog = "";
            for (String pack : basePackages) {
                basePackagesLog += "\n " + pack;
            }
            log.info("扫描[" + annoClass.getName() + "]注册数量" + count + ":" + basePackagesLog);
        }
    }


}
