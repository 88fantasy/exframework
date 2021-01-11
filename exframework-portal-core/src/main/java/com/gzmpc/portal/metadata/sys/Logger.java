package com.gzmpc.portal.metadata.sys;

import java.util.Date;

public class Logger {
  private Long loggerid;//主键
  private String cremanname;//制单人
  private Long cremanid;//制单人ID
  private Date credate;//制单时间
  private String funcname;//功能名
  private String operatype;//操作类型
  private String content;//操作内容
  private String moduleid;//模块ID
  public Logger() {
  }
  public Long getLoggerid() {
    return loggerid;
  }
  public void setLoggerid(Long loggerid) {
    this.loggerid = loggerid;
  }
  public String getCremanname() {
    return cremanname;
  }
  public void setCremanname(String cremanname) {
    this.cremanname = cremanname;
  }
  public Long getCremanid() {
    return cremanid;
  }
  public void setCremanid(Long cremanid) {
    this.cremanid = cremanid;
  }
  public Date getCredate() {
    return credate;
  }
  public void setCredate(Date credate) {
    this.credate = credate;
  }
  public String getFuncname() {
    return funcname;
  }
  public void setFuncname(String funcname) {
    this.funcname = funcname;
  }
  public String getOperatype() {
    return operatype;
  }
  public void setOperatype(String operatype) {
    this.operatype = operatype;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getModuleid() {
    return moduleid;
  }
  public void setModuleid(String moduleid) {
    this.moduleid = moduleid;
  }

}