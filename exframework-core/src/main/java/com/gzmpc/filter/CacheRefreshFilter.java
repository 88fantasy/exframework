package com.gzmpc.filter;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CacheRefreshFilter implements Filter {
	
	public void destroy() {  
        
    }  
  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {  
    	HttpServletRequest req = (HttpServletRequest)request;
    	CoverResponse cr = new CoverResponse((HttpServletResponse)response);
        
        fc.doFilter(request, cr);
        
        String contenttype = response.getContentType();
        if(contenttype!=null){ 
        	if(contenttype.contains("text/html")) {// 只对html作替换操作
		        //处理替换  
		        String origin = cr.getContent();
		        StringBuffer sb = new StringBuffer();
		        String base = req.getContextPath();
		        Pattern p = Pattern.compile(base+"/js/.*\\.js");// 匹配 js文件夹下js后缀的
		        Matcher m = p.matcher(origin);
		        while (m.find()) {
		          String s = m.group();
		          String filepath = req.getSession().getServletContext().getRealPath(s.substring(base.length()));//需要减去base的长度,不然会重复
		          File file = new File(filepath);
		          if(file.exists()){
			          String str = s+"?refreshtime="+file.lastModified();	//用最后修改时间做参数刷新,当js文件有更新时前台可以刷新,无修改时也能使用缓存
			          m.appendReplacement(sb, str);
		          }
		        }
		        m.appendTail(sb);
		        
		        String jsfin = sb.toString();
		        sb = new StringBuffer();
		        p = Pattern.compile(base+"/css/.*\\.css");// 匹配 css文件夹下css后缀的
		        m = p.matcher(jsfin);
		        while (m.find()) {
		          String s = m.group();
		          String filepath = req.getSession().getServletContext().getRealPath(s.substring(base.length()));//需要减去base的长度,不然会重复
		          File file = new File(filepath);
		          if(file.exists()){
			          String str = s+"?refreshtime="+file.lastModified();
			          m.appendReplacement(sb, str);
		          }
		        }
		        m.appendTail(sb);
		        
		        response.getWriter().print(sb.toString()); 
        	}
        	else if(contenttype.startsWith("text")){
        		response.getWriter().print(cr.getContent()); 
        	}
        }
    }  
  
    public void init(FilterConfig arg0) throws ServletException {  
          
    }  
}
