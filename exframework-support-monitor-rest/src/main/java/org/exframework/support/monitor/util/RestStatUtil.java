package org.exframework.support.monitor.util;

import java.util.Map;

/**
* @author rwe
* @version 创建时间：2018年5月10日 下午2:25:37
* 类说明
*/

public class RestStatUtil extends StatUtil {

	@Override
	public Map<String, Object> getStatData(Object stat) {
		return getCommonStatData(stat);
	}

	@Override
	public void reset(Object stat) {
		resetCommon(stat);
	}

}
