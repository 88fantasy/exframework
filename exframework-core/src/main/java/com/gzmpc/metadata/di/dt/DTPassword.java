package com.gzmpc.metadata.di.dt;

import com.gzmpc.metadata.attribute.Attribute;
import com.gzmpc.metadata.form.Form;

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
@Component("DTPassword")
public class DTPassword implements DispType {
	
	public Map<String,Object> retDisplay(Form form, Attribute formAttr) {
		return retDisplayCommon(form,formAttr);
	}
}