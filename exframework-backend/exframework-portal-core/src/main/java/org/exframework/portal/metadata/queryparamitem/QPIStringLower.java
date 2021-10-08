package org.exframework.portal.metadata.queryparamitem;

import org.exframework.portal.metadata.queryparam.QueryParamItem;
import org.exframework.portal.metadata.sys.Account;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIStringLower extends QPIString {

	@Override
	public void initBase(Account account, QueryParamItem qpi) {
		super.initBase(account, qpi);
		this.setDataType(QUERYPARAM_DATATYPE_STRINGLOWER);
	}

}
