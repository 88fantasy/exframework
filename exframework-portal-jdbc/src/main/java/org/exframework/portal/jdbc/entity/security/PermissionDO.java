package org.exframework.portal.jdbc.entity.security;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.exframework.portal.metadata.sys.Permission;
import org.exframework.support.doc.annotation.TableDoc;
import org.exframework.support.doc.annotation.TableFieldDoc;

/**
 * 权限
 * @author rwe
 * @since Dec 28, 2020
 *
 * Copyright @ 2020 
 * 
 */
@TableDoc("权限")
@TableName( value = "sys_permission", excludeProperty = "children")
public class PermissionDO extends Permission  {

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
	@TableFieldDoc("权限入口")
	@TableField
	private String permissionEntry;

	/**
	 * 父权限
	 */
	@TableFieldDoc("父权限")
	@TableField
	private String parentPermissionKey;

	/**
	 * 排序
	 */
	@TableFieldDoc("排序")
	@TableField
	private Long permissionOrder;

	/**
	 * 是否可用
	 */
	@TableFieldDoc("可用")
	@TableField
	private boolean valid;

	/**
	 * 是否不可见
	 */
	@TableFieldDoc("不可见")
	@TableField
	private boolean notDisplay;
}
