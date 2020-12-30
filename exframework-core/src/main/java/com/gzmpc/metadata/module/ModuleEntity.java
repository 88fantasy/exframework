package com.gzmpc.metadata.module;

import com.gzmpc.metadata.Meta;

/**
 * 功能实体
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */

public class ModuleEntity extends Meta {

	private static final long serialVersionUID = 4291650305596301224L;

	private boolean valid;

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
