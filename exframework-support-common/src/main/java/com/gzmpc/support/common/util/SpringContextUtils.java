package com.gzmpc.support.common.util;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
	
	/**
     * 根据bean的id来查找对象
     * @param id
     * @return
     */
    public static Object getBeanById(String id){
        return applicationContext.getBean(id);
    }
    
    /**
     * 根据bean的id来查找对象
     * @param <T>
     * @param id
     * @param requiredType
     * @return
     */
    public static <T> T getBeanById(String id,Class<T> requiredType){
        return applicationContext.getBean(id, requiredType);
    }
     
    /**
     * 根据bean的class来查找对象
     * @param <T>
     * @param c
     * @return
     */
    public static <T> T getBeanByClass(Class<T> c){
        return applicationContext.getBean(c);
    }
     
    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param c
     * @return
     */
    public static <T> Map<String, T> getBeansByClass(Class<T> c){
        return applicationContext.getBeansOfType(c);
    }
    
    /**
     * 根据bean的class来查找所有的对象(包括子类)
     * @param c
     * @return
     */
    public static Resource getResuorce(String resource){
        return applicationContext.getResource(resource);
    }
}
