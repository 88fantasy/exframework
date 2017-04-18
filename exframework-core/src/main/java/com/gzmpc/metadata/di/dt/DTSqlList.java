package com.gzmpc.metadata.di.dt;

import com.gzmpc.metadata.attribute.Attribute;
import com.gzmpc.metadata.di.DataItem;
import com.gzmpc.metadata.form.Form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.gzmpc.utils.QuerySupport;
import com.gzmpc.utils.QueryUtil;

import java.sql.*;

/**
 * 
 * <p>
 * Title:读用户自己写的sql语句
 * </p>
 * <p>
 * Description:通过用户自己写一个sql语句对数据库进行操作，在前台画出下拉框
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

@Component("DTSqlList")
public class DTSqlList extends DispType {

	@Autowired
	QuerySupport querySupport;
	
	@Autowired
	QueryUtil queryUtil;

	public Map<String, Object> retDisplay(Form form, Attribute formAttr) {
		Map<String, Object> result = retDisplayCommon(form, formAttr);
		DataItem di = formAttr.getDi();
		String sql = di.getQuerySql();
		if (sql == null || "".equals(sql)) {
			throw new IllegalArgumentException(di.getCode() + "没有配置sql,请先配置");
		}
		String dbname = di.getDisptypekey();// 利用来存dbname

		Map<String, String> dict = new ConcurrentHashMap<String, String>();
		try {
			dict = queryUtil.getDict(sql, dbname);
		} catch (SQLException sqle) {
			throw new IllegalArgumentException("sql出现错误:"+sql);
		}
		result.put("type", "list");
		result.put("list", dict);
		return result;
	}

}