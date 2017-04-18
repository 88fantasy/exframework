package com.gzmpc.metadata.toolbar;

import com.gzmpc.metadata.MDObject;

/**
 *
 * <p>
 * Title: 菜单栏定义
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
public class ToolBar extends MDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2416105537621411136L;
	
	private ToolButton[] toolButtons;

	public ToolBar() {
	}

	public ToolButton[] getToolButtons() {
		return toolButtons;
	}

	public void setToolButtons(ToolButton[] toolButtons) {
		this.toolButtons = toolButtons;
	}
}
