package com.carrey.carrey.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Conway
 * @className AopTestAspect
 * @description
 * @date 2020/11/17 4:56 下午
 */
@Component
@Aspect
public class AopTestAspect {

    @Pointcut("@annotation(com.carrey.carrey.aop.AopTestAnno)")
    private void pointcut() {
        throw new UnsupportedOperationException();
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入环绕通知");
        System.out.println("方法执行前环绕");
        Object proceed = pjp.proceed();
        System.out.println("方法执行后环绕");
        return proceed;
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+ methodName +"】之前进入<前置通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+ methodName +"】之前进入<后置通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "pointcut()",returning="obj")
    public void returning(JoinPoint joinPoint,Object obj) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+ methodName +"】之前进入<返回通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }

    @AfterThrowing(value = "pointcut()",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【"+ methodName +"】之前进入<异常通知>,入参" + Arrays.asList(joinPoint.getArgs()));
    }


}
