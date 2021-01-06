package com.gzmpc.metadata.module;


import java.util.Collection;

import com.gzmpc.metadata.Meta;
import com.gzmpc.metadata.di.DataItem;
import com.gzmpc.metadata.hov.Hov;

/**
 * 功能模块
 * @author rwe
 *
 */
public class Module extends Meta {

	private static final long serialVersionUID = -5171708714094421044L;
	
	private boolean valid;
	
	private long star;

	private Collection<DataItem> dataItems;
	
	private Collection<String> permissions;
	
	private Collection<Hov> hovs;



	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public long getStar() {
		return star;
	}

	public void setStar(long star) {
		this.star = star;
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