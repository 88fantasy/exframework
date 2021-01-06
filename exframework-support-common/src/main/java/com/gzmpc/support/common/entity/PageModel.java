package com.gzmpc.support.common.entity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

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

	public  <E> PageModel<E> copy(Class<E> clazz) {
		List<T> tlist = this.getList();
		List<E> elist = tlist.stream().map(row -> {
			try {
				E e = clazz.newInstance();
				BeanUtils.copyProperties(row, e);
				return e;
			} catch (InstantiationException | IllegalAccessException e1) {
				return null;
			}
		}).collect(Collectors.toList());
		return new PageModel<E>(this.getPager(), elist);
	}
}
