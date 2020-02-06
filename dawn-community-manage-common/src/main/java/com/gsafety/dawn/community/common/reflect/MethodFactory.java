package com.gsafety.dawn.community.common.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by nusmg 2017-12-18
 */
public class MethodFactory {
    private static Logger logger = LoggerFactory.getLogger(ClassFactory.class);    //日志记录

    private MethodFactory(){}
    /**
     * 获取指定类指定字段名的get方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuilder sb = new StringBuilder();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
            logger.error("objectClass getMethod error:",e);
        }
        return null;
    }

    /**
     * 获取指定类指定字段名的set方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuilder sb = new StringBuilder();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            return objectClass.getMethod(sb.toString(), parameterTypes);
        } catch (Exception e) {
            logger.error("get Set Method error:",e);
        }
        return null;
    }

    /**
     * 调用set
     *
     * @param o
     * @param fieldName
     * @param value     ֵ
     */
    public static void invokeSet(Object o, String fieldName, Object value) {

        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, new Object[]{value});
        } catch (Exception e) {
            logger.error("invoke Set Method error:",e);
        }
    }

    /**
     * ִ调用get方法
     *
     * @param o         ִ
     * @param fieldName
     */
    public static Object invokeGet(Object o, String fieldName) {
        Method method = getGetMethod(o.getClass(), fieldName);
        try {
            return method.invoke(o, new Object[0]);
        } catch (Exception e) {
            logger.error("invoke Get Method error:",e);
        }
        return null;
    }
}

