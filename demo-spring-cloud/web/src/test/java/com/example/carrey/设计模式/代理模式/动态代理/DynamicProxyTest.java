package com.example.carrey.设计模式.代理模式.动态代理;

import org.junit.Test;

public class DynamicProxyTest {
    @Test
    public void test() {
        Spendthrift xiaoming = new XiaoMing();
        SpendthriftProxy<Spendthrift> proxy = new SpendthriftProxy<>();
        Spendthrift xiaomingProxy = proxy.createProxy(xiaoming);
        xiaomingProxy.eat();
        xiaomingProxy.drink();
        xiaomingProxy.play();
    }

}
