package com.gzmpc.metadata;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.metadata.queryparam.QueryParamAdapter;
import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.queryparamitem.QueryParamBase;
import com.gzmpc.metadata.toolbar.ToolButton;
import com.gzmpc.metadata.toolbar.ButtonAdapter;
import com.gzmpc.metadata.toolbar.ButtonContentBase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.dbutils.DbUtils;
import java.sql.*;

@Component
public class OperatorPool {
	private Log log = LogFactory.getLog(OperatorPool.class.getName());
	private Map<ToolButton, ButtonContentBase> pool = new ConcurrentHashMap<ToolButton, ButtonContentBase>(); // 缓存池

	@Autowired
	SystemDao systemDao;
	
	@Autowired
	ButtonAdapter buttonAdapter;
	
	@Autowired
	QueryParamAdapter queryParamAdapter;

	public ButtonContentBase retButtonBase(ToolButton tb) {
		if (!pool.containsKey(tb)) {
			synchronized (pool) {
				ButtonContentBase result = buttonAdapter.genContent(tb);
				pool.put(tb, result);
			}
		}
		return pool.get(tb);
	}

	private Map<QueryParamItem,QueryParamBase> qpipool = new ConcurrentHashMap<QueryParamItem,QueryParamBase>();

	/**
	 * 缓存 queryparamitem
	 * 
	 */
	public QueryParamBase retQueryParam(QueryParamItem qpi) {
		if (!qpipool.containsKey(qpi)) {
			synchronized (qpipool) {
				QueryParamBase result = queryParamAdapter.retBase(qpi);
				qpipool.put(qpi, result);
			}
		}
		return qpipool.get(qpi);
	}

	public Map<String,Map<String,String>> di = new ConcurrentHashMap<String,Map<String,String>>(); // 缓存数字字典


	public Map<String,String> retDtDictionary(String key) {
		if (!di.containsKey(key)) {
			synchronized (di) {
				Map<String,String> value = putInfoBuffer(key);
				di.put(key, value);
			}
		}
		return di.get(key);
	}
	
	public String retDtDictionaryValue(String key, String value) {
		return retDtDictionary(key).get(value);
	}

	private Map<String,String> putInfoBuffer(String key) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String,String> itemsMap = new ConcurrentHashMap<String,String>();
		try {
			con = systemDao.getUnautocommitConnection();
			pst = con.prepareStatement("select dd_id, dd_value from sys_datadictionary where dd_key = ? order by dd_id asc");
			pst.setString(1, key);
			rs = pst.executeQuery();

			while (rs.next()) {
				String keyValue = rs.getString("dd_id");
				String dispColValue = rs.getString("dd_value");
				if (dispColValue == null) {
					continue;
				}
				itemsMap.put(keyValue, dispColValue);
			}
			rs.close();
			pst.close();
			con.commit();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				if ( con != null ){
					con.rollback();
				}
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
		} finally {
			DbUtils.closeQuietly(con, pst, rs);
		}
		return itemsMap;
	}
}