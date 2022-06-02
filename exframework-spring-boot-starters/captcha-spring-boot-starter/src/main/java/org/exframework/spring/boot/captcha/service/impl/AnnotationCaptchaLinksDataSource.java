package org.exframework.spring.boot.captcha.service.impl;

import org.exframework.spring.boot.captcha.annotation.CaptchaRequired;
import org.exframework.spring.boot.captcha.service.CaptchaLinksDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 注解验证码链接数据源
 *
 * @author rwe
 * @date 2022/5/15 16:12
 **/
public class AnnotationCaptchaLinksDataSource implements CaptchaLinksDataSource {

    private final static String DATA_SOURCE_TYPE = "annotation";

    private static ApplicationContext applicationContext;

    @Override
    public String dataSourceType() {
        return DATA_SOURCE_TYPE;
    }

    @Override
    public Collection<String> uris() {
        final List<String> uris = new ArrayList<>();
        Map<String, Object> classes = applicationContext.getBeansWithAnnotation(Controller.class);
        for (Map.Entry<String,Object> entry : classes.entrySet()) {
            String beanName = entry.getKey();
            Class<?> clazz = entry.getValue().getClass();
            List<String> row = new ArrayList<>();
            ReflectionUtils.doWithMethods(clazz, method -> {
                CaptchaRequired captchaRequired = method.getAnnotation(CaptchaRequired.class);
                String[] values = captchaRequired.value();
                if (values == null || values.length == 0) {
                    row.addAll(valueFromAnnotation(method, RequestMapping.class));
                    row.addAll(valueFromAnnotation(method, PostMapping.class));
                    row.addAll(valueFromAnnotation(method, GetMapping.class));
                    row.addAll(valueFromAnnotation(method, DeleteMapping.class));
                    row.addAll(valueFromAnnotation(method, PutMapping.class));
                    row.addAll(valueFromAnnotation(method, PatchMapping.class));
                }else {
                    row.addAll(Arrays.asList(values));
                }
            }, method -> method.isAnnotationPresent(CaptchaRequired.class));
            if(row.size() > 0) {
                RequestMapping root = applicationContext.findAnnotationOnBean(beanName, RequestMapping.class);
                if (root != null) {
                    String[] values = root.value();
                    if (values.length > 0) {
                        uris.addAll(row.stream().map(s -> values[0].concat(s)).collect(Collectors.toList()));
                    }
                } else {
                    uris.addAll(row);
                }
            }
        }
        return uris;

    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        AnnotationCaptchaLinksDataSource.applicationContext = applicationContext;
    }

    private Collection<String> valueFromAnnotation(Method method, Class<? extends Annotation> annotationClass) {
        if (method.isAnnotationPresent(annotationClass)) {
            Annotation mapping = method.getAnnotation(annotationClass);
            try {
                Method m = mapping.getClass().getMethod("value");
                return Arrays.asList((String[]) m.invoke(mapping));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else {
            return Collections.emptyList();
        }
    }
}
