package com.gzmpc.support.monitor.stat;

import java.util.Map;

/**
* @author rwe
* @version 创建时间：2018年5月10日 下午1:56:57
* 衡量数据收集类
*/

public abstract class Stat {
	
	public String getStatKey() {
		return this.getClass().getName();
	}

	public abstract Map<String, Object> getStatData();
	
	public abstract void reset();
}
