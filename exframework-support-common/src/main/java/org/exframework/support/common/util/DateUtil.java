package org.exframework.support.common.util;

import java.util.Date;

/**
 * 日期工具
 *
 * @author rwe
 * @date 2022/6/8 18:51
 **/
public class DateUtil extends cn.hutool.core.date.DateUtil {

    public static long toUnixTimestamp(Date date) {
        return translateToUnixTimestamp(date.getTime());
    }

    public static Date fromUnixTimestamp(long timestamp) {
        return new Date(timestamp * 1000L);
    }

    public static long translateToUnixTimestamp(long timestamp) {
        return timestamp / 1000L;
    }
}
