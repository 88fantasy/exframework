package com.gzmpc.portal.metadata.queryparamitem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.portal.metadata.queryparam.QueryParamItem;
import com.gzmpc.portal.metadata.sys.Account;

/**
 * 大写字符串
 * 
 * */
@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIStringUpper extends QPIString {

	@Override
	public void initBase(Account account, QueryParamItem qpi) {
		super.initBase(account, qpi);
		this.setDataType(QUERYPARAM_DATATYPE_STRINGUPPER);
	}

}
