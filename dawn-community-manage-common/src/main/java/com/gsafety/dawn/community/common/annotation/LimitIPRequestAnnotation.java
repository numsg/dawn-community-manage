package com.gsafety.dawn.community.common.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gaoqiang on 2017/12/4.
 */

/**
 * Created by gaoqiang on 2017/1/17.
 * API接口访问次数控制
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface LimitIPRequestAnnotation {

    /**
     * 限制某时间段内可以访问的次数，默认设置100
     *
     * @return 计数
     */
    int limitCounts() default 10;

    /**
     * 限制访问的某一个时间段，单位为毫秒
     *
     * @return 计数
     */
    int timeSecond() default 1000;

}