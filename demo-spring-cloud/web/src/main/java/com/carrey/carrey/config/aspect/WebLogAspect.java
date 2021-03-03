package com.carrey.carrey.config.aspect;

import com.google.gson.Gson;
import com.vip.vjtools.vjkit.collection.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class WebLogAspect {
  private static final String LINE_SEPARATOR = System.lineSeparator();

  /**
   * 切点.
   */
  @Pointcut("@annotation(com.carrey.carrey.config.aspect.WebLog)")
  public void webLog() {
    //切点。
  }

  /**
   * 环绕通知.
   */
  @Around("webLog()")
  public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object result = proceedingJoinPoint.proceed();
    //出参
    log.info("Response Args  :[{}]", new Gson().toJson(result));
    //执行耗时
    log.info("Time-Consuming :[{}]", System.currentTimeMillis() - startTime);
    return result;
  }

  /**
   * 前置通知.
   */
  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
    //开始打印请求日志
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (Objects.isNull(attributes)) {
      return;
    }
    HttpServletRequest request = attributes.getRequest();
    String methodDesc = getAspectLogDesc(joinPoint);
    log.info("======================================start=====================================");
    log.info("URL            :[{}]", request.getRequestURL().toString());
    log.info("Description    :[{}]", methodDesc);
    log.info("HTTP Method    :[{}]", request.getMethod());
    log.info("Class Method   :[{}.{}]", joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName());
    log.info("IP             :[{}]", request.getRemoteAddr());
    log.info("Request Args   :[{}]", new Gson().toJson(joinPoint.getArgs()));
  }

  private String getAspectLogDesc(JoinPoint joinPoint) throws NoSuchMethodException {
    return Optional.ofNullable(getWebLogAnnotation(joinPoint))
        .map(webLog -> webLog.description())
        .orElse(StringUtils.EMPTY);
  }

  private WebLog getWebLogAnnotation(JoinPoint joinPoint) throws NoSuchMethodException {
    return getMethod(joinPoint).getAnnotation(WebLog.class);
  }

  private Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
    Object target = joinPoint.getTarget();
    final String methodName = joinPoint.getSignature().getName();
    return target.getClass().getMethod(methodName, getMethodParamClassArr(joinPoint));
  }

  private Class[] getMethodParamClassArr(JoinPoint joinPoint) {
    return ArrayUtil.toArray(getMethodParamClassList(joinPoint), Class.class);
  }

  private List<Class> getMethodParamClassList(JoinPoint joinPoint) {
    return Arrays.stream(joinPoint.getArgs())
        .map(arg -> arg.getClass())
        .collect(Collectors.toList());
  }

  /**
   * 后置通知.
   */
  @After("webLog()")
  public void doAfter() {
    log.info("======================================end======================================"
        + LINE_SEPARATOR);
  }
}
