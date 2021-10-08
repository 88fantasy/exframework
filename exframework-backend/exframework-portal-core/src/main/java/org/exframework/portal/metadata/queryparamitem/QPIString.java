package org.exframework.portal.metadata.queryparamitem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.exframework.support.common.entity.FilterCondition.FilterConditionOper;
import org.exframework.portal.metadata.queryparam.QueryParamItem;
import org.exframework.portal.metadata.sys.Account;

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
