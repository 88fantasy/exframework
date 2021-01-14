package com.gzmpc.portal.metadata.queryparamitem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.portal.metadata.grid.Grid;
import com.gzmpc.portal.metadata.queryparam.QueryParamItem;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.portal.service.sys.GridService;
import com.gzmpc.portal.utils.Const;
import com.gzmpc.support.common.util.MapUtil;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIHov extends QueryParamBase {
	
	@Autowired
	GridService gridService;

	@Override
	public void initBase(Account account,QueryParamItem qpi) {
		initBaseCommon(account,qpi);
		Map<String,Object> result = new ConcurrentHashMap<String,Object>();
		MapUtil.putIfNotNull(result, Const.QUERY_PARAM_ITEM_DEFAULTCONDITION_JS, qpi.getDefaultconditionjs());
		
		//hov ddl e 
		if(qpi.getSnsretcolname()!=null){
			result.put(Const.QUERY_PARAM_ITEM_GRID_INDEXNAME, qpi.getSnsretcolname());
		}else{
			String gridKey = qpi.getQpiParam();
			Grid g = gridService.findByKey(gridKey);
			String pkname = g.getDataIndex();
			MapUtil.putIfNotNull(result, Const.QUERY_PARAM_ITEM_GRID_INDEXNAME, pkname);
		}
		this.setOperdata(result);
	}
}
