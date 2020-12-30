package com.gzmpc.metadata.attribute;

import com.gzmpc.metadata.Meta;
import com.gzmpc.metadata.di.DataItem;

/**
 *
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 数据库SYS_ATTRIBUTECFG表各列映射对象。form名可以不赋预
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
public class Attribute extends Meta {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4786625306700973711L;
	
	private Long attributeid;// 属性ID
	private boolean required;// 是否必填
	private String initValue;// 初始值
	private boolean readonly;// 只读
	private String visualaidconfig;// 控件辅助
	private String tooltip;// 提示
	private String dataItemCode;// 显示风格
	private String shortcutkey;// 快捷键
	private String onclickquery;// 回车自动查询
	private DataItem di;
	private String formcode;

	public Long getAttributeid() {
		return attributeid;
	}

	public String getDataItemCode() {
		return dataItemCode;
	}

	public String getInitValue() {
		return initValue;
	}

	public String getTooltip() {
		return tooltip;
	}

	public String getVisualaidconfig() {
		return visualaidconfig;
	}

	public void setVisualaidconfig(String visualaidconfig) {
		this.visualaidconfig = visualaidconfig;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}

	public void setDataItemCode(String dataItemCode) {
		this.dataItemCode = dataItemCode;
	}

	public void setAttributeid(Long attributeid) {
		this.attributeid = attributeid;
	}

	public boolean getRequired() {
		return required;
	}

	public boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String retCode() {
		return getKey();
	}

	public String getShortcutkey() {
		return shortcutkey;
	}

	public void setShortcutkey(String shortcutkey) {
		this.shortcutkey = shortcutkey;
	}

	public String retTitle() {
		return di.getName();
	}

	public String getOnclickquery() {
		return onclickquery;
	}

	public void setOnclickquery(String onclickquery) {
		this.onclickquery = onclickquery;
	}

	public DataItem getDi() {
		return di;
	}

	public void setDi(DataItem di) {
		this.di = di;
	}

	public String getFormcode() {
		return formcode;
	}

	public void setFormcode(String formcode) {
		this.formcode = formcode;
	}


}
