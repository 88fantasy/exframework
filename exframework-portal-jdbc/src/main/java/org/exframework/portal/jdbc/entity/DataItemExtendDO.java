package org.exframework.portal.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.exframework.support.doc.annotation.TableDoc;
import org.exframework.support.doc.annotation.TableFieldDoc;

/**
 * 数据项扩展
 * @author rwe
 * @since Dec 29, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("数据项扩展")
@TableName("sys_dataitem_extend")
public class DataItemExtendDO extends DataItemDO {

	private static final long serialVersionUID = -9073006232793863130L;
	
	@TableFieldDoc("组件code")
	@TableField
	private String objectCode;

	public String getObjectCode() {
		return objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
	
	
}
