package com.gzmpc.wf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;
import java.sql.Connection;

import com.gzmpc.utils.DateUtil;
import com.gzmpc.utils.QuerySupport;
import com.gzmpc.utils.QueryUtil;

import org.apache.commons.dbutils.DbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gzmpc.utils.Const;

import java.util.Date;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.exception.ProcessException;
import com.gzmpc.exception.StartException;
import com.gzmpc.login.LoginService;
import com.gzmpc.mapper.LowerColumnMapRowMapper;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.wf.AssService;
import com.gzmpc.wf.meta.WfMetaData;
import com.gzmpc.wf.pojo.WfAttributeinfo;
import com.gzmpc.wf.pojo.WfProcessinstance;
import com.gzmpc.wf.pojo.WfProcesstypeId;

@Controller("assInstance")
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AssInstance {
	
	private Log log = LogFactory.getLog(AssInstance.class);

	@Autowired
	WfMetaData wfMetaData;
	
	@Autowired
	SystemDao systemDao;
	
	@Autowired
	WfService wfService;
	
	@Autowired
	AssService assService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	QuerySupport querySupport;
	
	@Autowired
	QueryUtil queryUtil;
	
	WfProcesstypeId wfp ;
	
	
	public AssInstance() {
		
	}
	
	public AssInstance(WfProcesstypeId wfp) {
		this.wfp = wfp;
	}

	public void setWfp(WfProcesstypeId wfp) {
		this.wfp = wfp;
	}
	
	/**
	 * 提交至审批,启动审批进程
	 * 
	 * */
	
	public void doStartAss(Account account, List<String> entids,Map<String,Object> param,Long procid,Long wfcheckid) throws StartException {
		Connection con = null;
		
		try {
			con = systemDao.getUnautocommitConnection();
			doStartAss(account,con, entids,param,procid,wfcheckid);
			con.commit();
		} catch (SQLException e) {
			log.error(e.getMessage(),e);
			try{
				if( con != null && !con.isClosed()) {
					con.rollback();
				}
			}catch ( SQLException e2) {
				log.error(e2.getMessage(),e2);
			}
		}finally {
			DbUtils.closeQuietly(con);
		}
		
	}

	public void doStartAss(Account account,Connection con, List<String> entids,Map<String,Object> param,Long procid,Long wfcheckid) throws StartException {
		
		String pintype = wfp.getProcesstype();
		short dtltype = wfp.getDtltype();
		String pintypeDO = wfp.getActiondo();
		String pinstancename = wfp.getPinstancename();
		
		String doctable = wfp.getTablename();
		String docview = wfp.getDocview();
		String dtltable = wfp.getDtltable();
		String primarykey = wfp.getPkname();
		String dbname = wfp.getDbname();
		
		Connection dbcon = null;
		PreparedStatement pst = null, updatePst = null, pst2 = null;
		ResultSet rs = null;
		
		List<String> errormsg = new ArrayList<String>();
		try {
			
			if(dbname!=null && !"".equals(dbname.trim())){
				dbcon = querySupport.retConnection(dbname);
				dbcon.setAutoCommit(false);
			}
			
			String accountId = account.getAccountId();
			String accountName = account.getAccountName();

			Map<String,Object>[] doc = readDoc(dbcon==null?con:dbcon, docview == null? doctable : docview, primarykey, entids);
			Map<String,Object>[] docdtl = readDtl(dbcon==null?con:dbcon, dtltable, primarykey, entids);
			
			List<Map<String,Object>> checklist = new ArrayList<Map<String,Object>>();
			String checksql = "select * from wf_check_v where processtype = ? ";
			if(wfcheckid!=null){
				checksql += " and corder > (select corder from wf_check where wfcheckid = "+wfcheckid+" )";
			}
			pst = con.prepareStatement(checksql);
			pst.setString(1, pintype);
			rs = pst.executeQuery();
			while(rs.next()) {
				Map<String,Object> row = new LowerColumnMapRowMapper().mapRow(rs, checklist.size()+1);
				checklist.add(row);
			}
			rs.close();
			pst.close();
			
			if(checklist.size()==0) {
				return;
			}
			setParams(con,dbcon, doc, docdtl,checklist);
			
			for (int i = 0, j = doc.length; i < j; i++) {
				
				List<Map<String,Object>> tmp = new ArrayList<Map<String,Object>>();
				int m = 0, n = docdtl.length;
				String entid = (String) doc[i].get(primarykey);
				for (; m < n; m++) {
					if (entid.equals((String) docdtl[m].get(primarykey))) {
						tmp.add(docdtl[m]);
					}
				}
				try{
					Map<String,Object> doctmp = doc[i];
					@SuppressWarnings("unchecked")
					Map<String,Object>[] dtltmp = tmp.toArray(new Map[tmp.size()]);
					Map<String,Object> util = getUtil(doctmp, dtltmp);
					String filterDeptCode = getFilterDeptCode(doctmp, dtltmp);
					List<Map<String,Object>> result = wfService.needCheckedConditions(doctmp, dtltmp, checklist, util, filterDeptCode);
					if (result.size() > 0) {
						if(dtltype==2){
							result = result.subList(0, 1);
						}
						
						if(wfcheckid==null){
							procid = wfService.genProcessInstance(con, new Long(entid), pintype, pintypeDO,accountId, accountName, result);
							WfAttributeinfo attr = wfService.findCurNeedCheckAttr(con, procid.toString());
							afterGenInstance(account, con, attr);
							doc[i].put("attr", attr);
							
							if(pinstancename!=null&&!"".equals(pinstancename)){
								
								StringBuffer hhsl_update = new StringBuffer();
								hhsl_update.append(" update " + doctable
										+ " set "+pinstancename+" =? ");
								hhsl_update.append(" where " + primarykey + "=? ");
								updatePst = (dbcon==null?con:dbcon).prepareStatement(hhsl_update.toString());
								updatePst.setLong(1, procid.longValue());
								updatePst.setString(2, entid);
								updatePst.executeUpdate();
								updatePst.close();
							}
							afterRowStartAss(account,con,dbcon,doctmp,dtltmp,attr,procid,entid);
						}
						else{
							wfService.genNextNeedCheckAttribute(procid, con, result);
						}
					}
					else if(wfcheckid == null) { //当提交时已无需审批则直接自动完成?
						
					}
				} catch ( Exception e ) {
					errormsg.add(entid+"提交出现错误:"+e.getMessage());
				}
			}
			afterStartAss(account,con,dbcon, doc, docdtl);
			
			if(dbcon!=null){
				dbcon.commit();
			}
			
			con.commit();
		} catch(Exception e) {
			log.error(e.getMessage(),e);
			try{
				if(dbcon!=null){
					dbcon.rollback();
				}
			} catch (SQLException e2) {
				log.error(e2.getMessage(),e2);
			}
			throw new StartException("提交失败:"+e);
		} finally {
			DbUtils.closeQuietly(pst2);
			DbUtils.closeQuietly(updatePst);
			DbUtils.closeQuietly(null, pst, rs);
			DbUtils.closeQuietly(dbcon);
		}
		if ( errormsg.size() > 0) {
			String msg = "";
			for( int i=0,j=errormsg.size();i<j;i++) {
				msg += errormsg.get(i)+"\n";
			}
			throw new StartException("提交出现失败:\n"+msg);
		}
	}

	public Map<String,Object>[] readDoc(Connection con, String tablename, String primarykey,
			List<String> entids) throws SQLException {
		if(entids!=null && entids.size()>0) {
			String sql = "select * from "+ tablename +" where "+ primarykey +" in ( ? ";
			for(int i=1,j=entids.size();i<j;i++) {
				sql += ", ?";
			}
			sql += " )";
			Object[] o = entids.toArray(new String[entids.size()]);
			return queryUtil.query(con, sql, o);
		}
		else {
			return null;
		}
		
	}

	public Map<String,Object>[] readDtl(Connection con, String tablename, String primarykey,
			List<String> entids) throws SQLException {
		if (tablename == null || "".equals(tablename) || entids == null || entids.size() == 0) {
			return null;
		}
		String sql = "select * from "+ tablename +" where "+ primarykey +" in ( ? ";
		for(int i=1,j=entids.size();i<j;i++) {
			sql += ", ?";
		}
		sql += " )";
		Object[] o = entids.toArray(new String[entids.size()]);
		return queryUtil.query(con, sql, o);
	}

	public void afterGenInstance(Account account, Connection con,WfAttributeinfo attr) {
		
	}

	public Map<String,Object> getUtil(Map<String,Object> doc, Map<String,Object>[] dtl) {
		return null;
	}

	public String getFilterDeptCode(Map<String,Object> doc, Map<String,Object>[] dtl) {
		return null;
	}

	public void setParams(Connection con,Connection dbcon, Object[] doc, Object[] dtl, List<Map<String,Object>> checks) {

	}
	
	public void afterStartAss(Account account,Connection con,Connection dbcon, Object[] doc, Object[] dtl) {
		
	}
	
	public void afterRowStartAss(Account account,Connection con,Connection dbcon, Object doc, Object[] dtl,WfAttributeinfo attr,long procid,String entid){
		
	}
	
	
	
	
	
	
	
	//以下是审批时使用

	public boolean checkvalidate(Account account,
			WfAttributeinfo attr, String checkText, Boolean checkResult,
			Map<String,Object> person,  boolean bat, Connection con) {
		return true;
	}

	public void checkByManOri(Account account, String attrid,
			String checkText, Boolean checkResult, String checkaccount, Map<String,Object> extend,
			boolean bat, Connection con) {
		String account_id = checkaccount;
		if(account_id == null) {
			account_id = account.getAccountId();
		}
		String confirmmanname = account.getAccountName();
		try {

			synchronized (attrid) {
				WfAttributeinfo attr = wfService.findAttribute(con, new Long(attrid));
				if (attr == null) {
					throw new NotFoundException("输入的审批号ID不对！");
				}
				WfAttributeinfo	curAttr = wfService.findCurNeedCheckAttr(con, String.valueOf(attr.getProcessinstanceid()));
				if (curAttr == null) {
					throw new NotFoundException("找不到当前审批步骤！");
				}
				else if (curAttr.getObeattributeid().longValue() != attr.getObeattributeid().longValue()) {
					throw new ProcessException("提交的步骤不是当前审批步骤！");
				}
				else if (attr.getState().shortValue() == Const.OBE_STATE_CHECK) {
					throw new ProcessException("该任务已经结束了或者有人操作过了.请查看操作信息");
				}
				else if (attr.getState().shortValue() == Const.OBE_STATE_STOP) {
					throw new ProcessException("该任务已经被中止.请查看操作信息");
				}
				List<String> checkman =  java.util.Arrays.asList(attr.getCheckman().split(","));
				if(!checkman.contains(account_id) && !allowAss(account,attr.getCheckman(),account_id)){
					throw new NotAuthorizedException("你无权操作此单据");
				}
				if (checkvalidate(account, attr, checkText, checkResult,
						extend,  false, con)) {
					WfProcessinstance proc = wfService.findProcessInstance(con, attr.getProcessinstanceid());
					if (checkResult.booleanValue()){
						wfService.completeAttr(con, new Long(attrid),
								Const.WORKFLOW_STATUS_CHECKPASS, checkText,
								checkaccount,confirmmanname);
						
						if(proc.getDtltype()==2){
							//因为有可能未批完所以要刷新一下
							attr = wfService.findAttribute(con, attr.getObeattributeid());
							if(attr.getState() == Const.OBE_STATE_CHECK && attr.getWorkflowstatus() == Const.WORKFLOW_STATUS_CHECKPASS){
								createNextStep(account,attr,con);
							}
						}
					}
					else{
						wfService.completeAttr(con, new Long(attrid),Const.WORKFLOW_STATUS_NOPASS,checkText, checkaccount,confirmmanname);
						if(proc.getDtltype()==2){
							PreparedStatement pst = null;
							pst = con.prepareStatement("delete from obe_workiteminfo where obeattributeid = "+ attrid);
							pst.executeUpdate();
							pst.close();
							pst = con.prepareStatement("delete from obe_attributeinfo where obeattributeid = "+ attrid);
							pst.executeUpdate();
							pst.close();
						}
					}
					
					
					WfAttributeinfo	nextAttr = wfService.findCurNeedCheckAttr(con, String.valueOf(attr.getProcessinstanceid()));
					
					if (nextAttr == null) {
						if (checkResult.booleanValue()) { // 全部审批通过 回填最后审批人
							
							proc.setCompleteddate(new Date());
							proc.setWorkflowstatus(Const.WORKFLOW_STATUS_CHECKPASS);
							wfService.updateProcessInstance(con, proc);
							checkCompleted(account, con, proc, account_id, extend);
						}
						else{
							backToNoSubmit(account, con, proc, account_id, extend);
						}
					} else {
						if (checkResult.booleanValue()) {
							if(nextAttr.getObeattributeid().longValue()!=attr.getObeattributeid().longValue()){
								wfService.createNewWorkItem(con, nextAttr);
							}
							checkOver(account, con, nextAttr, false, bat, account_id, extend);
						} else {
							checkOver(account, con, attr, true, bat, account_id, extend);
						}
					}
				}
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 页面点同意或者不同意审批时触发的事件
	 * 
	 * @param attrid
	 * @param checkText
	 * @param checkResult
	 * @param person
	 * @ 
	 */
	public void checkByMan(Account account, String attrid,
			String checkText, Boolean checkResult, String checkaccount, Map<String,Object> extend)  {
		Connection con = null;
		try {
			con = systemDao.getUnautocommitConnection();
			checkByManOri(account, attrid, checkText, checkResult, checkaccount, extend, false, con);
			con.commit();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				con.rollback();
			} catch (Exception ex1) {
				log.error(ex1.getMessage(), ex1);
			}
			throw new RuntimeException(ex);
		} finally {
			DbUtils.closeQuietly(con);
		}
	}

	public boolean stopvalidate(Account account,
			WfAttributeinfo attr, String checkText, String checkaccount, Map<String,Object> extend, 
			boolean bat, Connection con) {
		return true;
	}

	public void stopByManOri(Account account, String attrid,
			String checkText, String checkaccount, Map<String,Object> extend,  boolean bat,
			Connection con) {
		String account_id = checkaccount;
		if(account_id == null) {
			account_id = account.getAccountId();
		}
		String confirmmanname = account.getAccountName();
		String skipcheck = (String)extend.get("skipcheck");
		try {
			synchronized (attrid) {
				WfAttributeinfo attr = wfService.findAttribute(con, new Long(attrid));
				if (attr == null) {
					throw new NotFoundException("输入的审批号ID不对！");
				}
				WfAttributeinfo	curAttr = wfService.findCurNeedCheckAttr(con, String.valueOf(attr.getProcessinstanceid()));
				if (curAttr == null) {
					throw new NotFoundException("找不到当前审批步骤！");
				}
				if (attr.getState().shortValue() == Const.OBE_STATE_CHECK) {
					throw new ProcessException("该任务已经结束了或者有人操作过了.请查看操作信息");
				}
				if (attr.getState().shortValue() == Const.OBE_STATE_STOP) {
					throw new ProcessException("该任务已经被中止.请查看操作信息");
				}
				WfProcessinstance process = wfService.findProcessInstance(con, attr.getProcessinstanceid());
				String creman = process.getCreman();
				List<String> checkman =  java.util.Arrays.asList(attr.getCheckman().split(","));
				if(!"1".equals(skipcheck) && !creman.equals(account_id) && !checkman.contains(account_id) && !allowAss(account,attr.getCheckman(),account_id) ){
					throw new NotAuthorizedException("你无权操作此单据");
				}
				if (stopvalidate(account, attr, checkText, account_id, extend,
						false, con)) {
					wfService.completeAttr(con, new Long(attrid),Const.WORKFLOW_STATUS_STOP, checkText, account_id, confirmmanname);
					checkStop(account, con, attr, bat, account_id, extend);
				}
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new RuntimeException(ex);
		}

	}

	/**
	 * 页面点中止审批时触发的事件
	 * 
	 * @param attrid
	 * @param checkText
	 * @param person
	 */
	public void stopByMan(Account account, String attrid,
			String checkText, String checkaccount, Map<String,Object> extend)  {
		Connection con = null;
		try {
			con = systemDao.getUnautocommitConnection();
			stopByManOri(account, attrid, checkText, checkaccount, extend, false, con);
			con.commit();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				con.rollback();
			} catch (Exception ex1) {
				log.error(ex1.getMessage(), ex1);
			}
			throw new RuntimeException(ex);
		} finally {
			DbUtils.closeQuietly(con);
		}
	}

	public void checkOver(Account account, Connection con,
			WfAttributeinfo attr,  boolean back, boolean bat, String checkaccount, Map<String,Object> extend) {

	}

	protected void checkCompleted(Account account, Connection con,
			WfProcessinstance proc, String checkaccount, Map<String,Object> extend) {

	}
	protected void backToNoSubmit(Account account, Connection con,
			WfProcessinstance proc, String checkaccount, Map<String,Object> extend) {

	}
	
	protected void checkStop(Account account, Connection con,
			WfAttributeinfo attr, boolean bat, String checkaccount, Map<String,Object> extend) {

	}

	public Map<String,Object> initRequest(Account account,WfProcesstypeId wfp, String entid) throws NotFoundException {
		Map<String,Object> result = null;
		if (entid == null) {
			throw new NotFoundException("错误信息：页面上参数没有传递主键值");
		}
		String tablename = wfp.getTablename();
		String primarykey = wfp.getPkname();
		String pinstancename = wfp.getPinstancename();
		String dbname = wfp.getDbname();
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = systemDao.getUnautocommitConnection();
			
			Map<String,Object> values = queryUtil.queryForObject("select "+pinstancename+" from "+tablename+" where "+primarykey+" = ?", dbname,entid);
			if (values != null) {
				String processinstanceid = ((BigDecimal)values.get(pinstancename)).toString();
				WfAttributeinfo attr = wfService.findCurNeedCheckAttr(con,processinstanceid);
				result = new ConcurrentHashMap<String,Object>();
				result.put(pinstancename, processinstanceid);
				if (attr != null) {
					result.put("wfcheckid", attr.getWfcheckid()
							.toString());
					result.put("workitemInfo", attr);
				}
			}
			else{
				throw new NotFoundException("找不到来源");
			}
			con.commit();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				if( con != null) {
					con.rollback();
				}
			} catch (Exception ex1) {
				log.error(ex1.getMessage(), ex1);
			}
			throw new RuntimeException(ex);
		} finally {
			DbUtils.closeQuietly(con, pst, rs);
		}
		return result;
	}

	public void backStop(Connection con, Long pinstanceid, String checktext, String checkaccount, String confirmmanname ) throws Exception {
		PreparedStatement pst = null,pst2 = null;
		ResultSet rs = null;
		try {
			WfProcessinstance pro = wfService.findProcessInstance(con,pinstanceid);
			if(pro.getWorkflowstatus() == Const.WORKFLOW_STATUS_STOP ){
				
				pst = con.prepareStatement("select obeattributeid from obe_attributeinfo a where a.processinstanceid = "+pinstanceid+" and a.state = 1 order by a.corder");
				rs = pst.executeQuery();
	        	boolean next = rs.next();
	        	if(next) {
	        		wfService.completeAttr(con, rs.getLong(1),
							Const.WORKFLOW_STATUS_NOPASS, checktext, checkaccount, confirmmanname);
					StringBuffer sql = new StringBuffer();
					sql.append("update obe_processinstance set workflowstatus = ?  where processinstanceid = "
									+ pinstanceid);
					pst2 = con.prepareStatement(sql.toString());
					pst2.setInt(1, Const.WORKFLOW_STATUS_NOCHECK);
					pst2.executeUpdate();
					pst2.close();
	        	}
	        	rs.close();
	        	pst.close();
	        	if(!next) { //最后一步中止
	        		pst = con.prepareStatement("select obeattributeid from obe_attributeinfo where corder = (select max(t.corder) from obe_attributeinfo t where t.processinstanceid = "+pinstanceid+" ) and processinstanceid = "+ pinstanceid);
					rs = pst.executeQuery();
					if(rs.next()) {
						long obeattributeid = rs.getLong(1);
						pst2 = con.prepareStatement("update obe_workiteminfo set completeddate = null,state = ? where obeattributeid = "+obeattributeid);
						pst2.setInt(1, Const.OBE_STATE_INIT);
						pst2.executeUpdate();
						pst2.close();
						
						pst2 = con.prepareStatement("update obe_attributeinfo set workflowstatus = ?,state = ? where obeattributeid = "+obeattributeid);
						pst2.setInt(1, Const.WORKFLOW_STATUS_NOCHECK);
						pst2.setInt(2, Const.OBE_STATE_INIT);
						pst2.executeUpdate();
						pst2.close();
						
						pst2 = con.prepareStatement("update obe_processinstance set completeddate = null,workflowstatus = ? where processinstanceid = "+pinstanceid);
						pst2.setInt(1, Const.WORKFLOW_STATUS_NOCHECK);
						pst2.executeUpdate();
						pst2.close();
					}
					else {
						throw new Exception(pinstanceid+"找不到相关审批流程");
					}
					rs.close();
					pst.close();
	        	}
	        	
			}
			else {
				throw new Exception(pinstanceid+"审批流程当前不是被中止,不能回退");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw ex;
		} finally {
			DbUtils.closeQuietly(pst2);
			DbUtils.closeQuietly(null,pst,rs);
		}
	}

	public boolean batCheckValidate(Account account, Map<String,Object>[] ents,
			Boolean pass, String checkaccount, Map<String,Object> extend, Connection con) {
		return true;
	}

	public String batAss(Account account, Map<String,Object>[] ents, Boolean pass)  {
		Map<String,Object> extend = new ConcurrentHashMap<String,Object>();
		return batAss(account, ents, pass, account.getAccountId(), extend);
	}
	public String batAss(Account account, Map<String,Object>[] ents, boolean pass, String checkaccount,
			Map<String,Object> extend)  {

		Connection con = null;
		PreparedStatement pst = null;
		String msg = "";
		Date now = new Date();
		long s = new Date().getTime();
		List<WfAttributeinfo> list = new ArrayList<WfAttributeinfo>();
		String error = "";
		try {
			con = systemDao.getUnautocommitConnection();
			String accountName = account.getAccountName();
			String accountId = checkaccount;
			int sum = 0;
			if (batCheckValidate(account, ents, pass, checkaccount, extend, con)) {
				for (int i = 0, j = ents.length; i < j; i++) {
					long ftime = new Date().getTime();
					try {
						Map<String,Object> ent = ents[i];
						String pinstanceid = (String) ent.get("pinstanceid");
						WfAttributeinfo attr = wfService.findCurNeedCheckAttr(con, pinstanceid);
						if(attr!=null){
							String checkman = attr.getCheckman();
							java.util.List<String> checkmans = new java.util.ArrayList<String>();
							  if("".equals(checkman) || checkman == null )
							     throw new Exception("无审批人");
							  else{
								  String[] checkmantmp = checkman.split(","); 
								  for(int k=0;k<checkmantmp.length;k++){
									  checkmans.add(checkmantmp[k]);
								  }
							  }
							  if(checkmans.contains(accountId) || allowAss(account,attr.getCheckman(),accountId)) {
								checkByManOri(account, attr.getObeattributeid().toString(),accountName+
										(pass ? "批量同意" : "批量不同意")
												+ DateUtil.formatDTime(now), pass,
												accountId, extend,  true, con);
								list.add(attr);
								sum++;
//								con.commit();
							}
						}
					} catch (Exception ex) {
						log.error(ex.getMessage(), ex);
						try {
							con.rollback();
						} catch (Exception ex3) {
							log.error(ex3.getMessage(), ex3);
						}
						error += "<br>" + "第" + (i + 1) + "条:"
								+ ex.getMessage();
					}
					log.info("审批第" + (i + 1) + "条用时:"
							+ (new Date().getTime() - ftime) / 1000);
				}
			}
			msg = "共完成审批" + sum + "条记录";
			if (!"".equals(error)) {
				msg += "<br><br>出现以下错误:" + error;
			}
			batCheckOver(account, con, list, pass);
			con.commit();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			try {
				con.rollback();
			} catch (Exception ex1) {
				log.error(ex1.getMessage(), ex1);
			}
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(con, pst, null);
			log.info(wfp.getProcesstype() + "审批所用时间:" + (new Date().getTime() - s) / 1000 + ", 结果: \n"+msg);
		}
		return msg;
	}

	public boolean batStopValidate(Account account, Map<String,Object>[] ents, String checkaccount,
			 Map<String,Object> extend, Connection con) {
		return true;
	}

	public String batStop(Account account, Map<String,Object>[] ents)  {
		Map<String,Object> extend = new ConcurrentHashMap<String,Object>();
		return batStop(account, ents, account.getAccountId(), extend);
	}
	
	public String batStop(Account account, Map<String,Object>[] ents, String checkaccount, Map<String,Object> extend)  {
		long s = new Date().getTime();
		String accountId = checkaccount;
		Connection con = null;
		String msg = "";
		int success = 0;
		try {
			con = systemDao.getUnautocommitConnection();
		
			List<WfAttributeinfo> list = new ArrayList<WfAttributeinfo>();
			String error = "";
			if (batStopValidate(account, ents, accountId, extend, con)) {
				for (int i = 0, j = ents.length; i < j; i++) {
					Map<String,Object> ent = ents[i];
					String pinid = (String) ent.get("pinstanceid");
					if (pinid == null || "".equals(pinid))
						continue;
					else {
						try {
							WfAttributeinfo attr = wfService.findCurNeedCheckAttr(con, pinid);
							if (attr != null) {
								String checkman = attr.getCheckman();
								java.util.List<String> checkmans = new java.util.ArrayList<String>();
								if ("".equals(checkman) || checkman == null)
									throw new Exception("无审批人");
								else {
									String[] checkmantmp = checkman.split(",");
									for (int k = 0; k < checkmantmp.length; k++) {
										checkmans.add(checkmantmp[k]);
									}
								}
								if(checkmans.contains(accountId) || allowAss(account,attr.getCheckman(),accountId)){
									stopByManOri(account, attr.getObeattributeid().toString(), account.getAccountName()
											+ "批量中止"
											+ DateUtil.formatDTime(new Date()),
											accountId, extend,  true, con);
									list.add(attr);
									success++;
//									con.commit();
									log.info(attr.getCheckinfo() + " 批量审批中止");
								}
							}
						} catch (Exception ex1) {
							log.error(ex1.getMessage(), ex1);
							error += "<br>" + "第" + (i + 1) + "条:"
									+ ex1.getMessage();
							try {
								con.rollback();
							} catch (Exception ex2) {
								log.error(ex1.getMessage(), ex2);
							}
						}
					}
				}
			}
			msg = "共完成审批" + success + "条记录";
			if (!"".equals(error)) {
				msg += "<br><br>出现以下错误:" + error;
			}
			batStopOver(account, con, list);
			con.commit();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			try {
				con.rollback();
			} catch (Exception ex1) {
				log.error(ex1.getMessage(), ex1);
			}
			throw new RuntimeException(ex);
		}finally{
			DbUtils.closeQuietly(con);
			log.info(msg);
			log.info(wfp.getProcesstype() + "中止审批所用时间:" + (new Date().getTime() - s) / 1000);
		}
		return msg;
	}

	public void batCheckOver(Account account, Connection con,
			List<WfAttributeinfo> list, boolean back) {
	}

	public void batStopOver(Account account, Connection con, List<WfAttributeinfo> list) {

	}

	
	public void createNextStep(Account account,WfAttributeinfo attr,Connection con) throws Exception{
		List<String> entids = new ArrayList<String>();
		entids.add(attr.getSourceid().toString());
		
		this.doStartAss(account, con, entids,null,attr.getProcessinstanceid(),attr.getWfcheckid());
			
	}
	
	protected boolean allowAss(Account account,String checkmans,String accountid){
		String pintype = wfp.getProcesstype();
		List<String> orgcheckman =  java.util.Arrays.asList(checkmans.split(","));
		List<String> checkman = new ArrayList<String>(orgcheckman);
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			con = systemDao.getUnautocommitConnection();
			pst = con.prepareStatement("select distinct grantto from wf_account_grant t where sysdate between nvl(startdate,sysdate) and nvl(enddate+1,sysdate) and usestatus = 1 and grantsource in ('"+checkmans.replaceAll(",","','")+"') and pintype='"+pintype+"'");
			rs = pst.executeQuery();
			while (rs.next()) {
				String[] accountids = rs.getString(1).split(",");
				for (int n = 0; n < accountids.length; n++) {
					if (!checkman.contains(accountids[n])) {
						checkman.add(accountids[n]);
					}
				}
			}
			rs.close();
			pst.close();
			
			con.commit();
		} catch (SQLException sqle) {
			log.error(sqle.getMessage(),sqle);
			try {
				if( con != null && !con.isClosed()) {
					con.rollback();
				}
			} catch (SQLException sqle2) {
				log.error(sqle2.getMessage(),sqle2);
			}
		} finally {
			DbUtils.closeQuietly(con, pst, rs);
		}
		
		if(checkman.contains(accountid)){
			return true;
		}
		else {
			return false;
		}
	}
}