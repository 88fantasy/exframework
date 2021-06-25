package org.exframework.support.common.build;

import org.springframework.context.ApplicationContext;

import org.exframework.support.common.exception.BuildException;

public interface Buildable {
	
	/*
	 * 在环境启动时或重新读取配置时调用
	 */
	public void build(ApplicationContext ac) throws BuildException;
}
