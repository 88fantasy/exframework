package com.gzmpc.metadata.queryparamitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.utils.Const;

@Component()
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIString extends QueryParamBase {

	@Override
	public void initBase(Account account,QueryParamItem qpi) {
		initBaseCommon(account,qpi);
		List<Map<String,String>> operations = new ArrayList<Map<String,String>>();
		operations.add(OPERATION_CONST.get(Const.OPER_EQUAL));
		operations.add(OPERATION_CONST.get(Const.OPER_MATCHING));
		operations.add(OPERATION_CONST.get(Const.OPER_IN));
		operations.add(OPERATION_CONST.get(Const.OPER_ISNULL));
		this.setOperations(operations);
	}
}
