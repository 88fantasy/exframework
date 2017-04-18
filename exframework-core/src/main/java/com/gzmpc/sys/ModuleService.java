package com.gzmpc.sys;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.build.Buildable;
import com.gzmpc.dao.SystemDao;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.metadata.sys.Module;
import com.gzmpc.stereotype.BuildComponent;

import java.sql.Connection;
import org.apache.commons.dbutils.DbUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * <p>
 * Title:
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
 * @author not attributable
 * @version 1.0
 */

@Service
@BuildComponent
public class ModuleService implements Buildable {
	private Log log = LogFactory.getLog(ModuleService.class.getName());
	private Map<String,Module> allModules = new ConcurrentHashMap<String,Module>(); // key 为moduleId,value为Module对象
	
	@Autowired
	SystemDao systemDao;

	public Map<String, Module> getAllModules() {
		return allModules;
	}

	public Module getModule(String moduleId) {
		return allModules.get(moduleId);
	}

	private synchronized void initAllModules() {
		allModules.clear(); 
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = systemDao.getUnautocommitConnection();
			pstm = con.prepareStatement("select module_id from sys_module where pmodule_id is null  order by module_order ");
			rs = pstm.executeQuery();
			List<String> ids = new ArrayList<String>();
			while(rs.next()) {
				ids.add(rs.getString(1));
			}
			rs.close();
			pstm.close();
			for(int i=0,j=ids.size();i<j;i++) {
				Module m = loadModuleConnection(con,ids.get(i));
				putModuleToMap(m);
			}
			
			con.commit();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				if(con != null) {
					con.rollback();
				}
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
		} finally {
			DbUtils.closeQuietly(con);
		}
	}

	/**
	 * @param con
	 * @param moduleId
	 * @return Module 
	 * @exception
	 */
	private Module loadModuleConnection(Connection con, String moduleId) {
		PreparedStatement pstm = null,pstm2 = null;
		ResultSet rs = null,rs2 = null;
		Module o = null;
		try {
			pstm = con.prepareStatement("select t.* from sys_module t where t.module_id = ?");
			pstm.setString(1, moduleId);
			rs = pstm.executeQuery();
			if (rs.next()) {
				o = new Module();

				String pmoduleid = rs.getString("pmodule_id");
				
				o.setModuleId(rs.getString("module_id"));
				o.setModuleOpcode(rs.getString("module_opcode"));
				o.setModuleName(rs.getString("module_name"));				
				o.setPmoduleId(pmoduleid);
				o.setModuleEntry(rs.getString("module_entry"));
				o.setModuleType(rs.getLong("module_type"));
				o.setValid(rs.getInt("module_valid")==1);
				o.setNotdisplay(rs.getInt("notdisplay")==1);
				o.setModuleMemo(rs.getString("module_memo"));
				o.setModuleOrder(rs.getLong("module_order"));
				
				if(pmoduleid != null) {
					List<String> ids = new ArrayList<String>();
					pstm2 = con.prepareStatement("select module_id from sys_module  where pmodule_id = ? order by module_order ");
					pstm2.setString(1, pmoduleid);
					rs2 = pstm2.executeQuery();
					while(rs2.next()) {
						ids.add(rs2.getString(1));			
					}
					rs2.close();
					pstm2.close();
					if(ids.size()>0) {
						List<Module> children = new ArrayList<Module>();
						for(int i=0,j=ids.size();i<j;i++) {
							Module m = loadModuleConnection(con,ids.get(i));
							if(m!= null) {
								children.add(m);
							}
						}
						o.setChildren(children);
					}
				}
			}
			
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			DbUtils.closeQuietly(null, pstm2, rs2);
			DbUtils.closeQuietly(null, pstm, rs);
		}
		return o;
	}
	
	private void putModuleToMap(Module m) {
		String moduleid = m.getModuleId();
		allModules.put(moduleid, m);
		List<Module> children = m.getChildren();
		if( children != null && children.size()>0) {
			for(int i=0,j=children.size();i<j;i++) {
				Module c = children.get(i);
				putModuleToMap(c);
			}
		}
	}

	public List<Module> getShortCutFunctions(Account account) {
		List<Module> list = new ArrayList<Module>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = systemDao.getUnautocommitConnection();
			StringBuffer sb = new StringBuffer();
			sb.append(" select m.module_id,m.module_name,m.module_entry, ");
			sb.append(" m.module_pic_big,m.module_pic_small,m.module_contextpath ");
			sb.append(" from sys_shortcutmenu t,sys_module m where m.module_id = t.module_id ");
			sb.append(" and t.account_id = ? and m.module_valid = 1 ");
			pstm = con.prepareStatement(sb.toString());
			pstm.setString(1, account.getAccountId());
			log.debug(sb.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Module o = new Module();
				o.setModuleId(rs.getString("module_id"));
				o.setModuleName(rs.getString("module_name"));
				o.setModuleEntry(rs.getString("module_entry"));
				list.add(o);
			}
			con.commit();

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				con.rollback();
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			DbUtils.closeQuietly(con, pstm, rs);
		}

		return list;
	}

	@Override
	public void build() {
		initAllModules();
	}
}