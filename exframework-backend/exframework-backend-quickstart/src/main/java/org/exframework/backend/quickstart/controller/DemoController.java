package org.exframework.backend.quickstart.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.backend.quickstart.dto.TestRequest;
import org.exframework.portal.jdbc.entity.base.DictionaryDO;
import org.exframework.portal.jdbc.mapper.DictionaryMapper;
import org.exframework.support.rest.entity.ApiResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 控制类
 * @author pro
 *
 */
@RestController
@Api(tags = "Demo")
public class DemoController {

	/**
	 * hello world
	 * 
	 * @param name
	 * @return
	 */
	@ApiOperation(value = "hello")
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String hello(
			@ApiParam(value = "name", required = true) @PathVariable String name) {
		return "Hello "+name;
	}

	@Autowired
	DictionaryMapper dictionaryMapper;

	@ApiOperation(value = "query")
	@PostMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<List<DictionaryDO>> query(@ApiParam(value = "keys", required = true) @Valid @RequestBody TestRequest request) {
		return new ApiResponseData<>(dictionaryMapper.selectList(dictionaryMapper.wrapperFromDTO(request)));
	}
}
