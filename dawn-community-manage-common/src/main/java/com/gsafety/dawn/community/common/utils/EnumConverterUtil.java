package com.gsafety.dawn.community.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 枚举类型与值的转换
 * Created by nusmg 2017-12-18
 */
public class EnumConverterUtil {
    private static final Logger logger = LoggerFactory.getLogger(EnumConverterUtil.class);

    private EnumConverterUtil() {
    }

    /**
     * 将枚举值转换为枚举类型（默认枚举值方法为getParamType,如果不是会抛异常）.
     *
     * @param <E>       枚举类型泛型参数
     * @param enumClass 枚举类型
     * @param value     枚举Int值
     * @return 枚举类型实例
     */
    public static <E> E toEntityAttribute(Class<E> enumClass, Integer value) {
        return toEntityAttribute(enumClass, "getParamType", value);
    }

    /**
     * 将枚举值转换为枚举类型
     *
     * @param <E>                枚举类型泛型参数
     * @param enumClass          枚举类型
     * @param getValueMethodName 获取枚举值的get方法名称
     * @param value              枚举Int值
     * @return 枚举类型实例
     */
    public static <E> E toEntityAttribute(Class<E> enumClass, String getValueMethodName, Integer value) {
        if (value == null)
            return null;
        E[] objs = enumClass.getEnumConstants();
        Method paramInt = null;
        try {
            paramInt = enumClass.getMethod(getValueMethodName);
            for (E obj : objs) {
                Integer typeInt = (Integer) paramInt.invoke(obj);
                if (typeInt.equals(value)) {
                    return obj;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
