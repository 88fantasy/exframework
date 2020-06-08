package com.gzmpc.wf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Iterator;
import java.util.List;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.support.common.util.SpringContextUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.gzmpc.utils.Const;
import com.gzmpc.utils.DateUtil;
import com.gzmpc.wf.meta.WfMetaData;
import com.gzmpc.wf.pojo.WfAttributeinfo;
import com.gzmpc.wf.pojo.WfProcessinstance;
import com.gzmpc.wf.pojo.WfProcesstypeId;
import com.gzmpc.wf.service.WfAccountService;

import bsh.EvalError;
import bsh.Interpreter;

import org.apache.commons.dbutils.DbUtils;
import java.sql.SQLException;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * Title:工作流服务后台类,包括新增实例
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

@Service
public class WfService {
	private Log log = LogFactory.getLog(WfService.class.getName());

	@Autowired
	private WfMetaData wfMetaData;

	@Autowired
	WfAccountService accountService;
	
	@Autowired
	SystemDao systemDao;
	
	/**
	 * 需要锁死，但现在还没有锁死
	 * 
	 * @param session
	 * @param orgobjid
	 * @param pintype
	 * @param result
	 * @return
	 */
	public  Long genProcessInstance(Connection con, Long orgobjid,
			String pintype, String actiondo,String creman, String cremanname, List<Map<String,Object>> result)
			throws SQLException {
		WfProcesstypeId wfp = wfMetaData.findWfProcesstypeIdByCode(pintype);
		StringBuffer hhsl_insertProc = new StringBuffer();
		hhsl_insertProc.append(" insert into obe_processinstance(processinstanceid, ");
		hhsl_insertProc.append(" sourceid,pintype,workflowstatus,startdate,completeddate,actiondo,cremanname,creman,dtltype) ");
		hhsl_insertProc.append(" values(?,?,?,?,?,?,?,?,?,?)");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select obe_process_seq.nextval from dual");
			rs = pst.executeQuery();
			rs.next();
			Long procid = rs.getLong(1);
			rs.close();
			pst.close();
			
			pst = con.prepareStatement(hhsl_insertProc.toString());
			pst.setLong(1, procid.longValue());
			pst.setLong(2, orgobjid.longValue());
			pst.setString(3, pintype);
			pst.setShort(4, Const.WORKFLOW_STATUS_NOCHECK);
			pst.setTimestamp(5, new Timestamp(new Date().getTime()));
			pst.setDate(6, null);
			pst.setString(7, actiondo);
			pst.setString(8, cremanname);
			pst.setString(9, creman);
			pst.setShort(10, wfp.getDtltype());
			pst.executeUpdate();
			pst.close();
			for (int i = 0; i < result.size(); i++) {
				Map<String,Object> map = result.get(i);
				String checkStr = (String) map.get("checkStr");
				String attractiondo = (String) map.get("actiondo");
				String wfroleid = (String) map.get("wfroleid");
				String roleId = null;
				String wfcheckid = (String) map.get("wfcheckid");
				String filtertype = (String) map.get("filtertype");
				String countersign = (String) map.get("countersign");
				short ftype = 1;
				if(!"".equals(filtertype)&&filtertype!=null){
					ftype = new Short(filtertype);
				}
				short cs = 0;
				if(!"".equals(countersign)&&countersign!=null){
					cs = new Short(countersign);
				}
				String finishlimit = (String) map.get("finishlimit");
				int limit = 0;
				if(!"".equals(finishlimit)&&finishlimit!=null){
					limit = new Integer(finishlimit);
				}
				// 这里的wfroleid是账号
				// 最终是以账号隔开。格式为账号1,账号2；
				WfAttributeinfo attr = createWfAttribute(con, procid, checkStr, wfroleid, new Long(i), orgobjid, pintype,
						roleId, wfcheckid,ftype,limit,attractiondo,cs);
				if (i == 0) {
					createNewWorkItem(con, attr);
				}
			}
			return procid;
		}
		finally {
			DbUtils.closeQuietly(pst);
		}
	}
	
	public  void genNextNeedCheckAttribute( Long procid,Connection con,List<Map<String,Object>> result)throws SQLException{
		WfProcessinstance wp =  findProcessInstance(con,procid);
		int j=0;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			pst = con.prepareStatement("select nvl(max(corder)+1,0) corder from obe_attributeinfo where processinstanceid=");
			pst.setLong(1, procid);
			rs = pst.executeQuery();
			if(rs.next()) {
				j = rs.getInt(1);
			}
			rs.close();
			pst.close();
			
		} catch (SQLException e) {
			throw e;
		} finally {
			DbUtils.closeQuietly(null, pst, rs);
		}
		Long orgobjid = wp.getSourceid();
		String pintype = wp.getPintype();
		for (int i = 0; i < result.size(); i++) {
			Map<String,Object> map = result.get(i);
			String checkStr = (String) map.get("checkStr");
			String attractiondo = (String) map.get("actiondo");
			String wfroleid = (String) map.get("wfroleid");
			String roleId = null;
			String wfcheckid = (String) map.get("wfcheckid");
			String filtertype = (String) map.get("filtertype");
			String countersign = (String) map.get("countersign");
			short ftype = 1;
			if(!"".equals(filtertype)&&filtertype!=null){
				ftype = new Short(filtertype);
			}
			short cs = 0;
			if(!"".equals(countersign)&&countersign!=null){
				cs = new Short(countersign);
			}
			String finishlimit = (String) map.get("finishlimit");
			int limit = 0;
			if(!"".equals(finishlimit)&&finishlimit!=null){
				limit = new Integer(finishlimit);
			}
			// 这里的wfroleid是账号
			// 最终是以账号隔开。格式为账号1,账号2；
			createWfAttribute(con, procid, checkStr, wfroleid, new Long(i)+j, orgobjid, pintype,
					roleId, wfcheckid,ftype,limit,attractiondo,cs);
		}
	}
	
	
	public  void genCurNeedCheckWork(HttpServletRequest request, String procid) {
		// synchronized (WfServiceBA.class) {//异步锁死,避免产生同一个流程ID,这样的话就
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = systemDao.getUnautocommitConnection();
			WfAttributeinfo attr = findCurNeedCheckAttr(con, procid);
			createNewWorkItem(con, attr);
			con.commit();
		}catch(SQLException e){
			log.error(e.getMessage(),e);
			if(con!=null){
				try{
					con.rollback();
				}catch(Exception e2){
					log.error(e2.getMessage(),e2);
				}
			}
			throw new RuntimeException(e);
		}finally{
			DbUtils.closeQuietly(con,pst,null);
		}
	}

	public  WfAttributeinfo findCurNeedCheckAttr(Connection con,
			String processInstanceid) throws SQLException {
		String sql = "select * from obe_attributeinfo a where not exists (select 1 from obe_processinstance b where b.processinstanceid = a.processinstanceid and b.workflowstatus = "+Const.WORKFLOW_STATUS_STOP+" ) and a.processinstanceid = ? and a.state = ? order by a.corder ";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, processInstanceid);
			pst.setShort(2, Const.OBE_STATE_INIT);
			rs = pst.executeQuery();
			if (rs.next()) {
				WfAttributeinfo attr = new WfAttributeinfo();
				attr.setObeattributeid(new Long(rs.getLong("obeattributeid")));
				attr.setCheckinfo(rs.getString("checkinfo"));
				attr.setProcessinstanceid(new Long(processInstanceid));
				attr.setCheckman(rs.getString("checkman"));
				attr.setCorder(new Long(rs.getLong("corder")));
				attr.setState(new Short(rs.getShort("state")));
				attr.setConfirmmanname(rs.getString("confirmmanname"));
				attr.setWorkflowstatus(new Short(rs.getShort("workflowstatus")));
				attr.setCheckresult(rs.getString("checkresult"));
				attr.setSourceid(new Long(rs.getLong("sourceid")));
				attr.setPintype(rs.getString("pintype"));
				attr.setWfcheckid(rs.getLong("wfcheckid"));
				attr.setFiltertype(rs.getShort("filtertype"));
				if(rs.getDate("limitdate")!=null)
					attr.setLimitdate(new java.util.Date(rs.getDate("limitdate").getTime()));
				attr.setMemo(rs.getString("memo"));
				attr.setActiondo(rs.getString("actiondo"));
				attr.setCountersign(rs.getShort("countersign"));
				return attr;
			}
		} finally {
			DbUtils.closeQuietly(null, pst, rs);
		}
		return null;
	}

	public  WfAttributeinfo findAttribute(Connection con, Long attrid)
			throws SQLException {
		WfAttributeinfo attr = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select * from obe_attributeinfo a where obeattributeid = ? ");
			pst.setLong(1, attrid.longValue());
			rs = pst.executeQuery();
			if (rs.next()) {
				attr = new WfAttributeinfo();
				attr.setObeattributeid(new Long(rs.getLong("obeattributeid")));
				attr.setCheckinfo(rs.getString("checkinfo"));
				attr.setProcessinstanceid(new Long(rs.getLong("processinstanceid")));
				attr.setCheckman(rs.getString("checkman"));
				attr.setCorder(new Long(rs.getLong("corder")));
				attr.setState(new Short(rs.getShort("state")));
				attr.setConfirmmanname(rs.getString("confirmmanname"));
				attr.setWorkflowstatus(new Short(rs.getShort("workflowstatus")));
				attr.setCheckresult(rs.getString("checkresult"));
				attr.setSourceid(new Long(rs.getLong("sourceid")));
				attr.setPintype(rs.getString("pintype"));
				attr.setWfcheckid(rs.getLong("wfcheckid"));
				attr.setFiltertype(rs.getShort("filtertype"));
				if(rs.getDate("limitdate")!=null)
					attr.setLimitdate(new java.util.Date(rs.getDate("limitdate").getTime()));
				attr.setMemo(rs.getString("memo"));
				attr.setActiondo(rs.getString("actiondo"));
				attr.setCountersign(rs.getShort("countersign"));
			}
			rs.close();
			pst.close();
		} finally {
			DbUtils.closeQuietly(null, pst, rs);
		}
		return attr;
	}

	public  WfProcessinstance findProcessInstance(Long procid){
		WfProcessinstance proc = null;
		Connection con = null;
		try{
			con = systemDao.getUnautocommitConnection();
			con.setAutoCommit(false);
			proc = findProcessInstance(con,procid);
			con.commit();
			con.setAutoCommit(true);
		}catch(Exception e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}finally{
			//关闭连接 CLQ 20120112
			DbUtils.closeQuietly(con);
		}
		return proc;
	}
	public  WfProcessinstance findProcessInstance(Connection con,Long procid)  {
		WfProcessinstance proc = null;
		StringBuffer hhsl_selectProc = new StringBuffer();
		hhsl_selectProc.append(" select * from  obe_processinstance where processinstanceid = ? ");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(hhsl_selectProc.toString());
			pst.setLong(1, procid.longValue());
			rs = pst.executeQuery();
			if (rs.next()) {
				proc = new WfProcessinstance();
				proc.setProcessinstanceid(procid);
				proc.setPintype(rs.getString("pintype"));
				proc.setSourceid(new Long(rs.getLong("sourceid")));
				proc.setWorkflowstatus(new Short(rs.getShort("workflowstatus")));
				proc.setStartdate(rs.getDate("startdate"));
				proc.setCompleteddate(rs.getDate("completeddate"));
				proc.setDtltype(rs.getInt("dtltype"));
				proc.setCreman(rs.getString("creman"));
				proc.setCremanname(rs.getString("cremanname"));
			}
			rs.close();
			pst.close();
		}
		catch(Exception e){
			log.error(e.getMessage(),e);
		} finally {
			DbUtils.closeQuietly(null, pst, rs);
		}
		return proc;
	}

	/* 修改By RWE 090811 加入WF_CHECK表里的 wfcheckid */
	public  WfAttributeinfo createWfAttribute(Connection con,
			Long processInstanceid, String checkStr, String checkman,
			Long corder, Long sourceid, String pintype, String roleName,
			String wfcheckid,short filtertype,int limit,String actiondo,short countersign) throws SQLException {
		StringBuffer hhsl_insert = new StringBuffer();
		hhsl_insert
				.append(" insert into obe_attributeinfo (obeattributeid,checkinfo,");
		hhsl_insert
				.append(" checkman, corder, state,  workflowstatus, sourceid,");
		hhsl_insert
				.append(" processinstanceid, pintype,rolename,wfcheckid,filtertype,limitdate,actiondo,countersign) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement("select obe_attribute_seq.nextval from dual");
			rs = pst.executeQuery();
			rs.next();
			Long attrid = rs.getLong(1);
			rs.close();
			pst.close();
			
			if(actiondo!=null&&!"".equals(actiondo)){
				if(actiondo.contains("{obeattributeid}")){
					actiondo = actiondo.replace("{obeattributeid}", attrid.toString());
				}
			}
			WfAttributeinfo attr = new WfAttributeinfo();
			attr.setObeattributeid(attrid);
			attr.setCheckinfo(checkStr);
			attr.setCheckman(checkman); //
			attr.setProcessinstanceid(processInstanceid);
			attr.setCorder(corder);
			attr.setState(new Short(Const.OBE_STATE_INIT));
			attr.setSourceid(sourceid);
			attr.setWorkflowstatus(new Short(Const.WORKFLOW_STATUS_INIT));
			attr.setPintype(pintype);
			attr.setWfcheckid(Long.valueOf(wfcheckid));
			attr.setFiltertype(filtertype);
			attr.setMemo(null);
			attr.setActiondo(actiondo);
			Date limitdate = DateUtil.addDays(new java.util.Date(),limit);
			attr.setLimitdate(limitdate);
			attr.setCountersign(countersign);

			pst = con.prepareStatement(hhsl_insert.toString());
			pst.setLong(1, attrid.longValue());
			pst.setString(2, checkStr);
			pst.setString(3, checkman);
			pst.setLong(4, corder.longValue());
			pst.setShort(5, Const.OBE_STATE_INIT);
			pst.setShort(6, Const.WORKFLOW_STATUS_INIT);
			pst.setLong(7, sourceid.longValue());
			pst.setLong(8, processInstanceid.longValue());
			pst.setString(9, pintype);
			pst.setString(10, roleName);
			pst.setString(11, wfcheckid);
			pst.setInt(12, filtertype);
			pst.setDate(13, new java.sql.Date( limitdate.getTime()));
			pst.setString(14, actiondo);
			pst.setShort(15, countersign);
			pst.executeUpdate();
			pst.close();
			return attr;
		} finally {
			DbUtils.closeQuietly(null,pst,rs);
		}
	}

	/* 修改By RWE 提取信息发送 */
	public  void createNewWorkItem(Connection con, WfAttributeinfo attr) throws SQLException {
		
		String[] sendMan = attr.getCheckman().split(","); // 格式为账号,账号2
		PreparedStatement pst = null;
		StringBuffer hhsl_insertWorkItem = new StringBuffer();
		hhsl_insertWorkItem
				.append(" insert into obe_workiteminfo( workitemid, ");
		hhsl_insertWorkItem
				.append("   obeattributeid,processinstanceid,checkman,");
		hhsl_insertWorkItem
				.append("   startdate,completeddate,state ) values(obe_workitem_seq.nextval,?,?,?,?,?,?)");
		try {
			pst = con.prepareStatement(hhsl_insertWorkItem.toString());
			for (int i = 0; i < sendMan.length; i++) {
				pst.setLong(1, attr.getObeattributeid().longValue());
				pst.setLong(2, attr.getProcessinstanceid().longValue());
				pst.setString(3, sendMan[i]);
				pst.setTimestamp(4, new Timestamp(new Date().getTime()));
				pst.setDate(5, null);
				if(attr.getFiltertype()==Const.FILTERTYPE_FIXED || sendMan.length==1){
					pst.setShort(6, Const.OBE_STATE_INIT);
				}
				else{
					pst.setShort(6, Const.OBE_STATE_HANG);
				}
				pst.addBatch();

			}
			pst.executeBatch();
			pst.close();
		} finally {
			DbUtils.closeQuietly(pst);
		}
		
	}

	public  void completeAttr(Connection con, Long attrid,
			short workflowstatus, String checktext, String checkman,String confirmmanname)
			throws SQLException {
		long result = attrid.longValue();
		PreparedStatement pst = null, pst2 = null;
		ResultSet rs = null;

		StringBuffer hhsl_update_workitem = new StringBuffer();
		hhsl_update_workitem
				.append(" update obe_workiteminfo t set t.completeddate = ?,");
		hhsl_update_workitem.append(" t.state = ? where obeattributeid = ?");
		
		WfAttributeinfo attr = findAttribute(con,attrid);
		// 会签
		boolean countersign = attr.getCountersign()==1;
		try {					
			if (workflowstatus == Const.WORKFLOW_STATUS_CHECKPASS) {
				
				if(countersign){
					hhsl_update_workitem.append(" and checkman = '"+checkman+"'");
				}
				pst = con.prepareStatement(hhsl_update_workitem.toString());
				pst.setTimestamp(1, new Timestamp(new Date().getTime()));
				pst.setShort(2, Const.OBE_STATE_CHECK);
				pst.setLong(3, attrid.longValue());
				pst.executeUpdate();
			}
			else if (workflowstatus == Const.WORKFLOW_STATUS_NOPASS) {
				pst = con.prepareStatement("select corder,processinstanceid from " +
						"obe_attributeinfo where obeattributeid = "+ attrid);
				rs = pst.executeQuery();
				int corder = 0;
				long processinstanceid = 0;
				if (rs.next()) {
					corder = rs.getInt("corder");
					processinstanceid = rs.getLong("processinstanceid");
				}
				corder--;
				pst.close();
				long obeattributeid = 0;
				pst = con.prepareStatement("select obeattributeid from " +
						"obe_attributeinfo where processinstanceid = "+ processinstanceid 
						+ " and corder = " + corder);
				rs = pst.executeQuery();
				if (rs.next()) {
					obeattributeid = rs.getLong("obeattributeid");
					result = obeattributeid;
					pst2 = con.prepareStatement("update obe_attributeinfo set state = "
									+ Const.OBE_STATE_INIT
									+ ",workflowstatus = "+Const.WORKFLOW_STATUS_NOCHECK+" where obeattributeid = "
									+ obeattributeid);
					pst2.executeUpdate();
					pst2.close();
					pst2 = con.prepareStatement("update obe_workiteminfo set state = ?,startdate = sysdate," +
							" completeddate = null  where obeattributeid = "+ obeattributeid);
					pst2.setLong(1, Const.OBE_STATE_INIT);
					pst2.executeUpdate();
					pst2.close();

					hhsl_update_workitem = new StringBuffer();
					hhsl_update_workitem.append(" delete from obe_workiteminfo t where obeattributeid = ?");
					pst = con.prepareStatement(hhsl_update_workitem.toString());
					pst.setLong(1, attrid.longValue());
					pst.executeUpdate();
				} else {
					String sql = "";
					try{
						WfProcesstypeId wfp = wfMetaData.findWfProcesstypeIdByCode(attr.getPintype());
						if(wfp.getPinstancename()!=null&&!"".equals(wfp.getPinstancename())){
							sql = "update "+wfp.getTablename()+" set "+wfp.getPinstancename()+" = null where "+wfp.getPkname()+" = "+attr.getSourceid();
							pst = con.prepareStatement(sql);
							pst.executeUpdate();
						}
						result = -1;
					}catch(Exception e){
						throw new SQLException(e);
					}
					sql = "delete from obe_workiteminfo where processinstanceid = "
							+ processinstanceid;
					pst = con.prepareStatement(sql);
					pst.executeUpdate();
					sql = "delete from obe_attributeinfo where processinstanceid = "
							+ processinstanceid;
					pst = con.prepareStatement(sql);
					pst.executeUpdate();
					sql = "delete from obe_processinstance  where processinstanceid = "
							+ processinstanceid;
					pst = con.prepareStatement(sql);
					pst.executeUpdate();
				}
			} else if (workflowstatus == Const.WORKFLOW_STATUS_STOP) {
				pst = con.prepareStatement(hhsl_update_workitem.toString());
				pst.setTimestamp(1, new Timestamp(new Date().getTime()));
				pst.setShort(2, Const.OBE_STATE_STOP);
				pst.setLong(3, attrid.longValue());
				pst.executeUpdate();
				pst.close();
				long processinstanceid = 0;
				pst = con.prepareStatement("select processinstanceid from " +
						"obe_attributeinfo where obeattributeid = "+ attrid);
				rs = pst.executeQuery();
				if (rs.next()) {
					processinstanceid = rs.getLong("processinstanceid");
				}
				pst.close();
				pst = con.prepareStatement("update obe_processinstance set workflowstatus = ?  " +
								"where processinstanceid = ?");
				pst.setShort(1, Const.WORKFLOW_STATUS_STOP);
				pst.setLong(2, processinstanceid);
				pst.executeUpdate();
			}
			
			if(countersign) {
				pst = con.prepareStatement("select  (select count(*) from obe_workiteminfo where  obeattributeid = "+attrid.toString()+" ) works,(select count(*) from obe_workiteminfo where state = 2 and  obeattributeid = "+attrid.toString()+") pass  from dual");
				rs = pst.executeQuery();
				if(rs.next()){
					int works = rs.getInt(1);
					int pass = rs.getInt(2);
					if(works==0){
						//不同意  回退
					}
					else if(works==pass){
						//全部同意
					}
					else{
						//部分同意
						workflowstatus = Const.WORKFLOW_STATUS_NOCHECK;
					}
				}
			}

			if(result!=-1){
				StringBuffer hhsl_update_attr = new StringBuffer();
				hhsl_update_attr.append(" update obe_attributeinfo t set t.state = ? ,");
				hhsl_update_attr.append(" t.checkresult = decode(checkresult,null,'审批时间('||to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')||')  "+confirmmanname+":"+checktext+"',checkresult||'<br>'||'审批时间('||to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')||')  "+confirmmanname+":"+checktext+"') , confirmmanname = decode(confirmmanname,null,'"+confirmmanname+"',confirmmanname||','||'"+confirmmanname+"'), ");
				hhsl_update_attr.append(" workflowstatus = ? where  obeattributeid = ?");
				
				pst = con.prepareStatement(hhsl_update_attr.toString());
				if (workflowstatus == Const.WORKFLOW_STATUS_CHECKPASS)
					pst.setShort(1, Const.OBE_STATE_CHECK);
				else if (workflowstatus == Const.WORKFLOW_STATUS_STOP) {
					pst.setShort(1, Const.OBE_STATE_STOP);
				}
				else {
					pst.setShort(1, Const.OBE_STATE_INIT);
				}
				pst.setShort(2, workflowstatus);
				pst.setLong(3, attrid.longValue());
				pst.executeUpdate();
				pst.close();
			}
		} finally {
			DbUtils.closeQuietly(pst);
			DbUtils.closeQuietly(pst2);
		}
	}

	/**
	 * 先根据Attrid和person找到所有当前该账号需要审批的任务，设置state状态为已审批，同时新增一条workitem,
	 * 更正obeattribute的checkman 审批流转其它人审批
	 * 
	 * @param session
	 *            数据库连接
	 * @param attr
	 *            Attr对象
	 * @param person
	 *            人员表
	 * @param accountno
	 *            新审批人员账号
	 */
	public  void sendOtherCheck(Connection con, WfAttributeinfo attr,
			Account person, Account target) throws SQLException,NotAuthorizedException {

		StringBuffer hhsl_commitWorkItem = new StringBuffer();
		hhsl_commitWorkItem.append(" update obe_workiteminfo set checkman = ? " +
				"where obeattributeid= ? and checkman = ? and state = ? ");

		String hhsl_updateAttr = " update obe_attributeinfo set checkman = ? , memo = ? where obeattributeid = ?";
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(hhsl_commitWorkItem.toString());
			pst.setString(1, target.getAccountId());
			pst.setLong(2, attr.getObeattributeid().longValue());
			pst.setString(3, person.getAccountId());
			pst.setShort(4, Const.OBE_STATE_INIT);
			int ret = pst.executeUpdate();
			pst.close();
			if(ret==0){
				throw new NotAuthorizedException("你没有当前任务的操作权限");
			}

			String checkman = attr.getCheckman(); // 原审批人应该不能再审批了！
			checkman.replaceAll(person.getAccountId(), target.getAccountId());

			pst = con.prepareStatement(hhsl_updateAttr);
			pst.setString(1, checkman);
			pst.setString(2, (attr.getMemo()==null?"":attr.getMemo()+";")+person.getAccountName()+"于"
					+DateUtil.formatDTime(new Date())+"转发任务至"+target.getAccountName());
			pst.setLong(3, attr.getObeattributeid().longValue());
			pst.executeUpdate();
			pst.close();

			attr.setCheckman(checkman);
			
		} finally {
			DbUtils.closeQuietly(pst);
		}
	}

	public  void updateProcessInstance(Connection con,
			WfProcessinstance proc) throws SQLException {
		StringBuffer hhsl_updateProc = new StringBuffer();
		hhsl_updateProc.append(" update  obe_processinstance set workflowstatus = ? ,");
		hhsl_updateProc.append(" completeddate = ? where processinstanceid = ? ");
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(hhsl_updateProc.toString());
			pst.setShort(1, proc.getWorkflowstatus().shortValue());
			pst.setTimestamp(2,new Timestamp(proc.getCompleteddate().getTime()));
			pst.setLong(3, proc.getProcessinstanceid().longValue());
			pst.executeUpdate();
			pst.close();
		} finally {
			DbUtils.closeQuietly(pst);
		}
	}

	
	public List<Map<String, Object>> needCheckedConditions(Map<String, Object> doc, Map<String, Object>[] dtl,
			List<Map<String, Object>> checks, Map<String, Object> util, String filterDeptCode) throws NotFoundException,EvalError {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		if (checks == null || checks.size() < 1) {
			return null;
		}

		String buffer = null; // 为避免重复计算，设置一个buffer
		// 这时候wfroleid只能是账号形式或者是role:开头的啦,接下来就可以对它进行找对应的账号信息了,这个放在wfService里面来弄

		for (int i = 0; i < checks.size(); i++) {
			Map<String, Object> step = checks.get(i);
			String sb = check(step, doc, dtl, util);
			if (sb != null) {

				// 由于现在都不用工作流引擎了，所以就自动带出数据库里配置的审批人信息项就行了
				// 用roleFilter来计算实际的审批人
				String title = (String)step.get("filtertitle");
				String filter = (String)step.get("wffilter");
				String realRole = (String)step.get("wfroleid");
				boolean skip = "1".equals((String)step.get("emptyskip")) ? true : false;
				boolean nosameskip = "1".equals((String)step.get("nosameskip")) ? true : false;

				if ((filter == null || filter.length() == 0) && (realRole == null || realRole.length() == 0) && !skip) { // 如果查找时没有过滤条件,则不需要找对应的账号
					throw new NotFoundException(title + "尚未配置人员信息");
				}
				else if (filter != null && filter.length() != 0) {
					RoleFilter f = SpringContextUtils.getBeanById(filter, RoleFilter.class);
					realRole = f.filterRole(realRole, filterDeptCode, doc, dtl);
				} else { // 非实现类的直接就是帐号
					
				}
				List<String> accountlist = new ArrayList<String>();
				if (realRole != null) {
					String[] accounts = realRole.split(",");
					for (int m = 0, n = accounts.length; m < n; m++) {
						if (!accountlist.contains(accounts[m])) {
							accountlist.add(accounts[m]);
						}
					}
				}
				String extendaccount = (String)step.get("extendaccount");
				if (!"".equals(extendaccount) && extendaccount != null) { //额外审批人 通常用于测试
					String[] accounts = extendaccount.split(",");
					for (int x = 0, y = accounts.length; x < y; x++) {
						if (!accountlist.contains(accounts[x])) {
							accountlist.add(accounts[x]);
						}
					}
				}

				if (realRole == null && accountlist.size() < 1) {
					if (skip) {
						continue;
					} else {
						throw new NotFoundException("找不到["+title+"]参与该步骤的帐号");
					}
				} else {
					realRole = accountlist.get(0);
					for (int m = 1, n = accountlist.size(); m < n; m++) {
						realRole += "," + accountlist.get(m);
					}
				}
				if (realRole.equals(buffer) && !nosameskip)
					continue;
				else
					buffer = realRole;

				String action = getActiondo(step, doc, dtl, util);
				
				Map<String,Object> row = new ConcurrentHashMap<String,Object>();
				row.put("account", realRole);
				row.put("checkStr", sb);
				
				row.put("wfcheckid", step.get("wfcheckid")); // 流程审批审批过滤有用
				row.put("actiondo", action);
				row.put("countersign", step.get("countersign"));
				
				result.add(row);
			}

		}

		return result;

	}
	
	/**
	 * 
	 * @param step
	 *            Map中key可为(String ctype, String checkexp, String message)
	 *            ctype代表类型(总单:"Z",细单:"X"), exp代表条件表达式 message代表错误信息
	 * @param purcon
	 *            合同总单
	 * @param purdtllist
	 *            合同细单
	 * @param util
	 *            自己配置可以调用的后台,则判断数据库条件时调用的后台 例如Util中有key值为"purUtil",value为 new
	 *            PurconUtil(); 如果不需要util,则传null
	 * @return
	 */
	private String check(Map<String,Object> step, Map<String,Object> doc, Map<String,Object>[] dtlList,
			Map<String,Object> util) throws EvalError {

		Interpreter interpreter = new Interpreter();
		if (util != null) {
			Iterator<String> it2 = util.keySet().iterator();
			while (it2.hasNext()) {
				String key = it2.next();
				interpreter.set(key, util.get(key));
			}

		}
		// 展开总单
		Iterator<String> it = doc.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = doc.get(key);

			interpreter.set(key.toString(), value);
		}
		StringBuffer errorMessage = new StringBuffer();
		// 判断总单
		String checkfilter = (String)step.get("checkfilter");
		if(checkfilter!=null && !"".equals(checkfilter)){
			try{
				CheckFilter cf = (CheckFilter)Class.forName((String)step.get("checkfilter")).newInstance();
				errorMessage = cf.filterCheck(interpreter, doc, dtlList, util);
			}catch(Exception e){
				log.error(e.getMessage(),e);
				throw new RuntimeException(e);
			}
		}
		String type =  (String)step.get("ctype");
		if ("Z".equals(type)) { // 总单
			String checkexp = step.get("checkexp").toString();
			if (checkexp.indexOf("%") != -1) {
				String checkparam = step.get("checkexpparam").toString();
				String[] paramvalues = checkparam.split(",");
				for (int m = 0; m < paramvalues.length; m++) {
					checkexp = checkexp.replaceAll("%" + (m + 1),
							paramvalues[m]);
				}
			}
			Object b = interpreter.eval(checkexp);
			Boolean _b = (Boolean) b;
			if (_b.booleanValue()) {
				String message = step.get("message").toString();
				if (message.indexOf("%") != -1) {
					String checkparam = step.get("checkexpparam").toString();
					String[] paramvalues = checkparam.split(",");
					for (int m = 0; m < paramvalues.length; m++) {
						message = message.replaceAll("%" + (m + 1),
								paramvalues[m]);
					}
				}
				Object msg = interpreter.eval(message);

				errorMessage.append("\n" + msg);
			}
		}


		// 展开细单
		for (int i = 0; i < dtlList.length; i++) {
			Map<String,Object> dtlMap = dtlList[i];
			it = dtlMap.keySet().iterator();

			interpreter.unset("dtl");
			while (it.hasNext()) {
				Object key = it.next();
				Object value = dtlMap.get(key);
				interpreter.set("dtl." + key.toString(), value);
			}

			if ("X".equals(type)) { // 细单
				String checkexp = step.get("checkexp").toString();
				if (checkexp.indexOf("%") != -1) {
					String checkparam = step.get("checkexpparam").toString();
					String[] paramvalues = checkparam.split(",");
					for (int m = 0; m < paramvalues.length; m++) {
						checkexp = checkexp.replaceAll("%" + (m + 1),
								paramvalues[m]);
					}
				}
				Object b = interpreter.eval(checkexp);
				Boolean _b = (Boolean) b;
				if (_b.booleanValue()) {
					String message = step.get("message").toString();
					if (message.indexOf("%") != -1) {
						String checkparam = step.get("checkexpparam").toString();
						String[] paramvalues = checkparam.split(",");
						for (int m = 0; m < paramvalues.length; m++) {
							message = message.replaceAll("%" + (m + 1),
									paramvalues[m]);
						}
					}
					Object msg = interpreter.eval(message);

					errorMessage.append("\n" + msg);
				}
			}

		}
		if (errorMessage == null || errorMessage.length() == 0) {
			return null;
		} else {
			return errorMessage.toString();
		}
	}
	
	/**
	 * 
	 * @param conditions
	 *            Map中key可为(String ctype, String checkexp, String message)
	 *            ctype代表类型(总单:"Z",细单:"X"), exp代表条件表达式 message代表错误信息
	 * @param purcon
	 *            合同总单
	 * @param purdtllist
	 *            合同细单
	 * @param util
	 *            自己配置可以调用的后台,则判断数据库条件时调用的后台 例如Util中有key值为"purUtil",value为 new
	 *            PurconUtil(); 如果不需要util,则传null
	 * @return
	 */
	private String getActiondo(Map<String,Object> step, Map<String,Object> doc, Map<String,Object>[] dtlList,
			Map<String,Object> util) throws EvalError {

		Interpreter interpreter = new Interpreter();
		// interpreter.set("simpleUtil", new WFSimpleUtil());
		if (util != null) {
			Iterator<String> it = util.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				interpreter.set(key, util.get(key));
			}

		}
		// 展开总单
		Iterator<String> it = doc.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = doc.get(key);
			interpreter.set(key.toString(), value);
		}
		StringBuffer errorMessage = new StringBuffer();
		// 判断总单
		
		String type = (String)step.get("ctype");
		if ("Z".equals(type)) { // 总单

			String actiondo = (String)step.get("actiondo");
			if(actiondo!=null){
				if (actiondo.indexOf("%") != -1) {
					String actiondoparam = step.get("actiondoparam").toString();
					String[] paramvalues = actiondoparam.split(",");
					for (int m = 0; m < paramvalues.length; m++) {
						actiondo = actiondo.replaceAll("%" + (m + 1),
								paramvalues[m]);
					}
				}
				Object msg = interpreter.eval(actiondo);
				
				if(msg!=null)
					errorMessage.append(msg);
			}
		}


		// 展开细单
		for (int i = 0; i < dtlList.length; i++) {
			Map<String,Object> dtlMap = dtlList[i];
			it = dtlMap.keySet().iterator();

			interpreter.unset("dtl");
			while (it.hasNext()) {
				Object key = it.next();
				Object value = dtlMap.get(key);
				interpreter.set("dtl." + key.toString(), value);
			}
			// 判断细单条件

			if ("X".equals(type)) { // 细单

				String actiondo = (String)step.get("actiondo");
				if(actiondo!=null){
					if (actiondo.indexOf("%") != -1) {
						String checkparam = step.get("actiondoparam")
								.toString();
						String[] paramvalues = checkparam.split(",");
						for (int m = 0; m < paramvalues.length; m++) {
							actiondo = actiondo.replaceAll("%" + (m + 1),
									paramvalues[m]);
						}
					}
					Object msg = interpreter.eval(actiondo);
					if(msg!=null)
						errorMessage.append("\n" + msg);
				}
			}

		}
		if (errorMessage.length() == 0) {
			return null;
		} else {
			return errorMessage.toString();
		}
	}
}