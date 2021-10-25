package org.exframework.portal.metadata.di.dt;

import org.exframework.portal.metadata.attribute.Attribute;
import org.exframework.portal.metadata.form.Form;
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

	@Override
	public Map<String,Object> retDisplay(Form form, Attribute formAttr) {
		return retDisplayCommon(form,formAttr);
	}
}