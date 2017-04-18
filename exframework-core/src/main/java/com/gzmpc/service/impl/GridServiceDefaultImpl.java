package com.gzmpc.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.grid.tableimpl.TableDataProvider;
import com.gzmpc.metadata.MetaData;
import com.gzmpc.metadata.OperatorPool;
import com.gzmpc.metadata.di.DataItem;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.service.GridService;
import com.gzmpc.sys.SystemParameterService;
import com.gzmpc.utils.Const;
import com.gzmpc.utils.DateUtil;
import com.gzmpc.utils.QueryUtil;

@Service
public class GridServiceDefaultImpl implements GridService {

	private Log log = LogFactory.getLog(TableDataProvider.class.getName());

	@Autowired
	SystemDao systemDao;

	@Autowired
	MetaData metaData;

	@Autowired
	OperatorPool operatorPool;

	@Autowired
	QueryUtil queryUtil;

	@Autowired
	SystemParameterService systemParameterService;
	
	

	@Override
	public List<Map<String, Object>> drawGridTitleInfo(ResultSetMetaData rsmd, String gridcode) throws SQLException {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> rownum = new ConcurrentHashMap<String, Object>();
		rownum.put(FIELDNAME, "rownum_");
		rownum.put(FIELDTYPE, Types.VARCHAR);
		rownum.put(DATATYPE, "string");
		rownum.put(HEADER, "行号");
		result.add(rownum);
		
		int count = rsmd.getColumnCount();
		for (int i = 0; i <= count - 1; i++) {
			Map<String, Object> row = new ConcurrentHashMap<String, Object>();
			String fieldname = rsmd.getColumnLabel(i + 1).toLowerCase();
			row.put(FIELDNAME, fieldname);

			DataItem dt = metaData.findDataItem(gridcode, fieldname.toUpperCase());
			if (dt == null) {
				dt = new DataItem();
				dt.setCode(fieldname.toUpperCase());
				dt.setName(fieldname);
				dt.setDisptypecfg("edit");
			}
			int t = rsmd.getColumnType(i + 1);
			int scale = rsmd.getScale(i + 1);
			int precision = -1;
			String validtype = dt.getValidataTypecfg();
			String precisionStr = dt.getPrecision();
			if (validtype != null && precisionStr != null && !"".equals(precisionStr)
					&& dt.getValidataTypecfg().equals("F")) {
				precision = new Integer(precisionStr);
			}
			row.put(FIELDTYPE, t);
			row.put(HEADER, dt.getName());
			// 要求浮点的要求能实现小数点自动对齐，因此改为字符串，但整型则不需要，可以排序
			if (t == Types.DATE || t == Types.TIME || t == Types.TIMESTAMP) {
				row.put(DATATYPE, "dt");
			} else {
				if (t == Types.INTEGER || (t == Types.NUMERIC && (scale == 0 || precision == 0))) {
					row.put(DATATYPE, "int");
				} else if (t == Types.FLOAT || t == Types.NUMERIC || t == Types.DOUBLE || t == Types.DECIMAL) {
					row.put(DATATYPE, "float");
					if (precision == -1) {
						row.put(PRECISION, scale);
					} else {
						row.put(PRECISION, precision);
					}
				} else {
					if (validtype != null && "I".equals(validtype)) {
						row.put(DATATYPE, "int");
					} else if (validtype != null && "F".equals(validtype)) {
						row.put(DATATYPE, "float");
						if (precision == -1) {
							row.put(PRECISION, scale);
						} else {
							row.put(PRECISION, precision);
						}
					} else {
						row.put(DATATYPE, "string");
					}
				}
				// 看一下是否需要控制下拉框或者复选框
				String disptype = dt.getDisptypecfg();
				if (disptype != null && !"edit".equals(disptype)) {
					if ("querylist".equals(disptype)) {
						Map<String, String> dict = operatorPool.retDtDictionary(dt.getDisptypekey());
						row.put(DATATYPE, "list");
						row.put(DATA, dict);
					} else if ("sqllist".equals(disptype)) {
						Map<String, String> dict = queryUtil.getDict(dt.getQuerySql(), dt.getDisptypekey());
						row.put(DATATYPE, "list");
						row.put(DATA, dict);
					} else if ("checkbox".equals(disptype)) {
						row.put(DATATYPE, "checkbox");
					} else if ("checksqllist".equals(disptype)) {
						Map<String, String> dict = queryUtil.getDict(dt.getQuerySql(), dt.getDisptypekey());
						row.put(DATATYPE, "checklist");
						row.put(DATA, dict);
					}

				}
			}
			result.add(row);
		}
		return result;
	}

