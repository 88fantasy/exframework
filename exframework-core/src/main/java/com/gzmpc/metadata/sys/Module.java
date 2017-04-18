package com.gzmpc.metadata.sys;

import java.io.Serializable;
import java.util.List;

/**
 * 对表 SYS_MODULE：功能模块 进行数据映射的bean
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 */
public class Module implements Serializable {

	private static final long serialVersionUID = -7275087103665173246L;

	public static final long MODULETYPE_FOLDER = 1;
	public static final long MODULETYPE_FUNCTION = 2;
	public static final long MODULETYPE_OPERATION = 3;

	public static final long MODULEVALID_VALID = 1;
	public static final long MODULEVALID_INVALID = 0;
	
	// 模块ID
	private String moduleId;
	private String moduleOpcode;
	private String moduleName;
	private String moduleEntry;
	private String pmoduleId;
	private Long moduleOrder;
	private Long moduleType;
	private boolean valid;
	private boolean notdisplay;
	private String moduleMemo;
	
	private List<Module> children;


	public String getModuleEntry() {
		return moduleEntry;
	}


	public String getModuleId() {
		return moduleId;
	}

	public String getModuleMemo() {
		return moduleMemo;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getModuleOpcode() {
		return moduleOpcode;
	}

	public Long getModuleOrder() {
		return moduleOrder;
	}

	public Long getModuleType() {
		return moduleType;
	}


	public String getPmoduleId() {
		return pmoduleId;
	}


	public void setModuleEntry(String moduleEntry) {
		this.moduleEntry = moduleEntry;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public void setModuleMemo(String moduleMemo) {
		this.moduleMemo = moduleMemo;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setModuleOpcode(String moduleOpcode) {
		this.moduleOpcode = moduleOpcode;
	}

	public void setModuleOrder(Long moduleOrder) {
		this.moduleOrder = moduleOrder;
	}

	public void setModuleType(Long moduleType) {
		this.moduleType = moduleType;
	}

	public void setPmoduleId(String pmoduleId) {
		this.pmoduleId = pmoduleId;
	}

	public List<Module> getChildren() {
		return children;
	}

	public void setChildren(List<Module> children) {
		this.children = children;
	}


	public boolean isValid() {
		return valid;
	}


	public void setValid(boolean valid) {
		this.valid = valid;
	}


	public boolean isNotdisplay() {
		return notdisplay;
	}


	public void setNotdisplay(boolean notdisplay) {
		this.notdisplay = notdisplay;
	}

}