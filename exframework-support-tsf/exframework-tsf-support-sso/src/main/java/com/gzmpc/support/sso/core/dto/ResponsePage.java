package com.gzmpc.support.sso.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 *
 * @author zlt
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePage<T> implements Serializable {

    private static final long serialVersionUID = -275582248840137389L;

    /**
     * 当前页结果集
     */
    private List<T> data;

    private Pager pager;


}
