package com.gzmpc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.login.LoginService;
import com.gzmpc.metadata.MetaData;
import com.gzmpc.metadata.attribute.Attribute;
import com.gzmpc.metadata.form.Form;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.permission.PermissionSupport;
import com.gzmpc.pojo.OptionResult;
import com.gzmpc.service.FormService;

@Controller
@Path("form")
public class FormApi {

	@Autowired
	MetaData metaData;
	
	@Autowired
	PermissionSupport permissions;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	FormService formService;
	
	@Context
	HttpServletRequest request;

	@Path("get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public OptionResult get(@QueryParam("formcode") String formcode) throws NotFoundException {
		
		Form form = metaData.findFormByCode(formcode);
		if (form == null) {
			throw new NotFoundException("根据编码(" + formcode + ")找不到对应的表单定义！！！");
		}
		Attribute[] attrs = form.getAttributes();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i=0,j=attrs.length;i<j;i++) {
			Attribute attr = attrs[i];
			list.add(formService.showAttr(form, attr));
		}
		return OptionResult.ok(list);
	}

	@Path("getInitValue")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public OptionResult initValue(@QueryParam("formcode") String formcode) throws NotAuthorizedException {
		Account account = loginService.getAccount(request);
		
		Map<String, Object> values = formService.getFormDefaultValue(account, formcode);
		
		return OptionResult.ok(values);
	}
	
}
