package com.gzmpc.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 管理DBNAME类
 * 
 */
public class DbnameUtil {
	private Log log = LogFactory.getLog(DbnameUtil.class);

//	public String retDBname(String stageid, String stagetype) {
//		if (dbnames == null) {
//			synchronized (DBnameUtil.class) {
//				if (dbnames == null) {
//					dbnames = new HashMap();
//					Connection con = null;
//					Statement stmt = null;
//					ResultSet rs = null;
//					try {
//						con = QuerySupport.retConnection();
//						stmt = con.createStatement();
//						String sql = "select stageid,stagetype,dbname  from DW_STAGE_DBNAME ";
//						rs = stmt.executeQuery(sql);
//						while (rs.next()) {
//
//							String stid = rs.getString("stageid");
//							String sttype = rs.getString("stagetype");
//							String dbname = rs.getString("dbname");
//
//							dbnames.put(sttype + stid, dbname);
//
//						}
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						log.error(e.getMessage(), e);
//					} finally {
//						DbUtils.closeQuietly(con, stmt, rs);
//					}
//				}
//
//			}
//
//		}
//
//		return (String) dbnames.get(stagetype + stageid);
//	}

}
