package com.gzmpc.web.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gzmpc.metadata.module.Module;
import com.gzmpc.service.sys.ModuleService;
import com.gzmpc.support.rest.entity.ApiResponseData;
import com.gzmpc.web.constants.WebApiConstants;
import com.gzmpc.web.service.LoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Component
public class ModuleController {

	private Log log = LogFactory.getLog(ModuleController.class.getName());

	@Autowired
	LoginService loginService;

	@Autowired
	ModuleService moduleService;

	@ApiOperation(value = "获取下拉列表")
	@RequestMapping(value = WebApiConstants.API_MODULE_INIT, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<Module> init(
			@Valid @ApiParam(value = "key", required = true) @NotEmpty @PathVariable String key) {
//		Account account = loginService.getAccount();
		Module module = moduleService.findByKey(key);
		return new ApiResponseData<Module>(module);
	}

}
