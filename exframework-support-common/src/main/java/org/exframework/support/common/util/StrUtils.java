package org.exframework.support.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rwe
 * @since Jan 22, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class StrUtils extends org.springframework.util.StringUtils {

    static Pattern humpPattern = Pattern.compile("[A-Z]");

	public static String humpToUnderline(String camelCase){

        Matcher matcher = humpPattern.matcher(camelCase);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String toCamelCase(String underLineCase){
        Matcher m = Pattern.compile("[_|-](\\w)").matcher(underLineCase);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        return m.appendTail(sb).toString();
    }
}
