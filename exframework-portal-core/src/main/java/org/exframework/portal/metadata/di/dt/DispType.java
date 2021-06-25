package org.exframework.portal.metadata.di.dt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.exframework.portal.metadata.attribute.Attribute;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.form.Form;
import org.exframework.support.common.util.MapUtil;


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
