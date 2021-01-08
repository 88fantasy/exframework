package com.gzmpc.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.gzmpc.metadata.dict.DictionaryItem;

/**
 * 字典实体类
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableName( value = "sys_dictionary", excludeProperty = {"value"} )
public class DictionaryDO extends DictionaryItem {

	private static final long serialVersionUID = 5176816281781626949L;

	@TableId(type = IdType.ASSIGN_ID)
	private String code;
	
	@TableField
	private String name;
	
	@TableField
	private String description;
	
	@TableField
	@ColumnType(value = MySqlTypeConstant.VARCHAR, length = 2000)
	private String valueJson;
 
	public String getValueJson() {
		return valueJson;
	}

	public void setValueJson(String valueJson) {
		this.valueJson = valueJson;
	}
	
}
