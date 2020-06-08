package com.gzmpc.support.common.build;


import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gzmpc.support.common.annotation.BuildComponent;
import com.gzmpc.support.common.exception.BuildException;
import com.gzmpc.support.common.util.SpringContextUtils;

/**
* @author rwe
* @version 创建时间：2017年2月24日 下午4:22:26
* BuildComponent 的 一些常用函数
*/

@Service
public class BuildService {
	private Log log = LogFactory.getLog(BuildService.class.getName());
	
	public void build(String beanid) {
		if(beanid != null){
			Buildable b = SpringContextUtils.getBeanById(beanid,Buildable.class);
			if(b != null ) {
				try {
					b.build();
				} catch (BuildException e) {
					log.error(e.getMessage(), e);
					throw new RuntimeException("找不到实现接口Bean["+beanid+"]加载失败");
				}
			}
			else {
				throw new RuntimeException("找不到实现接口[Buildable]的Bean["+beanid+"]");
			}
		}
	}
	
	public void reload() {
		reload(SpringContextUtils.getApplicationContext());
	}
	
	public void reload(ApplicationContext ac) {
		log.info("开始加载[BuildComponent]注解");
		Map<String, Object> builds = ac.getBeansWithAnnotation(BuildComponent.class);
		for(String name : builds.keySet()) {
			Object o = builds.get(name);
			if(o instanceof Buildable){	//判断是否实现了接口
				log.info("加载["+o.getClass().getName()+"]开始");
				try{
					((Buildable) o).build();
					log.info("加载["+o.getClass().getName()+"]结束");
				} catch (BuildException e) {
					log.error("加载["+o.getClass().getName()+"]失败:"+e.getMessage(),e);
				}
			}
		}
		log.info("加载[BuildComponent]注解结束");
	}
}
