package org.exframework.portal.service.sys;

import java.util.ArrayList;
import java.util.List;

import org.exframework.portal.dao.PortalCoreQueryParamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.exframework.portal.exception.NotFoundException;
import org.exframework.portal.metadata.queryparam.QueryParam;
import org.exframework.portal.metadata.queryparam.QueryParamAdapter;
import org.exframework.portal.metadata.queryparam.QueryParamItem;
import org.exframework.portal.metadata.queryparamitem.QueryParamBase;
import org.exframework.support.common.util.SpringContextUtils;

@Service
public class QueryparamService {

	@Autowired
	PortalCoreQueryParamDao queryParamDao;
	
	@Autowired
	PortalCoreAccountService portalCoreAccountService;

	public QueryParamBase[] get(String key) {

		QueryParam queryparam = queryParamDao.findByKey(key);
		if (queryparam == null) {
			throw new NotFoundException("根据查询框编码:" + key + "找不到对应的查询框定义");
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
			QueryParamBase base = retBase(qpi);
			base.initBase(portalCoreAccountService.getAccount(), qpi);
			list.add(base);
		}
		return list.toArray(new QueryParamBase[list.size()]);
	}
	
	private QueryParamBase retBase(QueryParamItem qpi) {
		QueryParamAdapter[] values = QueryParamAdapter.values();
		for(QueryParamAdapter value : values) {
			if(value.getType().equals(qpi.getQpiType())) {
				QueryParamBase instance = SpringContextUtils.getBeanById(value.getBean(), QueryParamBase.class);
				return instance;
			}
		}
		return null;
		
	}
}
