package org.exframework.backend.quickstart.dto;

import org.exframework.support.jdbc.annotation.Query;

/**
 * @author rwe
 * @date 2022/7/5 14:01
 **/
public class TestRequest {

    @Query(condition = Query.Condition.LIKE, field = "code")
    @Query(condition = Query.Condition.LIKE, field = "name")
    private String search;

    public String getSearch() {
        return search;
    }

    public TestRequest setSearch(String search) {
        this.search = search;
        return this;
    }
}
