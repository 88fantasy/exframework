package com.gzmpc.metadata.queryparamitem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gzmpc.metadata.OperatorPool;
import com.gzmpc.metadata.queryparam.QueryParamItem;
import com.gzmpc.metadata.sys.Account;
import com.gzmpc.utils.Const;

@Component
@Scope(value = org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QPIDdl extends QueryParamBase {
	
	@Autowired
	OperatorPool operatorPool;
	
	@Override
	public void initBase(Account account, QueryParamItem qpi) {
		initBaseCommon(account,qpi);
		this.setDatatype(QueryParamBase.QUERYPARAM_DATATYPE_DDL);
		this.setOper(Const.OPER_IN);
		Map<String, String> dict = getDdl(qpi);
		this.setOperdata(dict);
		List<Map<String,String>> operations = new ArrayList<Map<String,String>>();
		operations.add(OPERATION_CONST.get(Const.OPER_IN));
		operations.add(OPERATION_CONST.get(Const.OPER_ISNULL));
		this.setOperations(operations);
	}

	public Map<String, String> getDdl(QueryParamItem qpi) {
		String key = qpi.getQueryoper();
		return operatorPool.retDtDictionary(key);
	}
}
