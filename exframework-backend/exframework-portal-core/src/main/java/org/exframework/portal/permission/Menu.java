package org.exframework.portal.permission;

import org.exframework.portal.metadata.Meta;
import org.exframework.support.common.constants.CommonConstants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 菜单权限
 * @author rwe
 * @since 2021年6月30日
 *
 */
@Component
@Scope(CommonConstants.SCOPE_PROTOTYPE)
public class Menu extends Meta implements PermissionEntry {

	private static final long serialVersionUID = -6199910746691894006L;

	private String icon;
	
	private String path;
	
	private String parentCode;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	
}
