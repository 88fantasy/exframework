package com.gzmpc.wf.pojo;

import java.util.Date;

public class WfAttributeinfo {
	private Long obeattributeid;
	private String checkinfo;
	private Long processinstanceid;
	private String pintype;
	private String checkman;
	private Long corder;
	private Short state;
	private String confirmmanname;
	private Short workflowstatus;
	private String checkresult;
	private Long sourceid;
	private Long wfcheckid;
	private Date limitdate;
	private short filtertype;
	private String memo;
	private String actiondo;
	private Short countersign;

	public WfAttributeinfo() {
	}

	public String getCheckinfo() {
		return checkinfo;
	}

	public String getCheckman() {
		return checkman;
	}

	public String getCheckresult() {
		return checkresult;
	}

	public String getConfirmmanname() {
		return confirmmanname;
	}

	public Long getCorder() {
		return corder;
	}

	public Long getObeattributeid() {
		return obeattributeid;
	}

	public Long getProcessinstanceid() {
		return processinstanceid;
	}

	public Long getSourceid() {
		return sourceid;
	}

	public Short getState() {
		return state;
	}

	public Short getWorkflowstatus() {
		return workflowstatus;
	}

	public void setWorkflowstatus(Short workflowstatus) {
		this.workflowstatus = workflowstatus;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public void setSourceid(Long sourceid) {
		this.sourceid = sourceid;
	}

	public void setProcessinstanceid(Long processinstanceid) {
		this.processinstanceid = processinstanceid;
	}

	public void setObeattributeid(Long obeattributeid) {
		this.obeattributeid = obeattributeid;
	}

	public void setCorder(Long corder) {
		this.corder = corder;
	}

	public void setConfirmmanname(String confirmmanname) {
		this.confirmmanname = confirmmanname;
	}

	public void setCheckresult(String checkresult) {
		this.checkresult = checkresult;
	}

	public void setCheckman(String checkman) {
		this.checkman = checkman;
	}

	public void setCheckinfo(String checkinfo) {
		this.checkinfo = checkinfo;
	}

	public String getPintype() {
		return pintype;
	}

	public void setPintype(String pintype) {
		this.pintype = pintype;
	}

	public Long getWfcheckid() {
		return wfcheckid;
	}

	public void setWfcheckid(Long wfcheckid) {
		this.wfcheckid = wfcheckid;
	}

	public Date getLimitdate() {
		return limitdate;
	}

	public void setLimitdate(Date limitdate) {
		this.limitdate = limitdate;
	}

	public short getFiltertype() {
		return filtertype;
	}

	public void setFiltertype(short filtertype) {
		this.filtertype = filtertype;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getActiondo() {
		return actiondo;
	}

	public void setActiondo(String actiondo) {
		this.actiondo = actiondo;
	}

	public Short getCountersign() {
		return countersign;
	}

	public void setCountersign(Short countersign) {
		this.countersign = countersign;
	}
	
}