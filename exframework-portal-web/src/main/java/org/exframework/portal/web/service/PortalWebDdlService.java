package org.exframework.portal.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.exframework.portal.service.sys.PortalCoreDdlService;
import org.exframework.portal.web.dto.OptionsResponse;

/**
 *
 * @author rwe
 * @since Jan 12, 2021
 *
 * Copyright @ 2021 
 * 
 */
@Service
public class PortalWebDdlService {
	
	private Logger log = LoggerFactory.getLogger(PortalWebDdlService.class.getName());

	@Autowired
    PortalCoreDdlService portalCoreDdlService;
	
	public OptionsResponse[] options(String ddlKey) {
		List<OptionsResponse> result = new ArrayList<>();
		Map<String, String> dicts = portalCoreDdlService.get(ddlKey);
		for(Entry<String,String> entry : dicts.entrySet()) {
			OptionsResponse or = new OptionsResponse(entry.getKey(),entry.getValue());
			result.add(or);
		}
		return result.toArray(new OptionsResponse[result.size()]);
	}
	
	public Map<String,OptionsResponse[]> manyOptions(String[] ddlKeys) {
		Map<String,OptionsResponse[]> result = new ConcurrentHashMap<>();
		for(String key: ddlKeys) {
			result.put(key, options(key));
		}
		return result;
	}
}
