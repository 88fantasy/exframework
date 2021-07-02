package org.exframework.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.support.doc.annotation.TableDoc;
import org.exframework.support.doc.annotation.TableFieldDoc;

/**
 * 数据项
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("数据项")
@TableName("sys_dataitem")
public class DataItemDO extends DataItem {

	private static final long serialVersionUID = -343508405872768155L;

	/**
	 * 代码
	 */
	@TableFieldDoc("代码")
	@TableId
	private String code;
	
	/**
	 * 名称
	 */
	@TableFieldDoc("名称")
	@TableField
	private String name;
	
	/**
	 * 描述
	 */
	@TableFieldDoc("描述")
	@TableField
	private String description;
	
	/**
	 * 显示风格
	 */
	@TableFieldDoc("显示风格")
	@TableField
	@EnumValue
	@ColumnType(value = MySqlTypeConstant.VARCHAR)
	private DataItemDisplayTypeEnum type;

	/**
	 * 风格的关键值（如果是querylist形的，就配置key,如果是sqllist形的就配置sqllist,如果是list形的，就直接配置list列表内容)
	 */
	@TableFieldDoc("风格的关键值")
	@TableField
	private String displayKey;

	/**
	 * 校证输入值类型
	 */
	@TableFieldDoc("校证输入值类型")
	@TableField
	@EnumValue
	@ColumnType(value = MySqlTypeConstant.VARCHAR)
	private DataItemValueTypeEnum valueType;

	/**
	 * 长度
	 */
	@TableFieldDoc("长度")
	@TableField
	private int maxlength;

	/**
	 * 精度
	 */
	@TableFieldDoc("精度")
	@TableField
	private int digits;
}
