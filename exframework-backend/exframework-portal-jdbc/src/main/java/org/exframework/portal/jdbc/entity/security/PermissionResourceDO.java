package org.exframework.portal.jdbc.entity.security;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;

import org.exframework.support.doc.annotation.TableDoc;
import org.exframework.support.doc.annotation.TableFieldDoc;

import java.io.Serializable;
import java.util.List;

/**
 * 权限组
 * @author rwe
 * @since Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("权限组")
@TableName( value = "sys_permission_group", autoResultMap = true)
public class PermissionResourceDO implements Serializable {

	private static final long serialVersionUID = 5821041616688028717L;

	/**
	 * 权限编码
	 */
	@TableFieldDoc("权限编码")
	@TableId
	private String code;

	/**
	 * 权限名称
	 */
	@TableFieldDoc("权限名称")
	@TableField
	private String name;

	/**
	 * 权限描述
	 */
	@TableFieldDoc("权限描述")
	@TableField
	private String description;

	/**
	 * 权限入口
	 */
	@TableFieldDoc("权限列表")
	@TableField(typeHandler = FastjsonTypeHandler.class)
	@ColumnType(value = MySqlTypeConstant.JSON)
	private List<String> permissionKeys;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPermissionKeys() {
		return permissionKeys;
	}

	public void setPermissionKeys(List<String> permissionKeys) {
		this.permissionKeys = permissionKeys;
	}
	
}
