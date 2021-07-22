package org.exframework.support.common.entity;

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
    private Long current;

	/**
	 * 每页条数
	 */
	@Nullable
    private Long pageSize;
	
	public Page() {
		this.current = DEFAULT.current;
		this.pageSize = DEFAULT.pageSize;
	}
	
	public Page(@Nullable Long current, @Nullable Long pageSize) {
		this.current = current;
		this.pageSize = pageSize;
	}
	
	public Page(Integer current, Integer pageSize) {
		this.current = current.longValue();
		this.pageSize = pageSize.longValue();
	}

	@Nullable
	public Long getCurrent() {
		return current;
	}

	public void setCurrent(@Nullable Long current) {
		this.current = current;
	}

	@Nullable
	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(@Nullable Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long skip() {
		return (this.getCurrent() - 1 ) * this.getPageSize();
	}
	
	public static Page DEFAULT = new Page(1L,20L);
}