	@Override
	public String makeupCondition(String oprea, String fieldname, String value1, String value2, List<Object> conditions,
			List<Map<String, Object>> fields) {
		if (oprea.equals(Const.OPER_STR)) {
			return " (" + value1 + ") ";
		}

		Map<String, Object> field = null;
		for( int i=0,j=fields.size();i<j;i++) {
			Map<String,Object> row = fields.get(i);
			String name = (String)row.get(FIELDNAME);
			if(fieldname.equals(name)) {
				field = row;
				break;
			}
		}
		if ( field != null ) {
			int fieldType = (int)field.get(FIELDTYPE);
	
			if ("rownum_".equals(fieldname)) {
				return "";
			}
			boolean isDdateType = false; // 数据类型为时间类型的
	
			if (fieldType == Types.NULL) { // 非查询条件
				return fieldname + oprea + value1;
			}
			if ((fieldType == Types.DATE) || (fieldType == Types.TIME) || (fieldType == Types.TIMESTAMP)) {
				isDdateType = true;
			}
	
			StringBuffer condition = new StringBuffer();
	
			if (oprea.equals(Const.OPER_EQUAL)) { // 等于号
				condition.append(fieldname);
				if (!isDdateType) {
					condition.append(" = ? ");
					conditions.add(value1);
				} else {
					condition.append(" between ? and ?");
					Date value = DateUtil.parse(value1.trim());
					java.sql.Date dt1 = new java.sql.Date(value.getTime());
					java.sql.Date dt2 = new java.sql.Date(DateUtils.addDays(value, 1).getTime());
					conditions.add(dt1);
					conditions.add(dt2);
				}
			} else if (oprea.equals(Const.OPER_MATCHING)) { // 匹配
				condition.append(fieldname);
				condition.append(" like ?");
				if (!value1.endsWith(".")) // 如果以.号为结束的表示模糊查询时后面没有%号
					value1 = value1 + "%";
				else
					value1 = value1.substring(0, value1.length() - 1);
				conditions.add(value1);
			} else if (oprea.equals(Const.OPER_GREATER_EQUAL)) { // 大于等于
				condition.append(fieldname);
				if (isDdateType) {
					condition.append(" >= ?");
					Date value = DateUtil.parse(value1);
					java.sql.Date dt1 = new java.sql.Date(value.getTime());
					conditions.add(dt1);
				} else {
					condition.append(" >=?");
					conditions.add(value1);
				}
			} else if (oprea.equals(Const.OPER_LESS_EQUAL)) { // 小于等于
				condition.append(fieldname);
				if (isDdateType) {
					condition.append(" < ?");
					Date value = DateUtil.parse(value1);
					java.sql.Date dt1 = new java.sql.Date(DateUtils.addDays(value, 1).getTime());
					conditions.add(dt1);
				} else {
					condition.append(" <=?");
					conditions.add(value1);
				}
			} else if (oprea.equals(Const.OPER_IN)) { // in 语句
				condition.append(fieldname);
				if (value1.length() > 0) {
					String[] arr_str = value1.split(",");
					int length = arr_str.length;
					condition.append(" in (");
					for (int i = 0; i < length; i++) {
						if (i == length - 1) {
							condition.append(" ?");
						} else {
							condition.append(" ?,");
						}
						conditions.add(arr_str[i]);
					}
					condition.append(" )");
				}
			} else if (oprea.equals(Const.OPER_GREATER)) { // 大于号
				condition.append(fieldname);
				if (isDdateType) {
					condition.append(" >= ?"); // 取下一天
					Date value = DateUtil.parse(value1);
					java.sql.Date dt1 = new java.sql.Date(DateUtils.addDays(value, 1).getTime());
					conditions.add(dt1);
	
				} else {
					condition.append(" > ?");
					conditions.add(value1);
				}
			} else if (oprea.equals(Const.OPER_LESS)) { // 小于号
				condition.append(fieldname);
				if (isDdateType) {
					condition.append(" < ?");
					Date value = DateUtil.parse(value1);
					java.sql.Date dt1 = new java.sql.Date(value.getTime());
					conditions.add(dt1);
				} else {
					condition.append(" < ?");
					conditions.add(value1);
				}
			} else if (oprea.equals(Const.OPER_BETWEEN)) { // between
				condition.append(fieldname);
				if (isDdateType) {
					condition.append(" between ? and ?");
					Date value = DateUtil.parse(value1.trim());
					Date v = DateUtil.parse(value2.trim());
					java.sql.Timestamp dt1 = new java.sql.Timestamp(value.getTime());
					java.sql.Timestamp dt2 = new java.sql.Timestamp(v.getTime());
	
					conditions.add(dt1);
					conditions.add(dt2);
				} else {
					condition.append(" between ? and ?");
					conditions.add(value1);
					conditions.add(value2); 
				}
			} else if (oprea.equals(Const.OPER_NOT_EQUAL)) { // 不等于
				condition.append(fieldname);
				if (!isDdateType) {
					condition.append(" != ?");
					conditions.add(value1);
				} else {
					condition.append(" != ?");
					Date value = DateUtil.parse(value1);
					java.sql.Date dt1 = new java.sql.Date(value.getTime());
					conditions.add(dt1);
				}
			}
			return condition.toString();
		}
		else {
			return "";
		}
	}

