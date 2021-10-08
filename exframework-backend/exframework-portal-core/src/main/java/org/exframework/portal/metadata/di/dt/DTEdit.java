package org.exframework.portal.metadata.di.dt;

import org.exframework.portal.metadata.attribute.Attribute;
import org.exframework.portal.metadata.form.Form;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 解析html页面上的对应的文本框显示的控件 算法： 以下为Attribute formAttr对象控制 1。不管是只读还是可读写，都需要赋予一个样式给它。
 * 2。如果是必填项的，则需要置上个*号给它
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
 * @author rwe
 * @version 1.0
 */
@Component("DTEdit")
public class DTEdit implements DispType {
	public Map<String,Object> retDisplay(Form form, Attribute formAttr) {
		return retDisplayCommon(form,formAttr);
	}
}