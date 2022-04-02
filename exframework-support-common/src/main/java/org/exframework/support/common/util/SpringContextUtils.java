package org.exframework.support.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据bean的id来查找对象
     *
     * @param id
     * @return
     */
    public static Object getBeanById(String id) {
        return applicationContext.getBean(id);
    }

    /**
     * 根据bean的id来查找对象
     *
     * @param <T>
     * @param id
     * @param requiredType
     * @return
     */
    public static <T> T getBeanById(String id, Class<T> requiredType) {
        return applicationContext.getBean(id, requiredType);
    }

    /**
     * 根据bean的class来查找对象
     *
     * @param <T>
     * @param c
     * @return
     */
    public static <T> T getBeanByClass(Class<T> c) {
        return applicationContext.getBean(c);
    }

    /**
     * 根据bean的class来查找所有的对象(包括子类)
     *
     * @param c
     * @return
     */
    public static <T> Map<String, T> getBeansByClass(Class<T> c) {
        return applicationContext.getBeansOfType(c);
    }

    /**
     * 根据bean的class来查找所有的对象(包括子类)
     *
     * @param resource
     * @return
     */
    public static Resource getResuorce(String resource) {
        return applicationContext.getResource(resource);
    }

    public static <T> List<T> getBeansOfType(Class<T> clazz) {
        //声明一个结果
        Map<String, T> map;
        try {
            //获取类型
            map = applicationContext.getBeansOfType(clazz);
        } catch (Exception e) {
            map = null;
        }
        //返回 bean 的类型
        return map == null ? null : new ArrayList<>(map.values());
    }


    /**
     * 获取所有被注解的 bean
     *
     * @return
     */
    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> anno) {
        Map<String, Object> map;
        try {
            //获取注解的 bean
            map = applicationContext.getBeansWithAnnotation(anno);
        } catch (Exception e) {
            map = null;
        }
        return map;
    }
}
