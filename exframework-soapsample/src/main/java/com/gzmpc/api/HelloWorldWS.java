package com.gzmpc.api;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Controller;

/**
* @author rwe
* @version 创建时间：2017年5月23日 上午11:20:43
* 类说明
*/

@WebService
@Controller
public class HelloWorldWS {
	
	@WebMethod(operationName="hello")
	public String getHelloWorld() {

		return "Hello World!!";

	}
}
