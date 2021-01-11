package com.gzmpc.service.sys;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gzmpc.portal.metadata.grid.Grid;
import com.gzmpc.portal.metadata.sys.Account;


public interface GridService {

	public static final String SORT_KEY = "_const_sort";
	public static final String FIELDNAME = "_const_field";
	public static final String FIELDTYPE = "_const_fieldtype";
	public static final String HEADER = "_const_header";
	public static final String DATATYPE = "_const_type";
	public static final String PRECISION = "_const_precision";
	public static final String DATA = "_const_data";
	public static final String VALUE = "_const_value";
	public static final String RENDER = "_const_render";
	public static final String HIDDEN = "_const_hidden";
	
	public static final String COLCONFIG = "_colsconfig";
	
	
	public Grid findByKey(String key);
	/*
	 * 规划表头
	 */
	public List<Map<String, Object>> drawGridTitleInfo(ResultSetMetaData rsmd, String gridcode) throws SQLException;

	
	/*
	 * 拼装查询条件
	 */
	public String makeupCondition(String oprea, String fieldName, String value1, String value2, List<Object> conditions,
			List<Map<String, Object>> fields);
	
	/* 
	 * 删除 过滤字段
	 */
	public void filterGridColumns(String gridcode, Account account,List<Map<String, Object>> rows);
	
	
	public  Map<String,Object> outputOneRow(ResultSet rs, List<Map<String, Object>> headers) throws SQLException;
}
