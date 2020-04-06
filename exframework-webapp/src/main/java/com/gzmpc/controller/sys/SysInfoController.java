package com.gzmpc.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gzmpc.controller.BaseController;
import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.web.ui.ToolbarService;

@Controller
@RequestMapping("/sys/sysinfo")
public class SysInfoController extends BaseController {
	private Log log = LogFactory.getLog(SysInfoController.class.getName());

	@Autowired
	ToolbarService toolbarService;
	
	@RequestMapping("/main.view")
	public String main(final HttpServletRequest request, final ModelMap model) throws NotAuthorizedException {
		setCommonAttribute(request,model);
	    return "/sys/sysinfo/main";
	}
}
