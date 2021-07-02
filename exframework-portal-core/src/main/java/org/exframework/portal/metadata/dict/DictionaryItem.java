package org.exframework.portal.metadata.dict;

import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.exframework.portal.metadata.Meta;
import org.exframework.portal.permission.PermissionEntry;
import org.exframework.support.common.constants.SpringConstants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 字典项
 * @author rwe
 * @since Jan 7, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Component
@Scope(SpringConstants.SCOPE_PROTOTYPE)
public class DictionaryItem extends Meta implements PermissionEntry {

	private static final long serialVersionUID = 1734744807375718094L;
	
	@NotEmpty
	private Map<String,String> value;
	
	private Boolean local;

	public Map<String, String> getValue() {
		return value;
	}

	public void setValue(Map<String, String> value) {
		this.value = value;
	}

	public Boolean getLocal() {
		return local;
	}

	public void setLocal(Boolean local) {
		this.local = local;
	}
	
	
}
