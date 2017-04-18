package com.gzmpc.metadata.sys;

import java.io.Serializable;

public class No implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 5372899744996214623L;
private String noid;
  private String noname;
  private Long nolength;
  private Long notype;
  private String prefix;
  private String postfix;
  private int stageid; //clq 20120628
  private int catchsize;
  
  public No() {
  }
  public String getNoid() {
    return noid;
  }
  public String getNoname() {
    return noname;
  }
  public Long getNolength() {
    return nolength;
  }
  public Long getNotype() {
    return notype;
  }
  public String getPrefix() {
    return prefix;
  }
  public String getPostfix() {
    return postfix;
  }
  public void setNoid(String noid) {
    this.noid=noid;
  }
  public void setNoname(String noname) {
    this.noname=noname;
  }
  public void setNolength(Long nolength) {
    this.nolength=nolength;
  }
  public void setNotype(Long notype) {
    this.notype=notype;
  }
  public void setPrefix(String prefix) {
    this.prefix=prefix;
  }
  public void setPostfix(String postfix) {
    this.postfix=postfix;
  }
  public Object clone() {
    Object o = null;
    try {
      o = super.clone();
    }
    catch (CloneNotSupportedException ex) {
      System.out.println(o);
    }
    return o;
  }
  public boolean equals(Object obj) {
    No o = (No)obj;

    boolean result = true
        && (noid == o.noid || noid.equals(o.noid))
        && (noname == o.noname || noname.equals(o.noname))
        && (nolength == o.nolength || nolength.equals(o.nolength))
        && (notype == o.notype || notype.equals(o.notype))
        && (prefix == o.prefix || prefix.equals(o.prefix))
        && (postfix == o.postfix || postfix.equals(o.postfix))
    ;
    return result;
  }

  public static void main(String[] args)  {
  }
public int getStageid() {
	return stageid;
}
public void setStageid(int stageid) {
	this.stageid = stageid;
}
public int getCatchsize() {
	return catchsize;
}
public void setCatchsize(int catchsize) {
	this.catchsize = catchsize;
}
}
