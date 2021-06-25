package org.exframework.support.common.entity;

/**
* @author rwe
* @version 创建时间：Jun 9, 2020 11:06:42 PM
* 分页信息
*/

public class Pager extends Page {

	/**
	 * 总页数
	 */
    private Long size;
	
	/**
	 * 总数
	 */
    private Long total;
	
	public Pager(Long total, Page page) {
		this.total = total;
		this.setCurrent(page.getCurrent());
		this.setPageSize(page.getPageSize());
		this.size = page.getPageSize() != null && page.getPageSize() != 0 ? ((total / page.getPageSize())+1): 0 ;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getSize() {
		return size;
	}
}