	@Override
	public void filterGridColumns(String gridcode, Account account, List<Map<String, Object>> rows) {
		List<String> gridroleColumns = getRoleColumnsParams(gridcode, account);
		// 删除 过滤字段
		for (int i = rows.size() - 1; i > -1; i--) {
			Map<String, Object> row = rows.get(i);
			String field = (String) row.get("fieldname");
			if (gridroleColumns.contains(field)) {
				rows.remove(i);
			}
		}

		// 参数格式为(列名,)多个以逗号隔开
		String gridConfigInfo = systemParameterService.getAccoutParameter(account.getAccountId(),
				gridcode + "-gridcolsconfig");
		if (gridConfigInfo != null && !"".equals(gridConfigInfo)) {
			
			String[] existClos = gridConfigInfo.split(",");
			int length = existClos.length;
			for (int i = 0; i < length; i++) {
				String colname = existClos[i];
				
				for(int x=0,y=rows.size();x<y;x++) {
					Map<String, Object> row = rows.get(x);
					if(colname.equals(row.get(FIELDNAME))){
						row.put(HIDDEN, false);
						row.put(SORT_KEY,i);
						break;
					}
				}
			}
			for( int i=0,j= rows.size();i<j;i++) {// 其它数据库里没有保存的，可能是表或视图新增的
				Map<String, Object> row = rows.get(i);
				if(!row.containsKey(SORT_KEY)) {
					row.put(SORT_KEY, existClos.length+i);
					row.put(HIDDEN, true);
				}
			}
			
			final class ColumnComparator implements Comparator<Map<String, Object>> {
				@Override
				public int compare(Map<String, Object> o1, Map<String, Object> o2) {
					int sort1 = (int)o1.get(SORT_KEY);
					int sort2 = (int)o2.get(SORT_KEY);
					return Integer.compare(sort1, sort2);
				}
				
			}
			Collections.sort(rows, new ColumnComparator());
			for( int i=0,j= rows.size();i<j;i++) {// 去除临时变量
				rows.get(i).remove(SORT_KEY);
			}
		}
	}

	private List<String> getRoleColumnsParams(String gridcode, Account account) {
		List<String> result = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = systemDao.getUnautocommitConnection();
			pst = con.prepareStatement(" select distinct gridcolumncode from sys_accountrole v, sys_rolecolumns r where v.role_id = r.role_id and gridcode = ? and account_id = ? ");
			pst.setString(1, gridcode);
			pst.setString(2, account.getAccountId());
			rs = pst.executeQuery();
			while (rs.next()) {
				result.add(rs.getString(1));
			}
			rs.close();
			pst.close();
			con.commit();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
		} finally {
			DbUtils.closeQuietly(con, pst, rs);
		}
		return result;
	}

	@Override
	public Map<String,Object> outputOneRow(ResultSet rs, List<Map<String, Object>> headers) throws SQLException {
		Map<String,Object> result = new ConcurrentHashMap<String,Object>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		for ( int i = 1 ; i <= count ; i++) {
			String column = rsmd.getColumnLabel(i);
			String value = rs.getString(column);
			if (value == null) {
				value = "";
			}
			String render = value;
			String columnname = column.toLowerCase();
			
			Map<String, Object> header = null;
			for ( int x = 0, y = headers.size(); x < y; x++) {
				Map<String, Object> row = headers.get(x);
				String fieldname = (String)row.get(FIELDNAME);
				if ( columnname.equals(fieldname)) {
					header = row;
					break;
				}
			}
			if ( header != null ) {
				String type = (String)header.get(DATATYPE);
				if ("float".equals(type)) {
					BigDecimal d = new BigDecimal(value);
					int precision = (int)header.get(PRECISION);
					d.setScale(precision, RoundingMode.HALF_UP);
					value = d.toString();
					render = value;
				}
				else if ("dt".equals(type)) {
					if (value.endsWith("00:00:00") || value.endsWith("00:00:00.0")) {
						value = value.substring(0, 10);
					}
					int index = value.indexOf("."); //删除毫秒
					if (index > 0) {
						value = value.substring(0, index);
					}
					render = value;
				}
				else if ( "checkbox".equals(type)) {
					if( "1".equals(value) || "true".equals(value)) {
						render = "是";
					}
					else if ("0".equals(value) || "false".equals(value)) {
						render = "否";
					}
					else {
						render = "[格式错误]";
					}
				}
				else if ( "list".equals(type)) {
					@SuppressWarnings("unchecked")
					Map<String, String> dict = (Map<String, String>) header.get(DATA);
					render = dict.get(value);
				}
				else if ("checksqllist".equals(type)) {
					//todo
				}
			}
			Map<String,Object> v = new ConcurrentHashMap<String,Object>();
			v.put(VALUE, value);
			v.put(RENDER, render);
			result.put(columnname, v);
		}
		return result;
	}

	@Override
	public void searchDBLinkFromSQL(List<String> orglist, String sql) {
		if (orglist != null && sql != null) {
			Pattern p = Pattern.compile("@[^@,\\(\\s\\t\\n]*"); // 搜索dblink @关键字
			Matcher m = p.matcher(sql);
			while (m.find()) {
				String s = m.group().substring(1); // 把第1位@删除
				if (!orglist.contains(s)) {
					orglist.add(s);
				}
			}
		}
		
	}

}
