package org.exframework.spring.boot.canal.config;


import cn.hutool.core.thread.ExecutorBuilder;
import org.exframework.spring.boot.canal.client.core.SimpleCanalClient;
import org.exframework.spring.boot.canal.client.interfaces.CanalClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static org.exframework.spring.boot.canal.config.CanalClientConfiguration.PREFIX;


/**
 * canal 自动装配类
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */


public class CanalClientConfiguration {
    /**
     * 记录日志
     */
    private final static Logger logger = LoggerFactory.getLogger(CanalClientConfiguration.class);

    public final static String PREFIX = "canal";

    /**
     * canal 配置
     */
    @Autowired
    private CanalProperties canalProperties;


    @Bean
    @ConditionalOnMissingBean
    ThreadPoolExecutor threadPoolExecutor() {
        return ExecutorBuilder.create()
                .setCorePoolSize(canalProperties.getInstances().size())
                .setMaxPoolSize(canalProperties.getInstances().size())
                .useSynchronousQueue()
                .build();
    }

    /**
     * 返回 canal 的客户端
     *
     * @param
     * @return
     */
    @Bean
    CanalClient canalClient(ThreadPoolExecutor threadPoolExecutor) {
        logger.info("正在尝试连接 canal 客户端....");
        //连接 canal 客户端
        CanalClient canalClient = new SimpleCanalClient(canalProperties, threadPoolExecutor);
        logger.info("正在尝试开启 canal 客户端....");
        //开启 canal 客户端
        canalClient.start();
        logger.info("启动 canal 客户端成功....");
        //返回结果
        return canalClient;
    }
}
