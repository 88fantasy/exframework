package org.exframework.portal.web.constants;
/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
public interface WebApiConstants {
	
	String API_PARAM = "/param";
	String API_PARAM_GET = API_PARAM + "/get/{code}";
	String API_PARAM_QUERY = API_PARAM + "/queryList";
	String API_PARAM_DELETE = API_PARAM + "/delete";
	String API_PARAM_POST = API_PARAM + "/post";

	String API_DDL = "/ddl";
	String API_DDL_GET = API_DDL + "/get/{ddlkey}";
	String API_DDL_OPTIONS = API_DDL + "/options/{ddlkey}";
	String API_DDL_MANY = API_DDL + "/many";
	String API_DDL_MANY_OPTIONS = API_DDL + "/manyOptions";
	String API_DDL_QUERY = API_DDL + "/queryList";
	String API_DDL_POST = API_DDL + "/post";
	
	String API_DATAITEM = "/dataitem";
	String API_DATAITEM_GET = API_DATAITEM + "/get/{ddlkey}";
	String API_DATAITEM_QUERY = API_DATAITEM + "/queryList";
	String API_DATAITEM_POST = API_DATAITEM + "/post";
	
	String API_FORM = "/form";
	String API_FORM_GET = API_FORM + "/get/{formcode}";
	
	String API_GRID = "/grid";
	
	String API_QUERYPARAM = "/queryparam";
	String API_QUERYPARAM_GET = API_QUERYPARAM + "/get/{queryparamcode}";

	String API_MODULE = "v1/module";
	String API_MODULE_INIT = API_MODULE+ "/init/{code}";
	
	String API_HOV = "/hov";
	String API_HOV_QUERY = API_HOV+ "/queryList";
	
	String API_HOV_DATA_CONDITION = API_HOV+ "/data/condition/{code}";
	String API_HOV_DATA_PARAM = API_HOV+ "/data/param/{code}";

	String API_SYS = "/sys";
	String API_SYS_BUILD = API_SYS + "/build/{beanid}";
	String API_SYS_RELOAD = API_SYS + "/reload";
	

	String API_LOG = "/log";
	String API_LOG_QUERY = API_LOG + "/queryList";
	
	String API_LOGIN = "/login";
	String API_LOGIN_ASYNC = API_LOGIN+"/async";
}
