package com.gzmpc.wf.meta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.support.common.build.Buildable;
import com.gzmpc.wf.pojo.WfProcesstypeId;

@Repository("wfMetaData")
public class WfMetaData implements Buildable {

	static private Log log = LogFactory.getLog(WfMetaData.class.getName());

	private Hashtable<String, WfProcesstypeId> wfprocesstypeIds;

	@Autowired
	SystemDao systemDao;

	public WfProcesstypeId findWfProcesstypeIdByCode(String code) {
		if (wfprocesstypeIds == null) {
			return null;
		}
		return (WfProcesstypeId) wfprocesstypeIds.get(code);
	}

	@Override
	public void build(ApplicationContext ac) {
		log.info("工作流流程配置初始化开始");

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuffer hhsl_select = new StringBuffer();
		try {
			con = systemDao.getDataSource().getConnection();
			con.setAutoCommit(false);
			hhsl_select.append(" select * from wf_processtype");
			pstm = con.prepareStatement(hhsl_select.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Long wfpid = rs.getLong("wfpid");
				String processtype = rs.getString("processtype");
				String processname = rs.getString("processname");
				String moduleid = rs.getString("moduleid");
				String actiondo = rs.getString("actiondo");
				String formcode = rs.getString("formcode");
				String tablename = rs.getString("tablename");
				String pkname = rs.getString("pkname");
				String instance = rs.getString("instance");
				short dtltype = rs.getShort("dtltype");
				if (dtltype == 0) {
					dtltype = 1;
				}
				String dtltable = rs.getString("dtltable");
				String pinstancename = rs.getString("pinstancename");
				String docview = null;
				String dbname = null;
				try {// 有些没有加字段的就忽略
					docview = rs.getString("docview");
					dbname = rs.getString("dbname");
				} catch (Exception e) {

				}

				WfProcesstypeId wfp = new WfProcesstypeId(wfpid, processtype, processname, moduleid, actiondo, formcode,
						tablename, pkname, instance, dtltype, dtltable, pinstancename, docview, dbname);

				wfprocesstypeIds.put(processtype, wfp);

			}
			rs.close();
			pstm.close();
			
			log.info("工作流流程配置读取结束");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);

			log.error("工作流流程配置读取失败,请检查配置");
		} finally {
			DbUtils.closeQuietly(con, pstm, rs);
		}
	}
}
