package com.gzmpc.web.api;

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
import com.gzmpc.metadata.toolbar.ToolButton;
import com.gzmpc.web.pojo.OptionResult;
import com.gzmpc.web.ui.ToolbarService;

@Controller
@Path("toolbar")
public class ToolbarApi {

	@Autowired
	ToolbarService toolbarService;
	
	@Context
	HttpServletRequest request;
	
	@Autowired
	LoginService loginService;

	@Path("get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public OptionResult get(@QueryParam("menu") String menu) throws NotAuthorizedException, NotFoundException {
		Account account = loginService.getAccount(request);
		
		if (menu != null) {
			ToolButton[] tbs = toolbarService.get(menu, account);
			Map<String,String>[] list = toolbarService.translateToMap(tbs);
			return OptionResult.ok(list);
		} else {
			return OptionResult.error("缺少menu");
		}
	}

}
