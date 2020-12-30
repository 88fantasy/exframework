package com.gzmpc.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 *
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName("sys_dataitem_extend")
public class DataItemExtendDO extends DataItemDO {

	private static final long serialVersionUID = -9073006232793863130L;
	
	private String objectCode;

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
	
	
}
