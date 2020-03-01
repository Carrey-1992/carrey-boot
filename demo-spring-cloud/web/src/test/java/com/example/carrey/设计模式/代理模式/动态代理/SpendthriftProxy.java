package com.example.carrey.设计模式.代理模式.动态代理;

import java.lang.reflect.Proxy;

public class SpendthriftProxy<T> {

    public T createProxy(T proxiedObj) {
        Class<?>[] interfaces = proxiedObj.getClass().getInterfaces();
        SpendthriftProxyHandler<T> proxyHandler = new SpendthriftProxyHandler<>(proxiedObj);
        ClassLoader classLoader = proxiedObj.getClass().getClassLoader();
        return (T)Proxy.newProxyInstance(classLoader,interfaces,proxyHandler);
    }

}
