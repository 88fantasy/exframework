package org.exframework.support.common.lang;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.lang.PatternPool;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证器
 *
 * @author rwe
 * @date 2021/8/12 08:56
 **/
public class Validator extends cn.hutool.core.lang.Validator{

    /**
     * 日期
     */
    public final static Pattern DATE = PatternPool.get("^\\d{1,4}(-)(1[0-2]|0?[1-9])\\1(0?[1-9]|[1-2]\\d|30|31)$");

    /**
     * 固话
     */
    public final static Pattern TEL = PatternPool.get("^(?:(?:\\d{3}-)?\\d{8}|^(?:\\d{4}-)?\\d{7,8})(?:-\\d+)?$");

    public static boolean isDate(CharSequence date) {
        final Matcher matcher = DATE.matcher(date);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static Date validateDate(CharSequence date, String errorMsg) {
        if (false == isDate(date)) {
            throw new ValidateException(errorMsg);
        }
        return DateUtil.parse(date);
    }

    public static boolean isTel(CharSequence tel) {
        final Matcher matcher = TEL.matcher(tel);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static String validateTel(CharSequence tel, String errorMsg) {
        if (false == isTel(tel)) {
            throw new ValidateException(errorMsg);
        }
        return tel.toString();
    }
}
