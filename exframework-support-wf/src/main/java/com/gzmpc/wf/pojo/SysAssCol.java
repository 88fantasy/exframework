package com.gzmpc.wf.pojo;

public class SysAssCol {

  private Long asscolid;
  private Long wfcheckid;
  private String colname;
  private int noviewflag;
  private int readonly;
  private Long roleId;

  public Long getRoleId() {
	return roleId;
}

public void setRoleId(Long roleId) {
	this.roleId = roleId;
}

public SysAssCol() {
  }

  public Long getAsscolid() {
    return asscolid;
  }

  public void setAsscolid(Long asscolid) {
    this.asscolid = asscolid;
  }

  public String getColname() {
    return colname;
  }

  public void setColname(String colname) {
    this.colname = colname;
  }

  public int getReadonly() {
    return readonly;
  }

  public void setReadonly(int readonly) {
    this.readonly = readonly;
  }

  public int getNoviewflag() {
    return noviewflag;
  }

  public void setNoviewflag(int noviewflag) {
    this.noviewflag = noviewflag;
  }

  public Long getWfcheckid() {
    return wfcheckid;
  }

  public void setWfcheckid(Long wfcheckid) {
    this.wfcheckid = wfcheckid;
  }
}