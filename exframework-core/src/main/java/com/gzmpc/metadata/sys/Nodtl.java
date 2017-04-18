package com.gzmpc.metadata.sys;

import java.io.Serializable;

public class Nodtl implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = -9058487526947404883L;
private java.lang.Long nodtlid;
  private java.lang.Long noyear;
  private java.lang.Long nomonth;
  private java.lang.Long noday;
  private String prefix;
  private String postfix;
  private java.lang.Long nextid;
  private String noid;
  private Long prenextid;//预取下一值 clq 20120628
  private int lockflag;//锁标志
  private int stageid;
  public Nodtl() {
  }
  public java.lang.Long getNodtlid() {
    return nodtlid;
  }
  public java.lang.Long getNoyear() {
    return noyear;
  }
  public java.lang.Long getNomonth() {
    return nomonth;
  }
  public java.lang.Long getNoday() {
    return noday;
  }
  public String getPrefix() {
    return prefix;
  }
  public String getPostfix() {
    return postfix;
  }
  public java.lang.Long getNextid() {
    return nextid;
  }
  public String getNoid() {
    return noid;
  }
  public void setNodtlid(java.lang.Long nodtlid) {
    this.nodtlid=nodtlid;
  }
  public void setNoyear(java.lang.Long noyear) {
    this.noyear=noyear;
  }
  public void setNomonth(java.lang.Long nomonth) {
    this.nomonth=nomonth;
  }
  public void setNoday(java.lang.Long noday) {
    this.noday=noday;
  }
  public void setPrefix(String prefix) {
    this.prefix=prefix;
  }
  public void setPostfix(String postfix) {
    this.postfix=postfix;
  }
  public void setNextid(java.lang.Long nextid) {
    this.nextid=nextid;
  }
  public void setNoid(String noid) {
    this.noid=noid;
  }
  public static void main(String[] args) {

  }
public Long getPrenextid() {
	return prenextid;
}
public void setPrenextid(Long prenextid) {
	this.prenextid = prenextid;
}
public int getLockflag() {
	return lockflag;
}
public void setLockflag(int lockflag) {
	this.lockflag = lockflag;
}
public int getStageid() {
	return stageid;
}
public void setStageid(int stageid) {
	this.stageid = stageid;
}
}
