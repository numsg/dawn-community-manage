package com.gsafety.dawn.community.backend.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * The type Controller log aspect j.
 */
@Aspect
@Component
public class ControllerLogAspectJ {
    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(ControllerLogAspectJ.class);

    /**
     * Log before.
     *
     * @param joinPoint the join point
     */
//  前置通知：
    @Before("execution(public * com.gsafety.pms.base.support.controller..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        StringBuilder args = new StringBuilder();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            args.append(joinPoint.getArgs()[i] + ",");
        }

        String log="The method " + joinPoint.getSignature().getName() + " begin, Args:" + args.toString();
        logger.info(log);
    }

    /**
     * Log after.
     *
     * @param joinPoint the join point
     */
    /*最终通知（after advice）在连接点结束之后执行，不管返回结果还是抛出异常。*/
    @After("execution(public * com.gsafety.pms.base.support.controller..*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("The method " + joinPoint.getSignature().getName() + " end");
    }

    /**
     * Log after throwing.
     *
     * @param joinPoint the join point
     * @param throwable the throwable
     */
    /*异常通知：仅当连接点抛出异常时执行。*/
    @AfterThrowing(pointcut = "execution(public * com.gsafety.pms.base.support.controller..*.*(..))", throwing = "throwable")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        String error="exception " + throwable + " in method" + joinPoint.getSignature().getName();
        logger.error(error);
    }

}
