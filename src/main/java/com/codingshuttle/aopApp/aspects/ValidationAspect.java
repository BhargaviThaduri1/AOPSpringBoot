package com.codingshuttle.aopApp.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidationAspect {

//    @Around("allServiceMethodsPointCuts()")
//    public Object validateOrderId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        Object args[] = proceedingJoinPoint.getArgs();
//
//        Long orderId = (Long) args[0];
//
//        if(orderId>0) return proceedingJoinPoint.proceed();
//        return "Cannot call with negative orderId";
//    }

    @Around("allServiceMethodsPointCuts()")
    public void LogExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws  Throwable{
        Long startTime = System.currentTimeMillis();
        Object returnedValue = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        Long diff = endTime - startTime;
        log.info("Time took for execution {} is: {}",proceedingJoinPoint.getSignature(),diff);

    }


    @Pointcut("execution(* com.codingshuttle.aopApp.services.impl.*.*(..))")
    public void allServiceMethodsPointCuts(){}
}
