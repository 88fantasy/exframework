package com.gzmpc.metadata.di.dt;

import com.gzmpc.metadata.attribute.Attribute;
import com.gzmpc.metadata.di.DataItem;
import com.gzmpc.metadata.form.Form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import com.gzmpc.utils.QuerySupport;
import com.gzmpc.utils.QueryUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 读取数据字典，从而画出checkboxlist控件的算法
 * <p>
 * Title: 算法： 数据字典必须要求配置有disptypekey（由DataItem控制） 以下为Attribute formAttr对象控制
 * DataItem.getDisptypekey * 1。不管是只读还是可读写，都需要赋予一个样式给它。
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author rwe
 * @version 1.0
 */
@Component("DTCheckSqlList")
public class DTCheckSqlList extends DispType {

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
		result.put("type", "checklist");
		result.put("list", dict);
		return result;
	}

}