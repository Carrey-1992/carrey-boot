package com.example.carrey.domain.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Conway
 * @className MyTestBeanB
 * @description
 * @date 2020/11/16 5:11 下午
 */
@Component
public class MyTestBeanB {

    private MyTestBeanA myTestBeanA;

    public MyTestBeanA getMyTestBeanA() {
        return myTestBeanA;
    }

    @Autowired
    public void setMyTestBeanA(MyTestBeanA myTestBeanA) {
        this.myTestBeanA = myTestBeanA;
    }
}
