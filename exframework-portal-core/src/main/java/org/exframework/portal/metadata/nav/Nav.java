package org.exframework.portal.metadata.nav;

import org.exframework.portal.metadata.Meta;
import org.exframework.portal.metadata.module.Module;

public class Nav extends Meta{
	
	/**
	 * 导航栏
	 */
	private static final long serialVersionUID = -6936675036950399098L;
	
	private String icon;
	
	private Module[] list;
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Module[] getList() {
		return list;
	}
	public void setList(Module[] list) {
		this.list = list;
	}
	
	
}
