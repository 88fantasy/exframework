package com.gzmpc.portal.metadata.queryparamitem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.support.common.entity.FilterCondition.FilterConditionOper;
import com.gzmpc.portal.metadata.queryparam.QueryParamItem;
import com.gzmpc.portal.metadata.sys.Account;

@Component()
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIString extends QueryParamBase {

	@Override
	public void initBase(Account account,QueryParamItem qpi) {
		initBaseCommon(account,qpi);
		FilterConditionOper[] operations = { FilterConditionOper.EQUAL, FilterConditionOper.MATCHING, FilterConditionOper.ISNULL, FilterConditionOper.ISNULL };
		this.setOperations(operations);
	}
}
