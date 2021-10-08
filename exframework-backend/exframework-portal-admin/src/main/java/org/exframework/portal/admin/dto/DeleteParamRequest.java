package org.exframework.portal.admin.dto;

import javax.validation.constraints.NotNull;

/**
 *
 * @author rwe Date: Jan 6, 2021
 *
 * Copyright @ 2021
 * 
 */
public class DeleteParamRequest {

	@NotNull
	private ParamKey[] keys;

	public ParamKey[] getKeys() {
		return keys;
	}

	public void setKeys(ParamKey[] keys) {
		this.keys = keys;
	}

	
}
