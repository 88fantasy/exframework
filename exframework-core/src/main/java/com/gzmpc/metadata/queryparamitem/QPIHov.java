package com.gzmpc.metadata.queryparamitem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.metadata.MetaData;
import com.gzmpc.metadata.grid.Grid;
import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.support.common.util.MapUtil;
import com.gzmpc.utils.Const;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIHov extends QueryParamBase {
	
	@Autowired
	MetaData metaData;

	@Override
	public void initBase(Account account,QueryParamItem qpi) {
		initBaseCommon(account,qpi);
		Map<String,Object> result = new ConcurrentHashMap<String,Object>();
		MapUtil.putIfNotNull(result, Const.QUERY_PARAM_ITEM_DEFAULTCONDITION_JS, qpi.getDefaultconditionjs());
		
		//hov ddl e 
		if(qpi.getSnsretcolname()!=null){
			result.put(Const.QUERY_PARAM_ITEM_GRID_INDEXNAME, qpi.getSnsretcolname());
		}else{
			String gridcode = qpi.getQueryoper();
			Grid g = metaData.findGridDefByCode(gridcode);
			String pkname = g.getDataIndex();
			MapUtil.putIfNotNull(result, Const.QUERY_PARAM_ITEM_GRID_INDEXNAME, pkname);
		}
		this.setOperdata(result);
	}
}
