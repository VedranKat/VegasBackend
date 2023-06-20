package com.example.vegasBackend.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOG = LogManager.getLogger(LoggingAspect.class);

    @Before("within(com.example.vegasBackend.service..*)")
    public void beforeService(JoinPoint joinPoint) {
        LOG.info("Entering Service - {}", joinPoint.getSignature().toShortString());
    }

    @Before("within(com.example.vegasBackend.controller..*)")
    public void beforeController(JoinPoint joinPoint) {
        LOG.info("** Entering Controller - {} **", joinPoint.getSignature().toShortString());
    }
}
