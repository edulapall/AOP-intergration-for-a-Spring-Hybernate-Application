package com.luv2code.springboot.thymeleafdemo.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    private Logger mylogger= Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    public void forService(){}
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    public void forController(){}
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    public void fordao(){}
    @Pointcut("forService() || forController() || fordao()")
    public void appflow(){}
    @Before( "appflow()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
        mylogger.info("before calling  "+methodSignature);
        Object[]  args=joinPoint.getArgs();
        for(Object out:args){
            mylogger.info("sys out... " +out);
        }
      // mylogger.info("final result ... " +theResult);
    }
    @AfterReturning(
            pointcut = "appflow()",
            returning = "theResult")
    public void After(JoinPoint joinPoint){
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
        mylogger.info("before calling  " +methodSignature);
        mylogger.info("====>after "  + theResult );
    }
}
