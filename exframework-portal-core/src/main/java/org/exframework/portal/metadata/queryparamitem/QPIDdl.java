package org.exframework.portal.metadata.queryparamitem;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.exframework.support.common.entity.FilterCondition.FilterConditionOper;
import org.exframework.portal.metadata.queryparam.QueryParamItem;
import org.exframework.portal.metadata.sys.Account;
import org.exframework.portal.service.sys.PortalCoreDdlService;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIDdl extends QueryParamBase {
	
	@Autowired
    PortalCoreDdlService portalCoreDdlService;
	
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
		return portalCoreDdlService.get(qpi.getQpiParam());
	}
}
