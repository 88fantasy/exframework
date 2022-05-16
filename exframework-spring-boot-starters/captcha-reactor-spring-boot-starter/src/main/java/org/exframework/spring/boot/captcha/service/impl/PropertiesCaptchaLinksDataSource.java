package org.exframework.spring.boot.captcha.service.impl;

import org.exframework.spring.boot.captcha.annotation.CaptchaRequired;
import org.exframework.spring.boot.captcha.service.CaptchaLinksAggregation;
import org.exframework.spring.boot.captcha.service.CaptchaLinksDataSource;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 注解验证码链接数据源
 *
 * @author rwe
 * @date 2022/5/15 16:12
 **/
public class PropertiesCaptchaLinksDataSource implements CaptchaLinksDataSource {

    private final static String DATA_SOURCE_TYPE = "properties";

    private final static List<String> URIS = new ArrayList<>();

    @Override
    public String dataSourceType() {
        return DATA_SOURCE_TYPE;
    }

    @Override
    public Collection<String> uris() {
        return URIS;
    }

    public static void register(List<String> uris) {
        URIS.addAll(uris);
    }
}
