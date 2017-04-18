package com.gzmpc.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

public class ResponseHeaderFilter implements Filter {  
    FilterConfig fc;   
  
    public void doFilter(ServletRequest req, ServletResponse res,  
            FilterChain chain) throws IOException, ServletException {  
        HttpServletResponse response = (HttpServletResponse) res;  
        // set the provided HTTP response parameters
        // 循环设置增加header
        for (Enumeration e = fc.getInitParameterNames(); e.hasMoreElements();) {  
            String headerName = (String) e.nextElement();  
            response.addHeader(headerName, fc.getInitParameter(headerName));  
        }  
        // pass the request/response on  
        chain.doFilter(req, response);  
    }   
  
    public void init(FilterConfig filterConfig) {  
        this.fc = filterConfig;  
    }   
  
    public void destroy() {  
        this.fc = null;  
    }   
  
} 