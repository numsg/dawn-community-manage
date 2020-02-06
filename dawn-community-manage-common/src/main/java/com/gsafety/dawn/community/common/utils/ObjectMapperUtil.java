package com.gsafety.dawn.community.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nusmg 2017-12-18
 * Created by  on 2016/6/28.
 */
public class ObjectMapperUtil {
    private static final Logger logger = LoggerFactory.getLogger(ObjectMapperUtil.class);

    private ObjectMapperUtil() {

    }

    /**
     * 实体属性浅拷贝
     *
     * @param <S>    源对象类型
     * @param <T>    目标对象类型
     * @param source 源对象
     * @param target 目标对象
     * @return the t
     */
    public static <S, T> T mapper(S source, T target) {

        Field[] sourceFields = source.getClass().getDeclaredFields();
        for (Field field : sourceFields) {
            Field targetField;
            try {
                //当实体中没有DTO里面的属性时，跳过该属性的赋值
                targetField = target.getClass().getDeclaredField(field.getName());

                if (targetField != null && targetField.getType() == field.getType()) {
                    field.setAccessible(true);
                    targetField.setAccessible(true);
                    targetField.set(target, field.get(source));
                }
            } catch (Exception e) {
                logger.error("mapper error", e);
                continue;
            }
        }
        return target;

    }


    /**
     * * 通用实体转换方法,将JPA返回的数组转化成对应的实体集合,这里通过泛型和反射实现
     * * @param <T>
     * * @param list
     * * @param clazz 需要转化后的类型
     * * @return
     * * @throws Exception
     */
    public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) {
        List<T> returnList = new ArrayList<T>();
        if(list != null && !list.isEmpty()) {
            Object[] co = list.get(0);
            Class[] c2 = new Class[co.length];
            try {
                //确定构造方法
                for (int i = 0; i < co.length; i++) {
                    c2[i] = co[i].getClass();
                }
                for (Object[] o : list) {
                    Constructor<T> constructor = clazz.getConstructor(c2);
                    returnList.add(constructor.newInstance(o));
                }
            } catch (Exception e) {
                logger.error("mapper error", e);
            }
        }
        return returnList;
    }
}
