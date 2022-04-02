package org.exframework.spring.boot.canal.client.interfaces;

import com.alibaba.otter.canal.client.CanalConnector;
import org.exframework.spring.boot.canal.client.core.ListenerPoint;
import org.exframework.spring.boot.canal.config.CanalProperties;

import java.util.List;
import java.util.Map;

/**
 * 信息转换工厂类接口层
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
@FunctionalInterface
public interface TransponderFactory {
	
	/**
	 * @param connector        canal 连接工具
	 * @param config           canal 链接信息
	 * @param listeners 实现接口的监听器
	 * @param annoListeners    注解监听拦截
	 * @return
	 * 
	 */
	MessageTransponder newTransponder(CanalConnector connector, Map.Entry<String, CanalProperties.Instance> config, List<CanalEventListener> listeners, List<ListenerPoint> annoListeners);
}
