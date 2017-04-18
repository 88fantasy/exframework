package com.gzmpc.metadata.toolbar;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gzmpc.metadata.OperatorPool;
import com.gzmpc.util.SpringContextUtils;

/**
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

@Component
public class ButtonAdapter {
	public Map<String,String> contentClassMap = new ConcurrentHashMap<String,String>();

	@Autowired
	OperatorPool operatorPool;
	
	ButtonAdapter () {
		contentClassMap.put("text", "textButtonContent");
		contentClassMap.put("module", "moduleButtonContent");
	}

	public ButtonContentBase genContent(ToolButton button) {
		String beanid = contentClassMap.get(button.getToolbartype());
		ButtonContentBase instance = SpringContextUtils.getBeanById(beanid, ButtonContentBase.class);
		instance.setToolbutton(button);
		return instance;
	}

}
