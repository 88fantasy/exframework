package org.exframework.support.sso.core.dto;


import java.io.Serializable;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-06-02 15:04
 */

public class Pager implements Serializable {

    public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
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

	private static final long serialVersionUID = 1L;

    /**
     * 总数
     */
    private Integer total;

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 每页条数
     */
    private Integer pageSize;
    

}
