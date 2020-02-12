package com.gsafety.dawn.community.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class CommonUtil {

    Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 实体属性浅拷贝
     *
     * @param <S>    源对象类型
     * @param <T>    目标对象类型
     * @param source 源对象
     * @param target 目标对象
     * @return the t
     */
    public <S, T> T mapper(S source, T target) {

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
}
