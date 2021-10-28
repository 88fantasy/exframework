package org.exframework.spring.boot.autoconfigure.chinaunicom.rest.entity;

import org.springframework.lang.Nullable;

/**
 * 产互页信息
 *
 * @author rwe
 * @date 2021/7/15 17:22
 **/
public class ChinaUnicomPage {

    /**
     * 当前页
     */
    @Nullable
    private Long current;

    /**
     * 每页条数
     */
    @Nullable
    private Long size;

    @Nullable
    public Long getCurrent() {
        return current;
    }

    public ChinaUnicomPage() {
        this.current = DEFAULT.current;
        this.size = DEFAULT.size;
    }

    public ChinaUnicomPage(@Nullable Integer current, @Nullable Integer size) {
        this.current = current.longValue();
        this.size = size.longValue();
    }

    public ChinaUnicomPage(@Nullable Long current, @Nullable Long size) {
        this.current = current;
        this.size = size;
    }

    public ChinaUnicomPage setCurrent(@Nullable Long current) {
        this.current = current;
        return this;
    }

    @Nullable
    public Long getSize() {
        return size;
    }

    public ChinaUnicomPage setSize(@Nullable Long size) {
        this.size = size;
        return this;
    }

    public static ChinaUnicomPage DEFAULT = new ChinaUnicomPage(1L,20L);
}
