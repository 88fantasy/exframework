package com.gzmpc.support.sso.core.dto;


import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 *
 * @author zlt
 */
public class ResponsePage<T> implements Serializable {

    private static final long serialVersionUID = -275582248840137389L;

    /**
     * 当前页结果集
     */
    private List<T> data;

    private Pager pager;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}


}
