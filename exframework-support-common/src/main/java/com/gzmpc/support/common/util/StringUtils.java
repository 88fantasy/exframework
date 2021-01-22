package com.gzmpc.support.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Author: rwe
 * Date: Jan 22, 2021
 *
 * Copyright @ 2021 
 * 
 */
public class StringUtils extends org.springframework.util.StringUtils {

	public static String humpToUnderline(String camelCase){
		Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(camelCase);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
