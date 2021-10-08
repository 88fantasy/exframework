package org.exframework.portal.metadata.dict;

import org.exframework.portal.metadata.Meta;
import org.exframework.portal.permission.PermissionEntry;
import org.exframework.support.common.constants.SpringConstants;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.List;

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
	private List<DictionaryItemValue> value;
	
	private Boolean local;

	public List<DictionaryItemValue> getValue() {
		return value;
	}

	public DictionaryItem setValue(List<DictionaryItemValue> value) {
		this.value = value;
		return this;
	}

	public Boolean getLocal() {
		return local;
	}

	public void setLocal(Boolean local) {
		this.local = local;
	}
	
	
}
