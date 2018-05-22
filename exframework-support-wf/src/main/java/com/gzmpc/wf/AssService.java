package com.gzmpc.wf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.exception.StartException;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.service.AccountService;
import com.gzmpc.support.common.exception.BuildException;
import com.gzmpc.util.SpringContextUtils;
import com.gzmpc.utils.Const;
import com.gzmpc.wf.meta.WfMetaData;
import com.gzmpc.wf.pojo.WfProcesstypeId;

import com.gzmpc.wf.AssInstance;

@Service
public class AssService {
	private Log log = LogFactory.getLog(AssService.class.getName());

	@Autowired
	WfMetaData wfMetaData;
	
	@Autowired
	SystemDao systemDao;
	
	@Autowired
	AccountService accountService;

	public void doStartAss(Account account, String[] ids, String pintype, Map<String,Object> param)
			throws StartException, NotFoundException {
		long s = new Date().getTime();
		int length = ids.length;
		List<String> entids = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			entids.add(ids[i]);
		}
		AssInstance ins = getInstance(pintype);
		ins.doStartAss(account, entids, param, null, null);
		log.info(pintype+"提交" + ids.length + "条,共耗时" + ((new Date().getTime() - s) / 1000) + "秒");
	}
	
	public void check(Account account, String attrid, String pintype, String checkText, boolean checkResult, String checkaccount, Map<String,Object> extend)
			throws NotFoundException {
		AssInstance ins = getInstance(pintype);
		ins.checkByMan(account, attrid, checkText, checkResult, checkaccount, extend);
	}
	
	public void stop(Account account, String attrid, String pintype, String checkText, String checkaccount, Map<String,Object> extend)
			throws NotFoundException {
		AssInstance ins = getInstance(pintype);
		ins.stopByMan(account, attrid, checkText, checkaccount, extend);
	}
	
	public String batcheck(Account account, Map<String,Object>[] ents, String pintype, boolean checkResult, String checkaccount, Map<String,Object> extend)
			throws NotFoundException {
		AssInstance ins = getInstance(pintype);
		return ins.batAss(account, ents, checkResult, checkaccount, extend);
	}
	
	public String batstop(Account account, Map<String,Object>[] ents, String pintype, String checkaccount, Map<String,Object> extend)
			throws NotFoundException {
		AssInstance ins = getInstance(pintype);
		return ins.batStop(account, ents, checkaccount, extend);
	}

	public JSONObject showAttr(Account account, long procid) throws BuildException {
		JSONObject result = new JSONObject();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = systemDao.getUnautocommitConnection();
			
			pst = con.prepareStatement(" select o.cremanname,o.creman,o.startdate, t.checkinfo,t.checkman, "
					+ " t.workflowstatus,t.confirmmanname,t.checkresult,t.countersign "
					+ " from obe_attributeinfo t,obe_processinstance o "
					+ " where t.processinstanceid = o.processinstanceid "
					+ " and t.processinstanceid = ? order by t.corder asc");
			pst.setLong(1, procid);
			rs = pst.executeQuery();
			
			JSONArray array = new JSONArray();
			while (rs.next()) {
				if(array.length()==0) {
					result.put("cremanname", rs.getString(1));
					result.put("creman", rs.getString(2));
					result.put("startdate", rs.getString(3));
				}
				
				JSONObject row = new JSONObject();
				String checkmans = rs.getString(5);
				short workflowstatus = rs.getShort(6);
				
				String[] accounts = checkmans.split(",");
				String names = "";
				for(int i=0,j=accounts.length;i<j;i++) {
					try{
						names += accountService.getAccountName(accounts[i], false) + ",";
					} catch ( NotFoundException e ) {
						names += "不存在帐号" + ",";
					}
				}
				if( names.length() > 0) {
					names = names.substring(0, names.length()-1);
				}
				
				row.put("checkinfo", rs.getString(4));
				row.put("checkmans", checkmans);
				row.put("checkmannames", names);
				row.put("confirmmanname", rs.getString(7));
				row.put("checkresult", rs.getInt(8)==1);
				row.put("countersign", rs.getString(9));
				String status = "默认";
				switch (workflowstatus) {
					case Const.WORKFLOW_STATUS_CHECKPASS:
						status = "通过";
						break;
					case Const.WORKFLOW_STATUS_NOPASS:
						status = "不通过";
						break;
					case Const.WORKFLOW_STATUS_STOP:
						status = "中止";
						break;
					case Const.WORKFLOW_STATUS_NOCHECK:
						status = "待审核";
						break;
				}
				row.put("workflowstatus", status);
				array.put(row);
			}
			rs.close();
			pst.close();
			
			con.commit();
		} catch (SQLException ex) {
			log.error(ex.getMessage(), ex);
			try {
				if( con != null && !con.isClosed()) {
					con.rollback();
				}
			} catch (SQLException ex1) {
				log.error(ex1.getMessage(), ex1);
			}
			throw new BuildException("获取审批步骤列表出现错误:"+ex.getMessage());
		} finally {
			DbUtils.closeQuietly(con, pst, rs);
		}
		return result;
	}

	private AssInstance getInstance(String pintype) throws NotFoundException {
		WfProcesstypeId wfp = wfMetaData.findWfProcesstypeIdByCode(pintype);
		String instance = wfp.getInstance();
		if (instance == null || "".equals(instance)) {
			instance = "";
		}
		ApplicationContext ac = SpringContextUtils.getApplicationContext();
		if (ac.containsBean(instance)) {
			AssInstance ins = (AssInstance) ac.getBean(instance, wfp);
			return ins;
		} else {
			throw new NotFoundException("找不到实现类bean:"+instance);
		}
	}
}
