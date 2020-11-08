package com.gzmpc.support.common.entity;

import org.springframework.lang.Nullable;

/**
* @author rwe
* @version 创建时间：Jun 10, 2020 12:53:48 PM
* 页信息
*/

public class Page {

	/**
	 * 当前页
	 */
	@Nullable
    private Integer current;

	/**
	 * 每页条数
	 */
	@Nullable
    private Integer pageSize;
	
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
	
	public static Page DEFAULT = new Page(1,20);
}
