package org.exframework.portal.jdbc.grid.queryimpl;
//package org.exframework.grid.queryimpl;
//
//import org.exframework.portal.exception.InitException;
//import org.exframework.grid.DefaultDataProvider;
//import org.exframework.grid.GridCache;
//import org.exframework.grid.IDataAfterQuery;
//import org.exframework.grid.IDataProviderDBnameSupport;
//
//import java.util.List;
//import java.util.Map;
//import org.exframework.portal.metadata.grid.Grid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.apache.commons.dbutils.DbUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import org.exframework.utils.QuerySupport;
//import org.exframework.portal.metadata.MetaData;
//
//import java.util.ArrayList;
//
//import org.exframework.portal.metadata.OperatorPool;
//import org.exframework.portal.metadata.query.QueryDef;
//import org.exframework.portal.metadata.queryparamitem.QueryParamBase.Oper;
//import org.exframework.portal.metadata.rowset.CacheRowSetAdapter;
//import org.exframework.portal.metadata.sys.Account;
//import org.exframework.service.sys.GridService;
//import org.exframework.support.common.exception.BuildException;
//import org.exframework.support.common.util.MapUtil;
//import org.exframework.support.common.util.SpringContextUtils;
//
//import java.util.Properties;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.regex.Pattern;
//
//import javax.sql.rowset.CachedRowSet;
//
//import java.util.regex.Matcher;
//import org.exframework.utils.Const;
//
//import java.sql.*;
//
//@Service
//public class QueryDataProvider extends DefaultDataProvider {
//	private Log log = LogFactory.getLog(QueryDataProvider.class.getName());
//	
//	@Autowired
//	QuerySupport querySupport;
//	
//	@Autowired
//	MetaData metaData;
//	
//	@Autowired
//	OperatorPool operatorPool;
//	
//	@Autowired
//	GridService gridService;
//	
//	@Autowired
//	CacheRowSetAdapter cacheRowSetAdapter;
//	
//	public Map<String,GridCache> cacheGridMap = new ConcurrentHashMap<String,GridCache>(); // 缓存根据表格名而获得的表格信息，只存放共有的信息项。例如字段名和类型
//	
//	@Override
//	public Map<String, Object> getJsonData(String gridcode, Map<String, Object> params, Account account) throws InitException,BuildException {
//		Connection con = null;
//		PreparedStatement pst = null;
//		
//		try {
//			String dbname = getDbname(gridcode,params);
//			if (dbname != null && !"".equals(dbname))
//				con = querySupport.retConnection(dbname);
//			else
//				con = querySupport.retConnection();
//			con.setAutoCommit(false);
//			Map<String,Object> result = getJsonData(con, gridcode, params, account);
//			con.commit();
//			return result;
//		} catch (SQLException ex) {
//			log.error(ex.getMessage(), ex);
//			try {
//				if (con != null) {
//					con.rollback();
//				}
//			} catch (SQLException ex1) {
//				log.error(ex1.getMessage(), ex1);
//			}
//			throw new RuntimeException(ex.getMessage(), ex);
//		} finally {
//			DbUtils.closeQuietly(con, pst, null);
//		}
//
//	}
//
//	public Map<String,Object> getJsonData(Connection con, String gridcode, Map<String, Object> params, Account account) throws SQLException {
//		Map<String,Object> dataMap = new ConcurrentHashMap<String,Object>();
//		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		try {
//			GridCache cache = getGridCache(gridcode, params);
//			List<Map<String, Object>> fields = cache.getFields();
//			int startIndex = (Integer) params.get(Const.GRID_START_INDEX);
//			int pagesize = (Integer) params.get(Const.GRID_PAGESIZE);
//			@SuppressWarnings("unchecked")
//			List<Map<String,String>> conditions = (List<Map<String,String>>)params.get(Const.CONDITIONS);
//
//			Grid grid = metaData.findGridDefByCode(gridcode);
//			String querycode = grid.getDataSource();
//			String sumField = grid.getSumfieldnames();
//			String orderby = grid.getOrderby();
//			boolean needpagecount = grid.getNeedpagecount();// 总页数
//			
//			String groupfields = (String) params.get("groupfields");
//			String selectFields = (String) params.get("selectfields");
//			int total = -1;
//			if (startIndex == -3) {	//不分页
//				
//			}
//			else if (startIndex == -2) { // 最后一页
//				total = getCount(con, fields, querycode, conditions);
//				startIndex = countTotalPage(total,pagesize) - 1;
//			}
//			List<Object> values = new ArrayList<Object>();
//			String sql_str = genSql(fields, values, querycode, conditions, selectFields);
//			if (groupfields != null && !"".equals(groupfields)) {
//				sql_str += " group by " + groupfields;
//			} else if (orderby != null && !"".equals(orderby)) {
//				sql_str += " order by " + orderby;
//			}
//			log.info(sql_str);
//			pst = con.prepareStatement(sql_str);
//			int size = values.size();
//			String info = "";
//			for (int i = 0; i < size; i++) {
//				Object obj = values.get(i);
//				info += obj + ",";
//				if (obj.getClass().getName().equals("java.sql.Date")) {
//					pst.setDate(i + 1, ((java.sql.Date) obj));
//				} else if (obj.getClass().getName().equals("java.sql.Timestamp")) {
//					pst.setTimestamp(i + 1, (Timestamp) obj);
//				} else {
//					pst.setString(i + 1, (String) obj);
//				}
//			}
//			if (size > 0) {
//				log.info(info.substring(0, info.length() - 1));
//			}
//			rs = pst.executeQuery();
//			boolean isQueryLastPage = false;
//			CachedRowSet crs = cacheRowSetAdapter.retSet();
//			if (startIndex != -3) {	//分页
//				crs.setPageSize(pagesize);// 每頁大小
//				crs.populate(rs);// 從第幾筆開始抓一頁大小的筆數
//				for (int pagenum = 1; pagenum < startIndex; pagenum++) {
//					crs.nextPage();
//				}
//				if (crs.size() < pagesize) {
//					isQueryLastPage = true;
//				}
//				dataMap.put(Const.CURRENTPAGE, startIndex);
//			} else {
//				crs.populate(rs);
//				isQueryLastPage = true;
//				dataMap.put(Const.CURRENTPAGE, 1);
//			}
//			boolean hasResult = false;
//			ResultSetMetaData rsmd = rs.getMetaData();
//			List<Map<String, Object>> headers = gridService.drawGridTitleInfo(rsmd,  gridcode);
//			while (crs.next()) {
//				Map<String, Object> data = gridService.outputOneRow(crs, headers); // 生成每一条记录
//				if (data != null) {
//					dataList.add(data);
//					hasResult = true;
//				}
//			}
//			rs.close();
//			pst.close();
//			
//			boolean sum = sumField != null && !"".equals(sumField);
//			if (hasResult && (sum || needpagecount)) {
//				if( !sum && needpagecount ) {
//					if ( total == -1 ) {
//						total = getCount(con, fields, querycode, conditions);
//					}
//				}
//				else {
//					StringBuffer buf = new StringBuffer();
//					if (needpagecount && total == -1) {
//						buf.append(" count(1) as _const_total ");
//					}
//					String[] sf = sumField.split(",");
//					for (int i = 0, j = sf.length; i < j; i++) {
//						if (i == 0 && needpagecount)
//							buf.append(",");
//						if (i != 0)
//							buf.append(",");
//
//						buf.append(" sum(");
//						buf.append(sf[i]);
//						buf.append(") ");
//						buf.append(sf[i]);
//					}
//					values.clear();
//					String innerSql = genSql(fields, values, querycode, conditions, buf.toString());
//					if (groupfields != null && !"".equals(groupfields)) {
//						innerSql += " group by " + groupfields;
//					}
//					pst = con.prepareStatement(innerSql);
//					log.info(innerSql);
//					size = values.size();
//					for (int i = 0; i < size; i++) {
//						Object obj = values.get(i);
//						if (obj.getClass().getName().equals("java.sql.Date")) {
//							pst.setDate(i + 1, ((java.sql.Date) obj));
//						} else if (obj.getClass().getName().equals("java.sql.Timestamp")) {
//							pst.setTimestamp(i + 1, (Timestamp) obj);
//						} else {
//							pst.setString(i + 1, (String) obj);
//						}
//					}
//					rs = pst.executeQuery();
//					if (rs.next()) {
//						Map<String,Object> sumrow = gridService.outputOneRow(rs, headers);
//						sumrow.put("rownum_", "汇总合计");
//						dataList.add(sumrow);
//						if (needpagecount && total == -1) {
//							total = rs.getInt("_const_total");
//						}
//					}
//					rs.close();
//					pst.close();
//				}
//			}
//			if( total != -1) {
//				dataMap.put(Const.TOTALROWS, total);
//				dataMap.put(Const.TOTALPAGE, countTotalPage(total,pagesize));
//			}
//			
//			dataMap.put(Const.ROWS, dataList);
//			dataMap.put(Const.ISQUERYLASTPAGE, isQueryLastPage);
//			
//			// 查询后处理数据
//			String beanid = grid.getAfterQueryBeanName();
//			if(beanid != null && !"".equals(beanid)) {
//				IDataAfterQuery after = SpringContextUtils.getBeanById(beanid, IDataAfterQuery.class);
//				after.afterQuery(con, account, params, dataMap);
//			}
//			
//			return dataMap;
//		} finally {
//			DbUtils.closeQuietly(null, pst, rs);
//		}
//	}
//
//	public int getCount(Connection con, List<Map<String, Object>> fields, String queryCode, List<Map<String,String>> params) throws SQLException {
//		int count = -1;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		try {
//			StringBuffer result = new StringBuffer();
//			result.append("select count(1) totalNum from ("); // 不要用全部列
//			List<Object> values = new ArrayList<Object>();
//			String innerSql = genSql(fields, values, queryCode, params, null);
//			result.append(innerSql);
//			result.append(") p");
//			log.info(result.toString());
//
//			pst = con.prepareStatement(result.toString());
//			int size = values.size();
//			for (int i = 0; i < size; i++) {
//				Object obj = values.get(i);
//				if (obj.getClass().getName().equals("java.sql.Date")) {
//					pst.setDate(i + 1, ((java.sql.Date) obj));
//				} else if (obj.getClass().getName().equals("java.sql.Timestamp")) {
//					pst.setTimestamp(i + 1, (Timestamp) obj);
//				} else {
//					pst.setString(i + 1, (String) obj);
//				}
//			}
//			rs = pst.executeQuery();
//			rs.next();
//			count = rs.getInt(1);
//			rs.close();
//			pst.close();
//		} catch (SQLException ex) {
//			throw ex;
//		} finally {
//			DbUtils.closeQuietly(null, pst, rs);
//		}
//		return count;
//	}
//
//	public String genSql(List<Map<String, Object>> fields, List<Object> values, String queryCode, List<Map<String,String>> params,
//			String selectFields) {
//		StringBuffer sb = new StringBuffer();
//		if (selectFields != null && !"".equals(selectFields)) {
//			sb.append("select  ");
//			sb.append(selectFields);
//			sb.append(" from (");
//		} else
//			sb.append("select rownum rownum_,t.* from ( ");
//
//		QueryDef queryDef = metaData.findQueryDefByCode(queryCode);
//		String sqldef = queryDef.getSqldef();
//		List<String> filterParamName = new ArrayList<String>(); //已经替换过的字段
//		String configsql = parseSql(sqldef, values, params, filterParamName);
//		sb.append(configsql);
//		sb.append(" ) t ");
//		// 构造where 语句
//		
//		List<String> conditions = new ArrayList<String>(); // sql条件的数组
//		if(params != null) {
//			for( int i=0,j=params.size();i<j;i++) {
//				Map<String,String> param = params.get(i);
//				String fieldname = param.get(Const.PRFIX_FIELDNAME);
//				String opera = param.get(Const.PRFIX_OPERA);
//				String value1 = param.get(Const.PRFIX_VALUE1);
//				String value2 = param.get(Const.PRFIX_VALUE2);
//				if (filterParamName.indexOf(fieldname) != -1) {
//					continue;
//				}
//				if (value1 != null && !value1.trim().equals("")) {
//					String condition = gridService.makeupCondition(opera, fieldname, value1, value2, values,
//							fields);
//					if (!"".equals(condition))
//						conditions.add(condition);
//				}
//			}
//		}
//		int size = conditions.size();
//		if (size > 0) {
//			sb.append(" where ");
//			sb.append(conditions.get(0));
//			sb.append("\n");
//			for (int i = 1; i < size; i++) {
//				sb.append("   and ");
//				sb.append(conditions.get(i));
//			}
//		}
//		return sb.toString();
//	}
//
//	public List<Map<String, Object>> retGridInfo(String gridcode, Account account, Map<String,Object> params) {
//		GridCache cache = getGridCache(gridcode,params);
//		List<Map<String, Object>> rows = cache.getFields();
//		gridService.filterGridColumns(gridcode, account, rows);
//		return rows;
//	}
//
//	public GridCache getGridCache(String gridcode,  Map<String,Object> params) {
//		synchronized (gridcode) {
//			if (cacheGridMap.get(gridcode) == null) {
//				GridCache instance = genGridCache(gridcode, params);
//				MapUtil.putIfNotNull(cacheGridMap, gridcode, instance);
//			}
//		}
//		return cacheGridMap.get(gridcode);
//	}
//
//	private GridCache genGridCache(String gridcode, Map<String,Object> params) {
//		GridCache cache = null;
//		Connection con = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		try {
//			
//			Grid gridDef = metaData.findGridDefByCode(gridcode);
//			if (gridDef == null) {
//				throw new RuntimeException("根据表格编码(" + gridcode + ")找不到对应的表格定义！！！");
//			}
//			String dbname = null;
//			if (gridDef.getDbbeanname() != null && !"".equals(gridDef.getDbbeanname())) {
//				IDataProviderDBnameSupport dbsup = SpringContextUtils.getBeanById(gridDef.getDbbeanname(),IDataProviderDBnameSupport.class);
//				dbname = dbsup.retDbname(params);
//			}
//			if (dbname == null || "".equals(dbname))
//				con = querySupport.retConnection();
//			else
//				con = querySupport.retConnection(dbname);
//			con.setAutoCommit(false);
//			
//			String querycode = gridDef.getDataSource();
//			QueryDef queryDef = metaData.findQueryDefByCode(querycode);
//			String sqldef = queryDef.getSqldef();
//			List<Object> values = new ArrayList<Object>();
//			StringBuffer sql = new StringBuffer();
//			sql.append(" select * from ( ");
//			String configsql = parseSql(sqldef, values, new ArrayList<Map<String,String>>(), new ArrayList<String>());
//			sql.append(configsql);
//			sql.append(" ) ");
//			sql.append(" where 1 = 2");
//			log.info(sql.toString());
//			pst = con.prepareStatement(sql.toString());
//			int size = values.size();
//			if (size > 0) { 
//				for (int i = 0; i < size; i++) {
//					Object obj = values.get(i);
//					pst.setString(i + 1, (String) obj);
//				}
//			}
//			rs = pst.executeQuery();
//			ResultSetMetaData rsmd = rs.getMetaData();
//			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
//
//			rows.addAll(gridService.drawGridTitleInfo(rsmd, gridcode));
//			
//			cache = new GridCache();
//			cache.setFields(rows);
//			
//			rs.close();
//			pst.close();
//			
//			con.commit();
//			
//		} catch (Exception ex) {
//			log.error(ex.getMessage(), ex);
//			try {
//				con.rollback();
//			} catch (SQLException ex1) {
//				log.error(ex1.getMessage(), ex1);
//			}
//		} finally {
//			DbUtils.closeQuietly(con);
//		}
//		return cache;
//	}
//
//	private String parseSql(String sql, List<Object> values, List<Map<String,String>> params, List<String> filterParamName)  {
//		Properties properties = new Properties();
//		for( int i=0,j=params.size();i<j;i++) {
//			Map<String,String> param = params.get(i);
//			String fieldname = param.get(Const.PRFIX_FIELDNAME);
//			String opera = param.get(Const.PRFIX_OPERA);
//			String value1 = param.get(Const.PRFIX_VALUE1);
////			String value2 = param.get(Const.PRFIX_VALUE2);
//			
//			if (opera.equals(Oper.STR.getKey())) {
//				// continue;
//				String cstr = (String) param.get("cstr");
//				cstr = cstr == null ? "" : cstr;
//				cstr += " and (" + value1 + ") ";
//				properties.put("cstr", cstr);
//			} else if (value1 != null && !value1.trim().equals("")) {
//				properties.put(fieldname, value1); // 保存变量值
//			}
//		}
//		
//		
//		String conditionPattern = "\\[[^\\[]*\\]";
//		StringBuffer sb = new StringBuffer();
//		// 进行替换，若参数没有赋值，去掉此参数关联的语句。
//		Pattern p = Pattern.compile(conditionPattern);
//		Matcher m = p.matcher(sql);
//		boolean result = m.find();
//		boolean cstrflag = false;
//		while (result) {
//			String s = m.group(); // s -----> [ companyid= {companyid} ]
//			String str = replaceParam(s.substring(1, s.length() - 1), properties, filterParamName);
//			if ( str == null){ // 在[...%param%...]条件中的param,无法在params中找到
//				str = " ";
//			}
//			else {
//				String temp = "\\{[a-zA-Z_0-9]*\\}"; // 找{}
//				Pattern p2 = Pattern.compile(temp);
//				Matcher m2 = p2.matcher(s);
//				while (m2.find()) {
//					String cds = m2.group(); // {companyid}
//					int paramIndex = s.indexOf(cds);
//					cds = cds.substring(1, cds.length() - 1); // companyid;
//
//					if (properties.get(cds) == null && !"cstr".equals(cds))
//						throw new RuntimeException("前台没有传参数" + cds + "的值");
//
//					String restr = "?";
//
//					if (!"cstr".equals(cds)) { 
//
//						boolean reqaddval = true;
//
//						String value = (String) properties.get(cds);
//
//						// 如果表达式中有/*hh24:mi:ss*/的,并且操作符是<、<=的，那么就需要转化
//						if (str.indexOf("hh24:mi:ss") != -1) {
//							if (str.indexOf("<") != -1 || str.indexOf("<=") != -1) {
//								if (value.indexOf("23:59:59") == -1)
//									value = value + " 23:59:59";
//							}
//						}
//
//						String v = s.substring(0, paramIndex).trim();
//						int paramOperIndex = v.lastIndexOf(" ");
//						if (paramOperIndex != -1) {
//							String opera = v.substring(paramOperIndex);
//							if ("like".equalsIgnoreCase(opera.trim())) {
//								if (!value.endsWith(".")) // 如果以.号为结束的表示模糊查询时后面没有%号
//									value = value + "%";
//								else
//									value = value.substring(0, value.length() - 1);
//							}
//
//							// 如果操作符是 in ，则参数中有几个值就需要有几个? 如: 1111,22222 需要 in
//							// (?,?)
//							if (v.toLowerCase().endsWith(" in (")) {
//								if (value != null && value.length() > 0) {
//									String[] vals = value.split(",");
//									int len = vals.length;
//									restr = "";
//									reqaddval = false;
//									for (int i = 0; i < len; i++) {
//										if (i == len - 1)
//											restr += "?";
//										else
//											restr += "?,";
//
//										values.add(vals[i]); // list保存变量名对应的值
//									}
//								}
//							}
//						}
//
//						str = s.substring(1, s.length() - 1).replaceAll(temp, restr); // 只是替换成变量
//
//						if (reqaddval) {
//							values.add(value); // list保存变量名对应的值
//						}
//					} else {
//						String value = (String) properties.get(cds);
//						str = value == null ? "" : value;
//						cstrflag = true;
//					}
//				}
//			}
//			m.appendReplacement(sb, str);
//			result = m.find();
//		}
//		m.appendTail(sb);
//
//		// 如果已嵌入CSTR 则删去 否则导致重复条件
//		if (cstrflag) {
//			for(int i=params.size()-1;i>-1;i--) {
//				Map<String,String> param = params.get(i);
//				String opera = param.get(Const.PRFIX_OPERA);
//				if (opera.equals(Oper.STR.getKey())) {
//					params.remove(i);
//				}
//			}
//		}
//		return sb.toString();
//	}
//
//	private String replaceParam(String sqlstr, Properties params, List<String> filterParamName) {
//		String paramPattern = "\\{[a-zA-Z_0-9]*\\}";
//		Pattern p = Pattern.compile(paramPattern);
//		Matcher m = p.matcher(sqlstr);
//		StringBuffer sb = new StringBuffer();
//		boolean result = m.find();
//		while (result) {
//			String str = m.group();
//			String fieldname = str.substring(1, str.length() - 1);
//			Object object = params.get(fieldname);
//			String value = null;
//			if (object != null) {
//				value = params.get(fieldname).toString();
//				filterParamName.add(fieldname);
//			}
//
//			if (value == null) {
//				return null;
//			}
//			m.appendReplacement(sb, value);
//			result = m.find();
//		}
//		m.appendTail(sb);
//		return sb.toString();
//	}
//
//}