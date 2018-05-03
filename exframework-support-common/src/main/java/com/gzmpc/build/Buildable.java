package com.gzmpc.build;

import com.gzmpc.exception.BuildException;

public interface Buildable {
	
	/*
	 * 在环境启动时或重新读取配置时调用
	 */
	public void build() throws BuildException;
}
