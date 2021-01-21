package com.tsvlad.barber_boot.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class LoggerAOP {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* *(..)) && within(com.tsvlad.barber_boot..*)")
    public void exceptionPointcut() {

    }

    @AfterThrowing(pointcut = "exceptionPointcut()", throwing = "ex")
    public void logException(Exception ex) {
        logger.error(LocalDateTime.now() + " Exception caught: " + ex);
    }
}
