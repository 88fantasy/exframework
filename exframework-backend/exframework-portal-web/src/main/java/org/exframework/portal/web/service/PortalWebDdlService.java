package org.exframework.portal.web.service;

import org.exframework.portal.metadata.dict.DictionaryItemValue;
import org.exframework.portal.service.sys.PortalCoreDdlService;
import org.exframework.portal.web.dto.OptionsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author rwe
 * @since Jan 12, 2021
 * <p>
 * Copyright @ 2021
 */
@Service
public class PortalWebDdlService {

    private Logger log = LoggerFactory.getLogger(PortalWebDdlService.class.getName());

    @Autowired
    PortalCoreDdlService portalCoreDdlService;

    public OptionsResponse[] options(String ddlKey) {
        List<OptionsResponse> result = new ArrayList<>();
        List<DictionaryItemValue> dicts = portalCoreDdlService.getSorted(ddlKey);
        return dicts.stream().map(d -> new OptionsResponse(d.getValue(), d.getKey())).collect(Collectors.toList()).toArray(new OptionsResponse[0]);
    }

    public Map<String, OptionsResponse[]> manyOptions(String[] ddlKeys) {
        Map<String, OptionsResponse[]> result = new ConcurrentHashMap<>();
        for (String key : ddlKeys) {
            result.put(key, options(key));
        }
        return result;
    }
}
