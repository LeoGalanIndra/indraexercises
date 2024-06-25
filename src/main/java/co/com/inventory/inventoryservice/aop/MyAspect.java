package co.com.inventory.inventoryservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class MyAspect {


    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

    @Before("execution(* co.com.inventory.inventoryservice.aop.TargetObject.*(..))")
    public void before(JoinPoint joinPoint){
        log.info("{}: {}" , "joinPoint.getSignature().getName()", joinPoint.getSignature().getName());
        log.info("{}: {}" , "joinPoint.getSignature().getDeclaringType()", joinPoint.getSignature().getDeclaringType());
        log.info("{}: {}" , "joinPoint.getSignature().getModifiers()", joinPoint.getSignature().getModifiers());
        log.info("{}: {}" , "joinPoint.getArgs()", joinPoint.getArgs());
        log.info("{}: {}" , "joinPoint.getTarget()", joinPoint.getTarget());

    }



}
