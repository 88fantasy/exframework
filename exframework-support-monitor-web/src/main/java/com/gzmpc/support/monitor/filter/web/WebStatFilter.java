package com.gzmpc.support.monitor.filter.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author rwe
 * @version 创建时间：2018年5月10日 下午5:07:12 类说明
 */

public class WebStatFilter implements Filter {

	public static final String PARAM_NAME_EXCLUSIONS = "exclusions";

	private Set<String> excludesPattern;

	private String contextPath;

	@Override
	public void init(FilterConfig config) throws ServletException {
		{
			String exclusions = config.getInitParameter(PARAM_NAME_EXCLUSIONS);
			if (exclusions != null && exclusions.trim().length() != 0) {
				excludesPattern = new HashSet<String>(Arrays.asList(exclusions.split("\\s*,\\s*")));
			}
		}


		this.contextPath = getContextPath(config.getServletContext());

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        if (isExclusion(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public boolean isExclusion(String requestURI) {
		if (excludesPattern == null || requestURI == null) {
			return false;
		}

		if (contextPath != null && requestURI.startsWith(contextPath)) {
			requestURI = requestURI.substring(contextPath.length());
			if (!requestURI.startsWith("/")) {
				requestURI = "/" + requestURI;
			}
		}

		for (String pattern : excludesPattern) {
			if (matches(pattern, requestURI)) {
				return true;
			}
		}

		return false;
	}

	private String getContextPath(ServletContext context) {
		if (context.getMajorVersion() == 2 && context.getMinorVersion() < 5) {
			return null;
		}

		try {
			String contextPath = context.getContextPath();

			if (contextPath == null || contextPath.length() == 0) {
				contextPath = "/";
			}
			return contextPath;
		} catch (NoSuchMethodError error) {
			return null;
		}
	}
	
	private boolean matches(String pattern, String source) {
		if (pattern == null || source == null) {
			return false;
		}
		pattern = pattern.trim();
		source = source.trim();
		if (pattern.endsWith("*")) {
			// pattern: /druid* source:/druid/index.html
			int length = pattern.length() - 1;
			if (source.length() >= length) {
				if (pattern.substring(0, length).equals(
						source.substring(0, length))) {
					return true;
				}
			}
		} else if (pattern.startsWith("*")) {
			// pattern: *.html source:/xx/xx.html
			int length = pattern.length() - 1;
			if (source.length() >= length
					&& source.endsWith(pattern.substring(1))) {
				return true;
			}
		} else if (pattern.contains("*")) {
			// pattern:  /druid/*/index.html source:/druid/admin/index.html
			int start = pattern.indexOf("*");
			int end = pattern.lastIndexOf("*");
			if (source.startsWith(pattern.substring(0, start))
					&& source.endsWith(pattern.substring(end + 1))) {
				return true;
			}
		} else {
			// pattern: /druid/index.html source:/druid/index.html
			if (pattern.equals(source)) {
				return true;
			}
		}
		return false;
	}
}
