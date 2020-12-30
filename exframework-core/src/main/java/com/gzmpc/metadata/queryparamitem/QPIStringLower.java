package com.gzmpc.metadata.queryparamitem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.sys.Account;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIStringLower extends QPIString {

	@Override
	public void initBase(Account account, QueryParamItem qpi) {
		super.initBase(account, qpi);
		this.setDataType(QUERYPARAM_DATATYPE_STRINGLOWER);
	}

}
