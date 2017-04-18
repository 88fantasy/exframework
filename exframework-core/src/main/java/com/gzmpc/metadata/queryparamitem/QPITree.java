package com.gzmpc.metadata.queryparamitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.mapper.LowerColumnMapRowMapper;
import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.utils.Const;
import com.gzmpc.utils.QuerySupport;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPITree extends QueryParamBase {
	static private Log log = LogFactory.getLog(QPITree.class.getName());
	
	@Autowired
	QuerySupport querySupport;

	@Override
	public void initBase(Account account,QueryParamItem qpi) {
		initBaseCommon(account,qpi);
		this.setOper(Const.OPER_IN);
		String sql = qpi.getQueryoper();
		String dbbean = qpi.getDbbean();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Map<String,Map<String,Object>> nodes = new ConcurrentHashMap<String,Map<String,Object>>();
		try {
			con = querySupport.retConnection(dbbean);
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colcount = rsmd.getColumnCount();
			if(colcount < 2) {
				throw new SQLException("sql字段数不足,最少要求id和pid:"+sql);
			}
			int rowcount = 0;
			while(rs.next()) {
				String id = rs.getString(1);
				String pid = rs.getString(2);
				String name = colcount>2?rs.getString(3):id;

				Map<String,Object> row = new LowerColumnMapRowMapper().mapRow(rs, rowcount++);
				row.put("nodeid",id);
				row.put("parentid", pid);
				row.put("name", name);
				
				nodes.put(id, row);
			}
			rs.close();
			pst.close();
			con.commit();
		} catch (SQLException e) {
			log.error("获取树型字段出现错误:"+e.getMessage(),e);
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
		
		Map<String,Object> root = new ConcurrentHashMap<String,Object>();
		root.put("nodeid","root");
		root.put("parentid", null);
		root.put("name", "根节点");
		List<Map<String,Object>> rootchild = new ArrayList<Map<String,Object>>();
		
		for(String id : nodes.keySet()) {
			Map<String, Object> row = nodes.get(id);
			String pid = (String)row.get("parentid");
			if(nodes.containsKey(pid)) {
				Map<String, Object> parent = nodes.get(pid);
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> children = (List<Map<String,Object>>)parent.get("children");
				if(children != null) {
					children.add(row);
				}
				else {
					List<Map<String,Object>> child = new ArrayList<Map<String,Object>>();
					child.add(row);
					parent.put("children", child);
				}
			}
			else {
				rootchild.add(row);
			}
		}
		root.put("children", rootchild);
		
		this.setOperdata(root);
	}

}
