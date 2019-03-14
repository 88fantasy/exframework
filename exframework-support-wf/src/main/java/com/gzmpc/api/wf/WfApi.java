package com.gzmpc.api.wf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gzmpc.exception.NotAuthorizedException;
import com.gzmpc.exception.NotFoundException;
import com.gzmpc.exception.StartException;
import com.gzmpc.login.LoginService;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.support.common.exception.BuildException;
import com.gzmpc.util.JSONUtil;
import com.gzmpc.wf.AssService;

@Controller
@Path("wf")
public class WfApi {

	private Log log = LogFactory.getLog(WfApi.class.getName());

	@Autowired
	HttpServletRequest request;

	@Autowired
	LoginService loginService;

	@Autowired
	AssService assService;

	@Autowired
	JSONUtil jsonUtil;

	/**
	 * 根据配置启动审批实例
	 * 
	 * @param ids 业务单id,用逗号分割
	 * @param pintype 审批类型
	 * @throws NotAuthorizedException
	 * @throws StartException
	 * @throws NotFoundException
	 */

	@Path("start")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response start(@FormParam("ids") String ids, @FormParam("pintype") String pintype,
			@FormParam("param") String param) throws NotAuthorizedException, StartException, NotFoundException {
		Account account = loginService.getAccount(request);

		Map<String, Object> params = null;
		if (param != null && !"".equals(param.trim())) {
			params = jsonUtil.toMap(JSONObject.parseObject(param));
		}
		assService.doStartAss(account, ids.split(","), pintype, params);
		return Response.ok().build();

	}

	/**
	 * 单步审批(同意或不同意)
	 * @param pintype 审批类型
	 * @param attrid 步骤id
	 * @param checkText	审批意见
	 * @param checkResult 审批结果(true同意,false不同意)
	 * @param checkaccount 审批人(由于存在授权审批情况,不一定与操作人相同)
	 * @param ext 额外参数
	 * @return
	 * @throws NotAuthorizedException
	 * @throws NotFoundException
	 */
	@Path("check")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response check(@FormParam("pintype") String pintype, 
			@FormParam("attrid") String attrid,
			@FormParam("checkText") String checkText,
			@FormParam("checkResult") boolean checkResult,
			@FormParam("checkaccount") String checkaccount,
			@FormParam("extend") String ext) throws NotAuthorizedException, NotFoundException {
		Account account = loginService.getAccount(request);
		Map<String, Object> extend = new ConcurrentHashMap<String,Object>();
		if (ext != null && !"".equals(ext.trim())) {
			extend = jsonUtil.toMap(JSONObject.parseObject(ext));
		}
		assService.check(account, attrid, pintype, checkText, checkResult, checkaccount, extend);
		return Response.ok().build();

	}

	/**
	 * 单步中止
	 * @param pintype 审批类型
	 * @param attrid 步骤id
	 * @param checkText	审批意见
	 * @param checkaccount 审批人(由于存在授权审批情况,不一定与操作人相同)
	 * @param ext 额外参数
	 * @return
	 * @throws NotAuthorizedException
	 * @throws NotFoundException
	 */
	@Path("stop")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response stop(@FormParam("pintype") String pintype, 
			@FormParam("attrid") String attrid,
			@FormParam("checkText") String checkText,
			@FormParam("checkaccount") String checkaccount,
			@FormParam("extend") String ext) throws NotAuthorizedException, NotFoundException {
		Account account = loginService.getAccount(request);
		Map<String, Object> extend = new ConcurrentHashMap<String,Object>();
		if (ext != null && !"".equals(ext.trim())) {
			extend = jsonUtil.toMap(JSONObject.parseObject(ext));
		}
		assService.stop(account, attrid, pintype, checkText, checkaccount, extend);
		return Response.ok().build();

	}
	
	
	/**
	 * 批量审批
	 * @param pintype
	 * @param ent
	 * @param checkResult
	 * @param checkaccount
	 * @param ext
	 * @return
	 * @throws NotAuthorizedException
	 * @throws NotFoundException
	 */
	@Path("batcheck")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response batcheck(@FormParam("pintype") String pintype, 
			@FormParam("ents") String ent,
			@FormParam("checkresult") boolean checkResult,
			@FormParam("checkaccount") String checkaccount,
			@FormParam("extend") String ext) throws NotAuthorizedException, NotFoundException {
		Account account = loginService.getAccount(request);
		if (ent == null || "".equals(ent.trim())) {
			throw new NotFoundException("缺少必要字段ents");
		}
		String json = ent.trim();
		Map<String,Object>[] ents = null;
		if(json.startsWith("[")) {//是json数组
			ents = jsonUtil.toMapArray(JSONArray.parseArray(json));
		}
		else {
			Map<String,Object> map = jsonUtil.toMap(JSONObject.parseObject(json));
			ents = new Map[]{map};
		}
		Map<String, Object> extend = new ConcurrentHashMap<String,Object>();
		if (ext != null && !"".equals(ext.trim())) {
			extend = jsonUtil.toMap(JSONObject.parseObject(ext));
		}
		String result = assService.batcheck(account, ents, pintype, checkResult, checkaccount, extend);
		return Response.ok(result).build();

	}
	
	@Path("batstop")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response batstop(@FormParam("pintype") String pintype, 
			@FormParam("ents") String ent,
			@FormParam("checkaccount") String checkaccount,
			@FormParam("extend") String ext) throws NotAuthorizedException, NotFoundException {
		Account account = loginService.getAccount(request);
		if (ent == null || "".equals(ent.trim())) {
			throw new NotFoundException("缺少必要字段ents");
		}
		String json = ent.trim();
		Map<String,Object>[] ents = null;
		if(json.startsWith("[")) {//是json数组
			ents = jsonUtil.toMapArray(JSONArray.parseArray(json));
		}
		else {
			Map<String,Object> map = jsonUtil.toMap(JSONObject.parseObject(json));
			ents = new Map[]{map};
		}
		Map<String, Object> extend = new ConcurrentHashMap<String,Object>();
		if (ext != null && !"".equals(ext.trim())) {
			extend = jsonUtil.toMap(JSONObject.parseObject(ext));
		}
		String result = assService.batstop(account, ents, pintype, checkaccount, extend);
		return Response.ok(result).build();
	}

	@Path("show")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response show(@QueryParam("procid") int procid) throws NotAuthorizedException, BuildException {

		Account account = loginService.getAccount(request);
		JSONObject result = assService.showAttr(account, procid);
		return Response.ok(result.toString()).build();

	}
}
