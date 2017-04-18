package com.gzmpc.metadata.nav;

import com.gzmpc.metadata.MDObject;
import com.gzmpc.metadata.func.Func;

public class Nav extends MDObject{
	
	/**
	 * 导航栏
	 */
	private static final long serialVersionUID = -6936675036950399098L;
	
	private String icon;
	
	private Func[] list;
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Func[] getList() {
		return list;
	}
	public void setList(Func[] list) {
		this.list = list;
	}
	
	
}
