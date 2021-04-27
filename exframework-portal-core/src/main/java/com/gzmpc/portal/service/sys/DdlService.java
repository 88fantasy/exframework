package com.gzmpc.portal.service.sys;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gzmpc.portal.dao.DictionaryDao;
import com.gzmpc.portal.metadata.dict.DictionaryItem;
import com.gzmpc.portal.metadata.sys.Account;
import com.gzmpc.support.common.annotation.BuildComponent;
import com.gzmpc.support.common.build.Buildable;
import com.gzmpc.support.common.exception.BuildException;

@Service
@BuildComponent
public class DdlService implements Buildable {

	private Logger log = LoggerFactory.getLogger(DdlService.class.getName());
	
	private Map<String,DictionaryItem> localItems = new ConcurrentHashMap<>();

	@Autowired
	DictionaryDao dictionaryDao;

	@Autowired
	PermissionService permissionService;

	@Autowired
	AccountService accountService;
	
	@Autowired
	ApplicationContext applicationContext;

	public Collection<String> getAllKeys() {
		return dictionaryDao.allKeys();
	}

	public Map<String, String> get(Account account, String ddlkey) {
		Map<String, String> ddl = get(ddlkey);
		Map<String, String> result = new ConcurrentHashMap<String, String>(ddl);
		for (String key : ddl.keySet()) {
			// 由于字典不限权限比较多,故采用排除算法
			if (permissionService.hasRight(account, "ddl-" + ddlkey + "-" + key)) {
				result.remove(key);
			}
		}
		return result;
	}

	public Map<String, String> get(String ddlkey) {
		//优先查找代码字典
		if(localItems.containsKey(ddlkey)) {
			return localItems.get(ddlkey).getValue();
		}
		else {
			return dictionaryDao.findMapByKey(ddlkey);
		}
	}
	
	public Map<String, Map<String, String>> many(String[] ddlkeys) {
		Map<String, Map<String, String>> dicts = new ConcurrentHashMap<>();
		if(ddlkeys != null && ddlkeys.length > 0) {
			for(String key : ddlkeys) {
				dicts.put(key, get(key));
			}
		}
		return dicts;
	}

	public String getValue(String ddlKey, String itemKey) {
		return get(ddlKey).get(itemKey);
	}

	public boolean saveDictionary(String code, String name, Map<String, String> value) {
		return dictionaryDao.saveDictionary(code, name, value, false);
	}
	
	public boolean saveDictionary(String code, String name, Map<String, String> value, boolean local) {
		if(local) {
			DictionaryItem item = new DictionaryItem();
			item.setCode(code);
			item.setName(name);
			item.setValue(value);
			item.setLocal(local);
			localItems.put(code, item);
		}
		return dictionaryDao.saveDictionary(code, name, value, local);
	}

	@Override
	public void build(ApplicationContext ac) throws BuildException {
		
	}
}
