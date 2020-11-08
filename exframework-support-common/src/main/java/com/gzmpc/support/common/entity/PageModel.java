package com.gzmpc.support.common.entity;

import java.util.List;

/**
* @author rwe
* @version 创建时间：Jun 9, 2020 10:50:30 PM
* 分页数据对象
*/

public class PageModel<T> {

	/**
	 * 分页信息
	 */
	private Pager pager;
	
	/**
	 * 列表数据
	 */
	private List<T> list;

	public PageModel(Pager pager, List<T> list) {
		this.pager = pager;
		this.list = list;
	}
	
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	
}
