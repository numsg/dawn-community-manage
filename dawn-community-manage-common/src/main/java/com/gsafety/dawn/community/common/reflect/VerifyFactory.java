package com.gsafety.dawn.community.common.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Set;

/**
 * Created by nusmg 2017-12-18
 */
public class VerifyFactory {
    private static Logger logger = LoggerFactory.getLogger(ClassFactory.class);    //日志记录


    private VerifyFactory() {
    }

    /**
     * 验证整个包下所有的类中所有字段是否都有正确的get，set方法.
     *
     * @param packageName the package name
     * @throws ClassNotFoundException the class not found exception
     * @throws NoSuchMethodException  the no such method exception
     */
    public static void verifyGetSetInPackage(String packageName) throws NoSuchMethodException {

        Set<Class<?>> classes = ClassFactory.getClasses(packageName);
        for (Class cls : classes) {
            verifyGetSetInClass(cls);
        }
    }

    /**
     * 验证类中所有字段是否都有正确的get，set方法.
     *
     * @param className the class name
     * @throws ClassNotFoundException the class not found exception
     * @throws NoSuchMethodException  the no such method exception
     */
    public static void verifyGetSetInClassName(String className) throws NoSuchMethodException {
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            logger.error(" class not found :" + className, e);
            return;
        }
        verifyGetSetInClass(cls);
    }

    /**
     * 验证类中所有字段是否都有正确的get，set方法
     *
     * @param cls 验证的目标类
     * @throws NoSuchMethodException the no such method exception
     */
    public static void verifyGetSetInClass(Class cls) throws NoSuchMethodException {
        Field[] fileds = cls.getDeclaredFields();
        for (Field field : fileds) {
            Method getMethod = MethodFactory.getGetMethod(cls, field.getName());
            Method setMethod = MethodFactory.getSetMethod(cls, field.getName());
            if (getMethod == null) {
                throw new NoSuchMethodException(MessageFormat.format("Class - {0}, Field - {1}  getMethod not found:", cls.getName(), field.getName()));
            }
            if (setMethod == null) {
                throw new NoSuchMethodException(MessageFormat.format("Class - {0}, Field - {1}  setMethod not found:", cls.getName(), field.getName()));
            }
        }
    }
}
