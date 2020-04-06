package com.gzmpc.web.api;


import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gzmpc.login.LoginService;
import com.gzmpc.metadata.OperatorPool;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.service.AccountService;
import com.gzmpc.web.pojo.OptionResult;

@Controller
@Path("login")
public class LoginApi {
	
	@Autowired
	OperatorPool operatorPool;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	LoginService loginService;
	
	@Context
	HttpServletRequest request;
			
	@Path("async")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public OptionResult in(@FormParam("username") String username,
			@FormParam("password") String password) {
		//认证要求不区分帐号与密码错误
		Account account = accountService.getAccount(username);
		if(account == null) {
			return OptionResult.error("帐号或密码错误");
		}
		else if( accountService.isValid(account, password)) {
			HttpSession session = request.getSession();
			String key = loginService.getSessionKey();
			session.setAttribute(key, account);
			return OptionResult.ok(null);
		}
		else {
			return OptionResult.error("帐号或密码错误");
		}
	}
	
	@Path("signout")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response out() throws URISyntaxException {
		HttpSession session = request.getSession();
		String key = loginService.getSessionKey();
		session.removeAttribute(key);
		session.invalidate();
		return Response.temporaryRedirect(new URI(request.getContextPath())).build();
	}
	
}
