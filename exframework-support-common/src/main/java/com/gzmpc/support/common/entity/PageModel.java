package com.gzmpc.support.common.entity;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.gzmpc.support.common.util.BeanUtils;

/**
 * @author rwe
 * @version 创建时间：Jun 9, 2020 10:50:30 PM 分页数据对象
 */

public class PageModel<T> {
	
	@SuppressWarnings("unchecked")
	public static final PageModel<?> EMPTY = new PageModel<>(new Pager(0l, Page.DEFAULT), Collections.EMPTY_LIST);

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

	public <E> PageModel<E> copy(Class<E> clazz) {
		List<T> tlist = this.getList();
		List<E> elist = tlist.stream().map(row -> {
			return BeanUtils.copyTo(row, clazz);
		}).collect(Collectors.toList());
		return new PageModel<E>(this.getPager(), elist);
	}
	
	public <E> PageModel<E> translate(Function<T,E> translator) {
		List<T> tlist = this.getList();
		List<E> elist = tlist.stream().map(translator).collect(Collectors.toList());
		return new PageModel<E>(this.getPager(), elist);
	}
}
