package com.gzmpc.portal.metadata.di.dt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gzmpc.portal.metadata.attribute.Attribute;
import com.gzmpc.portal.metadata.di.DataItem;
import com.gzmpc.portal.metadata.form.Form;
import com.gzmpc.support.common.util.MapUtil;


public interface DispType {

	Map<String, Object> retDisplay(Form form, Attribute formAttr);

	default Map<String, Object> retDisplayCommon(Form form, Attribute formAttr) {
		Map<String, Object> result = new ConcurrentHashMap<String, Object>();
		DataItem di = formAttr.getDi();
		String name = di.getName();
		MapUtil.putIfNotNull(result,"name", name);
		MapUtil.putIfNotNull(result,"type", di.getType());
		MapUtil.putIfNotNull(result,"readonly", formAttr.getReadonly());
		MapUtil.putIfNotNull(result,"required", formAttr.getRequired());
		MapUtil.putIfNotNull(result,"tooltip", formAttr.getTooltip());
		MapUtil.putIfNotNull(result, "shortcut", formAttr.getShortcutkey());

		return result;
	}

}
