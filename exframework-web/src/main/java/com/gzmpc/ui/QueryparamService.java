package com.gzmpc.ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzmpc.exception.NotFoundException;
import com.gzmpc.metadata.MetaData;
import com.gzmpc.metadata.OperatorPool;
import com.gzmpc.metadata.queryparam.QueryParam;
import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.queryparamitem.QueryParamBase;
import com.gzmpc.metadata.sys.Account;

@Service
public class QueryparamService {
	
	@Autowired
	OperatorPool operatorPool;

	@Autowired
	MetaData metaData;

	public QueryParamBase[] get(String queryparamcode,Account account) throws NotFoundException {

		QueryParam queryparam = metaData.findQueryParamByCode(queryparamcode);
		if (queryparam == null) {
			throw new NotFoundException("根据查询框编码:" + queryparamcode + "找不到对应的查询框定义");
		}

		QueryParamItem[] qpis = queryparam.getQueryParamItems();

		// 查找校验
		// List validls = queryparam.getJsonFormAttrValidInfo();

		if (qpis == null) {
			throw new NotFoundException("查询框没有配置对应的字段！");
		}

		// 权限控制,暂时取消
		// qpis = QPISrvBA.getModuleQPI(sc, queryparamcode, qpis);

		int length = qpis.length;
		List<QueryParamBase> list = new ArrayList<QueryParamBase>();
		for (int i = 0; i < length; i++) {
			QueryParamItem qpi = qpis[i];
			QueryParamBase base = operatorPool.retQueryParam(qpi);
			base.initBase(account, qpi);
			list.add(base);
		}
		return list.toArray(new QueryParamBase[list.size()]);
	}
}
