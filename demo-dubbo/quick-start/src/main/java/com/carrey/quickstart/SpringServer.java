package com.carrey.quickstart;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Conway
 * @className SpringServer
 * @description
 * @date 2021/3/8 2:25 下午
 */
public class SpringServer {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("provide.xml");
        System.out.println("服务已暴露");
        System.in.read();
    }
}
