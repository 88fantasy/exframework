package org.exframework.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import org.exframework.portal.metadata.grid.Column;
import org.exframework.portal.metadata.hov.Hov;
import org.exframework.portal.metadata.hov.HovQueryParams;
import org.exframework.support.doc.annotation.TableDoc;
import org.exframework.support.doc.annotation.TableFieldDoc;

/**
 * 参照
 * Author: rwe
 * Date: Dec 31, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("参照")
@TableName( value = "sys_hov", autoResultMap = true )
public class HovDO extends Hov {

	private static final long serialVersionUID = 7765402758315833737L;

	/**
	 * 参照编码
	 */
	@TableFieldDoc("参照编码")
	@TableId
	private String code;
	
	/**
	 * 参照名称
	 */
	@TableFieldDoc("参照名称")
	@TableField
	private String name;
	
	/**
	 * 参照描述
	 */
	@TableFieldDoc("参照描述")
	@TableField
	private String description;
	
	/**
	 * 查询条件
	 */
	@TableFieldDoc("查询条件")
	@TableField(typeHandler = JacksonTypeHandler.class)
	@ColumnType(value = MySqlTypeConstant.VARCHAR, length = 2000)
	private HovQueryParams[] queryParams;
	
	/**
	 * 展示列
	 */
	@TableFieldDoc("展示列")
	@TableField(typeHandler = JacksonTypeHandler.class)
	@ColumnType(value = MySqlTypeConstant.VARCHAR, length = 2000)
	private Column[] columns;
	
	/**
	 * 实现类
	 */
	@TableFieldDoc("实现类")
	@TableField
	private String dataClass;
	
	/**
	 * 返回字段
	 */
	@TableFieldDoc("返回字段")
	@TableField
	private String returnKey;
}
