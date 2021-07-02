package org.exframework.portal.metadata;

import org.exframework.portal.metadata.di.DataItemEntity;
import org.exframework.portal.metadata.entity.EntityClass;

/**
 *
 * @author rwe
 * @since Jan 11, 2021
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
