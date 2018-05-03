package com.gzmpc.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.gzmpc.dao.EbDao;
import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.login.LoginService;
import com.gzmpc.metadata.MetaData;
import com.gzmpc.metadata.exception.ExceptionDef;
import com.gzmpc.metadata.nav.Nav;
import com.gzmpc.metadata.sys.Account;

@Component
public class BaseController {
	
	private Log log = LogFactory.getLog(BaseController.class.getName());
	
	@Autowired
	EbDao systemDao;
	
	@Autowired
	MetaData metaData;
	
	@Autowired
	LoginService loginService;
	
	@Value("${webapp.name:默认系统名}")
	private String appname;
	
	
	public Nav[] getNavBar(final HttpServletRequest request) {
		Nav[] navs = metaData.getNavs();
		return navs;
	}
	
	public void setCommonAttribute(final HttpServletRequest request, final ModelMap model) throws NotAuthorizedException  {
		Account account = loginService.getAccount(request);
		model.addAttribute("appname", appname);
		model.addAttribute("account", account);
		model.addAttribute("basePath", request.getContextPath());
		model.addAttribute("navs", getNavBar(request));
		
	}
	
	@ExceptionHandler({Exception.class})  
    public ModelAndView handleError(HttpServletRequest req, Exception e) {
		log.error("访问地址["+req.getRequestURI()+"]时出现错误:"+ e.getMessage(),e);
		ModelAndView mav = new ModelAndView();
		String code = e.getClass().getName();
		ExceptionDef def = metaData.findExceptionByCode(code);
		if(def == null) {
			def = new ExceptionDef();
			def.setCode(code);
			def.setName(code);
		}
		mav.addObject("exception", def);
	    mav.addObject("msg", e.getMessage());
	    mav.addObject("url", req.getRequestURL());
	    mav.addObject("appname", appname);
	    mav.addObject("basePath", req.getContextPath());
	    mav.setViewName("error");
        return mav;  
    } 

}
