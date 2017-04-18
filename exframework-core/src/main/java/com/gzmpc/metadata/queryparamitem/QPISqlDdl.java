package com.gzmpc.metadata.queryparamitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.metadata.MetaData;
import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.utils.QuerySupport;
import com.gzmpc.utils.QueryUtil;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPISqlDdl extends QPIDdl {
	static private Log log = LogFactory.getLog(QPISqlDdl.class.getName());
	
	@Autowired
	MetaData metaData;
	
	@Autowired
	QueryUtil queryUtil;
	
	@Autowired
	QuerySupport querySupport;


	@Override
	public Map<String, String> getDdl(QueryParamItem qpi) {
		String sql = qpi.getQueryoper();
		String dbbean = qpi.getDbbean();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String,String> ddl = new ConcurrentHashMap<String,String>();
		try {
			con = querySupport.retConnection(dbbean);
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				String key = rs.getString(1);
				String value = rs.getString(2);
				ddl.put(key, value);
			}
			rs.close();
			pst.close();
			con.commit();
		} catch (SQLException e) {
			log.error("获取下拉字典出现错误:"+e.getMessage(),e);
			
			try{
				if( con != null && !con.isClosed()) {
					con.rollback();
				}
			} catch ( SQLException sqle2) {
				log.error("回滚出现错误:"+sqle2.getMessage(),sqle2);
			}
			
		} finally {
			DbUtils.closeQuietly(con, pst, rs);
		}
		return ddl;
	}
	
}
