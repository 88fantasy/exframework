package com.gzmpc.message;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MessageBody {
  private int    msgtype;
  private String from;
  private String message;
  private String orgdata;
  private String subject;

  public String getFrom() {
    return from;
  }
  public String getMessage() {
    return message;
  }
  public void setFrom(String from) {
    this.from = from;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public int getMsgtype() {
    return msgtype;
  }
  public void setMsgtype(int msgtype) {
    this.msgtype = msgtype;
  }
  public String getOrgdata() {
    return orgdata;
  }
  public void setOrgdata(String orgdata) {
    this.orgdata = orgdata;
  }
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
}