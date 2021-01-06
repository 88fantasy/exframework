package com.gzmpc.web.entity.dto;

import javax.validation.constraints.NotNull;

/**
 *
 * Author: rwe Date: Jan 6, 2021
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

	public class ParamKey {

		private String code;

		private String account;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

	}
}
