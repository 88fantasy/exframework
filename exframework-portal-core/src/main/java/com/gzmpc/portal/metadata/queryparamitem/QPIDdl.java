package com.gzmpc.portal.metadata.queryparamitem;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.portal.metadata.FilterCondition.FilterConditionOper;
import com.gzmpc.portal.metadata.queryparam.QueryParamItem;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.portal.service.sys.DdlService;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIDdl extends QueryParamBase {
	
	@Autowired
	DdlService ddlService;
	
	@Override
	public void initBase(Account account, QueryParamItem qpi) {
		initBaseCommon(account,qpi);
		this.setDataType(QueryParamBase.QUERYPARAM_DATATYPE_DDL);
		Map<String, String> dict = getDdl(qpi);
		this.setOperdata(dict);
		FilterConditionOper[] operations = { FilterConditionOper.IN, FilterConditionOper.ISNULL };
		this.setOperations(operations);
	}

	public Map<String, String> getDdl(QueryParamItem qpi) {
		return ddlService.get(qpi.getQpiParam());
	}
}
