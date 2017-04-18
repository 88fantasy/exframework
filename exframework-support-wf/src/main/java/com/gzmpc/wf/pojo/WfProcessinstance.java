package com.gzmpc.wf.pojo;

import java.util.Date;

public class WfProcessinstance {
	private Long processinstanceid;
	private Long sourceid;
	private String pintype;
	private Short workflowstatus;
	private Date startdate;
	private Date completeddate;
	private String actiondo;
	private String creman;
	private String cremanname;
	private int dtltype;

	public WfProcessinstance() {
	}

	public Date getCompleteddate() {
		return completeddate;
	}

	public String getPintype() {
		return pintype;
	}

	public Long getProcessinstanceid() {
		return processinstanceid;
	}

	public Long getSourceid() {
		return sourceid;
	}

	public Date getStartdate() {
		return startdate;
	}

	public Short getWorkflowstatus() {
		return workflowstatus;
	}

	public void setWorkflowstatus(Short workflowstatus) {
		this.workflowstatus = workflowstatus;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public void setSourceid(Long sourceid) {
		this.sourceid = sourceid;
	}

	public void setProcessinstanceid(Long processinstanceid) {
		this.processinstanceid = processinstanceid;
	}

	public void setPintype(String pintype) {
		this.pintype = pintype;
	}

	public void setCompleteddate(Date completeddate) {
		this.completeddate = completeddate;
	}

	public String getActiondo() {
		return actiondo;
	}

	public void setActiondo(String actiondo) {
		this.actiondo = actiondo;
	}

	public int getDtltype() {
		return dtltype;
	}

	public void setDtltype(int dtltype) {
		this.dtltype = dtltype;
	}

	public String getCreman() {
		return creman;
	}

	public void setCreman(String creman) {
		this.creman = creman;
	}

	public String getCremanname() {
		return cremanname;
	}

	public void setCremanname(String cremanname) {
		this.cremanname = cremanname;
	}

}