package org.exframework.portal.jdbc.entity.module;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 模块参照关系
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName("sys_module_hov")
public class ModuleHovDO {

	@TableId( type = IdType.ASSIGN_ID)
	private String id;
	
	@TableField
	private String moduleKey;
	
	@TableField
	private String hovKey;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuleKey() {
		return moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}

	public String getHovKey() {
		return hovKey;
	}

	public void setHovKey(String hovKey) {
		this.hovKey = hovKey;
	}
	
	
}
