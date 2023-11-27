package org.example.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAop {

    Logger logger = LogManager.getLogger(this.getClass());

    public Object logCreate(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        System.out.println("done");

        return joinPoint.proceed();
    }
}
