/**
 * 
 */
package com.gzmpc.metadata.queryparamitem;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.metadata.enums.FilterConditionOper;
import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.sys.Account;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIDate extends QueryParamBase {

	@Override
	public void initBase(Account account,QueryParamItem qpi) {
		initBaseCommon(account,qpi);
		this.setDataType(QUERYPARAM_DATATYPE_DATE);
		FilterConditionOper[] operations = { FilterConditionOper.BETWEEN, FilterConditionOper.GREATER, FilterConditionOper.LESS, FilterConditionOper.GREATER_EQUAL, FilterConditionOper.LESS_EQUAL, FilterConditionOper.ISNULL };
		this.setOperations(operations);
	}

}
