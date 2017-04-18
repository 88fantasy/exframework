package com.gzmpc.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.login.LoginService;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.pojo.OptionResult;
import com.gzmpc.ui.DdlService;

@Controller
@Path("ddl")
public class DdlApi {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	DdlService ddl;
	
	@Context
	HttpServletRequest request;
			
	@Path("get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public OptionResult get(@QueryParam("ddlkey") String ddlkey) throws NotAuthorizedException, NotFoundException {
		Account account = loginService.getAccount(request);
		
		if( ddlkey != null ) {
			Map<String, String> result = ddl.get(ddlkey, account);
			return OptionResult.ok(result);
		}
		else {
			return OptionResult.error("缺少ddlkey", null);
		}
	}
	
}
