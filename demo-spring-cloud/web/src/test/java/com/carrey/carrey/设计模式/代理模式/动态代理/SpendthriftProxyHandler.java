package com.carrey.carrey.设计模式.代理模式.动态代理;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

public class SpendthriftProxyHandler<T> implements InvocationHandler {

    private T proxiedObject;

    public SpendthriftProxyHandler(T proxiedObject) {
        this.proxiedObject = proxiedObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Objects.equals(method.getName(),"eat")) {
            System.out.println("找小明吃饭前得先准备好酒！有菜没酒不够朋友！");
        }
        if (Objects.equals(method.getName(),"drink")) {
            System.out.println("找小明喝酒前得不用准备好菜！有酒没菜不算慢待！");
        }

        Object result = ReflectionUtils.invokeMethod(method, proxiedObject, args);

        if (Objects.equals(method.getName(),"play")) {
            System.out.println("哪里有妹子去哪里玩！");
        }
        return result;
    }
}
