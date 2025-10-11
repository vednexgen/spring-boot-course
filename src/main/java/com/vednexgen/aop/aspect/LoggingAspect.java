package com.vednexgen.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.vednexgen.aop.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        IO.println("Before Method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.vednexgen.aop.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        IO.println("After Method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.vednexgen.aop.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        IO.println("After Returning from Method: " + joinPoint.getSignature().getName());
        IO.println("Returned Value: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.vednexgen.aop.service.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        IO.println("Exception in Method: " + joinPoint.getSignature().getName());
        IO.println("Exception Message: " + ex.getMessage());
    }

    @Around("execution(* com.vednexgen.aop.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        IO.println("------------------------------");
        IO.println("Around Before: " + joinPoint.getSignature().getName());

        Object result = null;
        try {
            result = joinPoint.proceed();
        } finally {
            long elapsedTime = System.currentTimeMillis() - start;
            IO.println("Around After: " + joinPoint.getSignature().getName() + " took " + elapsedTime + "ms");

        }
        return result;
    }
}