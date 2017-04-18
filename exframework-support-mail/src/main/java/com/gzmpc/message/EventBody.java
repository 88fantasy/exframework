package com.gzmpc.message;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class EventBody {
  private String errcode;
  private String errmsg;
  private String orgdata;

  public EventBody() {
  }
  public String getErrcode() {
    return errcode;
  }
  public void setErrcode(String errcode) {
    this.errcode = errcode;
  }
  public String getErrmsg() {
    return errmsg;
  }
  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }
  public String getOrgdata() {
    return orgdata;
  }
  public void setOrgdata(String orgdata) {
    this.orgdata = orgdata;
  }

}