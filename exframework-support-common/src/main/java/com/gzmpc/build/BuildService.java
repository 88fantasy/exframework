package com.gzmpc.build;


import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gzmpc.stereotype.BuildComponent;
import com.gzmpc.util.SpringContextUtils;

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
			b.build();
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
				} catch (Exception e) {
					log.error("加载["+o.getClass().getName()+"]失败:"+e.getMessage(),e);
				}
			}
		}
		log.info("加载[BuildComponent]注解结束");
	}
}
