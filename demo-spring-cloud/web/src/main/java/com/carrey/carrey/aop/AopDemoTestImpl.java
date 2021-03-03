package com.carrey.carrey.aop;

import org.springframework.stereotype.Component;

/**
 * @author Conway
 * @className AopDemoTest
 * @description
 * @date 2020/11/17 5:36 下午
 */
@Component
public class AopDemoTestImpl implements IAopDemoTest{

    @AopTestAnno
    @Override
    public String testMethod(String testParam) {
        return testParam;
    }
}
