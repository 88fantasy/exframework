package org.exframework.portal.metadata.module;


import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.hov.Hov;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

/**
 * 功能模块
 * @author rwe
 *
 */
public class Module extends ModuleBase {

	private static final long serialVersionUID = -5171708714094421044L;

	private Collection<DataItem> dataItems;
	
	private Collection<String> permissions;
	
	private Collection<Hov> hovs;

	public Module() {
		
	}
	
	public Module(@NotEmpty String code, @NotEmpty String name, String description, boolean valid, long star,
			Collection<DataItem> dataItems, Collection<String> permissions, Collection<Hov> hovs) {
		super(code, name, description, valid, star);
		this.dataItems = dataItems;
		this.permissions = permissions;
		this.hovs = hovs;
	}

	public Collection<DataItem> getDataItems() {
		return dataItems;
	}

	public void setDataItems(Collection<DataItem> dataItems) {
		this.dataItems = dataItems;
	}

	public Collection<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<String> permissions) {
		this.permissions = permissions;
	}

	public Collection<Hov> getHovs() {
		return hovs;
	}

	public void setHovs(Collection<Hov> hovs) {
		this.hovs = hovs;
	}

}