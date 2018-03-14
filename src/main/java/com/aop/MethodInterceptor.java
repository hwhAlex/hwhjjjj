package com.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by honor on 2017/9/22.
 */
@Aspect
public class MethodInterceptor {
    private static final Logger log = Logger.getLogger(MethodInterceptor.class);

    @Pointcut("bean(*Controller)")
    public void pointcut1(){

    }

    @Before(value = "pointcut1()")
    public void before(JoinPoint point){
        log.info(point.getSignature()+"start.....");
    }


}
