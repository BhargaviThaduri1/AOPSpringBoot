package com.codingshuttle.aopApp.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // ---- execution is for methods------

    //@Before("execution(* com.codingshuttle.aopApp.services.impl.ShipmentServiceImpl.orderPackage(..))")
    //@Before("execution(* orderPackage(..))")
    //@Before("execution(* com.codingshuttle.aopApp.services.impl.*.orderPackage(..))")
    //@Before("execution(* com.codingshuttle.aopApp.services.impl.*.*(..))")
    @Before("ImplMethodsExecutionPointCutMethods()")
    public void beforeOrderPackage(JoinPoint joinPoint){
        log.info("Before order package called: from logging aspect, {}",joinPoint.getKind());
        log.info("Before order package called: from logging aspect, {}",joinPoint.getSignature());

    }
    @After("ImplMethodsExecutionPointCutMethods()")
    public void afterOrderPackage(JoinPoint joinPoint){
        log.info("After order package called: from logging aspect, {}",joinPoint.getKind());
        log.info("After order package called: from logging aspect, {}",joinPoint.getSignature());

    }

    @Before("within(com.codingshuttle.aopApp.services.impl.*)")
    public void beforeServiceImplementationCalls(JoinPoint joinPoint){
        log.info("Service Impl Calls: from logging aspect, {}",joinPoint.getKind());
        log.info("Service Impl Calls: from logging aspect, {}",joinPoint.getSignature());

    }

    @Before("within(com.codingshuttle.aopApp..*)")
    public void beforePackageCalls(JoinPoint joinPoint){
        log.info("From Package Calls, {}",joinPoint.getKind());
        log.info("From Package Calls, {}",joinPoint.getSignature());

    }

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransactionalAnnotationCalls(){
        log.info("Before annotation transactional calls");
    }

    @Before("@annotation(com.codingshuttle.aopApp.aspects.MyLoggingAnnotation)")
    public void beforeMyAnnotationCalls(){
        log.info("Before myannotation calls");
    }

    @Pointcut("execution(* com.codingshuttle.aopApp.services.impl.*.*(..))")
    public void ImplMethodsExecutionPointCutMethods(){}
}
