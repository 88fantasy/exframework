package org.exframework.portal.metadata.di.dt;

import org.exframework.portal.exception.InitException;
import org.exframework.portal.metadata.attribute.Attribute;
import org.exframework.portal.metadata.di.DataItem;
import org.exframework.portal.metadata.form.Form;
import org.exframework.portal.service.sys.PortalCoreDdlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 读取数据字典，从而画出下拉框控件的算法
 * <p>
 * Title: 算法： 数据字典必须要求配置有disptypekey（由DataItem控制） 以下为Attribute formAttr对象控制
 * DataItem.getDisptypekey * 1。不管是只读还是可读写，都需要赋予一个样式给它。 2。如果是必填项的，则需要置上个*号给它
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
 * @author rwe
 * @version 1.0
 */
@Component("DTQueryList")
public class DTQueryList implements DispType {
	
	@Autowired
    PortalCoreDdlService portalCoreDdlService;
	
	@Override
	public Map<String,Object> retDisplay(Form form, Attribute formAttr) {
		Map<String,Object> result = retDisplayCommon(form,formAttr);
		DataItem di = formAttr.getDi();
		String key = di.getDisplayKey();
		if (key == null || "".equals(key)) {
			throw new InitException(di.getCode() + "没有配置disptypekey,请先配置");
		}
		Map<String,String> dict = portalCoreDdlService.get(key);
		result.put("type", "list");
		result.put("list", dict);
		return result;
	}
}