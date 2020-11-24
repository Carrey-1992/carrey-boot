package com.example.carrey.aop;

import java.lang.annotation.*;

/**
 * @author Conway
 * @className AopTestAnno
 * @description
 * @date 2020/11/17 5:02 下午
 */
@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopTestAnno {
}
