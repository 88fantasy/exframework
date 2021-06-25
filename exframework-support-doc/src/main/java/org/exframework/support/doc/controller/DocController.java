package org.exframework.support.doc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.exframework.support.doc.service.DataBaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "文档")
public class DocController {

	@Autowired
	private DataBaseService dataBaseService;

	@ApiOperation(value = "获取数据库物理表(markdown)")
	@RequestMapping(value = "/database/md", method = RequestMethod.GET)
	public String markdown() {
		return dataBaseService.markdown();
	}

}
