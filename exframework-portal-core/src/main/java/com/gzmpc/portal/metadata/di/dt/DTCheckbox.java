package com.gzmpc.portal.metadata.di.dt;

import com.gzmpc.portal.metadata.attribute.Attribute;
import com.gzmpc.portal.metadata.form.Form;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
@Component("DTCheckbox")
public class DTCheckbox implements DispType {

	public Map<String,Object> retDisplay(Form form, Attribute formAttr) {
		return retDisplayCommon(form,formAttr);
	}
}