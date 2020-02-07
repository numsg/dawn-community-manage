package com.gsafety.dawn.community.backend.aspectj;

import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import com.gsafety.java.common.exception.HttpRestException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The type Limit ip request aspect.
 */
@Aspect
@Component
public class LimitIPRequestAspect {

    /**
     * Map
     */
    private Map mapIp;

    private Logger log = LoggerFactory.getLogger(LimitIPRequestAspect.class);

    /**
     * init
     */
    @PostConstruct
    public void init() {
        mapIp = new ConcurrentHashMap();
    }

    /**
     * requestLimit
     *
     * @param joinPoint JoinPoint
     */
    @Before("execution(* com.gsafety.pms.base.support.controller.*.*(..)) && @annotation(com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation)")
    public void requestLimit(JoinPoint joinPoint) {

        // 获取HttpRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        LimitIPRequestAnnotation limit = this.getAnnotation(joinPoint);
        if (limit == null) {
            return;
        }

        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        String mapKey = "limit-ip-request:" + uri + ":" + ip;
        int count = 1;
        if (mapIp.containsKey(mapKey)) {
            count = (int) mapIp.get(mapKey) + 1;
            mapIp.put(mapKey, count);
        } else {
            mapIp.put(mapKey, count);
        }

        if (count == 1) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mapIp.remove(mapKey);
                    timer.cancel();
                }
            }, limit.timeSecond());
        }

        if (count > limit.limitCounts()) {
            log.info(mapKey,"The client QPS too high!");
            throw new HttpRestException(HttpStatus.FORBIDDEN, "The client QPS too high!");
        }

    }

    private LimitIPRequestAnnotation getAnnotation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(LimitIPRequestAnnotation.class);
        }
        return null;
    }

}
