package org.exframework.support.rest.entity;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 数字主键请求
 *
 * @author rwe
 * @date 2021/10/11 23:53
 **/
public class IdsLongRequest {

    @NotEmpty
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public IdsLongRequest setIds(List<Long> ids) {
        this.ids = ids;
        return this;
    }
}
