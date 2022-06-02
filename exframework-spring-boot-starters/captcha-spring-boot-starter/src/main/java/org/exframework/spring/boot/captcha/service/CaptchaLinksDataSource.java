package org.exframework.spring.boot.captcha.service;

import java.util.Collection;

/**
 * @author rwe
 * @date 2022/5/15 22:13
 **/
public interface CaptchaLinksDataSource {

    /**
     * 数据来源类型
     * @return
     */
    String dataSourceType();

    /**
     * 校验uri
     * @return
     */
    Collection<String> uris();
}