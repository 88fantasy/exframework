package com.gzmpc.portal.metadata;

import com.gzmpc.portal.metadata.di.DataItemEntity;
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

	@DataItemEntity(value = "code", name = "代码")
	private String code;
	
	@DataItemEntity(value = "name", name = "名称")
	private String name;
	
	@DataItemEntity(value = "description", name = "描述")
	private String  description;
}
