package com.gzmpc.metadata.queryparamitem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.metadata.FilterCondition.Oper;
import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.sys.Account;

@Component()
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIString extends QueryParamBase {

	@Override
	public void initBase(Account account,QueryParamItem qpi) {
		initBaseCommon(account,qpi);
		Oper[] operations = { Oper.EQUAL, Oper.MATCHING, Oper.ISNULL, Oper.ISNULL };
		this.setOperations(operations);
	}
}
