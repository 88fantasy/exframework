package com.gzmpc.metadata.sys;

import java.io.Serializable;
import java.util.*;

/**
 *对表 SYS_ROLE：角色定义  进行数据映射的bean
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class Role implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = -2136988627742271073L;
//
  private List modules = new ArrayList();
  private Long roleId;
  private String roleName; //角色名称
  private String roleDesc; //角色描述
  private int stageid;
  private Long prole_id;

  //角色ID
  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }

  public String getRoleDesc() {
    return roleDesc;
  }

  public List getModules() {
    return modules;
  }

  public void setModules(List modules) {
    this.modules = modules;
  }

  public ArrayList retModules() {
    return (ArrayList) modules;
  }

  public void saveModules(ArrayList modules) {
    this.modules = modules;
  }

public int getStageid() {
	return stageid;
}

public void setStageid(int stageid) {
	this.stageid = stageid;
}

public Long getProle_id() {
	return prole_id;
}

public void setProle_id(Long prole_id) {
	this.prole_id = prole_id;
}

}