package com.gzmpc.support.sso.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-06-02 15:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pager implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总数
     */
    private Integer total;

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 每页条数
     */
    private Integer pageSize;
    

}
