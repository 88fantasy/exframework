package org.exframework.support.sso.core.config;


import org.exframework.support.sso.core.constant.SecurityConstants;
import org.exframework.support.sso.core.service.LoginUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.annotation.Order;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
@Order(1)
@WebFilter(urlPatterns = {"/*"}, filterName = "ssoFilter")
public class SsoFilter implements Filter {

    private String loginUrl;

    private String loginSuccessUrl;

    private String serverUrl;

    private String whiteList;

    private static final Logger log = LoggerFactory.getLogger(SsoFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            Properties configProperties = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("sso.properties");
            configProperties.load(inputStream);
            this.loginUrl = configProperties.getProperty("jwt.sso.loginUrl");
            this.loginSuccessUrl = configProperties.getProperty("jwt.sso.loginSuccessUrl");
            this.serverUrl = configProperties.getProperty("jwt.sso.serverUrl");
            this.whiteList = configProperties.getProperty("jwt.sso.whiteList");

        } catch (IOException e) {
            log.error("读取登录认证配置文件出错-", e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("come into SessionFilter and do processes...");

        // 实际业务处理，这里就是下面图中的before doFilter逻辑
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resq = (HttpServletResponse) response;
        LoginUserService loginUserService = new LoginUserService(req);

        if (loginUserService.getCurrentUser() != null) {
            chain.doFilter(request, response);
        }

        String redirect = req.getHeader("Referer") != null ? req.getHeader("Referer") : req.getRequestURL().toString();
        String authorization = req.getHeader(SecurityConstants.AUTHORIZATION);
        whiteList = whiteList != null ? whiteList.trim() : "";
        String[] tmpList = whiteList.split(",");
        AntPathMatcher matcher = new AntPathMatcher();
        String respRedirect = loginUrl + "?backforward=true&&redirect=" + redirect;

        for (String str : tmpList) {


            if (matcher.match(str, req.getRequestURI())) {
                if (loginUserService.getCurrentUser() == null && authorization != null) {
                    try {
                        loginUserService.setLoginUserFromServer(serverUrl, authorization);
                        loginSuccessUrl = loginSuccessUrl + "?redirect=" + redirect;
                        req.getRequestDispatcher(loginSuccessUrl).forward(req, resq);
                    } catch (Exception e) {
                        resq.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        resq.setHeader(SecurityConstants.AUTHORIZATION, "");
                        resq.sendRedirect(respRedirect);
                    }
                }

            }
        }

        if (loginUserService.getCurrentUser() == null && authorization == null) {
            resq.sendRedirect(respRedirect);

        } else if (loginUserService.getCurrentUser() == null && authorization != null) {
            try {
                loginUserService.setLoginUserFromServer(serverUrl, authorization);
                loginSuccessUrl = loginSuccessUrl + "?redirect=" + redirect;
                req.getRequestDispatcher(loginSuccessUrl).forward(req, resq);
            } catch (Exception e) {
                resq.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resq.setHeader(SecurityConstants.AUTHORIZATION, "");
                resq.sendRedirect(respRedirect);
            }
        }


        // 当前过滤器处理完了交给下一个过滤器处理

        log.info("SessionFilter's process has completed!");
    }

    @Override
    public void destroy() {

    }
}
