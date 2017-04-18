package com.gzmpc.service;

import java.util.Map;

import com.gzmpc.metadata.attribute.Attribute;
import com.gzmpc.metadata.form.Form;
import com.gzmpc.metadata.sys.Account;

public interface FormService {

	public Map<String,Object> getFormDefaultValue(Account account,String formcode);
	
	public Map<String,Object> showAttr(Form form,Attribute formAttr);
}
