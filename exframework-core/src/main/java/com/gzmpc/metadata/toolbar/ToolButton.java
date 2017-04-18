package com.gzmpc.metadata.toolbar;

/**
 *
 * <p>
 * Title:菜单栏上的各个操作
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
 * @author rwe
 * @version 1.0
 */
public class ToolButton {
	private long buttonid; // id
	private String toolbartype; // 操作类型。可为module和Text操作;
	private String title; // 如果是Text类型的，则该信息表示页面显示的中文名
	private String texthandler; // Text类型的事件类型
	private String tooltip; // 提示信息
	private String iconcls; // 样式
	private String shortcutkey;// 快捷键
	private String moduleId;// 模块受权限所用

	public ToolButton() {
	}

	public long getButtonid() {
		return buttonid;
	}



	public void setButtonid(long buttonid) {
		this.buttonid = buttonid;
	}



	public String getIconcls() {
		return iconcls;
	}

	public String getTitle() {
		return title;
	}

	public String getTexthandler() {
		return texthandler;
	}

	public String getToolbartype() {
		return toolbartype;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public void setToolbartype(String toolbartype) {
		this.toolbartype = toolbartype;
	}

	public void setTexthandler(String texthandler) {
		this.texthandler = texthandler;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	public String getShortcutkey() {
		return shortcutkey;
	}

	public void setShortcutkey(String shortcutkey) {
		this.shortcutkey = shortcutkey;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}