package org.exframework.portal.jdbc.grid;

import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.grid.IDataProvider;
import org.exframework.support.common.util.SpringContextUtils;

@Component
public class DataProviderFactory {

	public IDataProvider getProvider(String queryType) throws NotFoundException {
		String name = queryType;
		if("table".equals(queryType)) {
			name = "tableDataProvider";
		}
		else if ("query".equals(queryType)) {
			name = "queryDataProvider";
		}
		try{
			return SpringContextUtils.getBeanById(name, IDataProvider.class);
		} catch (BeansException e) {
			throw new NotFoundException("找不到"+queryType+"实现类");
		}
	}
}
