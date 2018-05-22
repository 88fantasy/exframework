package com.gzmpc.support.common.exception;

/**
* @author rwe
* @version 创建时间：2017年12月17日 下午5:19:42
* 类说明
*/

public class BuildException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1174965060315548238L;

	public BuildException() {
		super();
	}
	
	public BuildException(String msg) {
		super(msg);
	}
	
	public BuildException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public BuildException(Throwable e) {
		super(e);
	}
}
