package org.exframework.support.monitor.util;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.exframework.support.monitor.stat.Stat;

/**
* @author rwe
* @version 创建时间：2018年5月10日 下午1:47:12
* 类说明
*/

public abstract class StatUtil {
	
	private static Log log = LogFactory.getLog(StatUtil.class.getName());

	public abstract Map<String, Object> getStatData(Object stat);
	
	public abstract void reset(Object stat);
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCommonStatData(Object stat) {
		if (stat.getClass() == Stat.class) {
            return ((Stat) stat).getStatData();
        }

        try {
            Method method = stat.getClass().getMethod("getStatData");
            Object obj = method.invoke(stat);
            return (Map<String, Object>) obj;
        } catch (Exception e) {
            log.error("getStatData error", e);
            return null;
        }
	}
	
	public void resetCommon(Object stat) {
		if (stat.getClass() == Stat.class) {
           ((Stat) stat).reset();
           return;
        }

        try {
            Method method = stat.getClass().getMethod("reset");
            method.invoke(stat);
        } catch (Exception e) {
            log.error("reset error", e);
        }
	}
}
