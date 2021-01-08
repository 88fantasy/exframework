package com.gzmpc.core.web.controller;
//package com.gzmpc.web.controller;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.gzmpc.metadata.FilterCondition;
//import com.gzmpc.exception.InitException;
//import com.gzmpc.exception.NotAuthorizedException;
//import com.gzmpc.exception.NotFoundException;
//import com.gzmpc.grid.DataProviderFactory;
//import com.gzmpc.grid.IDataProvider;
//import com.gzmpc.grid.IDataProviderQuerySupport;
//import com.gzmpc.metadata.MetaData;
//import com.gzmpc.metadata.OperatorPool;
//import com.gzmpc.metadata.grid.Grid;
//import com.gzmpc.metadata.sys.Account;
//import com.gzmpc.metadata.toolbar.ToolButton;
//import com.gzmpc.permission.PermissionSupport;
//import com.gzmpc.service.sys.GridService;
//import com.gzmpc.service.sys.SystemParameterService;
//import com.gzmpc.support.admin.service.ToolbarService;
//import com.gzmpc.support.common.exception.BuildException;
//import com.gzmpc.support.common.util.JSONUtil;
//import com.gzmpc.support.common.util.MapUtil;
//import com.gzmpc.support.common.util.SpringContextUtils;
//import com.gzmpc.utils.Const;
//import com.gzmpc.web.service.LoginService;
//
//import io.swagger.annotations.Api;
//
//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
//@Api(tags = "表格")
//public class GridController {
//	private Log log = LogFactory.getLog(GridController.class.getName());
//
//	@Autowired
//	MetaData metaData;
//	
//	@Autowired
//	OperatorPool operatorPool;
//	
//	@Autowired
//	ToolbarService toolbarService;
//	
//	@Autowired
//	PermissionSupport permissions;
//	
//	@Autowired
//	LoginService loginService;
//	
//	@Autowired
//	SystemParameterService systemParameterService;
//	
//	@Autowired
//	JSONUtil jsonUtil;
//	
//	@Autowired
//	DataProviderFactory dataProviderFactory;
//	
//	@Path("init/{gridcode}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public OptionResult init(@PathParam("gridcode") String gridcode,
//			@QueryParam("params") String params) throws NotFoundException,NotAuthorizedException {
//		
//		Account account = loginService.getAccount(request);
//		
//		Map<String,Object> param = null;
//		if(params != null && !"".equals(params.trim())){
//			param = jsonUtil.toMap(JSONObject.parseObject(params));
//		}
//		else {
//			param = new ConcurrentHashMap<String,Object>();
//		}
//		
//		if (gridcode == null) {
//			throw new NotFoundException("gridcode不能为空");
//		}
//		Grid gridDef = metaData.findGridDefByCode(gridcode);
//		if (gridDef == null) {
//			throw new NotFoundException("根据表格编码(" + gridcode + ")找不到对应的表格定义！！！");
//		}
//		String querymoduleid = gridDef.getQuerymoduleid();
//		String downloadmoduleid = gridDef.getDownloadmoduleid();
//		String autoQuery = gridDef.getAutoQuery();
//		String pagesize = gridDef.getPagesize();
//		String queryType = gridDef.getQueryType();
//		String dataSource = gridDef.getDataSource();
//		String sumfieldnames = gridDef.getSumfieldnames();
//		int selectmodel = gridDef.getSelectmodel();
//		String toolbarcode = gridDef.getToolbarCode();
//		String defaulttoolbar = gridDef.getDefalutToolbar();
//		boolean needpagecount = gridDef.getNeedpagecount();
//
//		if (!permissions.hasRight(account, querymoduleid)) {
//			throw new NotAuthorizedException("你没有权限访问此操作(" + querymoduleid + ")！！！");
//		}
//
//		IDataProvider provider = dataProviderFactory.getProvider(queryType);
//
//		Map<String,Object> map = new ConcurrentHashMap<String,Object>();
//		MapUtil.putIfNotNull(map, Const.GRID_AUTO_QUERY, autoQuery);
//		MapUtil.putIfNotNull(map, Const.GRID_PAGESIZE, pagesize);
//		map.put(Const.GRID_QUERY_TYPE, queryType);
//		map.put(Const.GRID_DATA_SOURCE, dataSource);
//		map.put(Const.GRID_QUERY_MODULEID, querymoduleid);
//		map.put(Const.GRID_DOWNLOADMODULEID, downloadmoduleid);
//		MapUtil.putIfNotNull(map, Const.GRID_SUM_FIELDNAMES, sumfieldnames);
//		map.put(Const.GRID_SELECT_MODEL, selectmodel);
//		MapUtil.putIfNotNull(map, Const.GRID_DEFALUT_TOOLBAR, defaulttoolbar);
//		map.put(Const.GRID_NEEDPAGECOUNT, needpagecount);
//		
//		if (toolbarcode != null && !"".equals(toolbarcode)) {
//			ToolButton[] tbs = toolbarService.get(toolbarcode, account);
//			Map<String,String>[] list = toolbarService.translateToMap(tbs);
//			map.put(Const.GRID_TOOLBAR_COMPOSITE, list);
//		}
//		// 画表格配置
//		List<Map<String,Object>> fields = provider.retGridInfo(gridcode, account, param); // 得到表格的配置内容
//		map.put(Const.GRID_FIELDS, fields);
//		
//		return OptionResult.ok(map);
//	}
//
//	@Path("query/{gridcode}")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public OptionResult query(@PathParam("gridcode") String gridcode,
//			String params) throws NotFoundException,NotAuthorizedException, InitException, BuildException {
//		Account account = loginService.getAccount(request);
//		
//		if(params == null || "".equals(params.trim())){
//			throw new NotFoundException("缺少必要参数param");
//		}
//		Map<String,Object> param = jsonUtil.toMap(JSONObject.parseObject(params));
//		
//		if (gridcode == null) {
//			throw new NotFoundException("gridcode不能为空");
//		}
//		Grid gridDef = metaData.findGridDefByCode(gridcode);
//		if (gridDef == null) {
//			throw new NotFoundException("根据表格编码(" + gridcode + ")找不到对应的表格定义！！！");
//		}
//		
//		String queryType = gridDef.getQueryType();
//		String querymoduleid = gridDef.getQuerymoduleid();
//		if (!permissions.hasRight(account, querymoduleid)) {
//			throw new NotAuthorizedException("你没有权限访问此操作(" + querymoduleid + ")！！！");
//		}
//
//		IDataProvider provider = dataProviderFactory.getProvider(queryType);
//		
//		String extendid = gridDef.getExtendBeanName();
//		if (extendid != null && !"".equals(extendid.trim())) {
//			IDataProviderQuerySupport support = null;
//			try{
//				support = SpringContextUtils.getBeanById(extendid, IDataProviderQuerySupport.class);
//			} catch (BeansException e) {
//				throw new NotFoundException("找不到extendbean["+extendid+"]实现类");
//			}
//			FilterCondition[] conditions = support.putDefCondition(request);
//			if (conditions != null && conditions.length > 0) {
//				@SuppressWarnings("unchecked")
//				List<Map<String,String>> cds = (List<Map<String,String>>) param.get(Const.CONDITIONS);
//				if(cds == null) {
//					cds = new ArrayList<Map<String,String>>();
//				}
//				for(int i=0,j=conditions.length;i<j;i++) {
//					FilterCondition condition = conditions[i];
//					Map<String,String> row = new ConcurrentHashMap<String,String>();
//					row.put(Const.PRFIX_FIELDNAME, condition.getFilterName());
//					row.put(Const.PRFIX_OPERA, condition.getFilterOpera());
//					row.put(Const.PRFIX_VALUE1, condition.getFilterValue());
//					row.put(Const.PRFIX_VALUE2, condition.getFilterValue2());
//					cds.add(row);
//				}
//			}
//		}
//		
//		long s = new Date().getTime();
//		Map<String, Object> dataMap = provider.getJsonData(gridcode, param, account);
//	
//		@SuppressWarnings("unchecked")
//		List<Map<String, Object>> dataList = (List<Map<String, Object>>) dataMap.get(Const.ROWS);
//		boolean isquerylastpage = (Boolean) dataMap.get(Const.ISQUERYLASTPAGE);
//		int currentpage = (Integer) dataMap.get(Const.CURRENTPAGE);
//		Map<String,Object> map = new ConcurrentHashMap<String,Object>();
//		if(dataMap.containsKey(Const.TOTALPAGE)){
//			map.put(Const.TOTALPAGE, dataMap.get(Const.TOTALPAGE));
//		}
//		if(dataMap.containsKey(Const.TOTALROWS)){
//			map.put(Const.TOTALROWS, dataMap.get(Const.TOTALROWS));
//		}
//		map.put(Const.ROWS, dataList);
//		map.put(Const.ISQUERYLASTPAGE, isquerylastpage);
//		map.put(Const.CURRENTPAGE, currentpage);
//		double mis = (new Date().getTime() - s) / (double) 1000;
//		String str = "";
//		if (mis < 1) {
//			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.000");
//			str = df.format(mis) + "秒";
//		} else if (mis >= 1 && mis < 60) {
//			java.text.DecimalFormat df = new java.text.DecimalFormat("#00.00");
//			str = df.format(mis) + "秒";
//		} else {
//			mis = mis / 60;
//			java.text.DecimalFormat df = new java.text.DecimalFormat("#0000.00");
//			str = df.format(mis) + "分";
//		}
//		while (str.startsWith("0")) {
//			str = str.substring(1);
//		}
//		if (str.startsWith("."))
//			str = "0" + str;
//		map.put("time", str);
//		return OptionResult.ok(map);
//	}
//
//	@Path("download/{gridcode}")
//	@POST
//	public Response download(@PathParam("gridcode") String gridcode,
//			@FormParam("params") String params) throws NotFoundException,NotAuthorizedException {
//		final String CONTENT_TYPE = "text/csv; charset=utf-8";
//
//		Account account = loginService.getAccount(request);
//		
//		if(params == null || "".equals(params.trim())){
//			throw new NotFoundException("缺少必要参数param");
//		}
//		Map<String,Object> param = jsonUtil.toMap(JSONObject.parseObject(params));
//		
//		if (gridcode == null) {
//			throw new NotFoundException("gridcode不能为空");
//		}
//		
//		Grid gridDef = metaData.findGridDefByCode(gridcode);
//		if (gridDef == null) {
//			throw new NotFoundException("根据表格编码(" + gridcode + ")找不到对应的表格定义！！！");
//		}
//		
//		String queryType = gridDef.getQueryType();
//		String querymoduleid = gridDef.getQuerymoduleid();
//		if (!permissions.hasRight(account, querymoduleid)) {
//			throw new NotAuthorizedException("你没有权限访问此操作(" + querymoduleid + ")！！！");
//		}
//
//		IDataProvider provider = dataProviderFactory.getProvider(queryType);
//
//		try{
//			long startTime = new Date().getTime();
//			String file = provider.doDownLoad(gridcode, param, account);
//			long returnTime = new Date().getTime();
//			log.debug("下载人" + account.getAccountName() + ",下载表格数据(" + gridcode + "["+file+"])所需时间:"+ (returnTime - startTime) / 1000 + "秒");
//			String filename = file.substring(file.lastIndexOf(File.separator)+1);
//			return Response.ok(new File(file), MediaType.valueOf(CONTENT_TYPE)).header("Content-Disposition", "attachment; filename=" + filename).build();
//		} catch ( BuildException e) {
//			return Response.ok(e.getMessage(),MediaType.TEXT_PLAIN_TYPE).build();
//		} catch (InitException e) {
//			return Response.ok(e.getMessage(),MediaType.TEXT_PLAIN_TYPE).build();
//		}
//	}
//	
//	@Path("setting/{gridcode}")
//	@POST
//	public Response setting(@PathParam("gridcode") String gridcode,
//			@FormParam("params") String params,
//			@FormParam("action") String action) throws NotFoundException,NotAuthorizedException {
//
//		Account account = loginService.getAccount(request);
//		
//		if(gridcode == null || "".equals(gridcode.trim())){
//			throw new NotFoundException("缺少必要参数gridcode");
//		}
//		if(action == null || "".equals(action.trim())){
//			throw new NotFoundException("缺少必要参数action");
//		}
//		
//		if("update".equals(action)) {
//			systemParameterService.putAccountKey(account.getAccountId(), gridcode+GridService.COLCONFIG, params);
//		}
//		else if("reset".equals(action)) {
//			systemParameterService.removeAccountKey(account.getAccountId(), gridcode+GridService.COLCONFIG); 
//		}
//		
//		
//
//		return Response.ok("保存成功",MediaType.TEXT_PLAIN_TYPE).build();
//	}
//}
