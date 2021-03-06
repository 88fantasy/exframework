package org.exframework.portal.metadata.queryparamitem;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.exframework.support.common.entity.FilterCondition.FilterConditionOper;
import org.exframework.portal.metadata.queryparam.QueryParamItem;
import org.exframework.portal.metadata.sys.Account;

/**
 * 数字类型
 * */
@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPINumber extends QueryParamBase {

	@Override
	public void initBase(Account account,QueryParamItem qpi) {
		initBaseCommon(account,qpi);
		this.setDataType(QUERYPARAM_DATATYPE_NUMBER);
		FilterConditionOper[] operations = { FilterConditionOper.EQUAL, FilterConditionOper.BETWEEN, FilterConditionOper.GREATER, FilterConditionOper.LESS, FilterConditionOper.GREATER_EQUAL, FilterConditionOper.LESS_EQUAL, FilterConditionOper.NOT_EQUAL, FilterConditionOper.ISNULL, FilterConditionOper.ISNULL };
		this.setOperations(operations);
	}
}
