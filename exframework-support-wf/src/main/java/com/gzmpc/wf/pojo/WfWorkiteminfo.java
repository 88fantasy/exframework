package com.gzmpc.wf.pojo;

import java.util.Date;

public class WfWorkiteminfo {
	private Long workitemid;
	private Long attributeid;
	private Long processinstanceid;
	private String checkman;
	private Date startdate;
	private Date completeddate;
	private Short state;

	public WfWorkiteminfo() {
	}

	public Long getAttributeid() {
		return attributeid;
	}

	public String getCheckman() {
		return checkman;
	}

	public Long getProcessinstanceid() {
		return processinstanceid;
	}

	public Date getStartdate() {
		return startdate;
	}

	public Short getState() {
		return state;
	}

	public void setWorkitemid(Long workitemid) {
		this.workitemid = workitemid;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public void setProcessinstanceid(Long processinstanceid) {
		this.processinstanceid = processinstanceid;
	}

	public void setCompleteddate(Date completeddate) {
		this.completeddate = completeddate;
	}

	public void setCheckman(String checkman) {
		this.checkman = checkman;
	}

	public void setAttributeid(Long attributeid) {
		this.attributeid = attributeid;
	}

	public Long getWorkitemid() {
		return workitemid;
	}

	public Date getCompleteddate() {
		return completeddate;
	}

}