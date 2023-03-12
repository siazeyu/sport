package com.sport.utils;

public class StringUtils {

    // 判断字符串是否为空
    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    /**
     * 移除字符串中所有的子串，支持正则表达式
     * @param str 原字符串
     * @param regex 正则
      */
    public static String removeString(String str, String regex){
        return str.replaceAll(regex, "");
    }
}
