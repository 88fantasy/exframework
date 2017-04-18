package com.gzmpc.metadata.exception;

import com.gzmpc.metadata.MDObject;

public class ExceptionDef extends MDObject{

	/**
	 * 错误定义
	 */
	private static final long serialVersionUID = -6069891754978463824L;
	
	private String heading;
	private String solution;
	private String toPath;
	
	public String getHeading() {
		return heading==null?"500 - 服务器错误(Internal Server Error).":heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getSolution() {
		return solution==null?"如果你想了解更详细的原因 可以联系管理员":solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getToPath() {
		return toPath;
	}
	public void setToPath(String toPath) {
		this.toPath = toPath;
	}
	
	
}
