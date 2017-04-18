package com.gzmpc.metadata;

import java.sql.Connection;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzmpc.build.Buildable;
import com.gzmpc.dao.SystemDao;
import com.gzmpc.metadata.di.*;
import com.gzmpc.metadata.exception.ExceptionDef;
import com.gzmpc.metadata.form.*;
import com.gzmpc.metadata.func.*;
import com.gzmpc.metadata.grid.*;
import com.gzmpc.metadata.nav.Nav;
import com.gzmpc.metadata.query.*;
import com.gzmpc.metadata.queryparam.QueryParam;
import com.gzmpc.metadata.toolbar.*;
import com.gzmpc.stereotype.BuildComponent;

/**
 * 存储了元数据信息的类，
 * 包括entity,dataItem,form与query,toolbar。这些信息都是由HandlerFactory构造(set方法)的。 </li>
 * <li>dataItem</li>
 */
@Repository("metaData")
@BuildComponent
public class MetaData implements Buildable {
	private Log log = LogFactory.getLog(MetaData.class.getName());
	
	@Autowired
	SystemDao systemDao;
	
	@Autowired
	HandlerFactory hanlerFactory;
	
	@Autowired
	DBTableColumnPool dBTableColumnPool;
	
	private Map<String,DataItem> dataItems; //
	private Map<String,Form> forms; // 所有Form
	private Map<String,ToolBar> toolBars; // 所有工具条 //画工具条时可以从内存里取出来比较
	private Map<String,QueryDef> querys; // 所有查询语句 暂时还没有实现
	private Map<String,Grid> grids;
	private Map<String,Func> funcs; // 所有工程
	private Map<String,Map<String,DataItem>> diexts;
	private Map<String,QueryParam> queryParams;// 所有的查询框定义
	private Nav[] navs;
	private Map<String,ExceptionDef> exceptions;

	public DataItem findDataItemByCode(String code) {
		return dataItems.get(code);
	}

	public Func findFuncsByCode(String funccode) {
		return funcs.get(funccode);
	}

	public Form findFormByCode(String formCode) {
		return forms.get(formCode);
	}

	public ToolBar findToolBarByCode(String toolbarCode) {
		return toolBars.get(toolbarCode);
	}

	public QueryParam findQueryParamByCode(String queryparamCode) {
		return queryParams.get(queryparamCode);
	}

	public QueryDef findQueryDefByCode(String code) {
		return querys.get(code);
	}

	public Grid findGridDefByCode(String code) {
		return grids.get(code);
	}

	public DataItem findExtend(String objectcode,String dtcode){
		DataItem result = null;
		if (diexts != null) {
			Map<String,DataItem> d =  diexts.get(objectcode);
			if(d!=null){
				DataItem di =  d.get(dtcode);
				if(di!=null){
					result = di;
				}
			}
		}
		return result;
	}
	
	public DataItem findDataItem(String objectcode,String dtcode) {
		DataItem result = findExtend(objectcode,dtcode);
		if(result == null ) {
			result = findDataItemByCode(dtcode);
		}
		return result;
	}
	
	public Nav[] getNavs() {
		return navs;
	}
	
	public ExceptionDef findExceptionByCode(String code) {
		return exceptions.get(code);
	}

	/**
	 * 再次从新读取配置文件
	 * 
	 * @throws java.lang.Exception
	 */
	
	@Override
	public void build() {
		
		Connection con = null;
		
		try{
			con = systemDao.getUnautocommitConnection();
			Func[] funcs = hanlerFactory.retFuncDefs(con);
			
			this.funcs = new ConcurrentHashMap<String, Func>();
			for (int i = 0; i < funcs.length; i++) {
				this.funcs.put(funcs[i].getCode(), funcs[i]);
			}
			
			Grid[] grids = hanlerFactory.retGridDefs(con);
			this.grids = new ConcurrentHashMap<String, Grid>();
			for (int i = 0; i < grids.length; i++) {
				this.grids.put(grids[i].getGridCode(), grids[i]);
			}
			
			QueryDef[] querys = hanlerFactory.retQueryDefs(con);
			this.querys = new ConcurrentHashMap<String, QueryDef>();
			for (int i = 0; i < querys.length; i++) {
				this.querys.put(querys[i].getCode(), querys[i]);
			}
			
			QueryParam[] qps = hanlerFactory.retQueryParams(con);
			this.queryParams = new ConcurrentHashMap<String, QueryParam>();
			for (int i = 0; i < qps.length; i++) {
				queryParams.put(qps[i].getCode(),qps[i]);
			}
			
			Form[] forms = hanlerFactory.retForms(con);
			this.forms = new ConcurrentHashMap<String, Form>();
			for (int i = 0; i < forms.length; i++) {
				this.forms.put(forms[i].getCode(), forms[i]);
			}
			
			ToolBar[] toolbars = hanlerFactory.retToolBars(con);
			this.toolBars = new ConcurrentHashMap<String, ToolBar>();
			for (int i = 0; i < toolbars.length; i++) {
				this.toolBars.put(toolbars[i].getCode(), toolbars[i]);
			}
			
			DataItem[] dataitems = hanlerFactory.retDataItems(con);
			this.dataItems = new ConcurrentHashMap<String, DataItem> ();
			for (int i = 0; i < dataitems.length; i++) {
				this.dataItems.put(dataitems[i].getCode(), dataitems[i]);
			}
			
			this.diexts = hanlerFactory.retDataItemEntends(con);
			
			this.navs = hanlerFactory.retNavs(con, funcs);
			
			this.exceptions = hanlerFactory.retExceptions(con);
			
//			TableDataProvider.cacheGridMap = null;
//			QueryDataProvider.cacheGridMap = null;
//			dBTableColumnPool.tableMap.clear();
//			OperatorPool.di.clear();
//			OperatorPool.divalue.clear();
			
			con.commit();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			try{
				if(con != null && !con.isClosed()){
					con.rollback();
				}
			} catch (Exception e2) {
				log.error(e2.getMessage(),e2);
			}
			
		} finally {
			DbUtils.closeQuietly(con);
		}
		
	}
	
	
}
