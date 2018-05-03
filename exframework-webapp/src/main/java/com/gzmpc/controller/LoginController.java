package com.gzmpc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController extends BaseController{

	@RequestMapping("/login.view")
	public String login(final HttpServletRequest request, final ModelMap model) {
		model.addAttribute("basePath", request.getContextPath());
	    return "login";
	}
	
	@RequestMapping("/marinologin.view")
	public String marinologin(final HttpServletRequest request, final ModelMap model) {
		model.addAttribute("basePath", request.getContextPath());
	    return "marinologin";
	}
	
	@RequestMapping("/newlogin.view")
	public String newlogin(final HttpServletRequest request, final ModelMap model) {
		model.addAttribute("basePath", request.getContextPath());
	    return "newlogin";
	}
}
