package com.gzmpc.build;

public interface Buildable {
	
	/*
	 * 在环境启动时或重新读取配置时调用
	 */
	public void build();
}
