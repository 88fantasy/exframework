package org.exframework.spring.boot.captcha.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 验证码校验链接
 *
 * @author rwe
 * @date 2022/5/15 16:02
 **/
public class CaptchaLinksAggregation {

    private static Logger logger = LoggerFactory.getLogger(CaptchaLinksAggregation.class);

    private volatile static Map<String, CaptchaLinksDataSource> instances = new ConcurrentHashMap<>();

    static {
        ServiceLoader<CaptchaLinksDataSource> sources = ServiceLoader.load(CaptchaLinksDataSource.class);
        for (CaptchaLinksDataSource item : sources) {
            instances.put(item.dataSourceType(), item);
        }
        logger.info("初始化加载-校验验证码:{}", instances.keySet());
    }

    public void register(CaptchaLinksDataSource source) {
        instances.put(source.dataSourceType(), source);
    }

    public static Map<String, CaptchaLinksDataSource> getInstances() {
        return instances;
    }

    public static List<String> getLinks() {
        return instances.values().stream().flatMap(source -> source.uris().stream()).collect(Collectors.toList());
    }

}
