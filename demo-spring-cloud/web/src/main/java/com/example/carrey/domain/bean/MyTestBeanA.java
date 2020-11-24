package com.example.carrey.domain.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Conway
 * @className MyTestBean2
 * @description
 * @date 2020/11/16 5:11 下午
 */
@Component
public class MyTestBeanA {

    private MyTestBeanB myTestBeanB;

    public MyTestBeanB getMyTestBeanB() {
        return myTestBeanB;
    }

    @Autowired
    public void setMyTestBeanB(MyTestBeanB myTestBeanB) {
        this.myTestBeanB = myTestBeanB;
    }
}
