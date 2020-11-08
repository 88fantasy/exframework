package com.gzmpc.support.common.entity;

/**
* @author rwe
* @version 创建时间：Jun 10, 2020 12:53:48 PM
* 页信息
*/

public class Page {

	/**
	 * 当前页
	 */
    private Integer current = 1;

	/**
	 * 每页条数
	 */
    private Integer pageSize = 20;
	
	public Page() {
		
	}
	
	public Page(Integer current, Integer pageSize) {
		this.current = current;
		this.pageSize = pageSize;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer skip() {
		return (this.getCurrent() - 1 ) * this.getPageSize();
	}
}
