package com.gzmpc.api.wf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gzmpc.dao.SystemDao;
import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.exception.BuildException;
import com.gzmpc.exception.StartException;
import com.gzmpc.login.LoginService;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.util.JSONUtil;
import com.gzmpc.utils.Const;
import com.gzmpc.utils.QuerySupport;
import com.gzmpc.wf.AssService;

@Controller
@Path("wfmgr")
public class WfManageApi {

	private Log log = LogFactory.getLog(WfManageApi.class.getName());

	@Autowired
	HttpServletRequest request;

	@Autowired
	LoginService loginService;
	
	@Autowired
	SystemDao systemDao;


	/**
	 * 作废审批授权
	 * 
	 * @param ids 业务单id,用逗号分割
	 * @param pintype 审批类型
	 * @throws NotAuthorizedException
	 * @throws StartException
	 * @throws NotFoundException
	 */

	@Path("invalidGrant")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response invalidGrant(@FormParam("ids") String ids) throws NotAuthorizedException, NotFoundException, BuildException {
		if(ids!=null){
			Account account = loginService.getAccount(request);
			Connection con = null;
			StringBuffer hhsl_update = new StringBuffer();
			hhsl_update.append(" update wf_account_grant set usestatus = 0 " +
					"where grantsource = ? and grantid in ("+ids+")");
			PreparedStatement pst = null;
			try {
				con = systemDao.getUnautocommitConnection();
				pst = con.prepareStatement(hhsl_update.toString());
				pst.setString(1, account.getAccountId());
				pst.executeUpdate();
				pst.close();
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
				throw new BuildException(e.getMessage());
			}finally{
				DbUtils.closeQuietly(con,pst,null);
			}
			return Response.ok().build();
		}
		else {
			throw new NotFoundException("缺少ids");
		}
		
	}
	
	
	public void doSave(String processtype, Map[] wfFilter) {
	    Connection con = null;
	    PreparedStatement pst = null;
	    try {
	      con = systemDao.getUnautocommitConnection();
	      StringBuffer delsql = new StringBuffer();
	      //清空以前存在的记录
	      delsql.append(" delete from wf_check where filterid in(select filterid ");
	      delsql.append(" from wf_filterconf where processtype =?) ");
	      pst = con.prepareStatement(delsql.toString());
	      pst.setString(1, processtype);
	      pst.executeUpdate();
	      pst.close();
	      //插入当前的记录
	      //先遍历当前审批类型的所有条件定义，方便下面获取表达式
	      StringBuffer insertsql = new StringBuffer();
	      insertsql.append(
	          " insert into wf_check( wfcheckid,wfroleid,corder,memo, ");
	      insertsql.append(" wffilter,checkexpparam,filterid) values(");
	      insertsql.append(" ?,?,?,?,?,?,?) ");
	      pst = con.prepareStatement(insertsql.toString());
	      int length = wfFilter.length;
	      int corder = 0;

	      for (int i = 0; i < length; i++) {
	        Map data = wfFilter[i];
	        String opera = (String) data.get(Const.GRID_OPER_FLAG);
	        if (Const.GRID_OPER_FLAG_DELETE.equals(opera))
	          continue;
	        corder = corder + 20;
	        String wfcheckid = (String) data.get("wfcheckid");
	        String wfroleid = (String) data.get("wfroleid");
//	        String corder = (String)data.get("corder");
	        String memo = (String) data.get("memo");
	        String wffilter = (String) data.get("wffilter");
	        String checkexpparam = (String) data.get("checkexpparam");
	        String filterid = (String) data.get("filterid");
	        pst.setLong(1, new Long(wfcheckid).longValue());
	        pst.setString(2, wfroleid);
	        pst.setLong(3, new Long(corder).longValue());
	        pst.setString(4, memo);
	        pst.setString(5, wffilter);
	        pst.setString(6, checkexpparam);
	        pst.setLong(7, new Long(filterid).longValue());
	        pst.addBatch();
	      }
	      pst.executeBatch();
	      con.commit();
	    }
	    catch (Exception ex) {
	      log.error(ex.getMessage(), ex);
	      try {
	        con.rollback();
	      }
	      catch (SQLException ex1) {
	      }
	      throw new RuntimeException(ex.getMessage());
	    }
	    finally {
	      DbUtils.closeQuietly(con, pst, null);
	    }

	  }

	  public void doDelete(String processtype) {
	    Connection con = null;
	    PreparedStatement pst = null;
	    try {
	      con = systemDao.getUnautocommitConnection();
	      con.setAutoCommit(false);
	      StringBuffer delsql = new StringBuffer();
	      //清空以前存在的记录
	      delsql.append(" delete from wf_check where filterid in(select filterid ");
	      delsql.append(" from wf_filterconf where processtype =?) ");
	      pst = con.prepareStatement(delsql.toString());
	      pst.setString(1, processtype);
	      pst.executeUpdate();
	      con.commit();
	    }
	    catch (Exception ex) {
	      log.error(ex.getMessage(), ex);
	      try {
	        con.rollback();
	      }
	      catch (SQLException ex1) {
	      }
	      throw new RuntimeException(ex.getMessage());
	    }
	    finally {
	      DbUtils.closeQuietly(con, pst, null);
	    }
	  }
}
