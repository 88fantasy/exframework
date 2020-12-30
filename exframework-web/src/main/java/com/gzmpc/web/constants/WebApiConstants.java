package com.gzmpc.web.constants;
/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface WebApiConstants {
	

	String API_DDL = "/ddl";
	String API_DDL_GET = API_DDL + "/get/{ddlkey}";
	
	String API_FORM = "/form";
	String API_FORM_GET = API_FORM + "/get/{formcode}";
	
	String API_GRID = "/grid";
	
	String API_QUERYPARAM = "/queryparam";
	String API_QUERYPARAM_GET = API_QUERYPARAM + "/get/{queryparamcode}";

	String API_MODULE = "v1/module";
	
	String API_MODULE_INIT = API_MODULE+ "/init/{key}";
	

	String API_SYS = "/sys";
	String API_SYS_BUILD = API_SYS + "/build/{beanid}";
	String API_SYS_RELOAD = API_SYS + "/reload";
	

	String API_LOGIN = "/login";
	String API_LOGIN_ASYNC = API_LOGIN+"/async";
}
