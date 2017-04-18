package com.gzmpc.grid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzmpc.exception.NotFoundException;
import com.gzmpc.grid.queryimpl.QueryDataProvider;
import com.gzmpc.grid.tableimpl.TableDataProvider;
import com.gzmpc.util.SpringContextUtils;

@Repository
public class DataProviderFactory {
	
	@Autowired
	QueryDataProvider queryDataProvider;
	
	@Autowired
	TableDataProvider tableDataProvider;

	public IDataProvider getProvider(String queryType) throws NotFoundException {
		if("table".equals(queryType)) {
			return tableDataProvider;
		}
		else if ("query".equals(queryType)) {
			return queryDataProvider;
		}
		else {
			try{
				return SpringContextUtils.getBeanById(queryType, IDataProvider.class);
			} catch (BeansException e) {
				throw new NotFoundException("找不到"+queryType+"实现类");
			}
		}
	}
}
