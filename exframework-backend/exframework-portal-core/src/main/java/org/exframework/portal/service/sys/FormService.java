package org.exframework.portal.service.sys;

import org.exframework.portal.metadata.attribute.Attribute;
import org.exframework.portal.metadata.form.Form;
import org.exframework.portal.metadata.sys.Account;

import java.util.Map;

public interface FormService {

	public Form findByKey(String key);
	
	public Map<String,Object> getFormDefaultValue(Account account,String formcode);
	
	public Map<String,Object> showAttr(Form form,Attribute formAttr);
}
