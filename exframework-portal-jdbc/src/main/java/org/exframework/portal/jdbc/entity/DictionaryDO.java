package org.exframework.portal.jdbc.entity;

import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import org.exframework.portal.metadata.dict.DictionaryItem;
import org.exframework.support.doc.annotation.TableDoc;
import org.exframework.support.doc.annotation.TableFieldDoc;

/**
 * 字典实体类
 * Author: rwe
 * Date: Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("字典")
@TableName( value = "sys_dictionary", autoResultMap = true )
public class DictionaryDO extends DictionaryItem {

	private static final long serialVersionUID = 5176816281781626949L;

	/**
	 * 字典编码
	 */
	@TableFieldDoc("字典编码")
	@TableId(type = IdType.ASSIGN_ID)
	private String code;
	
	/**
	 * 字典名称
	 */
	@TableFieldDoc("字典名称")
	@TableField
	private String name;
	
	/**
	 * 字典描述
	 */
	@TableFieldDoc("字典描述")
	@TableField
	private String description;
	
	/**
	 * 字典值
	 */
	@TableFieldDoc("字典值")
	@TableField(typeHandler = JacksonTypeHandler.class)
	@ColumnType(value = MySqlTypeConstant.VARCHAR, length = 2000)
	private Map<String,String> value;
	
	@TableFieldDoc("代码字典")
	@TableField
	private Boolean local;
}
