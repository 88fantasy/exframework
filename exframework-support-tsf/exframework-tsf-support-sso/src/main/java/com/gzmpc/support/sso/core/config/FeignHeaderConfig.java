package com.gzmpc.support.sso.core.config;

import feign.RequestInterceptor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Auther: yjf
 * @Date: 2020-5-29 11:58
 * @Description: 各微服务间增加header
 */
public class FeignHeaderConfig implements RequestInterceptor {

    @Override
    public void apply(feign.RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        template.header(name, values);
                }
            }

        }
    }

}
