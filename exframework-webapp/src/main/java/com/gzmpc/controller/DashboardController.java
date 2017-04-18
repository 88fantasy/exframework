package com.gzmpc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gzmpc.exception.NotAuthorizedException;


@Controller
public class DashboardController extends BaseController{

	@RequestMapping("/dashboard.view")
	public String admin(final HttpServletRequest request, final ModelMap model) throws NotAuthorizedException {
		setCommonAttribute(request,model);
	    return "dashboard";
	}
}
