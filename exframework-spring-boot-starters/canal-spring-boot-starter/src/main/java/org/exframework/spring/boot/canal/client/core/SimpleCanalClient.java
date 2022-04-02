package org.exframework.spring.boot.canal.client.core;

import com.alibaba.otter.canal.client.CanalConnector;
import org.exframework.spring.boot.canal.annotation.ListenPoint;
import org.exframework.spring.boot.canal.client.abstracts.AbstractCanalClient;
import org.exframework.spring.boot.canal.client.interfaces.CanalEventListener;
import org.exframework.spring.boot.canal.client.interfaces.MessageTransponder;
import org.exframework.spring.boot.canal.config.CanalProperties;
import org.exframework.support.common.util.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 通过线程池处理
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
public class SimpleCanalClient extends AbstractCanalClient {

    /**
     * 声明一个线程池
     */
    private ThreadPoolExecutor executor;

    /**
     * 通过实现接口的监听器
     */
    protected final List<CanalEventListener> listeners = new ArrayList<>();

    /**
     * 通过注解的方式实现的监听器
     */
    private final List<ListenerPoint> annoListeners = new ArrayList<>();

    /**
     * 日志记录
     */
    private final static Logger logger = LoggerFactory.getLogger(SimpleCanalClient.class);

    /**
     * 监听线程状态
     */
    private final List<Future> futures = new ArrayList<>();

    /**
     * 构造方法，进行一些基本信息初始化
     *
     * @param canalProperties
     * @return
     */
    public SimpleCanalClient(CanalProperties canalProperties, ThreadPoolExecutor executor) {
        super(canalProperties);
        this.executor = executor;
        //初始化监听器
        initListeners();
    }

    /**
     * @param connector
     * @param config
     * @return
     */
    @Override
    protected void process(CanalConnector connector, Map.Entry<String, CanalProperties.Instance> config) {
        futures.add(executor.submit(factory.newTransponder(connector, config, listeners, annoListeners)));
    }

    /**
     * 关闭 canal 客户端
     *
     * @param
     * @return
     */
    @Override
    public void stop() {
        //停止 canal 客户端
        super.stop();
        //线程关闭
        for(Future f : futures) {
            f.cancel(true);
        }
    }

    /**
     * 初始化监听器
     *
     * @param
     * @return
     */
    private void initListeners() {
        logger.info("canal 监听器正在初始化....");
        //获取监听器
        List<CanalEventListener> list = SpringContextUtils.getBeansOfType(CanalEventListener.class);
        if (list != null && list.size() > 0) {
            //若存在目标监听，放入 listenerMap
            listeners.addAll(list);
        }

        //若是你喜欢通过注解的方式去监听的话。。
        Map<String, Object> listenerMap = SpringContextUtils.getBeansWithAnnotation(org.exframework.spring.boot.canal.annotation.CanalEventListener.class);
        //也放入 map
        if (listenerMap != null) {
            for (Object target : listenerMap.values()) {
                //方法获取
                Method[] methods = target.getClass().getDeclaredMethods();
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        //获取监听的节点
                        ListenPoint l = AnnotatedElementUtils.findMergedAnnotation(method, ListenPoint.class);
                        if (l != null) {
                            annoListeners.add(new ListenerPoint(target, method, l));
                        }
                    }
                }
            }
        }
        //初始化监听结束
        logger.info("canal 监听器初始化完成. 函数监听器: {}, 注解监听器: {}", listeners.size(), annoListeners.size());
        //整个项目上下文都没发现监听器。。。
        if (logger.isWarnEnabled() && listeners.isEmpty() && annoListeners.isEmpty()) {
            logger.warn("{}: 该项目中没有任何监听的目标! ", Thread.currentThread().getName());
        }
    }
}
