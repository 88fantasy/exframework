package com.gzmpc.portal.service.sys;

import java.util.Map;

import com.gzmpc.portal.metadata.attribute.Attribute;
import com.gzmpc.portal.metadata.form.Form;
import com.gzmpc.portal.metadata.sys.Account;

public interface FormService {

	public Form findByKey(String key);
	
	public Map<String,Object> getFormDefaultValue(Account account,String formcode);
	
	public Map<String,Object> showAttr(Form form,Attribute formAttr);
}
