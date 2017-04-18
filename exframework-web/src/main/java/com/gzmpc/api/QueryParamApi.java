package com.gzmpc.api;


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
import com.gzmpc.metadata.queryparamitem.QueryParamBase;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.pojo.OptionResult;
import com.gzmpc.ui.QueryparamService;
import com.gzmpc.utils.Const;

@Controller
@Path("queryparam")
public class QueryParamApi {

	@Autowired
	QueryparamService queryparamService;

	@Autowired
	LoginService loginService;
	
	@Context
	HttpServletRequest req;

	@Path("get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public OptionResult get(@QueryParam(Const.QUERY_PARAM_CODE) String queryparamcode) throws NotAuthorizedException, NotFoundException {
		
		Account account = loginService.getAccount(req);
		
		if (queryparamcode != null) {
			QueryParamBase[] bases = queryparamService.get(queryparamcode, account);
			return OptionResult.ok(bases);
		} else {
			return OptionResult.error("缺少queryparamcode");
		}
	}

}
