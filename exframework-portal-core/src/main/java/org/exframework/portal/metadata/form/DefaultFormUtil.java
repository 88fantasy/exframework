package org.exframework.portal.metadata.form;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * 使用在initvalue的表达式中
 */

@Component("formInitUtil")
public class DefaultFormUtil {
	static private Log log = LogFactory.getLog(DefaultFormUtil.class.getName());
	public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public String getDate() {
		return SIMPLE_DATE_FORMAT.format(new Date());
	}
	
	public String getDateStr() {
		return SIMPLE_TIME_FORMAT.format(new Date());
	}
	
}
