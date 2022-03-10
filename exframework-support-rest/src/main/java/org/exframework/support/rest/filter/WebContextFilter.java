package org.exframework.support.rest.filter;

import org.exframework.support.rest.context.WebContextHolder;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 接口访问上下文过滤器
 *
 * @author rwe
 * @date 2022/3/8 16:43
 **/
public class WebContextFilter extends RequestContextFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        }
        finally {
            resetContextHolders();
        }
    }

    private void resetContextHolders() {
        WebContextHolder.clear();
    }
}
