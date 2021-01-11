package com.gzmpc.portal.metadata;

import com.gzmpc.portal.metadata.di.DataItemField;
import com.gzmpc.portal.metadata.entity.EntityClass;

/**
 *
 * Author: rwe
 * Date: Jan 11, 2021
 *
 * Copyright @ 2021 
 * 
 */
@EntityClass
public class MetaEntity {

	@DataItemField(value = "code", name = "代码")
	private String code;
	
	@DataItemField(value = "name", name = "名称")
	private String name;
	
	@DataItemField(value = "description", name = "描述")
	private String  description;
}
