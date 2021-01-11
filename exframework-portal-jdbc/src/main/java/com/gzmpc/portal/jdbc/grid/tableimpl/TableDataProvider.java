package com.gzmpc.portal.jdbc.grid.tableimpl;
//package com.gzmpc.grid.tableimpl;
//
//import com.gzmpc.portal.exception.InitException;
//import com.gzmpc.grid.DefaultDataProvider;
//import com.gzmpc.grid.GridCache;
//import com.gzmpc.grid.IDataAfterQuery;
//import com.gzmpc.grid.IDataProviderDBnameSupport;
//
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import javax.sql.rowset.CachedRowSet;
//
//import com.gzmpc.portal.metadata.grid.Grid;
//import com.gzmpc.portal.metadata.rowset.CacheRowSetAdapter;
//import com.gzmpc.portal.metadata.sys.Account;
//import com.gzmpc.service.sys.GridService;
//import com.gzmpc.support.common.exception.BuildException;
//import com.gzmpc.support.common.util.MapUtil;
//import com.gzmpc.support.common.util.SpringContextUtils;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import com.gzmpc.utils.Const;
//@Service
//public class TableDataProvider extends DefaultDataProvider {
//	
//	private Log log = LogFactory.getLog(TableDataProvider.class.getName());
//	
//	@Autowired
//	GridService gridService;
//	
//	@Autowired
//	CacheRowSetAdapter cacheRowSetAdapter;
//	
//	
//	public Map<String,GridCache> cacheGridMap = new ConcurrentHashMap<String,GridCache>(); // 缓存根据表格名而获得的表格信息，只存放共有的信息项。例如字段名和类型
//
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
//	public  GridCache genGridCache(String gridcode,  Map<String,Object> params) {
//		GridCache result = null;
//		Connection con = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		try {
//			Grid gridDef = metaData.findGridDefByCode(gridcode);
//			if (gridDef == null) {
//				throw new RuntimeException("根据表格编码(" + gridcode + ")找不到对应的表格定义！！！");
//			}
//			
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
//			String tableName = gridDef.getDataSource();
//			StringBuffer sql = new StringBuffer();
//			sql.append(" select * from ");
//			sql.append(tableName);
//			sql.append(" where 1 = 2 ");
//			pst = con.prepareStatement(sql.toString());
//			log.info(sql.toString());
//			rs = pst.executeQuery();
//
//			ResultSetMetaData rsmd = rs.getMetaData();
//			
//			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
//
//			rows.addAll(gridService.drawGridTitleInfo(rsmd, gridcode));
//			
//			result = new GridCache();
//			result.setFields(rows);
//			
//			rs.close();
//			pst.close();
//			
//			con.commit();
//		} catch ( SQLException sqle) {
//			log.error("初始化grid时出现错误:"+sqle.getMessage(),sqle);
//			try{
//				if ( con != null) {
//					con.rollback();
//				}
//			} catch ( SQLException sqle2) {
//				log.error("回滚出现错误:"+sqle2.getMessage(),sqle2);
//			}
//		} finally {
//			DbUtils.closeQuietly(con, pst, rs);
//		}
//		return result;
//	}
//
//	@Override
//	public Map<String, Object> getJsonData(String gridcode, Map<String, Object> params, Account account)  throws InitException,BuildException{
//		Connection con = null;
//		PreparedStatement pst = null;
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
//		} catch (Exception ex) {
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
//	}
//
//	/**
//	 * 获取表格初始化的信息，包括表格配置及表格显示信息等
//	 */
//	public List<Map<String, Object>> retGridInfo(String gridcode, Account account, Map<String,Object> params) {
//		GridCache cache = getGridCache(gridcode,params);
//		List<Map<String, Object>> rows = cache.getFields();
//		gridService.filterGridColumns(gridcode, account, rows);
//		return rows;
//	}
//
//	public Map<String,Object> getJsonData(Connection con, String gridcode, Map<String,Object> params, Account account) throws SQLException {
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
//			String tableName = grid.getDataSource();
//			String sumField = grid.getSumfieldnames();
//			String orderby = grid.getOrderby();
//			boolean needpagecount = grid.getNeedpagecount();// 总页数
//
//			String groupfields = (String) params.get("groupfields");
//			String selectFields = (String) params.get("selectfields");
//
//			int total = -1;
//			if (startIndex == Const.NOPAGINATION) {	//不分页
//				
//			}
//			else if (startIndex == Const.LASTPAGE) { // 最后一页
//				total = getCount(con, fields, tableName, conditions);
//				startIndex = countTotalPage(total,pagesize) - 1;
//			}
//			List<Object> values = new ArrayList<Object>();
//			String sql_str = genSql(fields, values, tableName, conditions,selectFields);
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
//			rs = pst.executeQuery();
//			if (size > 0) {
//				log.info(info.substring(0, info.length() - 1));
//			}
//			boolean isQueryLastPage = false;
//			CachedRowSet crs = cacheRowSetAdapter.retSet();
//			if (startIndex != Const.NOPAGINATION) {	//分页
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
//
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
//						total = getCount(con, fields, tableName, conditions);
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
//					String innerSql = genSql(fields, values, tableName, conditions, buf.toString());
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
//			
//			dataMap.put(Const.ROWS, dataList);
//			dataMap.put(Const.ISQUERYLASTPAGE, isQueryLastPage);
//			// 查询后处理数据
//			String beanid = grid.getAfterQueryBeanName();
//			if(beanid != null && !"".equals(beanid)) {
//				IDataAfterQuery after = SpringContextUtils.getBeanById(beanid, IDataAfterQuery.class);
//				after.afterQuery(con, account, params, dataMap);
//			}
//			return dataMap;
//		} finally {
//			DbUtils.closeQuietly(null, pst, rs);
//		}
//	}
//
//	public int getCount(Connection con,List<Map<String, Object>> fields, String tableName, List<Map<String,String>> params) throws SQLException {
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		int count = -1;
//		try {
//			StringBuffer result = new StringBuffer();
//			result.append("select count(1) totalNum from ("); // 不要用全部列
//			List<Object> values = new ArrayList<Object>();
//			String innerSql = genSql(fields, values, tableName, params, "1");
//			result.append(innerSql);
//			result.append(") p");
//			log.info(result.toString());
//
//			pst = con.prepareStatement(result.toString());
//			int size = values.size();
//			for (int i = 0; i < size; i++) {
//				Object obj = values.get(i);
//				if (obj  instanceof java.sql.Date) {
//					pst.setDate(i + 1, ((java.sql.Date) obj));
//				} else if (obj instanceof java.sql.Timestamp) {
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
//	private String genSql(List<Map<String, Object>> fields, List<Object> values, String tableName, List<Map<String,String>> params,
//			String selectFields) {
//		StringBuffer sb = new StringBuffer();
//		// 构造select语句
//		if (selectFields != null && !"".equals(selectFields)) {
//			sb.append("select  ");
//			sb.append(selectFields);
//			sb.append(" from ");
//		} else
//			sb.append("select rownum rownum_,t.* from ");
//
//		sb.append(tableName);
//		sb.append(" t ");
//
//		// 构造where 语句
//		
//		List<String> conditions = new ArrayList<String>();
//		if(params != null) {
//			for(int i=0,j=params.size();i<j;i++) {
//				Map<String,String> param = params.get(i);
//				String fieldname = param.get(Const.PRFIX_FIELDNAME);
//				String opera = param.get(Const.PRFIX_OPERA);
//				String value1 = param.get(Const.PRFIX_VALUE1);
//				String value2 = param.get(Const.PRFIX_VALUE2);
//				if (value1 != null && !value1.trim().equals("")) {
//					String condition = gridService.makeupCondition(opera, fieldname, value1, value2, values, fields);
//					if (!"".equals(condition))
//						conditions.add(condition);
//				}
//			}
//		}
//		int size = conditions.size();
//		if (size > 0) {
//			sb.append(" where ");
//			sb.append(conditions.get(0));
//			for (int i = 1; i < size; i++) {
//				sb.append(" and ");
//				sb.append(conditions.get(i));
//			}
//		}
//		return sb.toString();
//	}
//	
//	
//
//}