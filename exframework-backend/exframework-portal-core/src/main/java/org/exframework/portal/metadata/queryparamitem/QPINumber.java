package org.exframework.portal.metadata.queryparamitem;


import org.exframework.portal.metadata.queryparam.QueryParamItem;
import org.exframework.portal.metadata.sys.Account;
import org.exframework.support.common.entity.FilterCondition.FilterConditionOper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
		FilterConditionOper[] operations = { FilterConditionOper.EQUAL, FilterConditionOper.BETWEEN, FilterConditionOper.GREATER, FilterConditionOper.LESS, FilterConditionOper.GREATER_EQUAL, FilterConditionOper.LESS_EQUAL, FilterConditionOper.NOT_EQUAL, FilterConditionOper.IS_NULL, FilterConditionOper.IS_NULL};
		this.setOperations(operations);
	}
}
