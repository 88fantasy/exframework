package com.gzmpc.metadata.queryparamitem;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.metadata.FilterCondition.Oper;
import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.sys.Account;

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
		Oper[] operations = { Oper.EQUAL, Oper.BETWEEN, Oper.GREATER, Oper.LESS, Oper.GREATER_EQUAL, Oper.LESS_EQUAL, Oper.NOT_EQUAL, Oper.ISNULL, Oper.ISNULL };
		this.setOperations(operations);
	}
}
