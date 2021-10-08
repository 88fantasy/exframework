package org.exframework.portal.metadata.module;

import javax.validation.constraints.NotEmpty;

import org.exframework.portal.metadata.Meta;
import org.exframework.portal.metadata.di.DataItemEntity;
import org.exframework.portal.metadata.entity.EntityClass;
import org.exframework.portal.metadata.di.DataItem.DataItemDisplayTypeEnum;
import org.exframework.portal.metadata.di.DataItem.DataItemValueTypeEnum;

/**
 * 模块基础
 * @author rwe
 * @since Jan 13, 2021
 *
 * Copyright @ 2021 
 * 
 */
@EntityClass
public class ModuleBase extends Meta {

	private static final long serialVersionUID = 6481735743500461616L;

	@DataItemEntity(value = "valid", name = "可用", type = DataItemDisplayTypeEnum.CHECKBOX, valueType = DataItemValueTypeEnum.BOOLEAN )
	private boolean valid;
	
	private long star;
	
	public ModuleBase () {
		
	}


	public ModuleBase(@NotEmpty String code, @NotEmpty String name, String description) {
		this(code, name, description, true, 0L);
	}
	
	public ModuleBase(@NotEmpty String code, @NotEmpty String name, String description, boolean valid, long star) {
		super(code, name, description);
		this.valid = valid;
		this.star = star;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public long getStar() {
		return star;
	}

	public void setStar(long star) {
		this.star = star;
	}
}
