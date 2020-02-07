package com.gsafety.dawn.community.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

/**
 * Bean操作工具类
 */
public class BeanUtil {
    private BeanUtil(){
        //无参构造
    }

    /**
     * 获取对象中为null的所有属性名
     *
     * @param source 任意对象
     * @return 对象中所有为null的属性名数组 string [ ]
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper bw = new BeanWrapperImpl(source);
        return Stream.of(bw.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> bw.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    /**
     * 将实体类属性复制到DTO对象对应属性上
     *
     * @param source 源对象
     * @param target 目标对象
     * @return 复制后的目标对象 object
     */
    public static Object entityToModel(Object source, Object target) {
        CheckUtils.checkExist(source);
        Assert.notNull(target, "The given target object must not be null!");
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 复制对象属性到另一个对象对应属性上
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 复制源对象的非空属性到另一个对象对应属性上
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
}
