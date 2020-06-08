package com.gzmpc.metadata.di.dt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gzmpc.metadata.attribute.Attribute;
import com.gzmpc.metadata.di.DataItem;
import com.gzmpc.metadata.form.Form;
import com.gzmpc.support.common.util.MapUtil;

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
public abstract class DispType {

	public abstract Map<String, Object> retDisplay(Form form, Attribute formAttr);

	public Map<String, Object> retDisplayCommon(Form form, Attribute formAttr) {
		Map<String, Object> result = new ConcurrentHashMap<String, Object>();
		DataItem di = formAttr.getDi();
		String name = di.getName();
		MapUtil.putIfNotNull(result,"name", name);
		MapUtil.putIfNotNull(result,"type", di.getDisptypecfg());
		MapUtil.putIfNotNull(result,"readonly", formAttr.getReadonly());
		MapUtil.putIfNotNull(result,"required", formAttr.getRequired());
		MapUtil.putIfNotNull(result,"tooltip", formAttr.getTooltip());
		MapUtil.putIfNotNull(result, "shortcut", formAttr.getShortcutkey());

		return result;
	}

}
