package com.gsafety.dawn.community.common.util;


import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * The type String util.
 */
public class StringUtil {

    private StringUtil() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 判断字符串是否为空,为空则返回true,不为空则返回false
     *
     * @param str 任意字符串
     * @return 是否为空 boolean
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isBlank(str);
    }

    /**
     * 判断字符串是否为空,不为空则返回true,为空则返回false
     *
     * @param str 任意字符串
     * @return 是否不为空 boolean
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotBlank(str);
    }

    /**
     * 生成UUID(不包含中划线"-")
     *
     * @return 生成的UUID字符串
     */
    public static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
