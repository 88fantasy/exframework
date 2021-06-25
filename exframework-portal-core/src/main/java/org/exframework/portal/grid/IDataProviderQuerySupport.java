package org.exframework.portal.grid;

import javax.servlet.http.HttpServletRequest;

import org.exframework.support.common.entity.FilterCondition;

/**
 * 表格查询时辅助类，表格每次查询或者下载时会判断是否需要调用此接口方法，
 * 返回FilterCondition[]条件数组
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public interface IDataProviderQuerySupport {
  public FilterCondition[] putDefCondition(HttpServletRequest request);
}