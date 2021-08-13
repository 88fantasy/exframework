package org.exframework.portal.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.exframework.portal.metadata.hov.Hov;
import org.exframework.portal.metadata.hov.HovQueryParams;
import org.exframework.portal.metadata.hov.IHovDao;
import org.exframework.portal.service.sys.PortalCoreHovService;
import org.exframework.portal.web.dto.PostConditionQueryRequest;
import org.exframework.support.common.entity.FilterCondition;
import org.exframework.support.common.entity.Page;
import org.exframework.support.common.entity.PageModel;
import org.exframework.support.common.util.SpringContextUtils;
import org.exframework.support.rest.entity.ApiResponsePage;
import org.exframework.support.rest.enums.ResultCode;
import org.exframework.support.rest.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rwe
 * @since Jan 12, 2021
 * <p>
 * Copyright @ 2021
 */
@Service
public class PortalWebHovService {

    private Logger log = LoggerFactory.getLogger(PortalWebHovService.class.getName());

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PortalCoreHovService portalCoreHovService;

    public ApiResponsePage<?> query(String code, PostConditionQueryRequest request) {
        Hov hov = portalCoreHovService.findByKey(code);
        if (hov != null) {
            HovQueryParams[] params = hov.getQueryParams();
            String dataClassName = hov.getDataClass();
            Class<? extends IHovDao<?>> dataClass = null;
            try {
                dataClass = (Class<? extends IHovDao<?>>) Class.forName(hov.getDataClass());
            } catch (Exception e) {
                String message = MessageFormat.format("返回实体类{0}转换失败", dataClassName);
                log.error(message, e);
                return new ApiResponsePage<>(ResultCode.INTERNAL_SERVER_ERROR, message, null);
            }

            List<FilterCondition> fcs = new ArrayList<FilterCondition>();
            Page page = request.getPage() != null ? request.getPage() : Page.DEFAULT;
            FilterCondition[] conditions = request.getConditions();
            for (FilterCondition fc : conditions) {
                for (HovQueryParams param : params) {
                    String key = param.getKey();
                    if (key.contentEquals(fc.getKey())) {
                        fcs.add(fc);
                    }
                }
            }

            try {
                IHovDao<?> v = SpringContextUtils.getBeanByClass(dataClass);
                PageModel<?> model = v.query(fcs, page);
                return new ApiResponsePage<>(model);
            } catch (BeansException e) {
                return new ApiResponsePage<>(ResultCode.INTERNAL_SERVER_ERROR, dataClassName + "缺少实现: " + e.getMessage(), null);
            }

        } else {
            return ApiResponsePage.notFound("找不到此hov");
        }
    }

    public ApiResponsePage<?> query(String code, String requestJson) {
        Hov hov = portalCoreHovService.findByKey(code);
        if (hov != null) {
            HovQueryParams[] params = hov.getQueryParams();
            String dataClassName = hov.getDataClass();
            Class<? extends IHovDao<?>> dataClass = null;
            try {
                dataClass = (Class<? extends IHovDao<?>>) Class.forName(hov.getDataClass());
            } catch (Exception e) {
                String message = MessageFormat.format("返回实体类{0}转换失败", dataClassName);
                log.error(message, e);
                return new ApiResponsePage<>(ResultCode.INTERNAL_SERVER_ERROR, message, null);
            }

            Page page = Page.DEFAULT;
            List<FilterCondition> fcs = new ArrayList<FilterCondition>();
            try {
                JsonNode request = objectMapper.readTree(requestJson);
                if (request.isObject() && request.has("page")) {
                    page = objectMapper.treeToValue(request.get("page"), Page.class);
                    ((ObjectNode) request).remove("page");
                }
                for (HovQueryParams param : params) {
                    String dataIndex = param.getDataIndex();
                    if (request.has(dataIndex)) {
                        Object value = request.get(dataIndex);
                        if (value != null) {
                            FilterCondition fc = new FilterCondition(dataIndex, value);
                            fcs.add(fc);
                        }
                    }
                }
            } catch (JsonProcessingException e) {
                throw new ApiException(ResultCode.BAD_REQUEST.getCode(), "解析 json 错误", requestJson);
            }


            try {
                IHovDao<?> v = SpringContextUtils.getBeanByClass(dataClass);
                PageModel<?> model = v.query(fcs, page);
                return new ApiResponsePage<>(model);
            } catch (BeansException e) {
                return new ApiResponsePage<>(ResultCode.INTERNAL_SERVER_ERROR, dataClassName + "缺少实现: " + e.getMessage(), null);
            }

        } else {
            return ApiResponsePage.notFound("找不到此hov");
        }
    }
}
